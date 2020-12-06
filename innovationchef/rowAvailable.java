package com.innovationchef;

public class rowAvailable {
	
	long[][]matrix;
	long Rows;
	long col;
	public static boolean rowAvailable1(long[][] matrix, long col, long Rows) {
		for(int row=0;row<(int)Rows;row++) {
			if(matrix[row][(int) col]==0) {
				return true;}}
		return false;
	
}


}
