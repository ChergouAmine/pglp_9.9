package TestForme;

import static org.junit.Assert.*;
import org.junit.Test;
import forme.Rectangle;
import forme.Point;

public class RectangleTest {
  @Test
  public void moveTest() {
      
      Rectangle r = new Rectangle("r", new Point(2,2), 4,8 ,"groupe1");
      assertEquals(r.getPoint().getX(), 2);
      assertEquals(r.getPoint().getY(), 2);
      
      r.move(2, 2);
      
      assertEquals(r.getPoint().getX(), 4);
      assertEquals(r.getPoint().getY(), 4);
  }

}
