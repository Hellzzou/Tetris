import java.awt.*;

public class PlayTetris implements Runnable {
    private GamePanel gamepanel;
    private Window window;
    private Color[][] colors;

    PlayTetris(Window window) {
        this.gamepanel = window.getAnimation().getGamePanel();
        this.window = window;
        colors = window.getAnimation().getGamePanel().getTab();
    }
    @Override
    public void run() {
       if (window.isFirst()) {
           for(int i = 0 ; i < 12 ; i++){
               for( int j = 0 ; j < 22 ; j++){
                   colors[i][j] = Color.WHITE;
               }
           }
           gamepanel.nextBloc();
           gamepanel.drawShape();
           window.setFirst(false);
        }
       while ( window.isGo()) {
           while (gamepanel.nothingUnder() && window.isGo()) {
               gamepanel.lowerShape();
               gamepanel.drawShape();
               try {
                   Thread.sleep(window.getAnimation().getSleep());
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
           if (window.isGo()) {
               int lines = gamepanel.countCompleteLines();
               window.getAnimation().setScore(window.getAnimation().getScore() + gamepanel.scoreUpdate(lines));
               window.getAnimation().setLevel(window.getAnimation().getScore() / 5000 + 1);
               window.getAnimation().setSleep(300 - ((window.getAnimation().getLevel() - 1) * 25));
               window.getAnimation().repaint();

               gamepanel.nextBloc();
               if (gamepanel.lost() && window.isGo()) {
                   gamepanel.setLost(true);
                   gamepanel.repaint();
                   window.setGo(false);
               }
               gamepanel.drawShape();
               try {
                   Thread.sleep(window.getAnimation().getSleep());
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
       }
       if ( gamepanel.lost() && window.isGo() ){
           gamepanel.setLost(true);
           gamepanel.repaint();
           window.setGo(false);
       }
    }
}
