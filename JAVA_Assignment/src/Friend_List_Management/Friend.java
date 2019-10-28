package Friend_List_Management;

public class Friend {
	private int hashCode;
	public String name; 
	private String group;
	private String phoneNumber;
	private String email;
	private String photo;

	public Friend(String name, String group, String phoneNumber, String email, String photo) {
		this.name = name;
		this.group = group;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.photo = photo;
	}
	
	public void print() {
		System.out.println("Name : " + name + " | Group : " + group + " | Phone-Number : " + phoneNumber
				+ " | E-mail : " + email + " | Photo : " + photo);
	}

	public int getHashCode() {
		return hashCode;
	}

	public void setHashCode(int hashCode) {
		this.hashCode = hashCode;
	}

	public String getName() {
		return name;
	}

	public String getGroup() {
		return group;
	}
	
	public void setGroup(String group) {
		this.group = group;
	}

	public String getphoneNumber() {
		return phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public String getPhoto() {
		return photo;
	}

}
