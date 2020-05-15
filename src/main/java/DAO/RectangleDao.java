package DAO;

import forme.Rectangle;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import forme.Point;

public class RectangleDao extends Dao<Rectangle> {

  @Override
  public Rectangle create(Rectangle r) {
    
    this.connect();
    PreparedStatement insert = null;
    int i = -1;
    
    try {
      insert = this.connect.prepareStatement("Insert into Rectangle(nom, h, w, x, y, groupeId) values (?,?,?,?,?,?)");
      
      insert.setString(1, r.getNom());
      insert.setInt(2, r.getHeight());
      insert.setInt(3, r.getWidth());
      insert.setInt(4, r.getPoint().getX());
      insert.setInt(5, r.getPoint().getY());
      insert.setString(6, r.getGroupeId());
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
      return r;
    } else {
      return null;
    }
  }

  @Override
  public Rectangle find(String id) {
    
    this.connect();
    Rectangle c = null;
    PreparedStatement select = null;
    
    try {
      select = this.connect.prepareStatement("select * from Rectangle where nom = (?)");
      
      select.setString(1, id);
      select.execute();
      
      ResultSet result = select.getResultSet();
      
      if (result.next()) {
        
        String nom = result.getString("nom");
        int h = result.getInt("h");
        int w = result.getInt("w");
        int x = result.getInt("x");
        int y = result.getInt("y");
        String groupeId = result.getString("groupeId");
        
        c = new Rectangle(nom, new Point(x,y), h,w,  groupeId);
        
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
  public Rectangle update(Rectangle r) {
    
    this.connect();
    PreparedStatement update = null;
    
    try {
      update = this.connect.prepareStatement("update Rectangle set h = (?), w = (?), x = (?), y = (?) where nom = (?) ");
      
      update.setInt(1, r.getHeight());
      update.setInt(2, r.getWidth());
      update.setInt(3, r.getPoint().getX());
      update.setInt(4, r.getPoint().getY());
      update.setString(5, r.getNom());
      
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
    return r;
  }

  @Override
  public void delete(Rectangle r) {
    
    this.connect();
    PreparedStatement delete = null;
    
    try {
      delete = this.connect.prepareStatement("delete from Rectangle where nom = (?)");
      
      delete.setString(1, r.getNom());
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
  public ArrayList<Rectangle> getGroupeObject(String id) {
    
    this.connect();
    ArrayList<Rectangle> listRectangle = new ArrayList<Rectangle>();
    PreparedStatement select = null;
    
    try {
      select = this.connect.prepareStatement("Select * from Rectangle where groupeId = (?)");
      
      select.setString(1, id);
      select.execute();
      
      ResultSet result = select.getResultSet();

      while (result.next()) {
        
        String nom = result.getString("nom");
        int h = result.getInt("h");
        int w = result.getInt("w");
        int x = result.getInt("x");
        int y = result.getInt("y");
        
        Rectangle r = new Rectangle(nom, new Point(x,y), h, w, id);
        
        listRectangle.add(r);
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
    
    return listRectangle;
}
  
}