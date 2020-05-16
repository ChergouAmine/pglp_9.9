package TestForme;

import static org.junit.Assert.*;
import org.junit.Test;
import forme.Cercle;
import forme.Point;

public class CercleTest {
  
  @Test
  public void moveTest() {
      Cercle c = new Cercle("c", 2, new Point(2,2),"groupe1");
      assertEquals(c.getCentre().getX(), 2);
      assertEquals(c.getCentre().getY(), 2);
      
      c.move(2, 2);
      
      assertEquals(c.getCentre().getX(), 4);
      assertEquals(c.getCentre().getY(), 4);
  }

}
