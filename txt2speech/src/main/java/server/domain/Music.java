package server.domain;

public class Music {

	private String name;

	private String addr;

	public Music() {
	}

	public Music(String name, String addr) {
		this.name = name;
		this.addr = addr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

}
