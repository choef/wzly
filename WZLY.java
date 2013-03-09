import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class WZLY extends JApplet
    implements ActionListener {

    /**
     * This program creates the GUI for the WZLY Music Log program. It consists
     * of three primary panels: Login, MD, and DJ. Each of these panels will
     * coorespond with a main action in the program and will paint over each
     * other as necessary. This class has a similar role to that of the 
     * Tournament class from a previous CS230 problem set at Wellesley.
     *
     * @author Gabriela Lanza and Cassandra Hoef
     */
    //instance methods

    //instances for the overall class
    /**
     *Constructs a library object to hold all albums input to the MD GUI
     */
    private Library lib; 
    /**
     * Makes an account to check for and login users who interact with the GUI.
     */
    private Accounts acc;
    /**
     * Contains the DJ logged into the program at a given time
     */
    private DJ currentDJ;
    /**
     * Contains the date read in from the GUI during login as a String
     */
    private String date;
    /** 
     * Hold the input from the user into the userName field. Can either
     * Correspond to the MD login or a DJs username
     */
    private String nameInput;

    //
    //
    //instances for the login panel
    /**
     * Creates a panel to hold all of the elements that go into making the 
     * login screen
     */
    private JPanel loginPanel;
    /**
     * Labels for the login screen that provide information about
     * what information to type into which text field
     */
    private JLabel LIusernameLabel, LIdateLabel, LIwelcomeLabel1,LIwelcomeLabel2;
    /**
     * TextFields to take in information from the user in order to login.
     */
    private JTextField LIusernameField, LIdateField;
    /**
     * Button submits the information from the user and calls the methods needed to
     * process it and take them to the next "page".
     */
    private JButton LIsubmitButton;
   

    //
    //
    //instances for the MD panel
    /**
     *Creates the user screen for the MD
     */
    private JPanel MDPanel;
    
    /**
     * Creates labels to indicate what elements are or where the user should input 
     * information
     */
    private JLabel MDaddAlbumHeaderLabel, MDnameLabel, MDartistLabel, MDIDLabel,
	MDwelcomeLabel, MDchartsHeaderLabel, MDIDNumLabel;
   
    /**
     * Creates a text area for the charts to be displayed in on the GUI
     */
    private JTextArea MDchartsField;

    /**
     * Textfields where the MD user can enter information about a new album
     */
    private JTextField MDalbumName, MDalbumArtist;

    /**
     * Creates buttons that allow MDs to do administrative things with the data 
     * acquired by the program or to log out
     */
    private JButton MDsubmitButton, MDcompileChartsButton, MDnewWeekButton, 
	MDclearButton, MDendButton;
   

    //
    //
    //instances for the DJ panel
    /**
     * A new panel to hold all of the elements involved in creating the 
     * UI for DJS
     */
    private JPanel DJPanel;

    /**
     * Labels that indicate where a user should input information
     */
    private JLabel DJLabel, DJdateLabel, DJtrackHeader, DJartistHeader, DJhrHeader,
	DJlastSongHeader, DJlastSong;
    
    /**
     * Textfields for all of the song titles
     */
    private JTextField track1, track2, track3, track4, track5;
    /**
     * Text fields for all of the artists
     */
    private JTextField artist1, artist2, artist3, artist4, artist5;
    /**
     * Text fields for all HR numbers
     */
    private JTextField hr1, hr2, hr3, hr4, hr5;

    /**
     * Buttons for submitting songs or logging out
     */
    public JButton DJsubmitButton, DJendButton;
   


    //constructor
    /**
     *Creates a new WZLY object that initializes all of the panels, the library,
     *the Account, currentDJ,name and date. This will be called in Run_WZLY to make
     *the GUI work properly with all of the elements in it. 
     */
    public WZLY (){
	//for easily updating in the GUI
	currentDJ = null;
	date = "";
	nameInput = ""; 

	//classes that hold our data structures
	acc = new Accounts();
	lib = new Library();

	//three panels
	loginPanel = new JPanel();
	MDPanel = new JPanel();
	DJPanel = new JPanel();
	makeLoginPanel(loginPanel);
	makeDJPanel(DJPanel);
	makeMDPanel(MDPanel);

	lib.addTestAlbums();
	//	System.out.println(lib);

    }

    //THIS WHOLE SECTION IS FOR CONSTRUCTING THE LOGIN PANEL

    /**
     * Adds all elements to the login panel that will be displayed on the GUI
     *
     *@param 1 a JPanel that will contain all of the added elements
     */
    public void makeLoginPanel(JPanel l){
	
	makeLoginObjects();
	l.setLayout(new BorderLayout(2,2));
	JPanel in = new JPanel();
	l.add(in, BorderLayout.NORTH);
	in.setLayout(new GridLayout(5,4,0,0));

	in.add(new JLabel(""));
	in.add(LIwelcomeLabel1);
	in.add(LIwelcomeLabel2);
	in.add(new JLabel(""));

	in.add(new JLabel(""));
	in.add(new JLabel(""));
	in.add(new JLabel(""));
	in.add(new JLabel(""));

	in.add(new JLabel(""));
	in.add(LIusernameLabel);
	in.add(LIusernameField);
	in.add(new JLabel(""));

	in.add(new JLabel(""));
	in.add(LIdateLabel);
	in.add(LIdateField);
	in.add(new JLabel(""));

	in.add(new JLabel(""));
	in.add(new JLabel(""));
	in.add(LIsubmitButton);

	l.setBackground(Color.darkGray);
	in.setBackground(Color.darkGray);
	LIusernameLabel.setForeground(Color.white);
	LIusernameLabel.setFont(new Font("sansserif",Font.PLAIN,17));
	LIdateLabel.setForeground(Color.white);
	LIdateLabel.setFont(new Font("sansserif",Font.PLAIN,17));
	LIwelcomeLabel1.setForeground(Color.orange);
	LIwelcomeLabel1.setFont(new Font("sansserif",Font.PLAIN,24));
	LIwelcomeLabel2.setForeground(Color.orange);
	LIwelcomeLabel2.setFont(new Font("sansserif",Font.PLAIN,24));

    }

    /**
     * Initiates all of the elements of the Login panel and adds action listeners
     */
    
    public void makeLoginObjects(){

	//label construction
	LIusernameLabel = new JLabel("Username:");
	LIdateLabel = new JLabel("Date (xx-xx-xxxx):");
	LIwelcomeLabel1 = new JLabel("WZLY Music Log");
	LIwelcomeLabel2 = new JLabel("Login");
	
	//text field construction
	LIusernameField = new JTextField("", 20);
	LIusernameField.addActionListener(this);
	LIdateField = new JTextField("", 11);
	LIdateField.addActionListener(this);

	//button construction
	LIsubmitButton = new JButton("Submit");
	LIsubmitButton.addActionListener(this);


    }
	//LOGIN ENDS HERE

    //THIS WHOLE SECTION FOR CONSTRUCTING DJ PANEL
    /**
     * Adds elements and style to DJ panel
     *
     * @param j a panel to represent the DJ user "page".
     */
    public void makeDJPanel(JPanel j){
	makeDJTextField();
	makeDJLabels();
	makeDJButtons();
	j.setLayout(new BorderLayout(2,2));

	JPanel top = new JPanel();
	top.setLayout(new GridLayout(2,1,0,0));
	top.add(DJLabel);
	top.add(DJdateLabel);

	JPanel in = new JPanel();
	in.setLayout(new GridLayout(9,3,10,10));

	in.add(new JLabel(""));
	in.add(new JLabel(""));
	in.add(new JLabel(""));

	in.add(DJtrackHeader);
	in.add(DJartistHeader);
	in.add(DJhrHeader);

	in.add(track1);
	in.add(artist1);
	in.add(hr1);

	in.add(track2);
	in.add(artist2);
	in.add(hr2);

	in.add(track3);
	in.add(artist3);
	in.add(hr3);

	in.add(track4);
	in.add(artist4);
	in.add(hr4);

	in.add(track5);
	in.add(artist5);
	in.add(hr5);

	in.add(new JLabel(""));
	in.add(new JLabel(""));
	in.add(DJsubmitButton);

	in.add(new JLabel(""));
	in.add(new JLabel(""));
	in.add(new JLabel(""));

	JPanel ls = new JPanel();
	ls.setLayout(new GridLayout(2,1,0,0));
	ls.add(DJlastSongHeader);
	ls.add(DJlastSong);

	JPanel bot = new JPanel();
	bot.setLayout(new BorderLayout(2,2));
	bot.add(ls,BorderLayout.NORTH);
	bot.add(DJendButton, BorderLayout.EAST);

	j.add(top, BorderLayout.NORTH);
	j.add(in, BorderLayout.CENTER);
	j.add(bot, BorderLayout.SOUTH);

	j.setBackground(Color.darkGray);
	top.setBackground(Color.darkGray);
	in.setBackground(Color.darkGray);
	ls.setBackground(Color.darkGray);
	bot.setBackground(Color.darkGray);

	DJLabel.setForeground(Color.orange);
	DJLabel.setFont(new Font("sansserif",Font.PLAIN,18));
	DJdateLabel.setForeground(Color.orange);
	DJdateLabel.setFont(new Font("sansserif",Font.PLAIN,18));

	DJtrackHeader.setForeground(Color.white);
	DJtrackHeader.setFont(new Font("sansserif",Font.PLAIN,16));
	DJartistHeader.setForeground(Color.white);
	DJartistHeader.setFont(new Font("sansserif",Font.PLAIN,16));
	DJhrHeader.setForeground(Color.white);
	DJhrHeader.setFont(new Font("sansserif",Font.PLAIN,16));

	DJlastSongHeader.setForeground(Color.orange);
	DJlastSongHeader.setFont(new Font("sansserif",Font.PLAIN,18));
	DJlastSong.setForeground(Color.white);
	DJlastSong.setFont(new Font("sansserif",Font.PLAIN,16));
	
    }
    
    /**
     * Initializes and adds action listeners to all of the text fields in the DJpanel
     */
    public void makeDJTextField(){
	//Track text fields
	track1 = new JTextField("", 20);
	track1.addActionListener(this);
	track2 = new JTextField("", 20);
	track2.addActionListener(this);
	track3 = new JTextField("", 20);
	track3.addActionListener(this);
	track4 = new JTextField("", 20);
	track4.addActionListener(this);
	track5 = new JTextField("", 20);
	track5.addActionListener(this);

	//Artist text field
	artist1 = new JTextField("", 20);
	artist1.addActionListener(this);
	artist2 = new JTextField("", 20);
	artist2.addActionListener(this);
	artist3 = new JTextField("", 20);
	artist3.addActionListener(this);
	artist4 = new JTextField("", 20);
	artist4.addActionListener(this);
	artist5 = new JTextField("", 20);
	artist5.addActionListener(this);

	//HR Text field
	hr1 = new JTextField("", 3);
	hr1.addActionListener(this);
	hr2 = new JTextField("", 3);
	hr2.addActionListener(this);
	hr3 = new JTextField("", 3);
	hr3.addActionListener(this);
	hr4 = new JTextField("", 3);
	hr4.addActionListener(this);
	hr5 = new JTextField("", 3);
	hr5.addActionListener(this);
    }

    /**
     * After text has been entered into the text fields and submitted, this method
     * clears out the text fields so they appear blank once again
     */
    public void resetDJTextFields(){
	//reset text for track text field
	track1.setText("");
	track2.setText("");
	track3.setText("");
	track4.setText("");
	track5.setText("");

	//artist text field reset
	artist1.setText("");
	artist2.setText("");
	artist3.setText("");
	artist4.setText("");
	artist5.setText("");
	
	//hr text field reset
	hr1.setText("");
	hr2.setText("");
	hr3.setText("");
	hr4.setText("");
	hr5.setText("");

    }

    /**
     *Initializes and gives value to each Label for the DJ Panel
     */
 public void makeDJLabels(){
	DJLabel = new JLabel("DJ:");
	DJdateLabel = new JLabel("Date: ");
	DJtrackHeader = new JLabel("Track");
	DJartistHeader = new JLabel("Artist");
	DJhrHeader = new JLabel("HR Album #");
	//lastSong = new JLabel( //code for accessing last song of playlist
	DJlastSongHeader = new JLabel("Last Song Entered:");
	DJlastSong = new JLabel("Track     Artist");
    }

    /**
     * When information is submitted from the Login panel, this method will be
     * called to update the information on the DJ panel
     */
    public void setDJUserLabels(String userName, String date){
	DJLabel.setText("DJ: " + userName);
	DJdateLabel.setText("Date: "+ date);
    }
    /**
     * This method updates the last song on the DJ panel so if the DJ forgets
     * what they last entered, they won't enter it twice
     */
    public void setLastSongLabel(){

	DJlastSong.setText(currentDJ.current.getLastSong());

    }

    /**
     * Initalizes and adds action listeners to all of the buttons on the DJ panel
     */
 public void makeDJButtons(){
	DJsubmitButton = new JButton("Submit and Next");
	DJendButton = new JButton("End Session");
	DJsubmitButton.addActionListener(this);
	DJendButton.addActionListener(this);
    }
    //DJ PANEL ENDS HERE

    //THIS WHOLE SECTION MAKES THE MD PANEL
    /**
     *Adds elements to and styles a panel that represents the MD user page
     *
     * @param m a MD panel that allows the user to interact with the program
     */
    public void makeMDPanel(JPanel m){
	makeMDLabels();
	makeMDButtonsAndTextFields();

	m.setLayout(new BorderLayout(2,2));

	JPanel top = new JPanel();
	top.setLayout(new BorderLayout(2,2));
        JPanel a = new JPanel();
	a.setLayout(new GridLayout(4,3,10,10));

	a.add(MDaddAlbumHeaderLabel);
	a.add(new JLabel(""));
	a.add(new JLabel(""));

	a.add(MDnameLabel);
	a.add(MDartistLabel);
	a.add(new JLabel(""));

	a.add(MDalbumName);
	a.add(MDalbumArtist);
	a.add(MDsubmitButton);

	a.add(MDIDLabel);
	a.add(MDIDNumLabel);

	top.add(MDwelcomeLabel, BorderLayout.NORTH);
	top.add(a, BorderLayout.SOUTH);

	JPanel mid = new JPanel();
	mid.setLayout(new BorderLayout(2,2));
	mid.add(MDchartsHeaderLabel, BorderLayout.NORTH);
	JScrollPane scroll = new JScrollPane(MDchartsField);
	mid.add(scroll, BorderLayout.CENTER);

	JPanel bot = new JPanel();
	bot.setLayout(new GridLayout(2,5,10,10));

	bot.add(MDcompileChartsButton);
	bot.add(MDnewWeekButton);
	bot.add(new JLabel(""));
	bot.add(new JLabel(""));
	bot.add(new JLabel(""));

	bot.add(new JLabel(""));
	bot.add(new JLabel(""));
	bot.add(new JLabel(""));
	bot.add(MDclearButton);
	bot.add(MDendButton);

	m.add(top, BorderLayout.NORTH);
	m.add(mid, BorderLayout.CENTER);
	m.add(bot, BorderLayout.SOUTH);

	//	MDchartsField.setBackground(Color.yellow);
	m.setBackground(Color.darkGray);
	a.setBackground(Color.darkGray);
	bot.setBackground(Color.darkGray);
	mid.setBackground(Color.darkGray);
	top.setBackground(Color.darkGray);

	MDwelcomeLabel.setForeground(Color.orange);
	MDwelcomeLabel.setFont(new Font("sansserif",Font.PLAIN,20));
	MDaddAlbumHeaderLabel.setForeground(Color.white);
	MDaddAlbumHeaderLabel.setFont(new Font("sansserif",Font.PLAIN,18));
	MDnameLabel.setForeground(Color.white);
	MDnameLabel.setFont(new Font("sansserif",Font.PLAIN,16));
	MDartistLabel.setForeground(Color.white);
	MDartistLabel.setFont(new Font("sansserif",Font.PLAIN,16));
	MDIDLabel.setForeground(Color.orange);
	MDIDLabel.setFont(new Font("sansserif",Font.PLAIN,12));
	MDIDNumLabel.setForeground(Color.orange);
	MDIDNumLabel.setFont(new Font("sansserif",Font.PLAIN,12));
	MDchartsHeaderLabel.setForeground(Color.white);
	MDchartsHeaderLabel.setFont(new Font("sansserif",Font.PLAIN,18));

    }

    /**
     * Initializes and adds text to all of the MD labels in the GUI,
     * as well as the text area for the charts
     */
    public void makeMDLabels(){
	MDwelcomeLabel = new JLabel("Welcome, MD");
	MDaddAlbumHeaderLabel = new JLabel("Add Album:");
	MDnameLabel = new JLabel("Name: ");
	MDartistLabel = new JLabel("Artist:");
	MDIDLabel = new JLabel("Please enter an album."); 
	MDchartsHeaderLabel = new JLabel("Charts:");
	MDIDNumLabel = new JLabel("");
	MDchartsField = new JTextArea("",5,10);
    }

    /**
     * Initalizes and adds action listeners to the buttons and text fields in the
     * MD UI
     */
    public void makeMDButtonsAndTextFields(){
	//text fields
	MDalbumName = new JTextField("", 20);
	MDalbumArtist = new JTextField("", 20);
	MDalbumName.addActionListener(this);
	MDalbumArtist.addActionListener(this);

	//buttons
	MDsubmitButton = new JButton("Submit");
	MDcompileChartsButton = new JButton("Compile Charts");
	MDnewWeekButton = new JButton("Start New Week");
	MDclearButton = new JButton("Clear Semester");
	MDendButton = new JButton("Log out");
	MDsubmitButton.addActionListener(this);
	MDcompileChartsButton.addActionListener(this);
	MDnewWeekButton.addActionListener(this);
	MDclearButton.addActionListener(this);	
	MDendButton.addActionListener(this);
    }
    
    //MD PANEL ENDS

    /**
     *Initializes the layout for the GUI and adds the inital panel
     */
    public void init(){
	setLayout(new GridLayout(1, 1, 1, 1));
	//login.setPreferredSize( new Dimension(900, 600));
	add(loginPanel);
	
	
    }
    /**
     *When an action is performed on the GUI, another action is called in reponse.
     *This method makes the GUI function by interacting with the user when they
     *click buttons and enter text
     *
     * @param event When an object is clicked, it calls this method
     */
    public void actionPerformed(ActionEvent event) {
	//handles events generated by Button clicks
	Object source = event.getSource();

	//changes the panel from the login panel based on user input
	if(source.equals(LIsubmitButton)){

	    nameInput = LIusernameField.getText();
	    date = LIdateField.getText();

	    if(nameInput.equals("mdzly")){

		remove(loginPanel);
		add(MDPanel);
		validate();
		repaint();

	    }else{

		currentDJ = acc.login(nameInput);
		currentDJ.addPlaylist(date);
		remove(loginPanel);
		add(DJPanel);
		setDJUserLabels(nameInput, date);
		validate();
		repaint();

	    }
	    
	}else if(source.equals(DJendButton)){
	    
	    currentDJ.printLatestPlaylist();
	    currentDJ = null;
	    date = "";
	    //print playlist
	    remove(DJPanel);
	    add(loginPanel);
	    LIusernameField.setText("");
	    validate();
	    repaint();

	}else if (source.equals(MDendButton)){

	    remove(MDPanel);
	    add(loginPanel);
	    LIusernameField.setText("");
	    validate();
	    repaint();

	}else if(source.equals(MDclearButton)){

	    //popup
	    String pw = JOptionPane.showInputDialog(null, "Enter the password to clear charts.");
	    if(pw.equals("wellesleyzly")){
		lib.clear();
		acc.clear();
		MDIDLabel.setText("Please enter an album.");
		MDIDNumLabel.setText("");
		MDchartsField.setText("");
		validate();
		repaint();
	    } else {
		JOptionPane.showMessageDialog(null, "TRY AGAIN SUCKER");
	    }

	}else if(source.equals(MDnewWeekButton)){

	    lib.newWeek();
	    MDchartsField.setText("");
	    validate();
	    repaint();

	}else if(source.equals(MDcompileChartsButton)){

	    MDchartsField.setText(lib.compileCharts().toString());
	    

	}else if(source.equals(MDsubmitButton)){

	    String artist = MDalbumArtist.getText();
	    String title = MDalbumName.getText();
	    int albumID = lib.addAlbum(artist, title);
	    System.out.println(albumID);
	    MDIDLabel.setText(title+" by "+artist+" is: "); 
	    MDIDNumLabel.setText("#"+albumID);
	    MDalbumArtist.setText("");
	    MDalbumName.setText("");
	    validate();
	    repaint();

	}else if(source.equals(DJsubmitButton)){

	    String song1 = track1.getText() + " - " + artist1.getText();
	    String song2 = track2.getText() + " - " + artist2.getText();
	    String song3 = track3.getText() + " - " + artist3.getText();
	    String song4 = track4.getText() + " - " + artist4.getText();
	    String song5 = track5.getText() + " - " + artist5.getText();
	    String id1 = hr1.getText();
	    String id2 = hr2.getText();
	    String id3 = hr3.getText();
	    String id4 = hr4.getText();
	    String id5 = hr5.getText();
	    
	    if( song1.length()>4 ){
		currentDJ.current.addSong(song1);
	    }
	    
	    if( song2.length()>4 ){
		currentDJ.current.addSong(song2);	
	    }
	    
	    if( song3.length()>4 ){
		currentDJ.current.addSong(song3);	
	    }
	    
	    if( song4.length()>4 ){
		currentDJ.current.addSong(song4);		
	    }
	    
	    if( song5.length()>4 ){
		currentDJ.current.addSong(song5);		
	    }
	    
	    try{
		
		if (!id1.equals("")){
		    int idone = Integer.parseInt(id1);
		    lib.addPlay(idone);
		}
		if (!id2.equals("")){
		    int idtwo = Integer.parseInt(id2);
		    lib.addPlay(idtwo);
		}
		if (!id3.equals("")){
		    int idthree = Integer.parseInt(id3);
		    lib.addPlay(idthree);
		}
		if (!id4.equals("")){
		    int idfour = Integer.parseInt(id4);
		    lib.addPlay(idfour);
		}
		if (!id5.equals("")){
		    int idfive = Integer.parseInt(id5);
		    lib.addPlay(idfive);
		}
	    }catch(Exception e){
		JOptionPane j = new JOptionPane();
		j.showMessageDialog(null, "Please enter a valid number "
				    +"for the HR ID Number");
	    }
		
		try{
		    setLastSongLabel();
		}catch(Exception ex){
		    JOptionPane x = new JOptionPane();
		    x.showMessageDialog(null, "Please enter a track before"
					+" clicking submit");
		}
	    
	    resetDJTextFields();

	    System.out.println(currentDJ.current);



	}

			      

    }


   


}
	