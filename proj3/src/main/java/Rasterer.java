import java.util.*;


/**
 * This class provides all code necessary to take a query box and produce
 * a query result. The getMapRaster method must return a Map containing all
 * seven of the required fields, otherwise the front end code will probably
 * not draw the output correctly.
 */
public class Rasterer {

    public static final double ROOT_ULLAT = 37.892195547244356, ROOT_ULLON = -122.2998046875,
            ROOT_LRLAT = 37.82280243352756, ROOT_LRLON = -122.2119140625;

    public static final int TILE_SIZE = 256;

    private static final int DEFAULT_LEVE_ZOOM = 8;

    private static final int dimension = 3;
    private double[] imgLonDPP;


    public Rasterer() {
        // YOUR CODE HERE
        imgLonDPP = initImgLonDPP();

    }

    /**
     * Takes a user query and finds the grid of images that best matches the query. These
     * images will be combined into one big image (rastered) by the front end. <br>
     * <p>
     * The grid of images must obey the following properties, where image in the
     * grid is referred to as a "tile".
     * <ul>
     *     <li>The tiles collected must cover the most longitudinal distance per pixel
     *     (LonDPP) possible, while still covering less than or equal to the amount of
     *     longitudinal distance per pixel in the query box for the user viewport size. </li>
     *     <li>Contains all tiles that intersect the query bounding box that fulfill the
     *     above condition.</li>
     *     <li>The tiles must be arranged in-order to reconstruct the full image.</li>
     * </ul>
     *
     * @param params Map of the HTTP GET request's query parameters - the query box and
     *               the user viewport width and height.
     * @return A map of results for the front end as specified: <br>
     * "render_grid"   : String[][], the files to display. <br>
     * "raster_ul_lon" : Number, the bounding upper left longitude of the rastered image. <br>
     * "raster_ul_lat" : Number, the bounding upper left latitude of the rastered image. <br>
     * "raster_lr_lon" : Number, the bounding lower right longitude of the rastered image. <br>
     * "raster_lr_lat" : Number, the bounding lower right latitude of the rastered image. <br>
     * "depth"         : Number, the depth of the nodes of the rastered image <br>
     * "query_success" : Boolean, whether the query was able to successfully complete; don't
     * forget to set this to true on success! <br>
     */
    public Map<String, Object> getMapRaster(Map<String, Double> params) {

        System.out.println(params);
        Map<String, Object> results = new HashMap<>();
        int depth = getDepth(params);
        checkCoordinate(params);
        String[][] renderGrid = getRenderGrid(params, depth);
        double[] rasterLR = getRasterLR(params, depth);
        double[] rasterUL = getRasterUL(params, depth);
        results.put("render_grid", renderGrid);
        results.put("query_success", true);
        results.put("raster_ul_lon", rasterUL[0]);
        results.put("raster_ul_lat", rasterUL[1]);
        results.put("raster_lr_lon", rasterLR[0]);
        results.put("raster_lr_lat", rasterLR[1]);
        results.put("depth", depth);
        System.out.println(results);
        return results;

    }

    /**
     * Get the depth about ullon and lrlon in params.
     *
     * @param params the params list must contain w, ullon and lrlon field.
     * @return the depth that need to shown in front end.
     */
    private int getDepth(Map<String, Double> params) {
        // 1. compute box LonDPP
        double boxLonDPP = boxLonDPP(params);
        // 2. compare the img LonDPP
        for (int i = 0; i < imgLonDPP.length; i++) {
            if (boxLonDPP >= imgLonDPP[i]) {
                return i;
            }
        }
        int maxDepth = imgLonDPP.length - 1;
        return maxDepth;
    }

    /**
     * Compute the
     *
     * @param params
     * @return
     */
    private double boxLonDPP(Map<String, Double> params) {
        double xUL = params.get("ullon");
        double xLR = params.get("lrlon");
        double width = params.get("w");
        return LonDPP(xLR, xUL, width);
    }

