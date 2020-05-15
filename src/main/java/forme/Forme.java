package forme;

public abstract class Forme extends AbstractForme{
  

  protected String groupeId;
  protected String type;
  public Forme(String n, String g, String t) {
    name = n;
    groupeId = g;  
    type = t;
  }
  
  public abstract void affiche();
  

  public abstract void move(int x, int y);

  public String getNom() {
      return name;
  }
  
  public String getGroupeId() {
      return groupeId;
  }
  public String getType() {
      return type;
  }
  
  public void setGroupeid(String g) {
      groupeId = g;
      
  }

}
