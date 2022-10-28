import java.util.HashMap;
import java.util.Map;

/**
 * This class provides all code necessary to take a query box and produce
 * a query result. The getMapRaster method must return a Map containing all
 * seven of the required fields, otherwise the front end code will probably
 * not draw the output correctly.
 */
public class Rasterer {


    private static final int EACH_DEGREE_OF_LONGITUDE = 288200;
    public static final double ROOT_ULLAT = 37.892195547244356, ROOT_ULLON = -122.2998046875,
            ROOT_LRLAT = 37.82280243352756, ROOT_LRLON = -122.2119140625;
    private static double initImgLonDPP;
    private double[] imgLonDPPs;
    public static final int TILE_SIZE = 256;

    private static final double xDis = ROOT_ULLON - ROOT_LRLON;
    private static final double yDis = ROOT_LRLAT - ROOT_LRLAT;

    private static final int valveDepth = 8;

    public Rasterer() {
        // YOUR CODE HERE
        initImgLonDPP = computeLonDPP(ROOT_LRLON, ROOT_ULLON, TILE_SIZE);
        imgLonDPPs = new double[valveDepth];
        initImgLonDPPs(initImgLonDPP);
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
        initRaster(params, results);
        System.out.println(results);
        return results;
    }


    /**
     * Compare the user expecting LonDPPs with build-in imgLonDPP
     *
     * @param lonA
     * @param lonB
     * @param width
     * @return
     */
    private int depth(double lonA, double lonB, Double width) {
        double userLonDPP = Math.abs(lonA - lonB) / width;
        for (int i = 0; i < imgLonDPPs.length; i++) {
            if (imgLonDPPs[i] <= userLonDPP) {
                return i;
            }
        }
        return imgLonDPPs.length - 1;
    }

    /**
     * Init the imgLonDPPs array
     */
    private void initImgLonDPPs(double imgLonDPP) {
        for (int i = 0; i < imgLonDPPs.length; i++) {
            this.imgLonDPPs[i] = initImgLonDPP / (1 << i);
        }
    }


    /**
     * compute longitude distance per pixel
     *
     * @param lonA
     * @param lonB
     * @param width
     * @return
     */
    private double computeLonDPP(double lonA, double lonB, double width) {

        return Math.abs(lonA - lonB) / width;
    }

    /**
     * compute bounding box index  for x or y
     * if compute x index, the rootCoordinateUL should be ROOT_ULLAT and rootCoordinateLR should be ROOT_LRLAT.
     *
     * @param rootCoordinateLR
     * @param rootCoordinateUL
     * @param coordinate
     * @param depth
     * @return
     */
    private int boundingImgIndex(double rootCoordinateLR, double rootCoordinateUL, double coordinate, int depth) {
        double reldifference = Math.abs(rootCoordinateUL - coordinate);
        double lonLength = Math.abs(rootCoordinateUL - rootCoordinateLR);
        int result = (int) ((reldifference / lonLength) * (1 << depth)) + 1;
        return result;
    }

    /**
     * compute the bounding box img coordinate
     *
     * @param xIndex
     * @param yIndex
     * @param depth
     * @param choose choose == 0, compute UL, otherwise is LR
     */
    private double[] boundingImgCoordinate(int xIndex, int yIndex, int depth, int choose) {
        double xPer = xDis / (2 << depth);
        double yPer = yDis / (2 << depth);
        double[] coordinate = new double[2];
        coordinate[0] = ROOT_ULLON + xIndex * xPer;
        coordinate[1] = ROOT_ULLAT + yIndex * yPer;
        if (choose == 1) {
            coordinate[0] += xPer;
            coordinate[1] -= yPer;
        }
        return coordinate;
    }

    /**
     * set property  about  roaster_UL and roaster_LR
     *
     * @param ullon
     * @param ullat
     * @param lrlon
     * @param lrlat
     * @param depth
     * @param params save result
     */
    private void setCoordinate(double ullon, double ullat, double lrlon, double lrlat, int depth, Map params) {

        int XULIndex = boundingImgIndex(ROOT_ULLON, ROOT_LRLON, ullon, depth);
        int YULIndex = boundingImgIndex(ROOT_ULLAT, ROOT_LRLAT, ullat, depth);
        int XLRIndex = boundingImgIndex(ROOT_ULLON, ROOT_LRLON, lrlon, depth);
        int YLRIndex = boundingImgIndex(ROOT_ULLAT, ROOT_LRLAT, lrlat, depth);
        double[] roasterUL = boundingImgCoordinate(XULIndex, YULIndex, depth, 0);
        double[] roasterLR = boundingImgCoordinate(XLRIndex, YLRIndex, depth, 0);
        params.put("raster_ul_lon", roasterUL[0]);
        params.put("raster_ul_lat", roasterUL[1]);
        params.put("raster_lr_lat", roasterLR[0]);
        params.put("raster_lr_lon", roasterLR[1]);

    }

    /**
     * number of tiles
     *
     * @param xULIndex
     * @param yULIndex
     * @param xLRIndex
     * @param yLRIndex
     * @return
     */
    private int totalTiles(int xULIndex, int yULIndex, int xLRIndex, int yLRIndex) {
        return (xLRIndex - xULIndex + 1) * (yULIndex - yLRIndex + 1);
    }

    private void initRaster(Map<String, Double> params, Map<String, Object> results) {
        double ullon = (double) params.get("ullon");
        double ullat = (double) params.get("ullat");
        double lrlon = (double) params.get("lrlon");
        double lrlat = (double) params.get("lrlat");
        double width = params.get("w");
        int depth = depth(ullon, lrlon, width);
        results.put("depth", depth);
        setCoordinate(ullon, ullat, lrlon, lrlat, depth, results);
        setGrid(params, depth, results);
        setQueryState(results);
    }

    private void setGrid(Map<String, Double> params, int depth, Map<String, Object> results) {

        double ullon = params.get("ullon");
        double ullat = (double) params.get("ullat");
        double lrlon = (double) params.get("lrlon");
        double lrlat = params.get("lrlat");

        int XULIndex = boundingImgIndex(ROOT_ULLON, ROOT_LRLON, ullon, depth);
        int YULIndex = boundingImgIndex(ROOT_ULLAT, ROOT_LRLAT, ullat, depth);
        int XLRIndex = boundingImgIndex(ROOT_ULLON, ROOT_LRLON, lrlon, depth);
        int YLRIndex = boundingImgIndex(ROOT_ULLAT, ROOT_LRLAT, lrlat, depth);

        int col = Math.abs(XULIndex - XLRIndex);
        int row = Math.abs(YULIndex - YLRIndex);

        String[][] grid = generateGrid(row, col, XULIndex, YULIndex, depth);
        results.put("render_grid", grid);

    }

    private void setQueryState(Map<String, Object> results) {
        results.put("query_success", true);
    }

    private String[][] generateGrid(int row, int col, int XULIndex, int YULIndex, int depth) {
        String grid[][] = new String[row + 1][col + 1];
        StringBuilder sb;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                sb = new StringBuilder();
                sb.append("d").append(depth).append("_x").append(XULIndex + j).append("_y").append(YULIndex + i).append(".png");
                grid[i][j] = sb.toString();
            }
        }
        return grid;
    }
}