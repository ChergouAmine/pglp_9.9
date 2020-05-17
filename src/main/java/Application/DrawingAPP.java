package Application;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import DAO.DaoFactory;
import InputOutput.Afficherr;
import InputOutput.Scan;
import InputOutput.UserInput;
import InputOutput.UserOutput;
import PatternCommand.Command;
import PatternCommand.DrawingTUI;
import forme.Afficher;
import forme.Forme;
import forme.GroupeForme;

public enum DrawingAPP {

DrawingAPP;
  
public void run(final String[] args) {
  
    UserOutput aff = new Afficherr();
    UserInput input = new Scan();
    DrawingTUI drawing = new DrawingTUI();
    
    String message = "Tapez une commande";
    aff.showMessage(message);
    String userString = "";
    int count = 0;
    
    while (true) {
      
        userString = input.readValue();
        Command command = drawing.nextCommand(userString);
        
        
        if (command != null) {
    
            String commandString = command.getClass().getSimpleName();
            
            try {
                command.execute();
                
            } catch (NoSuchElementException e) { 
                message = "CEtte forme n'est pas permise ou n'existe pas";
                aff.showMessage(message);
            }
            
            switch(commandString.toLowerCase()) {
            case "deplacementforme":

                message = "cette forme a été déplacée : ";
                Forme forme = drawing.getformeDeplacee().get(0);
                message += forme.getNom() + " ";
                
                aff.showMessage(message);
                    
                message = "ses nouvelles coordonnées : ";
                aff.showMessage(message);
                forme.affiche();
                drawing.deleteformeMoved();
        
                break;
                
            case "deplacementformegroupe":

                message = "ces formes ont été déplacées : ";
                for (Forme f : drawing.getformeDeplacee()) {
                    message += f.getNom() + " ";
                }
                
                
                aff.showMessage(message);
                    
                message = "nouvelles coordonnées : ";
                aff.showMessage(message);
                for (Forme f : drawing.getformeDeplacee()) {
                    f.affiche();
                }
                drawing.deleteformeMoved();
                break;
                
            case "showallcommand":
                break;
                
            case "allformedelete":
                message = "Tout a été supprimé";
                aff.showMessage(message);
                count = 0;
                break;
                
            case "showcommand"://A REMPLIR
                break;
                
            case "formedelete":
                message = "ces formes ont été supprimées ";
                
                for (Forme f : drawing.getformeSupprimee()) {
                    message += f.getNom() + " ";
                }
                aff.showMessage(message);
                count = drawing.getforme().size();
                drawing.deleteformeSupprimee();
                break;
                
            case "quit":
                message = "FERMETURE DE L'APP";
                aff.showMessage(message);
                return;
                
            case "savecommand":
                message = "SAVED !";
                aff.showMessage(message);
                break;
                
            case "loadcommand":
                message = "LOADED";
                aff.showMessage(message);
                break;
                
            default://creer une forme
                message = drawing.getforme().get(count).getType()+" "+drawing.getforme().get(count).getNom()
                +  " has been created.";
                aff.showMessage(message);
                drawing.getforme().get(count).affiche();
                count += 1;
                break;
            
            }
        
                    

        } else {
            message = "Rentrez une commande valide svp";
            aff.showMessage(message);
        }
        
        
    }
}
/**
*
* @param args arguments.
 * @throws ClassNotFoundException 
 * @throws SQLException 
*/
 


public static void main(final String[] args) throws ClassNotFoundException, SQLException {
  
  
 /* Connection connect = null;
  
  Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
  connect = DriverManager.getConnection("jdbc:derby:dessindb;create=true");
 

     connect.createStatement().execute("CREATE TABLE groupe(groupeId varchar(30) primary key not null)");

      
      connect.createStatement().execute("CREATE TABLE Carre(nom varchar(20) PRIMARY KEY NOT NULL, cote int, x int, y int, "   
          + "groupeId varchar(30) references groupe(groupeId))");


      connect.createStatement().execute("CREATE TABLE Cercle(nom varchar(20) PRIMARY KEY NOT NULL, rayon int, x int, y int, "   
          + "groupeId varchar(30) references groupe(groupeId))");


      connect.createStatement().execute("CREATE TABLE Rectangle(nom varchar(20) PRIMARY KEY NOT NULL, h int, w int, x int, y int, "   
          + "groupeId varchar(30) references groupe(groupeId))");


      connect.createStatement().execute("CREATE TABLE Triangle(nom varchar(20) PRIMARY KEY NOT NULL, cote int, ax int, ay int, bx int, \"by\" int, cx int,  cy int,"   
          + "groupeId varchar(30) references groupe(groupeId))");

 connect.close();
    */
   
  
  
       DrawingAPP.run(args);
      
}

}
