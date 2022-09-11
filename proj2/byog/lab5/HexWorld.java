package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    private static final int WIDTH = 30;

    private static final int HEIGHT = 40;

    private static final long SEED = 750611;

    private static final Random RANDOM = new Random(SEED);

    /*
    * Presenting a hexagon
    * */
    class Hexagon {

        int size;
        int[] x;
        int[] length;

        public Hexagon(int size) {
            this.size = size;
            x = new int[size];
            length = new int[size];
        }

        public int getSize() {
            return size;
        }

        public int[] getX() {
            return x;
        }

        public int[] getLength() {
            return length;
        }
    }

    public static void main(String[] args) {

        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        initWord(world);
        HexWorld hexWorld = new HexWorld();
        hexWorld.add19Hexagon(15,35,world);
        ter.renderFrame(world);
    }

    /**
     * init the world
     * */
    static void initWord (TETile[][] world) {

        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }

    /**
     * Fill with the property of the world created by me where the hexagon exist.
     * */
    void addHexagon(int size, int x, int y, TETile[][] tiles, TETile symbol) {

        Hexagon hexagon = computeHexagon(x, size);
        int[] xoffset = hexagon.getX();
        int[] length = hexagon.getLength();

        for (int i = 0; i < xoffset.length; i++) {
            for (int j = 0; j < length[i]; j++) {
                tiles[xoffset[i] + j][y - i] = symbol;
            }
        }

        int count = size;
        for (int i = xoffset.length - 1; i >= 0; i--) {
            for (int j = 0; j < length[i]; j++) {
                tiles[xoffset[i] + j][y -count] = symbol;
            }
            count++;
        }
    }

    /**
     * compute a hexagon what they need beginning x and length in each row.
     * */
    Hexagon computeHexagon(int x, int size) {

        Hexagon hexagon = new Hexagon(size);

        int[] xoffset = hexagon.getX();
        int[] length = hexagon.getLength();

        for (int i = 0; i < size; i++) {
            xoffset[i] = x - i;
            length[i] = size + (i * 2);
        }

        return hexagon;
    }

    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(5);
        switch (tileNum) {
            case 0: return Tileset.SAND;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.TREE;
            case 3: return Tileset.MOUNTAIN;
            case 4: return Tileset.WATER;
            default: return Tileset.NOTHING;
        }
    }

    void add19Hexagon(int x,int y,TETile[][] tiles) {


        for (int i = 0; i < 5; i++) {
            addHexagon(3,x,y - i * 6,tiles,randomTile());
        }


        for (int i = 0; i < 4; i++) {
            addHexagon(3,x - 5,y - 3 - i * 6 ,tiles,randomTile());
            addHexagon(3,x + 5,y - 3 - i * 6 ,tiles,randomTile());
        }

        for (int i = 0; i < 3; i++) {
            addHexagon(3,x -10,y - 6 - i * 6,tiles,randomTile());
            addHexagon(3,x +10,y - 6 - i * 6,tiles,randomTile());
        }


    }

}
