package Main;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import Entity.Receiver;
import Entity.Sender;
import Entity.SenderReceiver;

public class Main {
	private static String USER_DIR = System.getProperty("user.dir") + "/src";

	public static void main(String[] args) throws InterruptedException {
		long start = System.currentTimeMillis();
		String message1 = "";
		String message2 = "";
		String message3 = "";
		try {
			File myObj = new File(Main.USER_DIR + "/Main/message1.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				message1 = myReader.nextLine();
			}
			myObj = new File(Main.USER_DIR + "/Main/message2.txt");
			myReader.close();
			myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				message2 = myReader.nextLine();
			}
			myObj = new File(Main.USER_DIR + "/Main/message3.txt");
			myReader.close();
			myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				message3 = myReader.nextLine();
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Can not find your message!");
			e.printStackTrace();
		}
		if (message1.equals("")) {
			System.out.println("Message can not be empty");
			return;
		}
		if (message2.equals("")) {
			System.out.println("Message can not be empty");
			return;
		}
		if (message3.equals("")) {
			System.out.println("Message can not be empty");
			return;
		}

		Sender sender1 = new Sender("Sender 1");
		Sender sender2 = new Sender("Sender 2");
		Sender sender3 = new Sender("Sender 3");

		Receiver receiver1 = new Receiver("Receiver 1");
		Receiver receiver2 = new Receiver("Receiver 2");
		Receiver receiver3 = new Receiver("Receiver 3");

		SenderReceiver conversation1 = new SenderReceiver(sender1, receiver2, message1);
		SenderReceiver conversation2 = new SenderReceiver(sender1, receiver1, message2);
		SenderReceiver conversation3 = new SenderReceiver(sender2, receiver1, message3);
		SenderReceiver conversation4 = new SenderReceiver(sender3, receiver3, message2);

		List<SenderReceiver> conversation = new ArrayList<SenderReceiver>(
				Arrays.asList(conversation1, conversation2, conversation3, conversation4));

		while (conversation.size() > 0) {
			double r = Math.random();
			if (r <= 1) {
				Random random = new Random();
				int num = random.nextInt(conversation.size());
				SenderReceiver conversationOfSender = conversation.get(num);
				if (!conversationOfSender.isEnd()) {
					conversationOfSender.send();
					Thread.sleep(800);
				}
			}
			if (r <= 0.4) {
				Random random = new Random();
				int num = random.nextInt(conversation.size());
				SenderReceiver conversationOfSender = conversation.get(num);
				if (!conversationOfSender.isEnd()) {
					conversation.remove(conversationOfSender);
					continue;
				}
				conversationOfSender.receiver();
				Thread.sleep(100);
			}
		}
		long end = System.currentTimeMillis();
		NumberFormat formarter = new DecimalFormat("#0.000000");
		System.out.println("Finish");
		System.out.println("Execution time is: " + formarter.format((end - start)/10000d) + " seconds");
		
	}
}
