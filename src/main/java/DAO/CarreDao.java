package DAO;


import forme.Carre;
import forme.Point;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class CarreDao extends Dao<Carre> {

  @Override
  public Carre create(Carre c) {
    
    this.connect();
    
    PreparedStatement insert = null;
    
    int i = -1;//pour pouvoir tester si la valeur est devenue positive ou pas à la fin de l'insertion
    
    try {
      insert = this.connect.prepareStatement("Insert into Carre(nom, cote, x, y, groupeId) values (?,?,?,?,?)");
      
      //REMPLACER LES ? avec les valeurs à insérer dans la BDD
      
      insert.setString(1, c.getNom());
      insert.setInt(2, c.getCote());
      insert.setInt(3, c.getPoint().getX());
      insert.setInt(4, c.getPoint().getY());
      insert.setString(5, c.getGroupeid());
      
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
  public Carre find(String id) {
    
    this.connect();
    
    Carre c = null;

    PreparedStatement select = null;
    
    try {
      
      select = this.connect.prepareStatement("select * from Carre where nom = (?)");
      
      select.setString(1, id);
      select.execute();
      
      ResultSet result = select.getResultSet();
      
      if (result.next()) {
        //extraction d'info
        String nom = result.getString("nom");
        int cote = result.getInt("cote");
        int x = result.getInt("x");
        int y = result.getInt("y");
        String groupeid = result.getString("groupeid");
        
        //création du carré
        c = new Carre(nom, cote, new Point(x,y), groupeid);
        
        select.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    try 
    {
      if (select != null) {
        select.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }


    return c;//si le carre n'existe pas ça retourne null

  }

  @Override
  public Carre update(Carre c) {
    
    this.connect();
    PreparedStatement update = null;
    
    try {
      update = this.connect.prepareStatement("update Carre set cote = (?), x = (?), y = (?) where nom = (?) ");
      
      update.setInt(1, c.getCote());
      update.setInt(2, c.getPoint().getX());
      update.setInt(3, c.getPoint().getY());
      update.setString(4, c.getNom());
      
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
    return c;
  }

  
  @Override
  public void delete(Carre obj) {
    
    this.connect();
    PreparedStatement delete = null;
    
    try {
      delete = this.connect.prepareStatement("delete from Carre where nom = (?)");
      
      delete.setString(1, obj.getNom());
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
  public ArrayList<Carre> getGroupeObject(String id) {

    this.connect();
    ArrayList<Carre> listCarre = new ArrayList<Carre>();
    PreparedStatement select = null;
    
    try {
      select = this.connect.prepareStatement("Select * from carre where groupeid = (?)");
      
      select.setString(1, id);
      select.execute();
      ResultSet result = select.getResultSet();

      while (result.next()) {
        
        String nom = result.getString("nom");
        int cote = result.getInt("cote");
        int x = result.getInt("x");
        int y = result.getInt("y");
        
        Carre c = new Carre(nom, cote, new Point(x, y), id);
        
        listCarre.add(c);
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
    return listCarre;
}
  
}
