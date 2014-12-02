package com.example.kmp;

import java.util.ArrayList;

/**
 * 字符串匹配的KMP算法 (http://www.ruanyifeng.com/blog/2013/05/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm.html)
 * @author zzwwws
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Scanner s = new Scanner(System.in);
//		String source = s.next();
//		String compare = s.next();
		String source = "BBC ABCDAB ABCDABCDABAE";
		String compare = "ABCDABA";

		ArrayList<Integer> kmp = generateKmpMap(compare);
		int index = match(source, compare,kmp);
		System.out.println("the matched index is " + index);
		
		
	}
	/**
	 * 生成匹配表
	 * @param compare
	 * @return
	 */
	public static ArrayList<Integer> generateKmpMap(String compare){
		ArrayList<Integer> kmp = new ArrayList<Integer>();
		for(int i = 0; i < compare.length(); i ++){
			String[] prefix = null;
			String[] surfix = null;
			if(i > 0){
				prefix = new String[i];
				surfix = new String[i];
			}
			String sub = compare.substring(0,i+1);
			int j = 0; 
			while(j < i){
				prefix[j] = sub.substring(0,j + 1);
				
				surfix[j] = compare.substring(j+1,sub.length());
				
				j++;
			}
			int v = overlapItemLength(prefix, surfix);
			kmp.add(v);
			System.out.println("the overlap count is " + v);
		}
		return kmp;
	}
	
	/**
	 * 重叠元素的长度
	 * @param prefix
	 * @param sufix
	 * @return
	 */
	public static int overlapItemLength(String[] prefix, String[] surfix){
		int cnt = 0;
		if(prefix == null || surfix == null || prefix.length != surfix.length)return 0;
		int length = prefix.length;
		for(int i = 0; i < length ; i++){
			if(prefix[i].equals(surfix[length - 1 - i])){
				if(prefix[i].length() > cnt){
					cnt = prefix[i].length();
				}
			}
		}
		return cnt;
	}

	/**
	 * 查询匹配序列下标，若不匹配返回-1
	 * @param src
	 * @param comp
	 * @param kmp
	 */
	public static int  match(String src, String comp, ArrayList<Integer> kmp){
		
		if(src.isEmpty() || comp.isEmpty() || kmp == null)return -1;
		int slength = src.length();
		int i = 0;
		while(i < slength){
			int j = 0;
			while(j < comp.length()){
				if(src.charAt(i + j) == comp.charAt(j)){
					j ++;
				}else{
					int step = kmp.get(j);
					i += step + 1;
					if(i >= slength)return -1;
					break;
				}	
			}
			
			if(j == comp.length()) return i;

		}
		
		return -1;
		
	}

	
}
