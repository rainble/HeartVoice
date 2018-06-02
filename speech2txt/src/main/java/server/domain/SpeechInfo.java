package server.domain;

public class SpeechInfo {

    private String text;

    private String path;

    public SpeechInfo() {
    }

    public SpeechInfo(String text, String path) {
        this.text = text;
        this.path = path;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
