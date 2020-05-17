package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import forme.Carre;
import forme.Cercle;
import forme.Forme;
import forme.Rectangle;
import forme.Triangle;
import forme.GroupeForme;

public class GroupeFormeDao extends Dao<GroupeForme> {

  @Override
  public GroupeForme create(GroupeForme g) {

    int i = -1;
    this.connect();
    PreparedStatement insert = null;
    
    try {
      
      insert = this.connect.prepareStatement("insert into Groupe(groupeId) values (?)");
      
      insert.setString(1, g.getNom());
      i = insert.executeUpdate();
      
      DaoFactory df = new DaoFactory();
      Dao<Carre> daoCar = df.createCarreDao();
      Dao<Cercle> daoCercle = df.createCercleDao();
      Dao<Rectangle> daoRectangle = df.createRectangleDao();
      Dao<Triangle> daoTriangle = df.createTriangleDao();
      
      Iterator<Forme> it = g.iterator();
      
      while (it.hasNext()) {
        
          Forme f = it.next();
          
          if ( f instanceof Carre) {
              daoCar.create((Carre) f);
          }
          
          if ( f instanceof Cercle) {
              daoCercle.create((Cercle) f);
          }
          
          if ( f instanceof Rectangle) {
              daoRectangle.create((Rectangle) f);
          }
          
          if ( f instanceof Triangle) {
            daoTriangle.create((Triangle) f);
          }
          
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    try {
      if (insert != null) {
        insert.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    if (i > 0) {
      return g;
    } else {
      return null;
    }
  }

  @Override
  public GroupeForme find(String id) {

    this.connect();
    GroupeForme g = null;
    PreparedStatement select = null;
    
    try {
      
      select = connect.prepareStatement("select * from groupe where groupeId = (?)");
      select.setString(1, id);
      select.execute();
     
      DaoFactory df = new DaoFactory();
      Dao<Carre> daoCar = df.createCarreDao();
      Dao<Cercle> daoCercle = df.createCercleDao();
      Dao<Triangle> daoTriangle = df.createTriangleDao();
      Dao<Rectangle> daoRectangle = df.createRectangleDao();
      
      ResultSet result = select.getResultSet();
      
      if (result.next()) {
          g = new GroupeForme(result.getString(1));
          
          ArrayList<Carre> listCarre = daoCar.getGroupeObject(g.getNom());
          ArrayList<Cercle> listCercle = daoCercle.getGroupeObject(g.getNom());
          ArrayList<Triangle> listTriangle = daoTriangle.getGroupeObject(g.getNom());
          ArrayList<Rectangle> listRectangle = daoRectangle.getGroupeObject(g.getNom());
          
          for (Carre c: listCarre) {
            g.addForme(c);
          }
          
          for (Rectangle r: listRectangle) {
              g.addForme(r);
          }
          
          for (Cercle c: listCercle) {
              g.addForme(c);
          }
          
          for (Triangle t: listTriangle) {
              g.addForme(t);
          }
          
        }

      
    } catch (SQLException e) {
      e.printStackTrace();
    }
    try {
      if (select != null) {
        select.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    return g;
  }

  @Override
  public GroupeForme update(GroupeForme g) {
    
    this.connect();
    PreparedStatement updateGroupe = null;
    
    try {
      updateGroupe = connect.prepareStatement("update Groupe set groupeId = (?)");
      
      updateGroupe.setString(1, g.getNom());
      
      DaoFactory df = new DaoFactory();
      Dao<Carre> daoCarre = df.createCarreDao();
      Dao<Cercle> daoCercle = df.createCercleDao();
      Dao<Rectangle> daoRectangle = df.createRectangleDao();
      Dao<Triangle> daoTriangle = df.createTriangleDao();
      
      Iterator<Forme> it = g.iterator();
      
      while (it.hasNext()) {
          
        Forme f = it.next();
        
        if (f instanceof Carre) {         
          if (daoCarre.find(((Carre) f).getNom()) != null) {
              daoCarre.update((Carre) f);
          } else {
              daoCarre.create((Carre) f);
          }
        }
        
        if (f instanceof Cercle) {           
          if (daoCercle.find(((Cercle) f).getNom()) != null) {
            daoCercle.update((Cercle) f);
          } else {
            daoCercle.create((Cercle) f);
          }
        }
        
        if (f instanceof Rectangle) {           
            if (daoRectangle.find(((Rectangle) f).getNom()) != null) {
                daoRectangle.update((Rectangle) f);
            } else {
                daoRectangle.create((Rectangle) f);
            }
          }
        
        if (f instanceof Triangle) {          
            if (daoTriangle.find(((Triangle) f).getNom()) != null) {
                daoTriangle.update((Triangle) f);
            } else {
                daoTriangle.create((Triangle) f);
            }
          }
        
        
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } 
    try {
      if (updateGroupe != null) {
        updateGroupe.close();
      } 
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    
    return g;
  }

  @Override
  public void delete(GroupeForme g) {
    
    this.connect();
    PreparedStatement delete = null;
    
    try {
      Iterator<Forme> it = g.iterator();
      
      while (it.hasNext()) {
          it.next();

      }
      
      String groupeId = g.getNom();
      delete = this.connect.prepareStatement("Delete from groupe where groupeId = (?)");
      
      delete.setString(1, groupeId);
      delete.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    try {
      if (delete != null) {
        delete.close();
      }
     
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

@Override
public ArrayList<GroupeForme> getGroupeObject(String id) {//retourner tout les groupesdes groupes n'a aucun sens
    return null;
}

}
