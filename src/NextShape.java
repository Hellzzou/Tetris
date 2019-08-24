import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class NextShape extends JPanel {
    private Bloc nextBloc;

    NextShape() {
        this.setPreferredSize(new Dimension(180,180));
        this.setBorder(BorderFactory.createEtchedBorder(Color.BLACK,Color.WHITE));
    }

    public void paintComponent(Graphics g){
        int shape = new Random().nextInt(7);
        nextBloc = new Bloc(shape,4,3);
        g.setColor(nextBloc.getColor());
        int[] positionX = nextBloc.getPositionsX();
        int[] positionY = nextBloc.getPositionsY();
        for ( int i = 0 ; i < 4 ; i++) {
            g.drawRect(positionX[i] * 20, positionY[i] * 20, 20, 20);
            g.fillRect(positionX[i] * 20 + 1, positionY[i] * 20 + 1, 18, 18);
        }
    }

    Bloc getNextBloc() {
        return nextBloc;
    }
}
