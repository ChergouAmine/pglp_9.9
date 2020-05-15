package forme;

public abstract class AbstractForme {
  
  protected String name;
  public abstract void affiche(); 
  public abstract void move(int x, int y);
  public String getNom() {return this.name;}
}
