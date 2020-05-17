package TestForme;

import static org.junit.Assert.*;
import org.junit.Test;
import PatternCommand.Command;
import PatternCommand.DrawingTUI;

public class SuppressionTest {

  @Test
  public void deleteTest() {
      
      DrawingTUI drawing = new DrawingTUI();
      
      Command command = drawing.nextCommand("c cercle 1 1 1");
      command.execute();
      assertEquals(drawing.getforme().get(0).getNom(), "c");
      
      command = drawing.nextCommand("delete c");
      command.execute();
      assertTrue(drawing.getforme().isEmpty());
       
  }
  
  @Test
  public void deleteAllCommandTest() {
    DrawingTUI drawing = new DrawingTUI();
    
      Command command = drawing.nextCommand("c cercle 1 1 1");
      command.execute();
      
      command = drawing.nextCommand("c2 cercle 1 1 1");
      command.execute();
      
      command = drawing.nextCommand("deleteall c c2");
      command.execute();
      
      assertTrue(drawing.getforme().isEmpty());
  }

}
