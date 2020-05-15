package DAO;

import forme.Carre;
import forme.Cercle;
import forme.Rectangle;
import forme.Triangle;
import forme.GroupeForme;

public class DaoFactory implements AbstractDaoFactory{

  @Override
  public Dao<Carre> createCarreDao() {
      return new CarreDao();
  }

  @Override
  public Dao<Triangle> createTriangleDao() {
      return new TriangleDao();
  }

  @Override
  public Dao<Rectangle> createRectangleDao() {
      return new RectangleDao();
  }

  @Override
  public Dao<Cercle> createCercleDao() {
      return new CercleDao();
  }

  @Override
  public Dao<FormeGroupe> createGroupeDao() {
      return new GroupeDao();
  }

}

