package Utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyUtils {
	public static String[] Splist(String messge, int NumberChars, String ch) {
		List<String> m = new ArrayList<String>();
		String s = "";
		for (int i = 0; i < messge.length(); i++) {
			s += messge.charAt(i);
			if (s.length() == NumberChars) {
				m.add(s);
				s = "";
			}
		}
		if (!s.equals("")) {
			m.add(s);
		}
		if (!ch.isEmpty()) {
			m.add(ch);
		}
		return m.toArray(new String[0]);
	}
}
