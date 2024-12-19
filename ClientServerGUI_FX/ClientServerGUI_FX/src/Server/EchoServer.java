// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 
package Server;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import logic.DBController;
import logic.Faculty;
import logic.Student;
import ocsf.server.*;

/**
 * This class overrides some of the methods in the abstract 
 * superclass in order to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * @version July 2000
 */

public class EchoServer extends AbstractServer 
{
  //Class variables *************************************************
  
  /**
   * The default port to listen on.
   */
  //final public static int DEFAULT_PORT = 5555;
  
  //Constructors ****************************************************
  
  /**
   * Constructs an instance of the echo server.
   *
   * @param port The port number to connect on.
   * 
   */
  public static List<Student> students = new ArrayList<>();

  public EchoServer(int port) 
  {
    super(port);
  }

  //Instance methods ************************************************
  
  /**
   * This method handles any messages received from the client.
   *
   * @param msg The message received from the client.
   * @param client The connection from which the message originated.
   * @param 
   */
  public void handleMessageFromClient(Object msg, ConnectionToClient client)
  {
	    String message = msg.toString();

	    if (message.startsWith("SAVE_STUDENT")) {
	        String[] parts = message.split(";");
	        int ID = Integer.parseInt(parts[1]);
	        String Name = parts[2];
	        int History = Integer.parseInt(parts[3]);
	        String Phone = parts[4];
	        String Email = parts[5];

	       
	        // Update the database
	        DBController dbController = new DBController();
	        boolean success = dbController.updateStudent(ID, Name, History, Phone, Email);

	        if (success) {
	            System.out.println("Student data updated successfully in the database.");
	            // Optionally, send a success message back to the client
	            this.sendToAllClients("Update successful for ID: " + ID);
	        } else {
	            System.out.println("Failed to update student data in the database.");
	            // Optionally, send an error message back to the client
	            this.sendToAllClients("Update failed for ID: " + ID);
	        }
	        

	    }
	    else {
	  
		    int flag=0;
		    System.out.println("Message received: " + msg + " from " + client);
		    
		    try {
		    	int idToSearch = Integer.parseInt((String) msg);
		    	for(Student student : students) {
					   if(student.getId() == idToSearch)
					   { 
							System.out.println("Server Found");
							this.sendToAllClients(student.toString());
							flag=1;
							break;
					   }
		    	}
			    if (flag!=1) {
					System.out.println("Not Found");
					this.sendToAllClients("Error");
				} 
		    }catch (NumberFormatException e) {
		        System.out.println("Invalid ID format: " + msg);
		        this.sendToAllClients("Error");
		    }
	    }
			
   }
  /**
   * This method overrides the one in the superclass.  Called
   * when the server starts listening for connections.
   */
  protected void serverStarted()
  {
    System.out.println("Server listening for connections on port " + getPort());
    // Fetch all students from the database
    DBController dbController = new DBController();
    students = dbController.getAllStudents(); // Populate the static list
    

    // Print all students for verification
//    if (students.isEmpty()) {
//        System.out.println("No students found in the database.");
//    } else {
//        for (Student student : students) {
//            System.out.println(student);
//        }
//    }
     

  }
  /**
   * This method overrides the one in the superclass.  Called
   * when the server stops listening for connections.
   */
  protected void serverStopped()  {
    System.out.println ("Server has stopped listening for connections.");
  }  
}
//End of EchoServer class
