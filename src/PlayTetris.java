import java.awt.*;

public class PlayTetris implements Runnable {
    private GamePanel gamepanel;
    private Window window;
    private Color[][] colors;

    PlayTetris(Window window) {
        this.gamepanel = window.getAnimation().getGamePanel();
        this.window = window;
        this.colors = window.getAnimation().getGamePanel().getTab();
    }
    @Override
    public void run() {
       if (window.isFirst()) {
            initNewGame();
        }
       while ( window.isGo()) {
           while (gamepanel.nothingUnder() && window.isGo()) {
                lowerTheBloc();
           }
           updateScoreLevelLinesBloc();
           if (window.isGo()) {
               checkLost();
               try {
                   Thread.sleep(window.getAnimation().getSleep() - ((window.getAnimation().getLevel() - 1) * 25));
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
       }
    }
    private void checkLost(){
        if (gamepanel.lost() && window.isGo()) {
            gamepanel.setLost(true);
            gamepanel.repaint();
            window.setGo(false);
        }
        gamepanel.drawShape();
    }
    private void initNewGame(){
        for(int i = 0 ; i < 12 ; i++){
            for( int j = 0 ; j < 22 ; j++){
                colors[i][j] = Color.WHITE;
            }
        }
        gamepanel.nextBloc();
        gamepanel.drawShape();
        window.setFirst(false);
        try {
            Thread.sleep(window.getAnimation().getSleep() - ((window.getAnimation().getLevel() - 1) * 25));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void lowerTheBloc(){
        gamepanel.lowerShape();
        gamepanel.drawShape();
        try {
            Thread.sleep(window.getAnimation().getSleep() - ((window.getAnimation().getLevel() - 1) * 25));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    private void updateScoreLevelLinesBloc(){
        int lines = gamepanel.countCompleteLines();
        window.getAnimation().setLines(window.getAnimation().getLines() + lines);
        window.getAnimation().setScore(window.getAnimation().getScore() + gamepanel.scoreUpdate(lines));
        window.getAnimation().setLevel(window.getAnimation().getLines() / 10 + 1);
        window.getAnimation().repaint();
        gamepanel.nextBloc();
    }
}
