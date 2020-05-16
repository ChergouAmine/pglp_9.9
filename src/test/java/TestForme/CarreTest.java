package TestForme;

import static org.junit.Assert.*;
import org.junit.Test;
import forme.Carre;
import forme.Point;

public class CarreTest {
  
  @Test
  public void moveTest() {
      Carre c = new Carre("c", 2, new Point(2,2),"groupe1");
      assertEquals(c.getPoint().getX(), 2);
      assertEquals(c.getPoint().getY(), 2);
      
      c.move(2, 2);
      
      assertEquals(c.getPoint().getX(), 4);
      assertEquals(c.getPoint().getY(), 4);
  }

}
