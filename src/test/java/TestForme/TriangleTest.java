package TestForme;

import static org.junit.Assert.*;
import org.junit.Test;
import forme.Triangle;
import forme.Point;


public class TriangleTest {
  
  @Test
  public void moveTest() {
      Triangle t = new Triangle("t", new Point(0,0),new Point(1,1), new Point(2,2), "aucun");
      
      assertEquals(t.getA().getX(), 0);
      assertEquals(t.getA().getY(), 0);
      
      assertEquals(t.getB().getX(), 1);
      assertEquals(t.getB().getY(), 1);
      
      assertEquals(t.getC().getX(), 2);
      assertEquals(t.getC().getY(), 2);
      
      t.move(1, 1);
      
      assertEquals(t.getA().getX(), 1);
      assertEquals(t.getA().getY(), 1);
      
      
      
  }

}
