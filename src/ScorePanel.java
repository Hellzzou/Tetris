import javax.swing.*;
import java.awt.*;

class ScorePanel extends JPanel {
    ScorePanel(){
        JLabel bestScores = new JLabel("Meilleurs Scores");
        JTextField top5 = new JTextField();
        JPanel pan = new JPanel();
        top5.setPreferredSize(new Dimension(200,230));
        top5.setBorder(BorderFactory.createEtchedBorder(Color.BLACK,Color.WHITE));
        pan.add(top5);
        Font font = new Font("arrial", Font.BOLD, 20);
        bestScores.setFont(font);
        bestScores.setHorizontalAlignment(JLabel.CENTER);
        this.setLayout(new BorderLayout());
        this.add(bestScores, BorderLayout.NORTH);
        this.add(pan, BorderLayout.CENTER);

    }
}