    /**
     * Compute longitudinal distance per pixel (LonDPP)
     *
     * @param a x1 coordinate
     * @param b x2 coordinate
     * @param w width or length in pixel
     * @return
     */
    private double LonDPP(double a, double b, double w) {
        return Math.abs(a - b) / w;
    }


    /**
     * Compute the LonDPP of depth from 0 to 7
     *
     * @param size
     * @return
     */
    private double[] initImgLonDPP(int size) {
        double[] imgLonDpp = new double[size];
        imgLonDpp[0] = LonDPP(ROOT_LRLON, ROOT_ULLON, TILE_SIZE);
        for (int i = 1; i < size; i++) {
            imgLonDpp[i] = imgLonDpp[0] / (1 << i);
        }
        return imgLonDpp;
    }


    /**
     * Default initImgLonDpp() uses default size
     *
     * @return
     */
    private double[] initImgLonDPP() {
        return initImgLonDPP(DEFAULT_LEVE_ZOOM);
    }


    /**
     * Compute the ulBounding(x1,y1) and lrBounding(x2,y2) about the file name .
     *
     * @param imgName like d1_x1_y1.
     * @return result[0], result[1] is ulBounding and result[2],result[3] is lrBounding.
     */
    public double[] boundingBox(String imgName) {
        int[] d = paraseImgName(imgName);
        double[] ulBounding = getULCoordinate(d);
        double[] lrBounding = getLRCoordinate(d);
        double[] result = concat(ulBounding, lrBounding);
        return result;
    }

    /**
     * Compute lower right (x2,y2) coordinate about the img dimension.
     *
     * @param dimension the img index: d2_x1_y1.png: dimension[0]=1(x),dimension[1]=1(y),dimension[2]=2(d)
     * @return
     */
    private double[] getLRCoordinate(int[] dimension) {
        double[] start = new double[]{ROOT_ULLON, ROOT_ULLAT};
        double segment = getSegment(dimension[2]);
        double xUnit = perUnitLength(ROOT_ULLON, ROOT_LRLON, segment);
        double yUnit = perUnitLength(ROOT_ULLAT, ROOT_LRLAT, segment);
        start[0] = start[0] + xUnit;
        start[1] = start[1] - yUnit;
        int[] offset = new int[]{dimension[0], dimension[1]};
        double[] res = getCoordinate(start, offset, segment);
        return res;
    }

    /**
     * Compute how many slice the length in one direction.
     *
     * @param depth the level of need to show
     * @return
     */
    private int getSegment(int depth) {
        return (1 << depth);
    }


    /**
     * Compute up left (x2,y2) coordinate about the img dimension.
     *
     * @param dimension the img index: d2_x1_y1.png: dimension[0]=1(x),dimension[1]=1(y),dimension[2]=2(d)
     * @return
     */
    private double[] getULCoordinate(int[] dimension) {
        double[] start = new double[]{ROOT_ULLON, ROOT_ULLAT};
        double segment = getSegment(dimension[2]);
        int[] offset = new int[]{dimension[0], dimension[1]};
        double[] res = getCoordinate(start, offset, segment);
        return res;
    }

    /**
     * Compute the coordinate
     *
     * @param start   the start coordinate (x1,x2)
     * @param offset  the index of the img (x5_y6 : 5,6) - start index (0,0)
     * @param segment the number of splicing.
     * @return res1 = x, res2 = y, x and y is coordinate.
     */

    private double[] getCoordinate(double[] start, int[] offset, double segment) {
        double xUnit = perUnitLength(ROOT_ULLON, ROOT_LRLON, segment);
        double yUnit = perUnitLength(ROOT_ULLAT, ROOT_LRLAT, segment);
        double x = start[0] + offset[0] * xUnit;
        double y = start[1] - offset[1] * yUnit;
        double[] res = {x, y};
        return res;
    }


