import java.awt.*;
import javax.swing.*;

public class Run_WZLY {

    /**
     * This class runs the entire program. It will construct the three panels
     * Required to make our program function. 
     *
     * @author Gabriela Lanza and Cassandra Hoef
     *
     */

    
	
    /**
     * This method puts all of the technical details of the GUI together
     * and makes it pop up and close upon running the program. The constructor
     * for WZLY is called in this class
     */
    public static void createAndShowGUI(){
	
	JFrame frame = new JFrame("WZLY Music Log");
	
	frame.setSize(790, 500);

	//Specify that the close button exits application.
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	WZLY zly = new WZLY ();
	zly.init();

	frame.add(zly, BorderLayout.CENTER);
	frame.setVisible(true);
	//Container contentPane = frame.getContentPane();
	//contentPane.add(WZLY.login);

    }

   

    /**
     * When the Run_WZLY is ran in the emacs shell, this calls the run method
     * for the GUI, starting our program when it should
     */
    public static void main(String [] args){
	//Schedule a job for the event-dispatching thread:
	//creating and showing this application's GUI.
	javax.swing.SwingUtilities.invokeLater(new Runnable() {
		public void run() {
		    createAndShowGUI();
		}
	    });
    }

}
