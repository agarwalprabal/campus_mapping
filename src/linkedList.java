
public class linkedList {

	node head;
	node start;
	
	public linkedList() {
		head = null;
		start = null;
	}

	public void insert(int d1, String s1, String s2) {
		node Node = new node(d1, s1, s2);
		if (head == null) {
			head = Node;
			head.nextNode = null;
			start = head;
		} 
		else {
			head.nextNode = Node;
			head = Node;
			head.nextNode=null;
		}

	}

	public void printList() {
		node current = start;
		System.out.println("List--->");
		while (current != null) {
			current.print();
			current = current.nextNode;
		}

	}
}
