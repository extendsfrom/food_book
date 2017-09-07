package test.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class Test {
	public static void main(String[] args) {
		Long a = null;
		if(null != a && 0L != a) {
			System.out.println("a");
		} else {
			System.out.println("b");
		}
	}
	
	
	/**
	 * 第一种方法 复杂度较高O（N^2）
	 * @param resArray
	 * @param sum
	 * @return
	 */
	public static List<int[]> twoSumByFirstMethod(int[] resArray, int sum) {
		List<int[]> resultList = new ArrayList<int[]>();
		for(int i=0; i<resArray.length-1; i++) {
			for(int j=i+1; j<resArray.length; j++) {
				if(resArray[i] + resArray[j] == sum) {
					int[] resultArray = new int[2];
					resultArray[0] = i;
					resultArray[1] = j;
					resultList.add(resultArray);
				}
			}
		}
		return resultList;
	}
	
	public static int[] sort(int[] resArray) {
		for(int i=0; i<resArray.length; i++) {
			for(int j=0; j<resArray.length-1; j++) {
				if(resArray[i] < resArray[j]) {
					int sum = resArray[i] + resArray[j];
					resArray[i] = sum - resArray[i];
					resArray[j] = sum - resArray[i];
				}
			}
		}
		return resArray;
	}
	
	/**
	 * 前提：数组是有序的
	 * @param resArray
	 * @param sum
	 * @return
	 */
	public static List<int[]> twoSumBySecondMethod(int[] resArray, int sum) {
		resArray = sort(resArray);
		List<int[]> resultList = new ArrayList<int[]>();
		int i = 0;
		int j = resArray.length-1;
		for(i=0; i<j;) {
			if(resArray[i] + resArray[j] == sum) {
				int[] resultArray = new int[2];
				resultArray[0] = i;
				resultArray[1] = j;
				resultList.add(resultArray);
				i++;
				j--;
			} else if(resArray[i] + resArray[j] < sum) {
				i++;
			} else {
				j--;
			}
		}
		return resultList;
	}
	
}
