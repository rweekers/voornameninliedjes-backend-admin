package nl.orangeflamingo;

public class Song {

    private final long id;
    private final String content;

    public Song(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
