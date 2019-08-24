import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Top5 extends JPanel {

    Top5(){
        this.setPreferredSize(new Dimension(200,230));
        this.setBorder(BorderFactory.createEtchedBorder(Color.BLACK,Color.WHITE));
    }

    public void paintComponent(Graphics g){
        int counter = 0;
        FileReader filereader = new FileReader();
        List<Joueur> joueurs = filereader.getJoueurs();

        for ( Joueur joueur : joueurs){
            g.setColor(Color.BLACK);
            Font font = new Font("arrial", Font.BOLD , 15);
            g.setFont(font);
            g.drawString("Nom \t  Lignes \t  Score",10,25);
            g.drawLine(0,35,this.getWidth(),35);
            g.drawString(joueur.getName(),10,60 + 40 * counter);
            g.drawString(String.valueOf(joueur.getLines()),90,60 + 40 * counter);
            g.drawString(String.valueOf(joueur.getScore()),140,60 + 40 * counter);
            counter ++;
        }
    }
}
