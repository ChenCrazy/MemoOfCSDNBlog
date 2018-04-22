package linkedList;
//二分查找
public class BinarySearch {
	
	/*此处使用数组而非链表,
	 * 是其需要快速访问到数组任意位置,
	 * 此种场景双向链表需要从头开始,何须二分查找
	 */
	public int binarySearch(int[] arr, int k){
		int a = 0;
		int b = arr.length;
		/* 1. a<=b
		 * 2. k可能包含在[a,b)中
		 * 3. 半开闭空间的优势 
		 * 	 [a,b) +[b,c) = [a,c)
		 * 	 b -a = len([a,b])
		 *   [a,a) ==> empty range
		 *   全闭区间[,]喵喵做不到的 
		 */
		while(a<b){
			//a==b:m=a=b;b==a+1:m=a;b==a+2:m=a+1
			//int m = ( a + b)/2; 			
			int m = a + (b - a)/2; 			
			if(k < arr[m]){
				b = m;
			}else if(k > arr[m]) {
				a = m + 1;
			}else{
				return m;
			}
		}
		
		return -1;
		
	}
	
	public static void main(String[] args){
		BinarySearch bs = new BinarySearch();
		System.out.println(bs.binarySearch(new int[]{1,2,5,10,15,39,100}, 15));//4
		System.out.println(bs.binarySearch(new int[]{1,2,5,10,15,39,100}, -6));//-1
		System.out.println(bs.binarySearch(new int[]{1,2,5,10,15,39,100}, 129));//-1
		System.out.println(bs.binarySearch(new int[]{1,2,5,10,15,39,100}, 12));//-1
		System.out.println(bs.binarySearch(new int[]{}, 12));//-1
		System.out.println(bs.binarySearch(new int[]{12}, 12));//0
		System.out.println(bs.binarySearch(new int[]{13}, 12));//-1
		System.out.println(bs.binarySearch(new int[]{12,13}, 12));//0
	}
}
