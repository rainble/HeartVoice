package server.domain;

public class InteractResult {

    private int code;

    private String path;

    public InteractResult() {
        //do nothing
    }

    public InteractResult(int code, String path) {
        this.code = code;
        this.path = path;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
