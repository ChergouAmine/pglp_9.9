package Application;

import java.util.NoSuchElementException;
import InputOutput.Afficherr;
import InputOutput.Scan;
import InputOutput.UserInput;
import InputOutput.UserOutput;
import PatternCommand.Command;
import PatternCommand.DrawingTUI;
import forme.Afficher;
import forme.Forme;

public enum DrawingAPP {
/**
*Application.
*/
DrawingAPP;
/**
*
* @param args arguments.
*/
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
            case "formedeplacement":

                message = "cette forme a été déplacées : ";
                Forme forme = drawing.getformeDeplacee().get(0);
                message += forme.getNom() + " ";
                
                aff.showMessage(message);
                    
                message = "ses nouvelles coordonnées : ";
                aff.showMessage(message);
                forme.affiche();
                drawing.deleteformeMoved();
        
                break;
                
            case "formedeplacementgroupe":

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
                
            case "deleteall":
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
*/
 


public static void main(final String[] args) {
       DrawingAPP.run(args);
}

}
