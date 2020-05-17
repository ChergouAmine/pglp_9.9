package TestForme;

import static org.junit.Assert.*;
import org.junit.Test;
import PatternCommand.Command;
import PatternCommand.DrawingTUI;
import forme.Carre;
import forme.Cercle;
import forme.Rectangle;
import forme.Triangle;

public class DeplacementTest {
  
  @Test
  public void CercleMoveTriangleMoveTest() {
      
      DrawingTUI drawing = new DrawingTUI();
      
      Command command = drawing.nextCommand("c cercle 1 1 1");
      command.execute();
      
      assertEquals(drawing.getforme().get(0).getNom(), "c");
      
      Cercle cer = (Cercle) drawing.getforme().get(0);
      command = drawing.nextCommand("move c 1 1");
      
      assertEquals(cer.getCentre().getX(), 1);
      assertEquals(cer.getCentre().getY(), 1);
      
      command.execute();
      
      assertEquals(cer.getCentre().getX(), 2);
      assertEquals(cer.getCentre().getY(), 2);
     
  }

  @Test
  public void CarreMoveTriangleMoveTest() {
      
      DrawingTUI drawing = new DrawingTUI();
      
      Command command = drawing.nextCommand("c carre 1 1 1");
      command.execute();
      
      assertEquals(drawing.getforme().get(0).getNom(), "c");
      
      Carre car = (Carre) drawing.getforme().get(0);
      
      command = drawing.nextCommand("move c 1 1");
      
      assertEquals(car.getPoint().getX(), 1);
      assertEquals(car.getPoint().getY(), 1);
      
      command.execute();
      
      assertEquals(car.getPoint().getX(), 2);
      assertEquals(car.getPoint().getY(), 2);
  }
  

  @Test
  public void RectangleMoveTriangleMoveTest() {
      DrawingTUI drawing = new DrawingTUI();
      Command command = drawing.nextCommand("r rectangle 1 1 5 2");
      command.execute();
      Rectangle rec = (Rectangle) drawing.getforme().get(0);
      command = drawing.nextCommand("move r 1 1");
      
      assertEquals(rec.getPoint().getX(), 1);
      assertEquals(rec.getPoint().getY(), 1);
      
      command.execute();
      
      assertEquals(rec.getPoint().getX(), 2);
      assertEquals(rec.getPoint().getY(), 2);
           
  }
  
  @Test
  public void TriangleMoveTest() {
      
      DrawingTUI drawing = new DrawingTUI();
      Command command = drawing.nextCommand("t triangle 0 0 1 1 2 2");
      command.execute();
      Triangle tri = (Triangle) drawing.getforme().get(0);
      command = drawing.nextCommand("move t 1 1");
      
      assertEquals(tri.getA().getX(), 0);
      assertEquals(tri.getA().getY(), 0);
      
      command.execute();
      
      assertEquals(tri.getA().getX(), 1);
      assertEquals(tri.getA().getY(), 1);
      
  }
  
  
  @Test
  public void groupeMovement() {
      DrawingTUI drawing = new DrawingTUI();
      
      Command command = drawing.nextCommand("r rectangle 1 1 5 2");
      command.execute();

      command = drawing.nextCommand("t triangle 1 1 2 2 3 3");
      command.execute();
      
      command = drawing.nextCommand("c cercle 1 1 1");
      command.execute();
      
      
      command = drawing.nextCommand("moveall r t c 1 1");
      
      
      Rectangle r = (Rectangle) drawing.getforme().get(0);
      Triangle t = (Triangle) drawing.getforme().get(1);
      Cercle c = (Cercle) drawing.getforme().get(2);
      
      
      assertEquals(r.getPoint().getX(), 1);
      assertEquals(r.getPoint().getY(), 1);
      assertEquals(t.getA().getX(), 1);
      assertEquals(t.getA().getY(), 1);
      assertEquals(c.getCentre().getX(), 1);
      assertEquals(c.getCentre().getY(), 1);

      command.execute();
      
      assertEquals(r.getPoint().getX(), 2);
      assertEquals(r.getPoint().getY(), 2);
      assertEquals(t.getA().getX(), 3);
      assertEquals(t.getA().getY(), 3);
      assertEquals(c.getCentre().getX(), 2);
      assertEquals(c.getCentre().getY(), 2);
      
      
  }
}
