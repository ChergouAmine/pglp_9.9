package DAO;

import forme.Triangle;
import forme.Point;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TriangleDao extends Dao<Triangle> {

  @Override
  public Triangle create(Triangle t) {
    
    this.connect();
    PreparedStatement insert = null;
    int i = -1;
    
    try {
      insert = this.connect.prepareStatement("Insert into Triangle(nom, ax, ay, bx, by, cx, cy, groupeId) values (?,?,?,?,?,?,?,?)");
      
      insert.setString(1, t.getNom());
      insert.setInt(2, t.getA().getX());
      insert.setInt(3, t.getA().getY());
      insert.setInt(4, t.getB().getX());
      insert.setInt(5, t.getB().getY());
      insert.setInt(6, t.getC().getX());
      insert.setInt(7, t.getC().getY());
      insert.setString(8, t.getGroupeId());
      
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
      return t;
    } else {
      return null;
    }
  }

  @Override
  public Triangle find(String id) {
    
    this.connect();
    Triangle t = null;
    PreparedStatement select = null;
    
    try {
      select = this.connect.prepareStatement("select * from Triangle where nom = (?)");
      
      select.setString(1, id);
      select.execute();
      ResultSet result = select.getResultSet();
      
      if (result.next()) {
        
        String nom = result.getString("nom");
        Point a = new Point(result.getInt("ax"), result.getInt("ay"));
        Point b = new Point(result.getInt("bx"), result.getInt("by"));
        Point c = new Point(result.getInt("cx"), result.getInt("cy"));
        String groupeId = result.getString("groupeId");
        
        t = new Triangle(nom, groupeId, a, b, c);
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
    return t;

  }

  @Override
  public Triangle update(Triangle t) {
    this.connect();
    PreparedStatement update = null;
    try {
      update = this.connect.prepareStatement("update Triangle set ax = (?), ay = (?), bx = (?), \"by\" = (?), cx = (?), cy = (?)" 
          + " where nom = (?) ");
      
      update.setInt(1, t.getA().getX());
      update.setInt(2, t.getA().getY());
      update.setInt(3, t.getB().getX());
      update.setInt(4, t.getB().getY());
      update.setInt(5, t.getC().getX());
      update.setInt(6, t.getC().getY());
      update.setString(7, t.getNom());
      
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
    return t;
  }

  @Override
  public void delete(Triangle t) {
    
    this.connect();
    PreparedStatement delete = null;
    
    try {
      delete = this.connect.prepareStatement("delete from Triangle where nom = (?)");
      
      delete.setString(1, t.getNom());
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
  public ArrayList<Triangle> getGroupeObject(String id) {
    
    this.connect();
    ArrayList<Triangle> listTriangle = new ArrayList<Triangle>();
    PreparedStatement select = null;
    
    try {
      select = this.connect.prepareStatement("Select * from triangle where groupeId = (?)");
      
      select.setString(1, id);
      select.execute();
      ResultSet result = select.getResultSet();

      while (result.next()) {
        
          String nom = result.getString("nom");
          Point a = new Point(result.getInt("ax"), result.getInt("ay"));
          Point b = new Point(result.getInt("bx"), result.getInt("by"));
          Point c = new Point(result.getInt("cx"), result.getInt("cy"));
          
          Triangle t = new Triangle(nom, id, a, b, c);
          listTriangle.add(t);
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
    
    return listTriangle;
}
  
}
