import javax.swing.*;
import java.awt.*;

class ShapePanel extends JPanel {
    private NextShape nextShape = new NextShape();
    private JLabel label = new JLabel("Bloc suivant");

    ShapePanel(){
        this.initLabel();
        this.add(label);
        this.add(nextShape);
    }
    private void initLabel(){
        Font font = new Font("Arial",Font.BOLD,20);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(font);
    }
    NextShape getNextShape() {
        return nextShape;
    }
}
