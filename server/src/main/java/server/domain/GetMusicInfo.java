package server.domain;

public class GetMusicInfo {

    private String path;

    public GetMusicInfo() {
        //do nothing
    }

    public GetMusicInfo(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
