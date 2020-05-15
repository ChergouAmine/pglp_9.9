package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class Dao<T> {
  public Connection connect = null;

  public abstract T create(T obj);
  public abstract T find(String id);
  public abstract T update(T obj);

  public abstract void delete(T obj);
  public abstract ArrayList<T> getAllGroupeObject(String id);

  public void connect() {

    try {
      Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
      connect = DriverManager.getConnection("jdbc:derby:dessindb;create=true");
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
      try {
        connect.close();
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
    }

  }


  public void disconnect() {
    try {
      connect.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}