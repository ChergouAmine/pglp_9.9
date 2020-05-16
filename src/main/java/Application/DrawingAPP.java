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
    UserOutput display = new Afficherr();
    UserInput input = new Scan();
    DrawingTUI drawing = new DrawingTUI();
    String message = "Welcome to the drawing application, type a command";
    display.showMessage(message);
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
                System.out.println("here");
                message = "Forme doesn't exist";
                display.showMessage(message);
            }
            switch(commandString.toLowerCase()) {
            case "formedeplacement":

                message = "The following forme has been moved : ";
                Forme forme = drawing.getformeDeplacee().get(0);
                message += forme.getNom() + " ";
                
                display.showMessage(message);
                    
                message = "New coordinates : ";
                display.showMessage(message);
                forme.affiche();
                drawing.deleteformeMoved();
        
                break;
            case "formegroupedeplacement":

                message = "The following forme has been moved : ";
                for (Forme f : drawing.getformeDeplacee()) {
                    message += f.getNom() + " ";
                }
                
                
                display.showMessage(message);
                    
                message = "New coordinates : ";
                display.showMessage(message);
                for (Forme f : drawing.getformeDeplacee()) {
                    f.affiche();
                }
                drawing.deleteformeMoved();
                break;
            case "showallcommand":
                break;
            case "formedeleteionall":
                message = "All formes have been deleted.";
                display.showMessage(message);
                
                count = 0;
                break;
            case "showcommand":
    
                break;
            case "formedeletion":
                message = "The following formes have been deleted ";
                for (Forme f : drawing.getformeSupprimee()) {
                    message += f.getNom() + " ";
                }
                display.showMessage(message);
                count = drawing.getforme().size();
                drawing.deleteformeSupprimee();
                break;
            case "quit":
                message = "You are quiting";
                display.showMessage(message);
                return;
            case "savecommand":
                message = "Your drawing has been saved !";
                display.showMessage(message);
                break;
            case "loadcommand":
                message = "Your drawing have been loaded";
                display.showMessage(message);
                break;
            default:
                message = drawing.getforme().get(count).getType()+" "+drawing.getforme().get(count).getNom()
                +  " has been created.";
                display.showMessage(message);
                drawing.getforme().get(count).affiche();
                count += 1;
                break;
            
            }
        
            
            
        
            
        
    
                    

        } else {
            message = "Please enter a valid command";
            display.showMessage(message);
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
