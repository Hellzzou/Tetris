import java.awt.*;

class Bloc {
    private Color color;
    private int[] positionsX = new int[4];
    private int[] positionsY = new int[4];
    private int[] lookup = new int[4];
    private int[] lookUnder = new int[4];
    private int[] lookLeft = new int[4];
    private int[] lookRight = new int[4];
    private int rotate;

    Bloc(int randomShape, int posX, int posY) {
        String[] shapes = {"rightzig","leftzig","rightL","leftL","bar","square","t"};
        String shape = shapes[randomShape];
        rotate = 1;
        switch(shape){
            case "rightzig" :
                this.color = Color.ORANGE;
                positionsX = new int[]{posX,posX, posX + 1, posX + 1 };
                positionsY = new int[]{posY,posY + 1, posY + 1, posY + 2 };
                lookUnder = new int[]{0,1,0,1};
                lookLeft = new int[]{1,1,0,1};
                lookRight = new int[]{1,0,1,1};
                lookup = new int []{1,0,1,0};
                break;
            case "leftzig" :
                this.color = Color.CYAN;
                positionsX = new int[]{posX,posX, posX - 1, posX - 1 };
                positionsY = new int[]{posY,posY + 1, posY + 1, posY + 2 };
                lookUnder = new int[]{0,1,0,1};
                lookLeft = new int[]{1,0,1,1};
                lookRight = new int[]{1,1,0,1};
                lookup = new int[]{1,0,1,0};
                break;
            case "rightL" :
                this.color = Color.PINK;
                positionsX = new int[]{posX,posX + 1, posX, posX };
                positionsY = new int[]{posY,posY, posY + 1, posY + 2 };
                lookUnder = new int[]{0,1,0,1};
                lookLeft = new int[]{1,0,1,1};
                lookRight = new int[]{0,1,1,1};
                lookup = new int []{1,1,0,0};
                break;
            case "leftL" :
                this.color = Color.YELLOW;
                positionsX = new int[]{posX,posX + 1, posX + 1, posX + 1 };
                positionsY = new int[]{posY,posY, posY + 1, posY + 2 };
                lookUnder = new int[]{1,0,0,1};
                lookLeft = new int[]{1,0,1,1};
                lookRight = new int[]{0,1,1,1};
                lookup = new int []{1,1,0,0};
                break;
            case "bar" :
                this.color = Color.GRAY;
                positionsX = new int[]{posX,posX, posX, posX };
                positionsY = new int[]{posY,posY + 1, posY + 2, posY + 3 };
                lookUnder = new int[]{0,0,0,1};
                lookLeft = new int[]{1,1,1,1};
                lookRight = new int[]{1,1,1,1};
                lookup = new int []{1,0,0,0};
                break;
            case "square" :
                this.color = Color.RED;
                positionsX = new int[]{posX,posX, posX + 1, posX + 1 };
                positionsY = new int[]{posY,posY + 1, posY, posY + 1 };
                lookUnder = new int[]{0,1,0,1};
                lookLeft = new int[]{1,1,0,0};
                lookRight = new int[]{0,0,1,1};
                lookup = new int []{1,0,1,0};
                break;
            case "t" :
                this.color = Color.green;
                positionsX = new int[]{posX,posX - 1, posX, posX + 1 };
                positionsY = new int[]{posY,posY + 1, posY + 1, posY + 1 };
                lookUnder = new int[]{0,1,1,1};
                lookLeft = new int[]{1,1,0,0};
                lookRight = new int[]{1,0,0,1};
                lookup = new int []{1,1,0,1};
                break;
        }
    }

    Color getColor() { return color; }

    int[] getPositionsX() { return positionsX; }

    int[] getPositionsY() { return positionsY; }

    int[] getLookUnder() { return lookUnder; }

    int[] getLookLeft() { return lookLeft; }

    int[] getLookRight() { return lookRight; }

