package PatternCommand;

import forme.Forme;

public class CreateForme implements Command{

  protected Forme forme;
  
  @Override
  public void execute() {
  }
  
  public void setForm(Forme f){
      forme = f;
  }
}