    /**
     * Zip two array into one array.
     *
     * @param ulBounding
     * @param lrBounding
     * @return res[ulBounding[0], ulBounding[1], lrBounding[0], lrBounding[1].
     */
    private double[] concat(double[] ulBounding, double[] lrBounding) {
        double[] res = Arrays.copyOf(ulBounding, ulBounding.length + lrBounding.length);
        System.arraycopy(lrBounding, 0, res, ulBounding.length, lrBounding.length);
        return res;
    }

    /**
     * analysis the file name and  generate the array (d3_x6_y8 --> array {6,8,3})
     *
     * @param imgName
     * @return int [] array (x,y,d)
     */
    private int[] paraseImgName(String imgName) {
        int[] res = new int[dimension];
        String[] sub = imgName.split("([xyd(.png)_])+");
        res[0] = Integer.parseInt(sub[2]);
        res[1] = Integer.parseInt(sub[3]);
        res[2] = Integer.parseInt(sub[1]);
        return res;
    }

    /**
     * Compute unit length
     *
     * @param start   start coordinate
     * @param end     end coordinate
     * @param segment number of splice
     * @return unit length.
     */
    private double perUnitLength(double start, double end, double segment) {
        return Math.abs(end - start) / segment;
    }


    /**
     * Compute the number of total titles.
     *
     * @param params must include ullon,ullat,lrlon,lrlat
     * @param depth
     * @return
     */
    public int getTitleNum(Map<String, Double> params, int depth) {
        double xUL = params.get("ullon");
        double yUL = params.get("ullat");
        double xLR = params.get("lrlon");
        double yLR = params.get("lrlat");
        int segment = getSegment(depth);
        double unitX = perUnitLength(ROOT_ULLON, ROOT_LRLON, segment);
        double unitY = perUnitLength(ROOT_ULLAT, ROOT_LRLAT, segment);
        int indexULX = getIndex(ROOT_ULLON, xUL, unitX);
        int indexLRX = getIndex(ROOT_ULLON, xLR, unitX);
        int indexULY = getIndex(ROOT_ULLAT, yUL, unitY);
        int indexLRY = getIndex(ROOT_ULLAT, yLR, unitY);
        int col = titleNum(indexULX, indexLRX);
        int row = titleNum(indexULY, indexLRY);
        return col * row;

    }

    /**
     * Compute title number in x or in y
     *
     * @param end1
     * @param end2
     * @return
     */
    private int titleNum(int end1, int end2) {
        return Math.abs(end1 - end2) + 1;

    }

    /**
     * Compute  index  about coordinate
     *
     * @param xR
     * @param xT
     * @param u
     * @return
     */
    private int getIndex(double xR, double xT, double u) {
        double res = Math.abs(xR - xT) / u;

        return (int) res;
    }


