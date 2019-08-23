import javax.swing.*;
import java.awt.*;

class Window extends JFrame {
    private AnimationPanel animation;
    private RightPanel rightPanel = new RightPanel();
    private boolean go = false;
    private boolean first = true;

    Window(){
        this.setSize(550,560);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        animation = new AnimationPanel(this);
        animation.addKeyListener(new KeyBoardListener(this));
        rightPanel.addKeyListener(new KeyBoardListener(this));
        JSplitPane game = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, animation, rightPanel);
        game.setDividerLocation(300);
        game.setBackground(Color.BLACK);
        game.setDividerSize(7);
        this.setContentPane(game);
        this.setVisible(true);
    }

    boolean isGo() { return go; }

    boolean isFirst() { return first; }

    AnimationPanel getAnimation() { return animation; }

    void setGo(boolean go) { this.go = go; }

    void setFirst(boolean first) { this.first = first; }

    RightPanel getRightPanel() {
        return rightPanel;
    }
}
