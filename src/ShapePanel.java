import javax.swing.*;
import java.awt.*;

class ShapePanel extends JPanel {
    private NextShape nextShape = new NextShape();

    ShapePanel(){
        JLabel label = new JLabel("Bloc suivant");
        Font font = new Font("Arial",Font.BOLD,20);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(font);
        nextShape.setPreferredSize(new Dimension(180,180));
        nextShape.setBorder(BorderFactory.createEtchedBorder(Color.BLACK,Color.WHITE));
        this.add(label);
        this.add(nextShape);
    }

    NextShape getNextShape() {
        return nextShape;
    }
}
