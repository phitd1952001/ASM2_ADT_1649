package ADT;

public class Queue {
	private String arr[];
	private int front;
	private int rear;
	private int size;

	public Queue(int size) {
		this.size = size;
		this.front = 0;
		this.rear = -1;
		this.arr = new String[size];
	}

	public String dequeue() {
		return arr[front++];
	}

	public void enqueue(String item) {
		try {
			arr[++rear] = item;
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("your queue is full");
			e.printStackTrace();
		}
	}

	public boolean isEmpty() {
		return (this.front > this.rear);
	}

	public boolean isFull() {
		return (this.rear == this.size - 1);
	}
}
