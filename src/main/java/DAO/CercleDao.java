package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import forme.Cercle;
import forme.Point;


public class CercleDao extends Dao<Cercle> {

  @Override
  public Cercle create(Cercle c) {
    
    this.connect();
    PreparedStatement insert = null;
    int i = -1;
    
    try {
      insert = this.connect.prepareStatement("Insert into Cercle (nom, rayon, x, y, groupeId) values (?,?,?,?,?)");

      insert.setString(1, c.getNom());
      insert.setInt(2, c.getRayon());
      insert.setInt(3, c.getCentre().getX());
      insert.setInt(4, c.getCentre().getY());
      insert.setString(5, c.getGroupeId());
      i = insert.executeUpdate();


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
      return c;
    } else {
      return null;
    }
  }



  @Override
  public Cercle find(String id) {

    this.connect();
    Cercle c = null;
    PreparedStatement select = null;

    try {
      select = this.connect.prepareStatement("select * from Cercle where nom = (?)");

      select.setString(1, id);
      select.execute();
      ResultSet result = select.getResultSet();

      if (result.next()) {

        String nom = result.getString("nom");
        int rayon = result.getInt("rayon");
        int x = result.getInt("x");
        int y = result.getInt("y");
        String groupeId = result.getString("groupeId");

        c = new Cercle(nom, rayon, new Point(x,y), groupeId);
        select.close();
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

    return c;

  }

  @Override
  public Cercle update(Cercle obj) {

    this.connect();
    PreparedStatement update = null;

    try {
      update = this.connect.prepareStatement("update Cercle set rayon = (?), x = (?), y = (?) where nom = (?) ");

      update.setInt(1, obj.getRayon());
      update.setInt(2, obj.getCentre().getX());
      update.setInt(3, obj.getCentre().getY());

      update.setString(4, obj.getNom());
      update.executeUpdate();
      update.close();

    } catch (SQLException e) {
      e.printStackTrace();
    }
    try {
      if (update != null) {
        update.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return obj;
  }

  @Override
  public void delete(Cercle c) {

    this.connect();
    PreparedStatement delete = null;

    try {
      delete = this.connect.prepareStatement("delete from Cercle where nom = (?)");

      delete.setString(1, c.getNom());
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
  public ArrayList<Cercle> getGroupeObject(String id) {

    this.connect();
    ArrayList<Cercle> listCercle = new ArrayList<Cercle>();
    PreparedStatement select = null;
    
    try {
      select = this.connect.prepareStatement("Select * from Cercle where groupeId = (?)");
      
      select.setString(1, id);
      select.execute();
      
      ResultSet result = select.getResultSet();

      while (result.next()) {
        
        String nom = result.getString("nom");
        int rayon = result.getInt("rayon");
        int x = result.getInt("x");
        int y = result.getInt("y");
        
        Cercle c = new Cercle(nom, rayon, new Point(x, y), id);
        
        listCercle.add(c);
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

    return listCercle;
  }
}