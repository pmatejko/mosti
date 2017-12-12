import java.util.ArrayList;
import java.util.Iterator;

import notifier.SendEmail;
import notifier.User.User;

public class ExampleTest {
	
	public static void main(String[] args) {
		ArrayList<String> tagsOfSzymon = new ArrayList<>();
		ArrayList<String> tagsOfKonrad = new ArrayList<>();
		ArrayList<String> tagsOfChris = new ArrayList<>();
		

		
		tagsOfSzymon.add("pizza");
		tagsOfSzymon.add("budyn");
		tagsOfSzymon.add("ciastko");
		tagsOfSzymon.add("drzewo");
		

		tagsOfKonrad.add("pizza");
		tagsOfKonrad.add("rzeka");
		tagsOfKonrad.add("sufit");
		tagsOfKonrad.add("okno");
		

		tagsOfChris.add("pizza");
		tagsOfChris.add("rzeka");
		tagsOfChris.add("maslo");
		tagsOfChris.add("ciastko");
		
		
		
		User szymon = new User("smalcerzszymonn@gmail.com","Szymon", "Smalcerz", tagsOfSzymon);
		User konrad = new User("asd@gmail.com", "Konrad", "Wieczorek", tagsOfKonrad);
		User chris = new User("dsa@gmail.com", "Chris", "Lada", tagsOfChris);
		
		ArrayList<User> users = new ArrayList<>();
		
		users.add(szymon);
		users.add(konrad);
		users.add(chris);
		
		
		ExampleTest.send(users.iterator(), "pizza");
		ExampleTest.send(users.iterator(), "maslo");
		ExampleTest.send(users.iterator(), "ciastko");
		System.out.println("Wyslane");
		
	}
	
	
	public static void send(Iterator<User> i, String tag) {
		
		while(i.hasNext()) {
			User u = i.next();
			if(u.intrestedIn(tag)){
				SendEmail.send("title", "Szanowny ziomku " + u.getName() + " " + u.getSurname() + "\nbyles zainteresowany w " + tag, u.getEmail());
			}
		}
		
	}
	
	
}
