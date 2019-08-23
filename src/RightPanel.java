import javax.swing.*;
import java.awt.*;

class RightPanel extends JPanel {
    private ShapePanel shapePanel = new ShapePanel();

    RightPanel(){
        ScorePanel scorePanel = new ScorePanel();
        JSplitPane rightPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, shapePanel, scorePanel);
        rightPanel.setDividerLocation(235);
        rightPanel.setBackground(Color.BLACK);
        rightPanel.setDividerSize(11);
        this.setLayout(new BorderLayout());
        this.add(rightPanel, BorderLayout.CENTER);
    }

    ShapePanel getShapePanel() {
        return shapePanel;
    }
}
