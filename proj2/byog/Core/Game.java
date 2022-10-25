package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import org.junit.Test;

import java.util.Random;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 50;
    public static final int MAXWIDTH=8;
    public static final int MAXHEIGHT=8;

    private static final int MAXNUM=100;

    private static boolean[][] isSetRoom=new boolean[WIDTH][HEIGHT];


    private static boolean[][] isSetTunnel=new boolean[WIDTH][HEIGHT];
    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {

    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().
        int seed=findInt(input);
        int RandomNumX=RandomUtils.uniform(new Random(seed),MAXNUM);
        int RandomNumY=RandomUtils.uniform(new Random(seed+5),MAXNUM);
        TETile[][] finalWorldFrame = new TETile[RandomNumX][RandomNumY];
        createWorld(finalWorldFrame,seed);
        return finalWorldFrame;
    }

    /***
     *
     * @param worldFrame: The world you are about to create
     *      The first[] means the row from the bottom line, it goes up
     *      The second[] means the column from the left-most line, it goes right
     */
    private void createWorld(TETile[][] worldFrame,int seed) {
        createRooms(worldFrame,seed);
        excavateTunnels(worldFrame,seed);
        connectRoomNTunnels(worldFrame,seed);
        fillEnds(worldFrame,seed);
    }

    private void fillEnds(TETile[][] worldFrame,int seed) {

    }

    private void connectRoomNTunnels(TETile[][] worldFrame, int seed) {

    }

    private static void excavateTunnels(TETile[][] worldFrame, int seed) {
        int startX=1,startY=1;
        for(int i=startX;i<WIDTH;i++)
        {
            for(int j=startY;j<HEIGHT;j++)
            {
                Random random=new Random(seed);
                int turn=RandomUtils.uniform(random,4);
                if(emptyEnough(worldFrame,i,j))dig(worldFrame,i,j,seed,turn);
            }
        }
    }

    private static boolean emptyEnough(TETile[][] worldFrame, int i, int j) {
        boolean ret=true;
        for(int x=i-1;x<=i+1;x++)
        {
            for(int y=j-1;y<=j+1;y++)
            {
                ret=ret&&isEmpty(x,y);
            }
        }
        return ret;
    }

    private static void dig(TETile[][] worldFrame, int i, int j, int seed,int willTurn) {
        if(outOfIndex( i, j))return;
        if(floodFillJudge(worldFrame,i,j,willTurn))
        {
            worldFrame[i][j]=Tileset.FLOWER;
            isSetTunnel[i][j]=true;
            Random random=new Random(seed);
            int[] turn=new int[4];
            turn[0]=RandomUtils.uniform(random,4);
            for(int y=1;y<4;y++)
            {
                turn[y]=(turn[y-1]+1)%4;
            }
            for(int k=0;k<4;k++)
            {
                int turnWhere=turn[k];
                switch (turnWhere)
                {
                    case 0:{
                        dig(worldFrame,i+1,j,seed+1,0);
                        break;
                    }
                    case 1:{
                        dig(worldFrame,i-1,j,seed+2,1);
                        break;
                    }
                    case 2:{
                        dig(worldFrame,i,j+1,seed+3,2);
                        break;
                    }
                    case 3:{
                        dig(worldFrame,i,j-1,seed+4,3);
                        break;
                    }
                }
            }
        }
    }

    private static boolean outOfIndex(int i, int j) {
           return i<0||j<0||i>=WIDTH||j>=HEIGHT;
    }

    private static boolean isEmpty(int i,int j)
    {
        if(i<0||j<0||i>=WIDTH||j>=HEIGHT)return false;
        return !isSetRoom[i][j]&&!isSetTunnel[i][j];
    }
    private static boolean floodFillJudge(TETile[][] worldFrame, int i, int j,int willTurn) {
        boolean con0=i>0&&j>0&&i<WIDTH-1&&j<HEIGHT-1;
        boolean con1=isEmpty(i,j);
        switch (willTurn)
        {
            case 0:
            {
                con1=con1&&isEmpty(i+1,j)&&isEmpty(i,j-1)&&isEmpty(i,j+1);
                break;
            }
            case 1:
            {
                con1=con1&&isEmpty(i-1,j)&&isEmpty(i,j-1)&&isEmpty(i,j+1);
                break;
            }
            case 2:
            {
                con1=con1&&isEmpty(i+1,j)&&isEmpty(i-1,j)&&isEmpty(i,j+1);
                break;
            }
            case 3:
            {
                con1=con1&&isEmpty(i+1,j)&&isEmpty(i,j-1)&&isEmpty(i-1,j);
                break;
            }
        }
        boolean con2=isEmpty(i+1,j)&&isEmpty(i+1,j-1)&&isEmpty(i+1,j+1);
        boolean con3=isEmpty(i-1,j)&&isEmpty(i-1,j-1)&&isEmpty(i-1,j+1);
        boolean con4=isEmpty(i+1,j+1)&&isEmpty(i,j+1)&&isEmpty(i-1,j+1);
        boolean con5=isEmpty(i+1,j-1)&&isEmpty(i,j-1)&&isEmpty(i-1,j-1);
        boolean conCombine=con2||con3||con4||con5;
        return con0&&con1&&conCombine;
    }


    @Test
    public void test()
    {
        Random r=new Random(16);
        for(int i=0;i<10;i++) {
            System.out.println(r.nextInt(4));
        }
    }
    

    private static void createRooms(TETile[][] worldFrame, int seed) {
        Random ran=new Random(seed);
        int time=40;
        while(time>0) {
            boolean judge=false;
            int xPos=0;
            int yPos=0;
            boolean loopJudge=true;
            while(loopJudge) {
                xPos = RandomUtils.uniform(ran, WIDTH);
                yPos = RandomUtils.uniform(ran, HEIGHT);
                if(xPos+4<WIDTH&&yPos+4<HEIGHT)loopJudge=false;
            }
            int maxWidth=xPos+MAXWIDTH>WIDTH?WIDTH-xPos:MAXWIDTH;
            int maxHeight=yPos+MAXHEIGHT>HEIGHT?HEIGHT-yPos:MAXHEIGHT;
            int width = RandomUtils.uniform(ran, 4,maxWidth);
            int height = RandomUtils.uniform(ran, 4,maxHeight);
            FLAG:for(int i=xPos;i<xPos+width;i++)
            {
                for(int j=yPos;j<yPos+height;j++)
                {
                    boolean outOfIndex1=i+1>WIDTH;
                    boolean outOfIndex2=i-1<0;
                    boolean outOfIndex3=j+1>HEIGHT;
                    boolean outOfIndex4=j-1<0;
                    if(isSetRoom[i][j] || (!outOfIndex1&&isSetRoom[i + 1][j]) || (!outOfIndex2&&isSetRoom[i - 1][j]) || (!outOfIndex3&&isSetRoom[i][j + 1]) || (!outOfIndex4&&isSetRoom[i][j - 1]))
                    {
                        judge=true;
                        break FLAG;
                    }
                }
            }
            if(!judge) {
                for (int i = xPos; i < xPos + width; i++) {
                    for (int j = yPos; j < yPos + height; j++) {
                        worldFrame[i][j] = Tileset.MOUNTAIN;
                        isSetRoom[i][j] = true;
                    }
                }
                time--;
            }
        }
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] randomTiles = new TETile[WIDTH][HEIGHT];
        initWorld(randomTiles);
        createRooms(randomTiles,12);
        excavateTunnels(randomTiles,12);
        ter.renderFrame(randomTiles);
    }

    private static void initWorld(TETile[][] randomTiles) {
        for(int i=0;i<WIDTH;i++)
        {
            for(int j=0;j<HEIGHT;j++)
                randomTiles[i][j]=Tileset.NOTHING;
        }
    }

    private static int findInt(String input) {
        int returnInt=0;
        int numStart=0,endSpot=0;
        while(numStart<input.length())
        {
            if(input.charAt(numStart)>='0'&&input.charAt(numStart)<='9')
            {
                endSpot=numStart;
                while(input.charAt(endSpot)>='0'&&input.charAt(endSpot)<='9')
                {
                    endSpot++;
                }
                if(input.charAt(endSpot)=='s'||input.charAt(endSpot)=='S')
                {
                    break;
                }
                else {
                    numStart=endSpot;
                }
            }
            numStart++;
        }
        String subInput="";
        if(numStart<input.length()) {
            subInput = input.substring(numStart, endSpot);
            returnInt=turnIntoInt(subInput);
        }
        return returnInt;
    }

    private static int turnIntoInt(String subInput) {
        int powRight=subInput.length()-1;
        int charP=0;
        int returnNum=0;
        while (powRight>=0)
        {
            int num=subInput.charAt(charP++)-'0';
            returnNum+=num*Math.pow(10,powRight--);
        }
        return returnNum;
    }
}
