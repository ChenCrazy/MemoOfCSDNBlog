package linkedList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 列出所有的组合
 * 从data中选取N个元素
 */
public class Combinations {

	public void combinations(List<Integer> selected, List<Integer> data, int n){
		if(n ==0){
			for(Integer i : selected){
				System.out.print(i + " ");
			}
			System.out.println( );
			return;
		}
		if(data.isEmpty()){
			return;
		}
		//select element 0
		selected.add(data.get(0));
		combinations(selected, data.subList(1, data.size()), n-1);
		//un-select element 0
		selected.remove(selected.size() - 1);
		combinations(selected, data.subList(1, data.size()), n);
	}
	
	
	
	public static void main(String[] args) {
		Combinations comb = new Combinations();
		comb.combinations(new ArrayList<>(), Arrays.asList(1,2,3,4,5), 2);
		System.out.println("===============================");
		comb.combinations(new ArrayList<>(), Arrays.asList(), 2);
		System.out.println("===============================");
		comb.combinations(new ArrayList<>(), Arrays.asList(), 0);
		System.out.println("===============================");
		comb.combinations(new ArrayList<>(), Arrays.asList(1,2,3,4,5), 1);
		System.out.println("===============================");
		comb.combinations(new ArrayList<>(), Arrays.asList(1,2,3,4,5), 0);
		System.out.println("===============================");
		comb.combinations(new ArrayList<>(), Arrays.asList(1,2,3,4,5,6,7,8,9,10), 4);
	}
	
/*
 * 1 2 
1 3 
1 4 
1 5 
2 3 
2 4 
2 5 
3 4 
3 5 
4 5 
===============================
===============================

===============================
1 
2 
3 
4 
5 
===============================

===============================
1 2 3 4 
1 2 3 5 
1 2 3 6 
1 2 3 7 
1 2 3 8 
1 2 3 9 
1 2 3 10 
1 2 4 5 
1 2 4 6 
1 2 4 7 
1 2 4 8 
1 2 4 9 
1 2 4 10 
1 2 5 6 
1 2 5 7 
1 2 5 8 
1 2 5 9 
1 2 5 10 
1 2 6 7 
1 2 6 8 
1 2 6 9 
1 2 6 10 
1 2 7 8 
1 2 7 9 
1 2 7 10 
1 2 8 9 
1 2 8 10 
1 2 9 10 
1 3 4 5 
1 3 4 6 
1 3 4 7 
1 3 4 8 
1 3 4 9 
1 3 4 10 
1 3 5 6 
1 3 5 7 
1 3 5 8 
1 3 5 9 
1 3 5 10 
1 3 6 7 
1 3 6 8 
1 3 6 9 
1 3 6 10 
1 3 7 8 
1 3 7 9 
1 3 7 10 
1 3 8 9 
1 3 8 10 
1 3 9 10 
1 4 5 6 
1 4 5 7 
1 4 5 8 
1 4 5 9 
1 4 5 10 
1 4 6 7 
1 4 6 8 
1 4 6 9 
1 4 6 10 
1 4 7 8 
1 4 7 9 
1 4 7 10 
1 4 8 9 
1 4 8 10 
1 4 9 10 
1 5 6 7 
1 5 6 8 
1 5 6 9 
1 5 6 10 
1 5 7 8 
1 5 7 9 
1 5 7 10 
1 5 8 9 
1 5 8 10 
1 5 9 10 
1 6 7 8 
1 6 7 9 
1 6 7 10 
1 6 8 9 
1 6 8 10 
1 6 9 10 
1 7 8 9 
1 7 8 10 
1 7 9 10 
1 8 9 10 
2 3 4 5 
2 3 4 6 
2 3 4 7 
2 3 4 8 
2 3 4 9 
2 3 4 10 
2 3 5 6 
2 3 5 7 
2 3 5 8 
2 3 5 9 
2 3 5 10 
2 3 6 7 
2 3 6 8 
2 3 6 9 
2 3 6 10 
2 3 7 8 
2 3 7 9 
2 3 7 10 
2 3 8 9 
2 3 8 10 
2 3 9 10 
2 4 5 6 
2 4 5 7 
2 4 5 8 
2 4 5 9 
2 4 5 10 
2 4 6 7 
2 4 6 8 
2 4 6 9 
2 4 6 10 
2 4 7 8 
2 4 7 9 
2 4 7 10 
2 4 8 9 
2 4 8 10 
2 4 9 10 
2 5 6 7 
2 5 6 8 
2 5 6 9 
2 5 6 10 
2 5 7 8 
2 5 7 9 
2 5 7 10 
2 5 8 9 
2 5 8 10 
2 5 9 10 
2 6 7 8 
2 6 7 9 
2 6 7 10 
2 6 8 9 
2 6 8 10 
2 6 9 10 
2 7 8 9 
2 7 8 10 
2 7 9 10 
2 8 9 10 
3 4 5 6 
3 4 5 7 
3 4 5 8 
3 4 5 9 
3 4 5 10 
3 4 6 7 
3 4 6 8 
3 4 6 9 
3 4 6 10 
3 4 7 8 
3 4 7 9 
3 4 7 10 
3 4 8 9 
3 4 8 10 
3 4 9 10 
3 5 6 7 
3 5 6 8 
3 5 6 9 
3 5 6 10 
3 5 7 8 
3 5 7 9 
3 5 7 10 
3 5 8 9 
3 5 8 10 
3 5 9 10 
3 6 7 8 
3 6 7 9 
3 6 7 10 
3 6 8 9 
3 6 8 10 
3 6 9 10 
3 7 8 9 
3 7 8 10 
3 7 9 10 
3 8 9 10 
4 5 6 7 
4 5 6 8 
4 5 6 9 
4 5 6 10 
4 5 7 8 
4 5 7 9 
4 5 7 10 
4 5 8 9 
4 5 8 10 
4 5 9 10 
4 6 7 8 
4 6 7 9 
4 6 7 10 
4 6 8 9 
4 6 8 10 
4 6 9 10 
4 7 8 9 
4 7 8 10 
4 7 9 10 
4 8 9 10 
5 6 7 8 
5 6 7 9 
5 6 7 10 
5 6 8 9 
5 6 8 10 
5 6 9 10 
5 7 8 9 
5 7 8 10 
5 7 9 10 
5 8 9 10 
6 7 8 9 
6 7 8 10 
6 7 9 10 
6 8 9 10 
7 8 9 10 

 */
}
