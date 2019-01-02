package arrowgame.src;

import java.awt.geom.AffineTransform;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.lang.Math;
import javax.swing.JPanel;

public class Arrow{
  private Vector pos, vel; //pos === position of the tip
  private final float LEN = 60, ANGLE_DELTA = (float)Math.PI / 36;
  private BufferedImage image = null;
  private boolean isHovered;

  public Arrow(Vector p){
    pos = p.copy();
    vel = Vector.zero();
    try{
      image = ImageIO.read(new File("arrow.png"));
    }catch(IOException e){
      e.printStackTrace();
    }
    isHovered = false;
  }

  public Arrow(Vector p, Vector v){
    pos = p.copy();
    vel = v.copy();
    try{
      image = ImageIO.read(new File("arrow.png"));
    }catch(IOException e){
      e.printStackTrace();
    }
    isHovered = false;
  }

  public void update(){
    if(isHovered){
      vel.rotate(ANGLE_DELTA);
    }else{
      pos.add(vel);
    }
  }

  public void draw(Graphics2D g2d, Game g){
    AffineTransform identity = new AffineTransform();
    AffineTransform trans = new AffineTransform();
    trans.setTransform(identity);
    int[] loc = pos.toIntArray();
    trans.translate(loc[0], loc[1]);
    trans.rotate(vel.heading());
    float scaleFactor = LEN / image.getHeight();
    trans.scale(scaleFactor, scaleFactor);
    g2d.drawImage(image, trans, g);
    System.out.println("pos = " + pos);
    System.out.println("vel = " + vel);
    System.out.println("vel.heading() = " + vel.heading());
  }
}