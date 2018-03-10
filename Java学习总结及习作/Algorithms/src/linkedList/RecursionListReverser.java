package linkedList;

import java.util.ArrayList;
import java.util.Arrays;

public class RecursionListReverser {

	public Node reverseLinkedList(Node head){
		//size == 0 or size ==1
		if(head == null || head.getNext() == null){
			return head;
		}
		Node newHead = reverseLinkedList(head.getNext());
		System.out.print(head.getNext().getValue()+",");
		System.out.print(newHead.getValue()+",");
		head.getNext().setNext(head);
		System.out.print(head.getNext().getValue());
		
		head.setNext(null);
		System.out.println(head.getNext());
		return newHead; 
	}
	public static void main(String[] args) {
		RecursionListCreator creator = new RecursionListCreator();
		RecursionListReverser reverser = new RecursionListReverser();
		
/*		Node.printLinkedList(reverser.reverseLinkedList(
				creator.createLinkedList(new ArrayList<>())));
		Node.printLinkedList(reverser.reverseLinkedList(
				creator.createLinkedList(Arrays.asList(1))));*/
		Node.printLinkedList(reverser.reverseLinkedList(
				creator.createLinkedList(Arrays.asList(1,2,3,4,5,6))));
		/*
		 * 6,6,6null
		 * 5,6,5null
		 * 4,6,4null
		 * 3,6,3null
		 * 2,6,2null
		 * 6 5 4 3 2 1 
		 */
		
		//Exception in thread "main" java.lang.StackOverflowError
//		reverser.reverseLinkedList(
//				creator.createLargeList(100000));
//		System.out.print("done");
	}

}
