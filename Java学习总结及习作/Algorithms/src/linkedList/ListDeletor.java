package linkedList;

import java.util.ArrayList;
import java.util.Arrays;

public class ListDeletor {

	
	public Node deleteIfEquals(Node head, int value){
		//将if条件判断改为while不断判断
		//先判断是否为空避免getValue时空指针异常
		while(head != null && head.getValue() == value){
			head = head.getNext();
		}
		
		if(head == null){
			return null;
		}
		
		Node prev = head;
		while(prev.getNext() != null){
			if(prev.getNext().getValue()==value){
				prev.setNext(prev.getNext().getNext());
			}else{
				prev = prev.getNext();
			}
			
		}
		return head;
	}
	
	
	public static void main(String[] args) {
		RecursionListCreator creator = new RecursionListCreator();
		ListDeletor deletor = new ListDeletor();
		
		Node.printLinkedList(deletor.deleteIfEquals(
				creator.createLinkedList(Arrays.asList(1,2,3,4,2,5,7,2,6)),2));
		
		Node.printLinkedList(deletor.deleteIfEquals(
				creator.createLinkedList(Arrays.asList(1,2,3,4,2,5,7,6,2,2,2)),2));
		
		Node.printLinkedList(deletor.deleteIfEquals(
				creator.createLinkedList(Arrays.asList(2,2,1,2,3,4,2,5,7,2,6)),2));
		
		Node.printLinkedList(deletor.deleteIfEquals(
				creator.createLinkedList(Arrays.asList(2,2,1,2,3,4,2,5,7,2,6)),2));

		Node.printLinkedList(deletor.deleteIfEquals(
				creator.createLinkedList(Arrays.asList(2,2)),2));
		
		Node.printLinkedList(deletor.deleteIfEquals(
				creator.createLinkedList(new ArrayList<Integer>()),2));
		
		Node.printLinkedList(deletor.deleteIfEquals(
				creator.createLinkedList(Arrays.asList(2,2,1,2,3,4,2,5,7,2,6)),9));
	}

}
