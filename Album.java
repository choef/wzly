
/**
 * This class creates a new album object that corresponds to a CD in WZLY's HR.
 * Each CD has an artist and an album title.
 *
 * @author Gabriela Lanza and Cassandra Hoef
 * 
 */
//ENTIRELY FINISHED CODING/COMMENTING FOR PRESENTATION

public class Album{
    //instance variables
    /**
     * A string representing the name of the artist
     */
    private String artist;
    /**
     * A String representing the title of the album
     */
    private String album;
    /**
     * An integer representing the album's ID number
     */
    public int idNum;
    /**
     * An integer counting the number of plays an album has each week
     */
    public int numPlays;
    /**
     * A boolean representing whether the album is currently in HR or not.
     */
 

    //constructor
    /**
     *Creates a new Album with the parameters artistName and albumTitle
     *
     * @param artistName the name of the artist who created the album
     * @param albumTitle the title of the album
     * @param idNumber the identification number of the album
     */
    public Album(String artistName, String albumTitle, int idNumber){
	artist = artistName;
	album = albumTitle;
	idNum = idNumber;
	numPlays = 0; 

    }


    //instance methods
   

    /**
     * Returns the string representing the name of the artist
     *
     * @return artist
     */
    public String getArtist(){
	return artist;
    }

    /**
     * Returns a string representing the title of the album
     *
     * @return album
     */
    public String getAlbum(){
	return album;
    }

    /**
     * Returns a string representation of the album object
     *
     * @return Artist: name of artist, Album: name of album
     */
    public String toString(){
	return "Artist: " + artist + " Album: " + album + " " + numPlays +"\n";
    }

}