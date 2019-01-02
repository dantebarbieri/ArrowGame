import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Game extends JPanel {
  int width, height;
  private Arrow a;

  public Game(int w, int h){
    width = w;
    height = h;
    a = new Arrow(new Vector(width / 2, height / 2), Vector.random2D(1));
  }

  private void doDrawing(Graphics g){
    Graphics2D g2d = (Graphics2D) g;
    a.draw(g2d, this);
  }

  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    doDrawing(g);
  }
}

public class Driver extends JFrame{
  private static final int width = 900, height = 600;

  public Driver(){
    initUI();
  }

  private void initUI(){

    setTitle("Simple Java 2D example");
    setSize(width, height);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    add(new Game(width, height));
  }

  public static void main(String[] args){
    EventQueue.invokeLater(new Runnable() {
      @Override
      public void run(){
        Driver d = new Driver();
        d.setVisible(true);
      }
    });
  }
}