    /**
     * Generate the title file
     *
     * @param params
     * @param depth
     * @return
     */
    private String[][] getRenderGrid(Map<String, Double> params, int depth) {
        int[] startIndex = getStartIndex(params, depth);
        int[] endIndex = getEndIndex(params, depth);
        int row = endIndex[1] - startIndex[1] + 1;
        int col = endIndex[0] - startIndex[0] + 1;
        String[][] res = new String[row][col];
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                res[r][c] = generateGrid(depth, startIndex[0] + c, startIndex[1] + r);
            }
        }
        return res;
    }

    /**
     * Generate a file name.
     *
     * @param depth
     * @param x     the x index.
     * @param y     the y index.
     * @return
     */
    private String generateGrid(int depth, int x, int y) {
        StringBuilder str = new StringBuilder();
        str.append("d").append(depth).append("_x").append(x).append("_y").append(y).append(".png");
        return str.toString();
    }

    /**
     * Compute start index
     *
     * @param params
     * @param depth
     * @return
     */
    private int[] getStartIndex(Map<String, Double> params, int depth) {
        double x = params.get("ullon");
        double y = params.get("ullat");
        int xIndex = getIndex(ROOT_ULLON, x, XUnitLength(depth));
        int yIndex = getIndex(ROOT_ULLAT, y, YUnitLength(depth));
        xIndex = checkIndex(xIndex, depth);
        yIndex = checkIndex(yIndex, depth);
        int[] res = new int[]{
                xIndex, yIndex
        };
        return res;
    }

    /**
     * Compute end Index.
     *
     * @param params
     * @param depth
     * @return
     */
    private int[] getEndIndex(Map<String, Double> params, int depth) {
        double x = params.get("lrlon");
        double y = params.get("lrlat");
        int xIndex = getIndex(ROOT_ULLON, x, XUnitLength(depth));
        int yIndex = getIndex(ROOT_ULLAT, y, YUnitLength(depth));
        xIndex = checkIndex(xIndex, depth);
        yIndex = checkIndex(yIndex, depth);
        int[] res = new int[]{
                xIndex, yIndex
        };
        return res;
    }

    /**
     * Check the index whether is out of bound
     *
     * @param index
     * @param depth
     * @return
     */
    private int checkIndex(int index, int depth) {
        int up = getSegment(depth) - 1;
        if (index > up) {
            index = up;
        }
        return index;
    }

    /**
     * Compute unit length in x
     *
     * @param depth
     * @return
     */
    private double XUnitLength(int depth) {
        int segment = getSegment(depth);
        double res = perUnitLength(ROOT_ULLON, ROOT_LRLON, segment);
        return res;
    }

    /**
     * Compute unit length in y
     *
     * @param depth
     * @return
     */
    private double YUnitLength(int depth) {
        int segment = getSegment(depth);
        double res = perUnitLength(ROOT_ULLAT, ROOT_LRLAT, segment);
        return res;
    }

    /**
     * Get raster_ul_lon,raster_ul_lat
     *
     * @param params
     * @param depth
     * @return
     */
    private double[] getRasterUL(Map<String, Double> params, int depth) {
        int[] res = new int[dimension];
        int[] startIndex = getStartIndex(params, depth);
        res[0] = startIndex[0];
        res[1] = startIndex[1];
        res[2] = depth;
        double[] ulCoordinate = getULCoordinate(res);
        return ulCoordinate;
    }

    /**
     * Get raster_lr_lon, raster_lr_lat
     *
     * @param params
     * @param depth
     * @return
     */
    private double[] getRasterLR(Map<String, Double> params, int depth) {
        int[] res = new int[dimension];
        int[] endIndex = getEndIndex(params, depth);
        res[0] = endIndex[0];
        res[1] = endIndex[1];
        res[2] = depth;
        double[] lrCoordinate = getLRCoordinate(res);
        return lrCoordinate;
    }

    /**
     * Check ullon, ullat, lrlon, lrlat are whether out of bound
     *
     * @param params
     */
    private void checkCoordinate(Map<String, Double> params) {
        double xUL = params.get("ullon");
        double yUL = params.get("ullat");
        double xLR = params.get("lrlon");
        double yLR = params.get("lrlat");

        check(params, xUL, ROOT_ULLON, ROOT_LRLON, "ullon");
        check(params, yUL, ROOT_LRLAT, ROOT_ULLAT, "ullat");
        check(params, xLR, ROOT_ULLON, ROOT_LRLON, "lrlon");
        check(params, yLR, ROOT_LRLAT, ROOT_ULLAT, "lrlat");

    }

    /**
     *  Check function , check up bound and lower bound.
     *
     *
     * @param params
     * @param target
     * @param lower
     * @param up
     * @param name
     */
    private void check(Map<String, Double> params, double target, double lower, double up, String name) {
        if (target < lower) {
            params.put(name, lower);
        }

        if (target > up) {
            params.put(name, up);
        }
    }

}
