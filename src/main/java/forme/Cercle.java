package forme;

public class Cercle extends Forme {
  
  private int rayon;
  private Point centre;
  
  public Cercle(String n, int r, Point p, String g) {
      super(n, g, "Cercle");
      rayon = r; 
      centre = new Point(p.getX(), p.getY());
  
  }

  @Override
  public void affiche() {
      String message =  this.name+" = Cercle (Rayon=" + rayon +", centre=("
               + centre.getX()+ ","+centre.getY()+"))";
      Afficher aff = new Afficher();
      aff.afficher(message);
  }

  @Override
  public void move(int a, int b) {
      centre.move(a,b);

  }

  public int getRayon() {
      return rayon;
  }

  public Point getCentre() {
      return centre;
  }
  

}
