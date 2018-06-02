package server.domain;

public class Txt2SpeechResult {

    private String text;

    public Txt2SpeechResult() {
    }

    public Txt2SpeechResult(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
