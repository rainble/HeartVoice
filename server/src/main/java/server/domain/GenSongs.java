package server.domain;

public class GenSongs {

    private String kind;

    public GenSongs() {
        //do nothing
    }

    public GenSongs(String kind) {
        this.kind = kind;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }
}
