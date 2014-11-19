public class node {

	int ID;
	String location;
	String event;
	public node nextNode;

	public node(int d1, String s1, String s2) {

		ID = d1;
		location = s1;
		event = s2;

	}

	public void print() {
		System.out.println(location + ":" + event);
	}

}
