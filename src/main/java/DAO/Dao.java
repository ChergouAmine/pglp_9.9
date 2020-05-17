package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class Dao<T> {
  public static final String DRIVER="org.apache.derby.jdbc.EmbeddedDriver";
  public static final String JDBC_URL="jdbc:derby:dessindb";
  public Connection connect;

  public abstract T create(T obj);
  public abstract T find(String id);
  public abstract T update(T obj);
  public abstract void delete(T obj);
  public abstract ArrayList<T> getGroupeObject(String id);

  public void connect() {

       
         try {
          Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
         try {
          this.connect = DriverManager.getConnection(JDBC_URL);
        } catch (SQLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
          
   
      
       try {
          connect.close();
        } catch (SQLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      
   

  }


  public void disconnect() {
    try {
      this.connect.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}