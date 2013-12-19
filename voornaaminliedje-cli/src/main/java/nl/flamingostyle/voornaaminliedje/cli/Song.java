package nl.flamingostyle.voornaaminliedje.cli;

public class Song {

    private String artist;
    private String title;
    private String firstname;
    private String background;
    private int nameIndex;
    private int nameLength;
    private String reporter;
    
	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public int getNameIndex() {
		return nameIndex;
	}

	public void setNameIndex(int nameIndex) {
		this.nameIndex = nameIndex;
	}

	public int getNameLength() {
		return nameLength;
	}

	public void setNameLength(int nameLength) {
		this.nameLength = nameLength;
	}

	public String getReporter() {
		return reporter;
	}

	public void setReporter(String reporter) {
		this.reporter = reporter;
	}
	
}
