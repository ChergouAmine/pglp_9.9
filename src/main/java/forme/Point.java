package forme;

public class Point {
  
  private int x;
  private int y;
  
  public Point(int x, int y) {
      this.x = x;
      this.y = y;
  }
  
  public void move (int a, int b) {
    System.out.println(a+" "+b);
      this.x = x+a;
      this.y = y+b;
  }
  
  public int getX() {
      return x;
  }
  
  public int getY() {
      return y;
  }
}
