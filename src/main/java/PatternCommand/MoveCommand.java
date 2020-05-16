package PatternCommand;

import forme.AbstractForme;

public abstract class MoveCommand implements Command{
  
  protected AbstractForme forme;
  protected int x;
  protected int y;
  
  public void setParameters(int a, int b) {
       x = a;
       y = b;
  }
  @Override
  public void execute() {
  }
  
  public void setForm(AbstractForme f) {
    forme = f;
  }
}
