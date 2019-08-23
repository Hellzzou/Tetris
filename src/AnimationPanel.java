import javax.swing.*;
import java.awt.*;

class AnimationPanel extends JPanel{
    private GamePanel gamePanel;
    private JLabel scoreLabel = new JLabel("Score : 0");
    private JLabel levelLabel = new JLabel("Level : 1");
    private Button pause = new Button("Pause", Color.darkGray);
    private Button newGame = new Button("Nouveau", Color.darkGray);
    private JPanel buttonPanel = new JPanel();
    private int score = 0;
    private int level = 1;
    private int sleep = 300;

    AnimationPanel(Window window){
        JPanel center = new JPanel();
        gamePanel = new GamePanel(window);
        gamePanel.addKeyListener(new KeyBoardListener(window));
        gamePanel.setPreferredSize(new Dimension(240,440));
        gamePanel.setBorder(BorderFactory.createEtchedBorder(Color.BLACK,Color.WHITE));
        this.setLayout(new BorderLayout());
        initButton(window);
        center.add(gamePanel);
        Font font = new Font("arrial", Font.BOLD, 20);
        scoreLabel.setFont(font);
        scoreLabel.addKeyListener(new KeyBoardListener(window));
        levelLabel.setFont(font);
        levelLabel.addKeyListener(new KeyBoardListener(window));
        JPanel labelPanel = new JPanel();
        labelPanel.add(scoreLabel);
        labelPanel.add(levelLabel);
            labelPanel.addKeyListener(new KeyBoardListener(window));
        this.add(labelPanel, BorderLayout.NORTH);
        this.add(center, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }
    private void initButton(Window window){
        newGame.setPreferredSize(new Dimension(120,40));
        newGame.addKeyListener(new KeyBoardListener(window));
        newGame.addActionListener(e->{
                int option = JOptionPane.showConfirmDialog(null, "Etes-vous sur de vouloir recommencer?"
                        , "Recommencer", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if ( option == JOptionPane.OK_OPTION) {
                    gamePanel.setLost(false);
                    newGame.setName("");
                    newGame.setColor(Color.LIGHT_GRAY);
                    newGame.setEnabled(false);
                    pause.setColor(Color.orange);
                    pause.setName("Pause");
                    pause.setEnabled(true);
                    window.setGo(true);
                    window.setFirst(true);
                    Thread t = new Thread(new PlayTetris(window));
                    t.start();
                }
            });
        buttonPanel.add(newGame);
        pause.setPreferredSize(new Dimension(120,40));
        pause.addKeyListener(new KeyBoardListener(window));
        pause.setName("");
        pause.setColor(Color.LIGHT_GRAY);
        pause.setEnabled(false);
        pause.addActionListener(e->{
            if ( window.isGo()) {
                window.setGo(false);
                newGame.setEnabled(true);
                newGame.setName("Nouveau");
                newGame.setColor(Color.GREEN);
                pause.setColor(Color.GREEN);
                pause.setName("Start");
                pause.repaint();
            }
            else {
                window.setGo(true);
                newGame.setName("");
                newGame.setColor(Color.LIGHT_GRAY);
                newGame.setEnabled(false);
                pause.setColor(Color.ORANGE);
                pause.setName("Pause");
                pause.repaint();
                Thread t = new Thread(new PlayTetris(window));
                t.start();
            }
        });
        buttonPanel.add(pause);
        buttonPanel.addKeyListener(new KeyBoardListener(window));
    }

    GamePanel getGamePanel() {
        return gamePanel;
    }

    Button getNewGame() {
        return newGame;
    }

    Button getPause() { return pause; }

    public int getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }

    public int getSleep() {
        return sleep;
    }

    public void setSleep(int sleep) {
        this.sleep = sleep;
    }

    void setScore(int score) {
        this.score = score;
        scoreLabel.setText(" Level : " + score);
    }

    void setLevel(int level) {
        this.level = level;
        levelLabel.setText(" Level : " + level);

    }
}
