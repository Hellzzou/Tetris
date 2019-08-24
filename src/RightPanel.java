import javax.swing.*;
import java.awt.*;

class RightPanel extends JPanel {
    private ShapePanel shapePanel = new ShapePanel();
    private ScorePanel scorePanel = new ScorePanel();
    private JSplitPane rightPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, shapePanel, scorePanel);

    RightPanel(Window window){
        this.initSplitPane();
        this.addKeyListener(new KeyBoardListener(window));
        this.setLayout(new BorderLayout());
        this.add(rightPanel, BorderLayout.CENTER);
    }
    private void initSplitPane(){
        rightPanel.setDividerLocation(235);
        rightPanel.setBackground(Color.BLACK);
        rightPanel.setDividerSize(11);
    }

    ShapePanel getShapePanel() {
        return shapePanel;
    }

    ScorePanel getScorePanel() { return scorePanel; }
}
