package notifier.User;

import java.util.ArrayList;
import java.util.Iterator;

public class User {
	
	private String email;
	private String telephoneNumber;
	private String name;
	private String surname;
	private ArrayList<String> tags;
	
	public enum WayOfNotifying{SMS, EMAIL};
	private ArrayList<WayOfNotifying> waysOfNotifying;
	
	
	
	public User(String email, String name, String surname, ArrayList<String> tags) {
		super();
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.tags = new ArrayList<>();
		this.tags = tags;
	}
	
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	
	public void addWayOfNotifying(WayOfNotifying way) {
		if(!this.waysOfNotifying.contains(way)) {
			this.waysOfNotifying.add(way);
		}
	}
	
	public void removeWayOfNotifying(WayOfNotifying way) {
		this.waysOfNotifying.remove(way);
	}
	
	public String getTelephoneNumber() {
		return this.telephoneNumber;
	}
	
	public void addTag(String tag) {
		this.tags.add(tag);
	}
	
	public void removeTag(String tag) {
		this.tags.remove(tag);
	}
	public String getEmail() {
		return email;
	}
	public String getName() {
		return name;
	}
	public String getSurname() {
		return surname;
	}
	public ArrayList<String> getTags() {
		return tags;
	}
	
	public boolean intrestedIn(String tag) {
		
		if(this.tags == null || this.tags.size() == 0) {
			return false;
		}
		
		Iterator<String> i = tags.listIterator();
		
		while(i.hasNext()) {
			if(i.next() == tag) {
				return true;
			}
		}
		
		return false;
	}
	
}
