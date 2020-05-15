package forme;

public class Carre extends Forme {
  
  private Point point;
  int cote;
  
  public Carre(String n, int c, Point p, String g) {
      super(n, g, "Carre");
      point = new Point(p.getX(), p.getY());
      cote = c; 
  }

  @Override
  public void affiche() {
      String message = this.name+" = Carre(cote:"+cote+", centre("
               +point.getX()+","+point.getY()+"))";
      
      Afficher aff = new Afficher();
      aff.afficher(message);
  }

  @Override
  public void move(int a, int b) {
      this.point.move(a,b);

  }

  public int getCote() {
      return this.cote;
  }

  public Point getPoint() {
      return this.point;
  }

}
