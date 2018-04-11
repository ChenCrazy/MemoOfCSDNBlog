package linkedList;
import java.util.ArrayList;
import java.util.Arrays;

import linkedList.Node;
public class LoopListReverser {

	public Node reverseList(Node head){
		Node newHead = null;
		Node curHead = head;
		//loop invariant
		//newHead points to the linked list already reversed
		//curHead points to the linked list not yet reversed
		while(curHead != null){
			Node next = curHead.getNext();
			curHead.setNext(newHead);
			newHead = curHead;
			curHead = next;
		}
		return newHead;
		
		
		
		
		
	}
	public static void main(String[] args) {
		RecursionListCreator creator = new RecursionListCreator();
		LoopListReverser reverser = new LoopListReverser();
		
 		Node.printLinkedList(reverser.reverseList(
				creator.createLinkedList(new ArrayList<>())));
		Node.printLinkedList(reverser.reverseList(
				creator.createLinkedList(Arrays.asList(1)))); //1
		Node.printLinkedList(reverser.reverseList(
				creator.createLinkedList(Arrays.asList(1,2,3,4,5,6))));//6 5 4 3 2 1 

		Node.printLinkedList(reverser.reverseList( 
				creator.createLargeList(100)));
		
		reverser.reverseList( 
				creator.createLargeList(100000));
		System.out.print("done");
				
		}
		
		

}