    void rotateBloc(GamePanel gamePanel){
        int[] temp = lookUnder;
        Color[][] colors = gamePanel.getTab();
        if (color == Color.ORANGE){
            switch (rotate){
                case 1 :
                    if (colors[positionsX[0] + 2][positionsY[0]] == Color.WHITE && colors[positionsX[0] + 1][positionsY[0]] == Color.WHITE ) {
                        positionsX[1] = positionsX[1] + 1;
                        positionsX[3] = positionsX[3] + 1;
                        positionsY[0] = positionsY[0] + 1;
                        positionsY[2] = positionsY[2] - 1;
                        positionsY[3] = positionsY[3] - 2;
                        rotate = 2;
                        lookUnder = lookLeft;
                        lookLeft = lookup;
                        lookup = lookRight;
                        lookRight = temp;
                    }
                    break;
                case 2 :
                    if (colors[positionsX[0] + 1][positionsY[0] + 1] == Color.WHITE && colors[positionsX[0]][positionsY[0] - 1] == Color.WHITE ) {
                        positionsX[1] = positionsX[1] - 1;
                        positionsX[3] = positionsX[3] - 1;
                        positionsY[0] = positionsY[0] - 1;
                        positionsY[2] = positionsY[2] + 1;
                        positionsY[3] = positionsY[3] + 2;
                        rotate = 1;
                        lookUnder = lookRight;
                        lookRight = lookup;
                        lookup = lookLeft;
                        lookLeft = temp;
                    }
                    break;
            }
        }
        if (color == Color.CYAN){
            switch (rotate){
                case 1 :
                    if (colors[positionsX[0] + 1][positionsY[0] + 1] == Color.WHITE && colors[positionsX[0] - 2][positionsY[0]] == Color.WHITE ) {
                        positionsX[0] = positionsX[0] - 1;
                        positionsX[2] = positionsX[2] + 1;
                        positionsX[3] = positionsX[3] + 2;
                        positionsY[1] = positionsY[1] - 1;
                        positionsY[3] = positionsY[3] - 1;
                        rotate = 2;
                        lookUnder = lookLeft;
                        lookLeft = lookup;
                        lookup = lookRight;
                        lookRight = temp;
                    }
                    break;
                case 2 :
                    if (colors[positionsX[0]][positionsY[0] + 1] == Color.WHITE && colors[positionsX[0]][positionsY[0] + 2] == Color.WHITE ) {
                        positionsX[0] = positionsX[0] + 1;
                        positionsX[2] = positionsX[2] - 1;
                        positionsX[3] = positionsX[3] - 2;
                        positionsY[1] = positionsY[1] + 1;
                        positionsY[3] = positionsY[3] + 1;
                        rotate = 1;
                        lookUnder = lookRight;
                        lookRight = lookup;
                        lookup = lookLeft;
                        lookLeft = temp;
                    }
                    break;
            }
        }
        if (color == Color.PINK){
            switch (rotate){
                case 1 :
                    if (colors[positionsX[0] + 2][positionsY[0]] == Color.WHITE && colors[positionsX[0] + 2][positionsY[0] + 1] == Color.WHITE ) {
                        positionsX[0] = positionsX[0] + 2;
                        positionsX[1] = positionsX[1] + 1;
                        positionsX[2] = positionsX[2] + 1;
                        positionsY[1] = positionsY[1] + 1;
                        positionsY[2] = positionsY[2] - 1;
                        positionsY[3] = positionsY[3] - 2;
                        rotate = 2;
                        lookUnder = lookRight;
                        lookRight = lookup;
                        lookup = lookLeft;
                        lookLeft = temp;
                    }
                    break;
                case 2 :
                    if (colors[positionsX[0] - 1][positionsY[0] + 1] == Color.WHITE && colors[positionsX[0] - 1][positionsY[0] + 2] == Color.WHITE && colors[positionsX[0] - 2][positionsY[0] + 2] == Color.WHITE) {
                        positionsX[0] = positionsX[0] - 1;
                        positionsX[1] = positionsX[1] - 2;
                        positionsX[3] = positionsX[3] + 1;
                        positionsY[0] = positionsY[0] + 2;
                        positionsY[1] = positionsY[1] + 1;
                        positionsY[2] = positionsY[2] + 1;
                        rotate = 3;
                        lookUnder = lookRight;
                        lookRight = lookup;
                        lookup = lookLeft;
                        lookLeft = temp;
                    }
                    break;
                case 3 :
                    if (colors[positionsX[0] - 1][positionsY[0] - 1] == Color.WHITE && colors[positionsX[0] - 2][positionsY[0] - 1] == Color.WHITE && colors[positionsX[0] - 2][positionsY[0] - 2] == Color.WHITE) {
                        positionsX[0] = positionsX[0] - 2;
                        positionsX[1] = positionsX[1] - 1;
                        positionsX[2] = positionsX[2] - 1;
                        positionsY[0] = positionsY[0] - 1;
                        positionsY[1] = positionsY[1] - 2;
                        positionsY[3] = positionsY[3] + 1;
                        rotate = 4;
                        lookUnder = lookRight;
                        lookRight = lookup;
                        lookup = lookLeft;
                        lookLeft = temp;
                    }
                    break;
                case 4 :
                    if (colors[positionsX[0] + 1][positionsY[0] - 1] == Color.WHITE && colors[positionsX[0] + 2][positionsY[0] - 1] == Color.WHITE && colors[positionsX[0] + 1][positionsY[0] + 1] == Color.WHITE) {
                        positionsX[0] = positionsX[0] + 1;
                        positionsX[1] = positionsX[1] + 2;
                        positionsX[3] = positionsX[3] - 1;
                        positionsY[0] = positionsY[0] - 1;
                        positionsY[3] = positionsY[3] + 1;
                        rotate = 1;
                        lookUnder = lookRight;
                        lookRight = lookup;
                        lookup = lookLeft;
                        lookLeft = temp;
                    }
                    break;
            }
        }
        if (color == Color.YELLOW){
            switch (rotate){
                case 1 :
                    if (colors[positionsX[0] - 1][positionsY[0] + 1] == Color.WHITE && colors[positionsX[0]][positionsY[0] + 1] == Color.WHITE) {
                        positionsX[0] = positionsX[0] + 1;
                        positionsX[2] = positionsX[2] - 1;
                        positionsX[3] = positionsX[3] - 2;
                        positionsY[1] = positionsY[1] + 1;
                        positionsY[3] = positionsY[3] - 1;
                        rotate = 2;
                        lookUnder = lookRight;
                        lookRight = lookup;
                        lookup = lookLeft;
                        lookLeft = temp;
                    }
                    break;
                case 2 :
                    if (colors[positionsX[0] - 1][positionsY[0]] == Color.WHITE && colors[positionsX[0]][positionsY[0] + 2] == Color.WHITE && colors[positionsX[0] - 1][positionsY[0] + 2] == Color.WHITE) {
                        positionsX[1] = positionsX[1] - 1;
                        positionsX[3] = positionsX[3] + 1;
                        positionsY[0] = positionsY[0] + 2;
                        positionsY[1] = positionsY[1] + 1;
                        positionsY[3] = positionsY[3] - 1;
                        rotate = 3;
                        lookUnder = lookRight;
                        lookRight = lookup;
                        lookup = lookLeft;
                        lookLeft = temp;
                    }
                    break;
                case 3 :
                    if (colors[positionsX[0]][positionsY[0] - 2] == Color.WHITE && colors[positionsX[0] + 1][positionsY[0] - 2] == Color.WHITE) {
                        positionsX[0] = positionsX[0] - 1;
                        positionsX[2] = positionsX[2] + 1;
                        positionsX[3] = positionsX[3] + 2;
                        positionsY[0] = positionsY[0] - 1;
                        positionsY[1] = positionsY[1] - 2;
                        positionsY[2] = positionsY[2] - 1;
                        lookUnder = lookRight;
                        lookRight = lookup;
                        lookup = lookLeft;
                        lookLeft = temp;
                        rotate = 4;
                    }
                    break;
                case 4 :
                    if (colors[positionsX[0] + 1][positionsY[0]] == Color.WHITE && colors[positionsX[0] + 1][positionsY[0] + 1] == Color.WHITE) {
                        positionsX[1] = positionsX[1] + 1;
                        positionsX[3] = positionsX[3] - 1;
                        positionsY[0] = positionsY[0] - 1;
                        positionsY[2] = positionsY[2] + 1;
                        positionsY[3] = positionsY[3] + 2;
                        lookUnder = lookRight;
                        lookRight = lookup;
                        lookup = lookLeft;
                        lookLeft = temp;
                        rotate = 1;
                    }
                    break;
            }
        }
        if (color == Color.GRAY){
            switch (rotate){
                case 1 :
                    if (colors[positionsX[0] - 1][positionsY[0] + 2] == Color.WHITE && colors[positionsX[0] + 1][positionsY[0] + 2] == Color.WHITE && colors[positionsX[0] + 2][positionsY[0] + 2] == Color.WHITE) {
                        positionsX[0] = positionsX[0] - 1;
                        positionsX[2] = positionsX[2] + 1;
                        positionsX[3] = positionsX[3] + 2;
                        positionsY[0] = positionsY[0] + 2;
                        positionsY[1] = positionsY[1] + 1;
                        positionsY[3] = positionsY[3] - 1;
                        rotate = 2;
                        lookUnder = lookLeft;
                        lookLeft = lookup;
                        lookup = lookRight;
                        lookRight = temp;
                    }
                    break;
                case 2 :
                    if (colors[positionsX[0] + 1][positionsY[0] - 2] == Color.WHITE && colors[positionsX[0] + 1][positionsY[0] -1] == Color.WHITE && colors[positionsX[0] + 1][positionsY[0] + 1] == Color.WHITE) {
                        positionsX[0] = positionsX[0] + 1;
                        positionsX[2] = positionsX[2] - 1;
                        positionsX[3] = positionsX[3] - 2;
                        positionsY[0] = positionsY[0] - 2;
                        positionsY[1] = positionsY[1] - 1;
                        positionsY[3] = positionsY[3] + 1;
                        rotate = 1;
                        lookUnder = lookRight;
                        lookRight = lookup;
                        lookup = lookLeft;
                        lookLeft = temp;
                    }
                    break;
            }
        }
        if (color == Color.GREEN){
            switch (rotate){
                case 1 :
                    if (colors[positionsX[0]][positionsY[0] + 2] == Color.WHITE) {
                        positionsX[0] = positionsX[0] + 1;
                        positionsX[1] = positionsX[1] + 1;
                        positionsX[3] = positionsX[3] - 1;
                        positionsY[0] = positionsY[0] + 1;
                        positionsY[1] = positionsY[1] - 1;
                        positionsY[3] = positionsY[3] + 1;
                        rotate = 2;
                        lookUnder = lookRight;
                        lookRight = lookup;
                        lookup = lookLeft;
                        lookLeft = temp;
                    }
                    break;
                case 2 :
                    if (colors[positionsX[0]][positionsY[0] - 1] == Color.WHITE && colors[positionsX[0] - 2][positionsY[0] - 1] == Color.WHITE) {
                        positionsX[0] = positionsX[0] - 1;
                        positionsX[1] = positionsX[1] + 1;
                        positionsX[3] = positionsX[3] - 1;
                        positionsY[2] = positionsY[2] - 1;
                        positionsY[3] = positionsY[3] - 2;
                        rotate = 3;
                        lookUnder = lookRight;
                        lookRight = lookup;
                        lookup = lookLeft;
                        lookLeft = temp;
                    }
                    break;
                case 3 :
                    if (colors[positionsX[0] - 1][positionsY[0]] == Color.WHITE && colors[positionsX[0]][positionsY[0] - 1] == Color.WHITE) {
                        positionsX[0] = positionsX[0] - 1;
                        positionsX[1] = positionsX[1] - 1;
                        positionsX[3] = positionsX[3] + 1;
                        positionsY[1] = positionsY[1] + 2;
                        positionsY[2] = positionsY[2] + 1;
                        rotate = 4;
                        lookUnder = lookRight;
                        lookRight = lookup;
                        lookup = lookLeft;
                        lookLeft = temp;
                    }
                    break;
                case 4 :
                    if (colors[positionsX[0] + 2][positionsY[0]] == Color.WHITE) {
                        positionsX[0] = positionsX[0] + 1;
                        positionsX[1] = positionsX[1] - 1;
                        positionsX[3] = positionsX[3] + 1;
                        positionsY[0] = positionsY[0] - 1;
                        positionsY[1] = positionsY[1] - 1;
                        positionsY[3] = positionsY[3] + 1;
                        rotate = 1;
                        lookUnder = lookRight;
                        lookRight = lookup;
                        lookup = lookLeft;
                        lookLeft = temp;
                    }
                    break;
            }
        }
    }

    void movePosX(int posX) {
        for ( int i = 0 ; i < 4 ; i++) {
            positionsX[i] = positionsX[i] + posX;
        }
    }

    void movePosY(int posY) {
        for ( int i = 0 ; i < 4 ; i++){
            positionsY[i] = positionsY[i] + posY;
        }
    }
}
