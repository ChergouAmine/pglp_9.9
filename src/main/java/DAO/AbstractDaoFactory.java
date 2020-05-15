package DAO;

import forme.Carre;
import forme.Cercle;
import forme.Rectangle;
import forme.Triangle;
import forme.GroupeForme;

public interface AbstractDaoFactory {
  
  Dao<Carre> createCarreDao();
  Dao<Cercle> createCercleDao();
  Dao<Rectangle> createRectangleDao();
  Dao<Triangle> createTriangleDao();
  Dao<GroupeForme> createGroupeDao();
  
}
