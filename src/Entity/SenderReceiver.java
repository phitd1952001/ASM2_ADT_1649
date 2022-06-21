package Entity;

import ADT.Queue;
import ADT.Stack;
import Utils.MyUtils;

public class SenderReceiver {
	private Sender sender;
	private Receiver receiver;
	private Queue queue;
	private Stack stack;
	private String[] messages;
	private int currIndexOfMessages;
	private String[] packets;
	private int currIndexOfPackets;
	private boolean isEnd;

	public SenderReceiver(Sender sender, Receiver receiver, String message) {
		this.sender = sender;
		this.receiver = receiver;
		this.queue = new Queue(10000);
		this.stack = new Stack(10000);
		this.messages = MyUtils.Splist(message, 250, "");
		this.currIndexOfMessages = -1;
		this.currIndexOfPackets = -1;
	}

	public void send() {
		if (this.sender.isEnd()) {
			return;
		}
		this.currIndexOfPackets++;
		if (this.currIndexOfMessages == -1) {
			this.currIndexOfMessages++;
			this.packets = MyUtils.Splist(this.messages[this.currIndexOfMessages], 25, "|");
		}
		if (this.currIndexOfPackets > this.packets.length - 1) {
			this.currIndexOfMessages++;
			if (this.currIndexOfMessages > this.messages.length - 1) {
				System.out.println(
						"[" + this.sender.getName() + "] to [" + this.receiver.getName() + "] : Finish sending!");
				this.sender.setIsEnd(true);
				return;
			}
			this.packets = MyUtils.Splist(this.messages[this.currIndexOfMessages], 25, "|");
			this.currIndexOfPackets = 0;
		}
		queue.enqueue(this.packets[this.currIndexOfPackets]);
		if (!this.packets[this.currIndexOfPackets].equals("|")) {
			System.out.println("[" + this.sender.getName() + "] to [" + this.receiver.getName() + "] : "
					+ this.packets[this.currIndexOfPackets]);
		}
	}

	public void receiver() {
		if (this.queue.isEmpty() && this.sender.isEnd()) {
			this.setEnd(true);
			return;
		}
		if (!this.queue.isEmpty()) {
			String s = this.queue.dequeue();
			if (s.equals("|")) {
				String x = "";
				while (!this.stack.isEmpty()) {
					x = this.stack.pop() + x;
				}
				System.out.println("[" + this.receiver.getName() + "] from [" + this.sender.getName() + "]: " + x);
			} else {
				System.out.println(
						"[" + this.receiver.getName() + "] from [" + this.sender.getName() + "]: Received packet!");
				this.stack.push(s);
			}
		}
	}

	public Sender getSender() {
		return sender;
	}

	public void setSender(Sender sender) {
		this.sender = sender;
	}

	public Receiver getReceiver() {
		return receiver;
	}

	public void setReceiver(Receiver receiver) {
		this.receiver = receiver;
	}

	public Queue getQueue() {
		return this.queue;
	}

	public boolean isEnd() {
		return isEnd;
	}

	public void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}
}
