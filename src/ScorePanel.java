import javax.swing.*;
import java.awt.*;

class ScorePanel extends JPanel {
    private Top5 top5 = new Top5();
    private JLabel bestScores = new JLabel("Meilleurs Scores");
    private JPanel pan = new JPanel();

    ScorePanel(){
        this.initScorePanel();
        this.setLayout(new BorderLayout());
        this.add(bestScores, BorderLayout.NORTH);
        this.add(pan, BorderLayout.CENTER);

    }
    private void initScorePanel(){
        pan.add(top5);
        Font font = new Font("arrial", Font.BOLD, 20);
        bestScores.setFont(font);
        bestScores.setHorizontalAlignment(JLabel.CENTER);
    }

    Top5 getTop5() {
        return top5;
    }
}
