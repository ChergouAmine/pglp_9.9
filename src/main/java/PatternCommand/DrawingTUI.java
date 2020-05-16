package PatternCommand;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import DAO.Dao;
import DAO.DaoFactory;
import forme.Forme;
import forme.GroupeForme;
import forme.Carre;
import forme.Cercle;
import forme.Rectangle;
import forme.Triangle;
import forme.GroupeForme;
import forme.Point;
import forme.AbstractForme;
import forme.Afficher;

public class DrawingTUI {

  private List<Forme> forme;

  private Map<String, Command> commands;

  private List<Forme> formeDeplacee;

  private List<Forme> formeSupprimee;

  private Connection connect = null;
  private PreparedStatement statement;

  private static int goupeId = 0;


  public DrawingTUI() {

    try {
      statement=null;

      DaoFactory daoFac = new DaoFactory();

      DAO.Dao<GroupeForme> daoG = daoFac.createGroupeDao();

      daoG.connect();   
      connect = daoG.connect; 

      DatabaseMetaData dbmd = connect.getMetaData();

      ResultSet rs = dbmd.getTables(null, "APP", "GROUPE", null);

      if(!rs.next())
      {

        statement.execute("CREATE TABLE groupe(groupeId varchar(30) primary key not null)");


        statement.execute("CREATE TABLE Carre(nom varchar(20) PRIMARY KEY NOT NULL, cote int, x int, y int, "   
            + "groupeId varchar(30) references groupe(groupeId))");


        statement.execute("CREATE TABLE Cercle(nom varchar(20) PRIMARY KEY NOT NULL, rayon int, x int, y int, "   
            + "groupeId varchar(30) references groupe(groupeId))");


        statement.execute("CREATE TABLE Rectangle(nom varchar(20) PRIMARY KEY NOT NULL, h int, w int, x int, y int, "   
            + "groupeId varchar(30) references groupe(groupeId))");


        statement.execute("CREATE TABLE Triangle(nom varchar(20) PRIMARY KEY NOT NULL, cote int, ax int, ay int, bx int, \"by\" int, cx int,  cy int,"   
            + "groupeId varchar(30) references groupe(groupeId))");

        statement.close(); 
      }
      connect.close();
    } catch (SQLException e) {

      e.printStackTrace();
    }

    commands = new HashMap<String, Command>();

    forme = new ArrayList<Forme>();
    formeDeplacee = new ArrayList<Forme>();

    commands.put("carre", new CreateCarre());
    commands.put("cercle", new CreateCercle());
    commands.put("rectangle", new CreateRectangle());
    commands.put("triangle", new CreateTriangle());

    commands.put("move", new DeplacementForme());
    commands.put("moveall", new DeplacementFormeGroupe());//j'ai créé cette classe pour justement différencier les commandes move et moveall


    commands.put("show", new ShowCommand());
    commands.put("showall", new ShowAllCommand());

    commands.put("delete", new FormeDelete());
    commands.put("deleteall", new AllFormeDelete());

    commands.put("save", new SaveCommand());
    commands.put("load", new LoadCommand());
    commands.put("quit", new Quit());
  }


  private class CreateCarre extends CreateForme implements Command{
    @Override
    public void execute() {
      DrawingTUI.this.forme.add(new Carre(((Carre)forme).getNom(), ((Carre)forme).getCote(), ((Carre)forme).getPoint(), ((Carre)forme).getGroupeId()));
    }
  }


  private class CreateCercle extends CreateForme implements Command{
    @Override
    public void execute() {
      DrawingTUI.this.forme.add(new Cercle(((Cercle)forme).getNom(), ((Cercle)forme).getRayon(), ((Cercle)forme).getCentre(), ((Cercle)forme).getGroupeId()));
    }

  }

  private class CreateRectangle extends CreateForme implements Command{
    @Override
    public void execute() {
      DrawingTUI.this.forme.add(new Rectangle(((Rectangle)forme).getNom(), ((Rectangle)forme).getPoint(), ((Rectangle)forme).getHeight(), ((Rectangle)forme).getWidth(), ((Rectangle)forme).getGroupeId()));
    }

  }

  private class CreateTriangle extends CreateForme implements Command{
    @Override
    public void execute() {
      DrawingTUI.this.forme.add(new Triangle(((Triangle)forme).getNom(), ((Triangle)forme).getA(),((Triangle)forme).getB(), ((Triangle)forme).getC(), ((Triangle)forme).getGroupeId()));
    }
  }

