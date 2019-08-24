import javax.swing.*;
import java.awt.*;

class AnimationPanel extends JPanel{
    private Window window;
    private GamePanel gamePanel;
    private JPanel labelPanel = new JPanel();
    private JLabel scoreLabel = new JLabel("Score : 0");
    private JLabel levelLabel = new JLabel("Level : 1");
    private JLabel linesLabel = new JLabel("Lignes : 0");
    private Button pause;
    private Button newGame;
    private JPanel buttonPanel = new JPanel();
    private int score = 0;
    private int level = 1;
    private int sleep = 300;
    private int lines = 0;

    AnimationPanel(Window window){
        this.window = window;
        this.addKeyListener(new KeyBoardListener(window));
        gamePanel = new GamePanel(window);
        this.setLayout(new BorderLayout());
        JPanel center = new JPanel();
        center.add(gamePanel);
        initButton(window);
        initLabels();
        this.add(labelPanel, BorderLayout.NORTH);
        this.add(center, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }
    private void initButton(Window window){
        newGame = new Button("Nouveau", Color.darkGray,window);
        newGame.addActionListener(e->{
                int option = JOptionPane.showConfirmDialog(null, "Etes-vous sur de vouloir recommencer?"
                        , "Recommencer", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if ( option == JOptionPane.OK_OPTION) {
                    displayWindowBegining();
                }
            });
        buttonPanel.add(newGame);
        pause = new Button("Pause", Color.darkGray,window);
        pause.setName("");
        pause.setColor(Color.LIGHT_GRAY);
        pause.setEnabled(false);
        pause.addActionListener(e->{
            managePauseButton();
        });
        buttonPanel.add(pause);
        buttonPanel.addKeyListener(new KeyBoardListener(window));
    }
    private void initLabels(){
        Font font = new Font("arrial", Font.BOLD, 15);
        scoreLabel.setFont(font);
        scoreLabel.addKeyListener(new KeyBoardListener(window));
        levelLabel.setFont(font);
        levelLabel.addKeyListener(new KeyBoardListener(window));
        linesLabel.setFont(font);
        linesLabel.addKeyListener(new KeyBoardListener(window));
        labelPanel.add(scoreLabel);
        labelPanel.add(levelLabel);
        labelPanel.add(linesLabel);
        labelPanel.addKeyListener(new KeyBoardListener(window));
    }
    private void displayWindowBegining(){
        window.getAnimation().setLevel(1);
        window.getAnimation().setLines(0);
        window.getAnimation().setScore(0);
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
    private void managePauseButton(){
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
    }

    GamePanel getGamePanel() {
        return gamePanel;
    }

    Button getNewGame() {
        return newGame;
    }

    Button getPause() { return pause; }

    int getScore() {
        return score;
    }

    int getLevel() {
        return level;
    }

    int getSleep() {
        return sleep;
    }

    int getLines() { return lines; }

    void setSleep(int sleep) { this.sleep = sleep; }

    void setScore(int score) {
        this.score = score;
        scoreLabel.setText(" Score : " + score); }

    void setLevel(int level) {
        this.level = level;
        levelLabel.setText(" Level : " + level);
    }
    void setLines(int lines) {
        this.lines = lines;
        linesLabel.setText(" Lignes : " + lines);
    }

}
