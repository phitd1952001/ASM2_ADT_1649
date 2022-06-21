package Entity;

public class Sender {
	private String name;
	private boolean isEnd;
	
	public Sender(String name) {
		this.name = name;
		this.isEnd = false;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isEnd() {
		return this.isEnd;
	}
	
	public void setIsEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}
}
