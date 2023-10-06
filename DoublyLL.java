package Driver;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class DoublyLL {
	Node head = null;
	Node tail = null;
	
	public static void main(String[] args) throws FileNotFoundException {
		DoublyLL list = new DoublyLL();
		Scanner sc = new Scanner(new File("evenNames.txt"));
		String data;
		
		while (sc.hasNext()) {
			data = sc.next();
			if (data.equals("delete")) {
				data = sc.next();
				list.delete(data);
			}
			else {
				list.add(data);
			}
		}
		
		list.traversePrint();
		System.out.println("============");
		list.reverseTraversePrint();
	}
	
	class Node {
		Node next;
		Node prev;
		String data;
		
		Node(String data) {
			this.data = data;
		}
	}
	
	public void add(String data) {
		Node nn = new Node(data);
		data = data.toLowerCase();
		
		if (head == null) {
			head = tail = nn;
		}
		else if (head.data.compareTo(data) > 0) {
			nn.next = head;
			head.prev = nn;
			head = nn;
		}
		else if (data.compareTo(tail.data) > 0) {
			tail.next = nn;
			nn.prev = tail;
			tail = nn;
		}
		else {
			Node curr = head;
			while (curr != null) {
				if (curr.data.compareTo(data) > 0) {
					curr.prev.next = nn;
					nn.prev = curr.next;
					nn.next = curr;
					curr.prev = nn;
					return;
					
				}
			curr = curr.next;	
			}
		}
	}
	
	public void traversePrint() {
		if (head == null) {
			System.out.println("Empty list.");
		}
		else {
			Node curr = head;
			while (curr != null) {
				System.out.println(curr.data);
				curr = curr.next;
			}
		}
	}
	
	public void delete(String data) {
		
		if (head != null) {
			if (head.data.compareTo(data) == 0) {
				head = head.next;
				if (head == null) {
					tail = null;
				}
				else {
					head.prev = null;
				}
			}
			else {
				Node curr = head;
				while (curr != null) {
					if (curr.data.compareTo(data) == 0) {
						curr.prev.next = curr.next;
						if (curr.next != null)
							curr.next.prev = curr.prev;
						else 
							tail = curr.next;
						return;
					}
					curr = curr.next;
				}
			}
		}
	}
	
	public void reverseTraversePrint() {
		if (head == null) {
			System.out.println("Empty List.");
		}
		else {
			Node curr = tail;
			while (curr != null) {
				System.out.println(curr.data);
				curr = curr.prev;
			}
		}
	}
}
