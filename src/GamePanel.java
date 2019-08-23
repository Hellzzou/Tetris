import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private Window window;
    private Bloc bloc;
    private Color[][] tab = new Color[12][22];
    private boolean lost = false;
    GamePanel(Window window){
        this.window = window;
        for(int i = 0 ; i < 12 ; i++){
            for( int j = 0 ; j < 22 ; j++){
                tab[i][j] = Color.WHITE;
            }
        }
    }
    public void paintComponent(Graphics g){
        for(int i = 0 ; i < 12 ; i++){
            for( int j = 0 ; j < 22 ; j++){
                g.setColor(tab[i][j]);
                g.drawRect(i * 20,j * 20,20,20);
                g.fillRect(i * 20 + 1,j* 20 + 1,18,18);
            }
        }
        if ( lost ) {
            g.setColor(Color.RED);
            Font font = new Font ("arrial", Font.BOLD, 40);
            g.setFont(font);
            g.drawString("PERDU", 50,250);
            window.getAnimation().getNewGame().setEnabled(true);
            window.getAnimation().getNewGame().setName("Nouveau");
            window.getAnimation().getNewGame().setColor(Color.GREEN);
            window.getAnimation().getNewGame().repaint();
            window.getAnimation().getPause().setEnabled(false);
            window.getAnimation().getPause().setName("");
            window.getAnimation().getPause().setColor(Color.LIGHT_GRAY);
            window.getAnimation().getPause().repaint();
        }
    }
    boolean nothingUnder(){
        int[] positionX = bloc.getPositionsX();
        int[] positionY = bloc.getPositionsY();
        int[] lookUnder = bloc.getLookUnder();
        boolean under = true;
        Color[][] colors = tab;
        for ( int i = 0 ; i < 4 ; i++){
            if(positionY[i] == 21) return false;
            if (lookUnder[i] == 1){
                if (colors[positionX[i]][positionY[i] + 1] != Color.WHITE) under  = false;
            }
        }return under;
    }
    boolean nothingLeft(){
        int[] positionX = bloc.getPositionsX();
        int[] positionY = bloc.getPositionsY();
        int[] lookLeft = bloc.getLookLeft();
        boolean under = true;
        for ( int i = 0 ; i < 4 ; i++){
            if(positionX[i] == 0) return false;
            if (lookLeft[i] == 1){
                if (tab[positionX[i] - 1][positionY[i]] != Color.WHITE) under  = false;
            }
        }return under;
    }
    boolean nothingRight(){
        int[] positionX = bloc.getPositionsX();
        int[] positionY = bloc.getPositionsY();
        int[] lookRight = bloc.getLookRight();
        boolean under = true;
        for ( int i = 0 ; i < 4 ; i++){
            if(positionX[i] == 11) return false;
            if (lookRight[i] == 1){
                if (tab[positionX[i] + 1][positionY[i]] != Color.WHITE) under  = false;
            }
        }return under;
    }

    boolean lost(){
        int[] positionX = bloc.getPositionsX();
        int[] positionY = bloc.getPositionsY();
        for ( int i = 0 ; i < 4 ; i++){
            if (tab[positionX[i]][positionY[i]] != Color.WHITE) return true;
        }
        return false;
    }
    int countCompleteLines(){
        int lines = 0;
        for (int i = 21; i >= 0; i--) {
            int counter = 0;
            for (int j = 0; j < 12; j++) {
                if (tab[j][i] != Color.WHITE) counter ++;
            }
            if (counter == 12) {
                lines ++;
                eraseCompleteLines(i);
                i++;
            }
        }
        return lines;
    }
    private void eraseCompleteLines(int line){
        for ( int i = line ; i >= 0 ; i--){
            for ( int j = 0 ; j < 12 ; j++){
                if ( i > 0 ) {
                    tab[j][i] = tab[j][i - 1];
                }
                else tab[j][i] = Color.WHITE;
            }
        }
    }
    int scoreUpdate(int lines){
        switch (lines){
            case 1 :
                return 100;
            case 2 :
                return 300;
            case 3 :
                return 800;
            case 4 :
                return 2000;
            default :
                return 0;
        }
    }
    void drawShape(){
        int[] positionX = bloc.getPositionsX();
        int[] positionY = bloc.getPositionsY();
        for ( int i = 0 ; i < 4 ; i++){
            tab[positionX[i]][positionY[i]] = bloc.getColor();
        }
        repaint();
    }
    void lowerShape(){
        int[] positionX = bloc.getPositionsX();
        int[] positionY = bloc.getPositionsY();
        for ( int i = 0 ; i < 4 ; i++){
            tab[positionX[i]][positionY[i]] = Color.WHITE;
        }
        bloc.movePosY(1);
    }
    void moveLeft(){
        int[] positionX = bloc.getPositionsX();
        int[] positionY = bloc.getPositionsY();

        for ( int i = 0 ; i < 4 ; i++){
            tab[positionX[i]][positionY[i]] = Color.WHITE;
        }
        bloc.movePosX(-1);
        drawShape();
    }
    void moveRight(){
        int[] positionX = bloc.getPositionsX();
        int[] positionY = bloc.getPositionsY();
        for ( int i = 0 ; i < 4 ; i++){
            tab[positionX[i]][positionY[i]] = Color.WHITE;
        }
        bloc.movePosX(1);
        drawShape();
    }
    void nextBloc(){
        bloc = window.getRightPanel().getShapePanel().getNextShape().getNextBloc();
        bloc.movePosX(1);
        bloc.movePosY(-3);
        window.getRightPanel().getShapePanel().getNextShape().repaint();
    }
    void rotate(){
        int[] positionX = bloc.getPositionsX();
        int[] positionY = bloc.getPositionsY();
        for ( int i = 0 ; i < 4 ; i++){
            tab[positionX[i]][positionY[i]] = Color.WHITE;
        }
        bloc.rotateBloc(this);
        drawShape();
    }

    Color[][] getTab() {
        return tab;
    }

    void setLost(boolean lost) { this.lost = lost; }

}
