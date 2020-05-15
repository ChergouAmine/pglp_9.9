package forme;

public class Rectangle extends Forme {
  private Point point;
  private int height;
  private int width;
  
  public Rectangle(String n, Point p, int h, int w, String g) {
      super(n, g, "Rectangle");
      point = new Point(p.getX(), p.getY());
      height = h;
      width = w;
  }

  @Override
  public void affiche() {
      String message = this.name + " = Rectangle ((h=" + height +",w="+ width + "), centre("
                       +point.getX()+","+point.getY()+"))";
      Afficher aff = new Afficher();
      aff.afficher(message);       
  }

  @Override
  public void move(int a, int b) {
      this.point.move(a,b);
  }
  
  public int getHeight() {
      return this.height;
  }
  
  public int getWidth() {
      return this.width;
  }
  
  public Point getPoint() {
      return this.point;
  }

}