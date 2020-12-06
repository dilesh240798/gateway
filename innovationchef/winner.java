package com.innovationchef;

public class winner {


	public static boolean winner1(long[][] matrix, long Rows, long Cols) {
		// TODO Auto-generated method stub
		for(int i=0;i<Rows-3;i++) {
			for(int j=0;j<Cols-3;j++) {
				if( matrix[i][j]==matrix[i+1][j+1] && matrix[i+1][j+1]==matrix[i+2][j+2] && matrix[i+2][j+2]==matrix[i+3][j+3] && matrix[i][j]!=0) {
					return true;
				}
			}
		}
		for(int i=3;i<Rows;i++) {
			for(int j=0;j<Cols-3;j++) {
				if( matrix[i][j]==matrix[i-1][j+1] && matrix[i-1][j+1]==matrix[i-2][j+2] && matrix[i-2][j+2]==matrix[i-3][j+3] && matrix[i-2][j+2]!=0) {
					return true;
				}
			}
		}
		for(int i=0;i<Rows;i++) {
			for(int j=0;j<Cols-3;j++) {
				if( matrix[i][j]==matrix[i][j+1] && matrix[i][j+1]==matrix[i][j+2] && matrix[i][j+2]==matrix[i][j+3] && matrix[i][j+2]!=0) {
					return true;
				}
			}
		}
		for(int i=0;i<Rows-3;i++) {
			for(int j=0;j<Cols;j++) {
				if( matrix[i][j]==matrix[i+1][j] && matrix[i+1][j]==matrix[i+2][j] && matrix[i+2][j]==matrix[i+3][j] && matrix[i+2][j]!=0) {
					return true;
				}
			}
		}
		return false;
	}


	
}
