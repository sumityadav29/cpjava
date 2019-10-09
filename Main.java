import java.util.*;
import java.io.*;

public class Main{
	public static void main(String args[]){
		/*long bit[] = {0,0,0,0,0,0,0,0,0};
		BIT.rangeAdd(bit, 2, 5, 5);
		BIT.rangeAdd(bit, 2, 3, 5);
		System.out.println(BIT.pointQuery(bit, 3));
		System.out.println(BIT.pointQuery(bit, 6));
		System.out.println(BIT.pointQuery(bit, 0));
		System.out.println(BIT.pointQuery(bit, 4));*/
		
		/*InputReader sc = new InputReader();
		int n = sc.nextInt();
		System.out.println(n);*/
	}
	
	static class InputReader{
		BufferedReader br;
		StringTokenizer st;
        
        public InputReader(){
        	br = new BufferedReader(new
            InputStreamReader(System.in)); 
        }
        
        String next(){
        	while (st == null || !st.hasMoreElements()){
        		try{
        			st = new StringTokenizer(br.readLine()); 
                }catch (IOException  e){
                	e.printStackTrace(); 
                }
            }
            return st.nextToken(); 
        }
        
        int nextInt(){
        	return Integer.parseInt(next()); 
        }
        
        long nextLong(){
        	return Long.parseLong(next()); 
        }
        
        double nextDouble(){
        	return Double.parseDouble(next()); 
        }
        
        String nextLine(){
        	String str = "";
        	try{
        		str = br.readLine();
        	}catch (IOException e){
        		e.printStackTrace(); 
            }
            return str; 
        }
    }
	
}


/*
 * Binary indexed tree (Fenwick tree)
 * Use for either
 * 1. Range query and point update
 * 2. Range update and point query
 * NOTE - Initially array is assumed to be of all 0's, otherwise build using build()
 * NOTE - Don't use simultaneously for both
 */
class BIT{
	
	public static long[] build(long arr[]) {
		long bit[] = new long[arr.length];
		for (int i = 0 ; i < bit.length ; i++) {
			pointUpdate(bit, i, arr[i]);
		}
		return bit;
	}
	
	/*
	 * returns prefix sum till [r]
	 */
	public static long sum(long bit[], int r){
        long res = 0;
        while (r >= 0){
            res += bit[r];
            r = (r & (r+1)) - 1 ;
        }
        return res;
    }
	
	/*
	 * for pointUpdate at idx,
	 * pass val as the increase/decrease in previous value at idx
	 */
	public static void pointUpdate(long bit[], int idx, long val){
        for (; idx < bit.length ; idx = idx | (idx+1)){
            bit[idx] += val;
        }
    }
	
	// rangeQuery
	public static long rangeQuery(long bit[], int l, int r) {
		return sum(bit, r) - sum(bit, l-1);
	}
	
	/*
	 * add val to all elements in [l, r]
	 */
	public static void rangeAdd(long bit[], int l, int r, long val) {
		pointUpdate(bit, l, val);
		pointUpdate(bit, r+1, -val);
	}
    
	// pointQuery
	public static long  pointQuery(long bit[], int idx) {
		return sum(bit, idx);
	}
}
