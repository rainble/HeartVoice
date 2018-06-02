package server.domain;

public class TypeAndSound {

    private int code;

    private String path;

    public TypeAndSound() {
        //do nothing
    }

    public TypeAndSound(int code, String path) {
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
