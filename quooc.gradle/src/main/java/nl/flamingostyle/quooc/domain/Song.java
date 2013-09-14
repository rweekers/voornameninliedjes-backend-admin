package nl.flamingostyle.quooc.domain;


public class Song {

	private String artist;
	private String title;
	private String firstName;
	
	public Song(){
		
	}
	
	public Song(String title, String artist, String firstName){
		this.title = title;
		this.artist = artist;
		this.firstName = firstName;
	}
	
	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String toString() {
		return "Song [name=" + title + ", artist=" + artist + ", firstname=" + firstName + "]";
	}	
}
