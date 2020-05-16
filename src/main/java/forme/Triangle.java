package forme;

public class Triangle extends Forme {

  private Point a;
  private Point b;
  private Point c;
  
  public Triangle(String n, Point a, Point b, Point c, String g) {
      super(n, g, "Triangle");
      this.a = new Point(a.getX(), a.getY());
      this.b = new Point(b.getX(), b.getY());
      this.c = new Point(c.getX(), c.getY());
      
  }

  @Override
  public void affiche() {
      String message = this.name + " = Triangle (a:(" + a.getX()+ ","+a.getY()+")"
                                      + "(b:(" + b.getX() +","+b.getY()+")"
                                      + "(c:(" + c.getX() +","+c.getY()+")";
      Afficher aff = new Afficher();
      aff.afficher(message);
  }
  @Override
  public void move(int x, int y) {
      this.a.move(x + this.a.getX(), y + this.a.getY());
      this.b.move(x + this.b.getX(), y + this.b.getY());
      this.c.move(x + this.c.getX(), y + this.c.getY());
      

  }
  
  public Point getA() {
      return this.a;
  }
  
  public Point getB() {
      return this.b;
  }
  
  public Point getC() {
      return this.c;
  }

}