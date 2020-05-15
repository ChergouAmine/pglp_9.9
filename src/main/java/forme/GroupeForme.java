package forme;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GroupeForme extends AbstractForme implements Iterable<Forme> {
  
  private List<Forme> formes;
  private String nom;
  
  public GroupeForme(String n) {
      formes = new ArrayList<Forme>();
      nom = n;
  }

  public Iterator<Forme> iterator() {
      return formes.iterator();
  }

  
  public void affiche() {
      for (Forme f : this.formes) {
          f.affiche();
      }
  }

  
  public void move(int a, int b) {
      for (Forme f : this.formes) {
          f.move(a,b);
      }
      
  }
  
  public String getNom() {
    return this.nom;
  }
  
  public void addForme(Forme f) {
      this.formes.add(f);
  } 
}
