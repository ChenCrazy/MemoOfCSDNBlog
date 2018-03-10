package linkedList;

public class Node {
	
	private final int value;
	private Node next;
	
	public Node(int value){
		this.value = value;
		this.next = null;
	}
	public Node getNext() {
		return next;
	}
	public void setNext(Node next) {
		this.next = next;
	}
	public int getValue() {
		return value;
	}

	public static void printLinkedList(Node head){
		while(head != null){
			System.out.print(head.getValue());
			System.out.print(" ");
			head = head.getNext();
			
		}
		System.out.println();
	}
}
