package TestForme;

import static org.junit.Assert.*;
import org.junit.Test;
import PatternCommand.Command;
import PatternCommand.DrawingTUI;

public class CreationTest {
  
  @Test
  public void carreCreationTest() {
      
      DrawingTUI drawing = new DrawingTUI();
      Command command = drawing.nextCommand("c carre 1 1 1");
      command.execute();
      assertEquals(drawing.getforme().get(0).getNom(), "c"); 
  }
  
  @Test
  public void cercleCreationTest() {
      
    DrawingTUI drawing = new DrawingTUI();
      Command command = drawing.nextCommand("c cercle 1 1 1");
      command.execute();
      assertEquals(drawing.getforme().get(0).getNom(), "c");
  }
  
  @Test
  public void triangleCreationTest() {
      
    DrawingTUI drawing = new DrawingTUI();
      Command command = drawing.nextCommand("t triangle 1 1 2 2 3 3");
      command.execute();
      assertEquals(drawing.getforme().get(0).getNom(), "t");
  }
  

  @Test
  public void rectangleCreationTest() {
    DrawingTUI drawing = new DrawingTUI();
      Command command = drawing.nextCommand("r rectangle 1 1 2 2");
      command.execute();
      assertEquals(drawing.getforme().get(0).getNom(), "r");   
  }

}
