package ADT;

public class Stack {
	private int top;
	private String arr[];
	private int size;

	public Stack(int size) {
		this.arr = new String[size];
		top = -1;
	}

	public boolean isEmpty() {
		return (top < 0);
	}

	public void push(String item) {
		if(this.isFull()) {
			System.out.println("Stack is full");
			return;
		}
		this.arr[++top] = item;
	}

	public String pop() {
		return this.arr[top--];
	}

	public boolean isFull() {
		return (top == size - 1);
	}
}
