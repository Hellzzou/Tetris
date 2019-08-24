import java.io.Serializable;

class Joueur implements Serializable {
    private String name;
    private int score;
    private int lines;

    Joueur(String name, int score, int lines) {
        this.name = name;
        this.score = score;
        this.lines = lines;
    }
    String getName() {
        return name;
    }

    int getScore() {
        return score;
    }

    int getLines() {
        return lines;
    }
}
