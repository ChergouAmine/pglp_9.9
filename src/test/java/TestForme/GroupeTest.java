package TestForme;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Test;
import forme.Carre;
import forme.Cercle;
import forme.Forme;
import forme.GroupeForme;
import forme.Point;
import forme.Rectangle;
import forme.Triangle;

public class GroupeTest {

  @Test
  public void moveTest() {
      
      GroupeForme g = new GroupeForme("groupe1");
      
      
      Carre c = new Carre("c", 2, new Point(0,0),"groupe1");
      Rectangle r = new Rectangle("r", new Point(1,1), 2,3 ,"groupe1");
      Cercle cc = new Cercle("c", 2, new Point(1,1),"groupe1");
      
      g.addForme(c);
      g.addForme(r);
      g.addForme(cc);
      
      g.move(1, 1);
      Iterator<Forme> i = g.iterator();
      
      while(i.hasNext()) {
          Forme f = (Forme) i.next();
         
          if (f instanceof Cercle) {
              Point p = ((Cercle) f).getCentre();
              assertEquals(p.getX(), 2);
              assertEquals(p.getY(), 2);
          }
  
          
          if (f instanceof Carre) {
              Point p = ((Carre) f).getPoint();
              assertEquals(p.getX(), 1);
              assertEquals(p.getY(), 1);
          }
  
      }
      
  }
}