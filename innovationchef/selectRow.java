package com.innovationchef;

public class selectRow {


	public static int selectRow1(long[][] matrix, long col, long Rows) {
		// TODO Auto-generated method stub
		for(int row=0;row<Rows;row++) {
			if(matrix[row][(int) col]==0) {
				return row;
			}
		}
		return 0;
	}

	
}