  private CreateForme getCreationCommand(String[] result) {

    Forme f = null;
    CreateForme command = null;

    try {

      switch(result[1].toLowerCase()) {

        case "cercle":
          String name = result[0];
          int x = Integer.parseInt(result[2]);
          int y = Integer.parseInt(result[3]);
          String groupeId = "aucun";
          int rayon = Integer.parseInt(result[4]);
          f = new Cercle(name, rayon, new Point(x, y), groupeId);

          command = (CreateCercle) commands.get("cercle");
          break;


        case "triangle"://CHERCHER POURQUOI ON NE PEUT PAS REDECLARER LES VARIABLE ICI
          groupeId = "aucun";
          name = result[0];
          Point A = new Point(Integer.parseInt(result[2]), Integer.parseInt(result[3]));
          Point B = new Point(Integer.parseInt(result[4]), Integer.parseInt(result[5]));
          Point C = new Point(Integer.parseInt(result[6]), Integer.parseInt(result[7]));
          f = new Triangle(name, A, B, C, groupeId);

          command =  (CreateTriangle) commands.get("triangle");

          break;

        case "carre":
          groupeId = "aucun";
          name = result[0];
          x = Integer.parseInt(result[2]);
          y = Integer.parseInt(result[3]);
          int cote = Integer.parseInt(result[4]);

          f = new Carre(name, cote, new Point(x, y), groupeId);
          command = (CreateCarre) commands.get("carre");

          break;

        case "rectangle":
          name = result[0];
          x = Integer.parseInt(result[2]);
          y = Integer.parseInt(result[3]);
          int h = Integer.parseInt(result[4]);
          int w = Integer.parseInt(result[5]);
          groupeId = "aucun";
          f = new Rectangle(name, new Point(x, y), h, w, groupeId);

          command = (CreateRectangle) commands.get("rectangle");

          break;

        default:
          return null;
      }
    } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
      return null;
    }
    command.setForm(f);
    return command;
  }


  
  private Forme getFormeWithName(List<Forme> list, String name){
   
    try {
      return list.stream().filter(o -> o.getNom().equals(name)).findFirst().get();
    } catch (NoSuchElementException e) {
      
      Afficher aff = new Afficher();
      aff.afficher("La forme "+ name +" n'existe pas ");
      return null;
    }
  }
  
  
  
  
  
  private class DeplacementForme extends MoveCommand implements Command{

    public void setParameters(int a, int b) {
      x = a;
      y = b;
    }
    
    @Override
    public void execute() {

      forme.move(x, y);
    }
  }


  private class DeplacementFormeGroupe  extends MoveCommand implements Command{


    public void setParameters(int a, int b) {
      x = a;
      y = b;
    }
    @Override
    public void execute() {
      forme.move(x, y);

    }

  }

  
  
  
  

  private DeplacementForme getMouvementCommand(String[] result) {
    
    try {
      AbstractForme f = getFormeWithName(forme, result[1]);
      
      DeplacementForme command = (DeplacementForme) this.commands.get("move");
      
      command.setForm(f);
      formeDeplacee.add((Forme)f);

      int x = Integer.parseInt(result[2]);
      int y = Integer.parseInt(result[3]);
      
      command.setParameters(x, y);
      
      return command;
    } catch(ArrayIndexOutOfBoundsException | NumberFormatException e) {
      return null;
    }

  }
  

  
  private DeplacementFormeGroupe getMovementGroupeCommand(String[] result) {
    
    GroupeForme groupeForme = new GroupeForme("gr"+goupeId);
    
    DeplacementFormeGroupe command = (DeplacementFormeGroupe) this.commands.get("moveall");
  
    int i = 1;
    
    try {
      while (i != result.length - 2) {
        Forme forme = getFormeWithName(this.forme, result[i]);
        formeDeplacee.add(forme);
        forme.setGroupeId("gr"+goupeId);
        groupeForme.addForme(forme);
        
        i++;
      }

      int x = Integer.parseInt(result[i]);
      int y = Integer.parseInt(result[i+1]);
      command.setParameters(x, y);
      command.setForm(groupeForme);
      goupeId++;
      return command;
      
    } catch (ArrayIndexOutOfBoundsException | NumberFormatException e){
      return null;
    }

  }


  private class SaveCommand implements Command{//A VOIR SI C EST LA MEILLEURE MANIERE DE FAIRE
    
    private GroupeForme groupe;
    private DaoFactory dao;
    private Dao<GroupeForme> daoGroupe;
    
    public SaveCommand() {
      dao = new DaoFactory();
      daoGroupe = dao.createGroupeDao();
    }

    public void setGroupe(GroupeForme g) {
      groupe = g;
    }
    
    @Override
    public void execute() {
      daoGroupe.delete(groupe);
      daoGroupe.create(groupe);
    }
  }

  
  private class LoadCommand implements Command{
    
    private DaoFactory dao;
    private Dao<GroupeForme> daoG;
    private GroupeForme GroupeForme;
    private String groupe;

    public LoadCommand() {
      dao = new DaoFactory();
      daoG = dao.createGroupeDao();
    }
    
    public void setGroupe(String groupe) {
      this.groupe = groupe;
    }
    
    @Override
    public void execute() {
      GroupeForme = daoG.find(groupe);
      Iterator<Forme> iterator = GroupeForme.iterator();
      
      while (iterator.hasNext()) {
        DrawingTUI.this.forme.add(iterator.next());
      }
    }
  }


  

  private LoadCommand getLoadCommand(String[] result) {
    LoadCommand command = (LoadCommand)commands.get("load");
    try {
      command.setGroupe(result[1]);

    } catch (ArrayIndexOutOfBoundsException e) {
      return null;
    }
    return command;
  }



  private class ShowCommand implements Command{
    private GroupeForme groupe;

    public void setForme(GroupeForme g) {
      groupe = g;
    }
    @Override
    public void execute() {
      groupe.affiche();
    }

  }
  
  
  private class ShowAllCommand implements Command{
    @Override
    public void execute() {
      for (Forme f : DrawingTUI.this.forme) {
        f.affiche();
      }
    }
  }
 

  private ShowCommand getShowCommand(String[] result) {
    GroupeForme g = new GroupeForme("aucun");
    
    if (result.length == 1) {
      return null;
    }

    for(int i = 1; i < result.length; i++) {
      Forme f = getFormeWithName(this.forme, result[i]);
      if (f == null) {
        return null;
      }else {
        g.addForme(f);
      }
    }

    ShowCommand command = (ShowCommand) this.commands.get("show");
    command.setForme(g);
    return command;
  }
  

  
  private Command getSaveCommand(String[] result) {
    
    SaveCommand command = (SaveCommand) this.commands.get("save");
    
    try {
      GroupeForme groupe = new GroupeForme(result[1]);
      for (Forme f: this.forme) {
        f.setGroupeId(result[1]);
        groupe.addForme(f);
      }
      command.setGroupe(groupe);
      
    } catch (ArrayIndexOutOfBoundsException e) {
      return null;
    }
    return command;
  }
  
  

  


  private static class Quit implements Command {

    public void execute() {
    //A COMPLETER
      return;
    }

  }



  private class FormeDelete implements Command{

    private List<Forme> listOfforme;

    public void setForme(List<Forme> forme) {
      listOfforme = forme;
    }

    public void execute() {
      for(Forme f : listOfforme) {
        
        for(Iterator<Forme> iterator = DrawingTUI.this.forme.iterator(); iterator.hasNext(); ) {
          
          if(iterator.next().getNom().equals(f.getNom()));
          iterator.remove();
        }
      }
    }


  }


  private class AllFormeDelete implements Command{

    @Override
    public void execute() {
      DrawingTUI.this.forme.clear();
    }
  }

  
  
  private FormeDelete getDeleteCommand(String[] result) {
    
    FormeDelete command = (FormeDelete) this.commands.get("delete");

    List<Forme> listForme = new ArrayList<Forme>();
    
    for(int i = 1; i < result.length;i++) {
      listForme.add(getFormeWithName(this.forme, result[i]));
      formeSupprimee = new ArrayList<Forme>(listForme);
    }
    command.setForme(listForme);
    return command;
  }
  
  
  public List<Forme> getforme(){
    return this.forme;
  }


  public List<Forme> getformeDeplacee() {
    return this.formeDeplacee;
  }



  public List<Forme> getformeSupprimee() {
    return formeSupprimee;
  }


  public void deleteformeDeleted() {
    this.formeSupprimee.clear();

  }
  public void deleteformeMoved() {
    this.formeDeplacee.clear();
  }


public Command nextCommand(String userText) {
    
    userText = userText.replaceAll("[=)(,]*-", " ");
    String[] result = userText.split("\\s+");
    Command command = null;


    switch(result[0].toLowerCase()) {
      
      case "move":
        command = getMouvementCommand(result);
        break;
        
      case "moveall":
        command = getMovementGroupeCommand(result);
        break;
        
      case "save":
        command = getSaveCommand(result);
        break;
        
      case "load":
        this.forme.clear();
        command = getLoadCommand(result);
        break;
        
      case "delete":
        command = getDeleteCommand(result);
        break;
        
      case "deleteall":
        //A COMPLETER
        break;
        
      case "show":
        if (result.length == 1) {
          return null;
        }
        command = getShowCommand(result);
        break;
        
        
      case "showall":
      //A COMPLETER
        break;
        
        
      case "quit":
      //A COMPLETER
        break;
      default:
        command = getCreationCommand(result);
        break;


    }
    return command;
  }





}
