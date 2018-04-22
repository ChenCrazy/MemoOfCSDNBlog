package linkedList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecursionListCreator {

	public Node createLinkedList(List<Integer> data){
		if(data.isEmpty()){
			return null;
		}
		Node firstNode = new Node(data.get(0));
		firstNode.setNext(createLinkedList(data.subList(1, data.size())));
		return firstNode;
	}
	
	public static void main(String[] args) {
		RecursionListCreator creator = new RecursionListCreator();
		
		Node.printLinkedList(creator.createLinkedList(new ArrayList<>()));
		Node.printLinkedList(creator.createLinkedList(Arrays.asList(1)));
		Node.printLinkedList(creator.createLinkedList(Arrays.asList(1,2,3,4)));
		Node.printLinkedList(creator.createLargeList(20));

	}
	
	public Node createLargeList(int size){
		Node prev = null;
		Node head = null;
		for(int i = 1; i <= size; i++){
			Node node = new Node(i);
			if(prev != null){
				prev.setNext(node);
			}else{
				head = node;
			}
			prev = node;
		}
		return head;
	}

}
