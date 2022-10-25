package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;
import java.util.Scanner;

/**
 * Draws a world that contains RANDOM tiles.
 */
public class RandomWorldDemo {
    private static int WIDTH = 50;
    private static int HEIGHT = 50;

    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);

    /**
     * Fills the given 2D array of tiles with RANDOM tiles.
     * @param tiles
     */
    public static void fillWithRandomTiles(TETile[][] tiles) {
        int height = tiles[0].length;
        int width = tiles.length;
        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height; y += 1) {
                tiles[x][y] = randomTile();
            }
        }
    }

    /*
    *     aaa
    *    aaaaa
    *   aaaaaaa
    *   aaaaaaa
    *    aaaaa
    *     aaa
    * */
    public static void addHexagon(TETile[][] tiles) {
        int width = tiles[0].length;
        int halfHeight = tiles.length/2;
        int level=1;
        for (int i = 0; i < halfHeight; i++)
        {
            int mov=0;
            int nothingBlockNum=halfHeight-level;
            for(int j=0;j<nothingBlockNum;j++)
            {
                tiles[i][mov++]=Tileset.NOTHING;
            }
            int contentBlockNum=halfHeight+2*(level-1);
            for(int k=0;k<contentBlockNum;k++)
            {
                tiles[i][mov++]=Tileset.FLOWER;
            }
            for(int j=0;j<nothingBlockNum;j++)
            {
                tiles[i][mov++]=Tileset.NOTHING;
            }
            level++;
            System.out.println();
        }
        level--;
        for (int i = halfHeight; i <2*halfHeight; i++)
        {
            int mov=0;
            int nothingBlockNum=halfHeight-level;
            for(int j=0;j<nothingBlockNum;j++)
            {
                tiles[i][mov++]=Tileset.NOTHING;
            }
            int contentBlockNum=halfHeight+2*(level-1);
            for(int k=0;k<contentBlockNum;k++)
            {
                tiles[i][mov++]=Tileset.FLOWER;
            }
            for(int j=0;j<nothingBlockNum;j++)
            {
                tiles[i][mov++]=Tileset.NOTHING;
            }
            level--;
            System.out.println();
        }
    }
    /** Picks a RANDOM tile with a 33% change of being
     *  a wall, 33% chance of being a flower, and 33%
     *  chance of being empty space.
     */
    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(3);
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.TREE;
            case 2: return Tileset.NOTHING;
            default: return Tileset.NOTHING;
        }
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        int sideLen=0;
        Scanner keyBoard=new Scanner(System.in);
        System.out.println("Decide the sideLen of the world:");
        WIDTH=80;
        HEIGHT=50;
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] randomTiles = new TETile[WIDTH][HEIGHT];
        //fillWithRandomTiles(randomTiles);
        //addHexagon(randomTiles);
        for(int j=0;j<HEIGHT;j++)
        {
            for(int i=0;i<WIDTH;i++) {
                if(i==0&&j==0||i==1&&j==0)
                randomTiles[i][j] = Tileset.FLOWER;
                else
                    randomTiles[i][j] = Tileset.GRASS;
            }
        }
        ter.renderFrame(randomTiles);
    }


}
