//package com.innovationchef;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Scanner;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.innovationchef.model.GameEntity;
//import com.innovationchef.model.ProgressEntity;
//import com.innovationchef.repository.DatabaseAccessObject;
//
//@Repository
//@RestController
//@RequestMapping("api/v1/game")
//public class ShowMatrixDemo {
//
//	@Autowired
//    private DatabaseAccessObject dao;
//	GameEntity gameEntity=new GameEntity();
//	ProgressEntity grider=new ProgressEntity();
//	
//	
//	@Transactional
//    public void run() {
//		int id=0;
//		int firstPlayerInput=0;
//		int  SecondPlayerInput=0;
//		int Rows=0;
//		int Cols=0;
//        startGame(id,firstPlayerInput,SecondPlayerInput,Rows,Cols);
//    }
//	
//	
//	public void showupMatrix(long [][] gr) {
//		for (int i = (int) (gr.length-1); i > -1; i--) { 
//        for (int j = 0; j < gr[0].length; j++) { 
//        System.out.print(gr[i][j]+ " ");}System.out.println(); }}
//	
//	@GetMapping("/{id}/{user1}/{user2}/{row}/{col}")
//	@Transactional
//	public void startGame(@PathVariable int id,@PathVariable int user1,@PathVariable int user2,@PathVariable int row,@PathVariable int col) {
//		this.dao.run();
////		Scanner input=new Scanner(System.in);System.out.println("Game Id");
////		int gameId=input.nextInt();System.out.println("Enter Player 1 id");
////   	 	int firstPlayerInput=input.nextInt();System.out.println("Enter Player 2 id");
////   	 	int SecondPlayerInput=input.nextInt();System.out.println("Rows");
////   	 	int Rows=input.nextInt();System.out.println("Columns");
////   	 	int Cols=input.nextInt();String Stat="open";
////		
//		int gameId=id;
//		int firstPlayerInput= user1;
//		int SecondPlayerInput=user2;
//		int Rows=row;
//		int Cols=col;
//   	 	GameEntity ge= new GameEntity();
//	
//	 	List<GameEntity>resultList1=this.dao.startGameSelectOnClosed(ge,gameId);
//   	 	gameEntity.setGameId(gameId);
//		gameEntity.setPlayer1(firstPlayerInput);
//		gameEntity.setPlayer2(SecondPlayerInput);
//		gameEntity.setNrows(Rows);
//		gameEntity.setNcols(Cols);
//		gameEntity.setStatus("open");
//   	 	
//	
//
//			Iterator<GameEntity> itr2= resultList1.iterator();
//		if(itr2.hasNext()) {
//			System.out.println("Restart Game With New Id");
//			gameId=0;
//			firstPlayerInput=0;
//			SecondPlayerInput=0;
//			Rows=0;
//			Cols=0;
////			startGame();}
//		}
//   	 	List<GameEntity>resultList=this.dao.startGameSelect(gameEntity,gameId);
//   	 	Iterator<GameEntity> itr= resultList.iterator();
//   	 	if(! itr.hasNext()) {
//			gameEntity.setGameId(gameId);
//			gameEntity.setPlayer1(firstPlayerInput);
//			gameEntity.setPlayer2(SecondPlayerInput);
//			gameEntity.setNrows(Rows);
//			gameEntity.setNcols(Cols);
//			gameEntity.setStatus("open");
//			this.dao.startGameInsert(gameEntity);
//			System.out.println("Start New Game");
//			grider.setMatchId(gameId);
//			showMatrix(gameId);
//			playGame(gameId,grider.getGrid());
//			return;}
//		else {
//			grider.setMatchId(gameId);
//			showMatrix(gameId);
//			long[][] gr= grider.getGrid();
//			gameEntity.setGameId(gameId);
//			gameEntity.setPlayer1(firstPlayerInput);
//			gameEntity.setPlayer2(SecondPlayerInput);
//			gameEntity.setNrows(Rows);
//			gameEntity.setNcols(Cols);
//			gameEntity.setStatus("open");
//			System.out.println("Previous Game Restarts");
//		    List<ProgressEntity>l=this.dao.selectProgress(grider,(int)gameId);
//		    Iterator<ProgressEntity>itr1=l.iterator();
//			while(itr1.hasNext()) {
//				ProgressEntity p=itr1.next();
//				if((int)p.getPlayerId()==(int)(gameEntity.getPlayer1())) {
//					gr[(int) p.getNrow()][(int) p.getNcol()]= 1;}
//				else {
//					gr[(int) p.getNrow()][(int) p.getNcol()]= 2;}}
//			grider.setGrid(gr);
//			grider.getGrid();
//			showupMatrix(grider.getGrid());
//			playGame(gameId,grider.getGrid());
//			}}
//
//	
//	
////	public void getGame(long givenId) {
////	    List<ProgressEntity>l=this.dao.selectProgress(grider,(int)givenId);
////	    Iterator<ProgressEntity>itr1=l.iterator();
////		if(!itr1.hasNext()) {
////			System.out.println("Start New Game");
////			showMatrix(givenId);
////			playGame(givenId,grider.getGrid());}
////		else {showMatrix(givenId);
////			System.out.println("Previous Game Restarts");
////			while(itr1.hasNext()) {
////				if(itr1.next().getPlayerId()==(gameEntity.getPlayer1())) {
////					grider.getGrid()[(int) itr1.next().getNrow()][(int) itr1.next().getNcol()]= 1;}
////				else {grider.getGrid()[(int) itr1.next().getNrow()][(int) itr1.next().getNcol()]= 2;}}
////					showupMatrix(grider.getGrid());
////					playGame(givenId,grider.getGrid());}} 
//		
//	
//	
//	public void showMatrix(long gameEntity_Id) {
//		GameEntity g= new GameEntity();
//		 List<GameEntity>rs=this.dao.showMatrixSelect(g,gameEntity_Id);
//	        Iterator<GameEntity> itr= rs.iterator();
//	        if(itr.hasNext()) {
//	        	
//	        	long id=gameEntity.getGameId();
//	        	long row =gameEntity.getNrows();
//	        	long col =gameEntity.getNcols();
//	        	long player1=gameEntity.getPlayer1();
//	        	long player2 =gameEntity.getPlayer2();
//	        	String status =gameEntity.getStatus();
//				long [][]gr=new long[(int)row][(int)col];
//				grider.setGrid(gr);
//				}
//	         
//	        showupMatrix(grider.getGrid());
//	        }
//
//	
//	
//	public void matrixFill1(ProgressEntity gride, int id, int row, int col,long player_Id,int move_Num) {
//		ProgressEntity p=new ProgressEntity();
//		p.setMatchId(grider.getMatchId());
//		System.out.println(p.getMatchId());
//		p.setPlayerId(player_Id);
//		p.setNrow(row);
//		p.setNcol(col);
//		p.setMove_num(move_Num+1);
//		p.setTimeS("second");
//        p.setGrid(grider.getGrid());
//        p.setGameEntity(grider.getGameEntity());
//		this.dao.saveProgressObject(p);
//		long[][] gr = grider.getGrid();
//		grider.getGrid()[row][col]=id;}
//	
//	
//	
//	public void gridUpdate(long gameEntity_Id,long FirstUser,int count,long  row,long firstPlayerInput) {
//        grider.setMatchId(gameEntity_Id);
//        grider.setPlayerId(FirstUser);
//        grider.setNrow((int)(row));
//        grider.setNcol((int)(firstPlayerInput));
//        grider.setMove_num(count);
//        grider.setTimeS("second");
//		this.dao.saveProgressObject(grider);
//
//	}	
//	
//	
//	public void playGame(long gameEntity_Id,long[][] grid_init) {
//		boolean gameOver=true;
//		int count=countValue(gameEntity_Id);
//		List<GameEntity>rs=this.dao.showMatrixSelect(gameEntity,(int)gameEntity_Id);
//	    Iterator<GameEntity> itr= rs.iterator();
//	    if(!itr.hasNext()) {
//				grider.setMatchId(gameEntity.getGameId());
//				grider.setGameEntity(gameEntity);}
//		while(gameOver) {
//	    	 Scanner input=new Scanner(System.in);
//	    	 System.out.println("Enter Column Player 1 ");
//	    	 int firstPlayerInput=input.nextInt();
//	    	 grider.setNcol(firstPlayerInput);
//	    	 long[][] gr = grid_init;
//	    	 if (rowAvailable.rowAvailable1(grider.getGrid(), grider.getNcol() , gameEntity.getNrows()) ){count+=1;
//	    		 int row=selectRow.selectRow1(grider.getGrid(),grider.getNcol(),gameEntity.getNrows());
//	    		 matrixFill1(grider,1,row,firstPlayerInput,gameEntity.getPlayer1(),count);
//	    		 showupMatrix(grider.getGrid());}
//	    	 if(winner.winner1(grider.getGrid(),gameEntity.getNrows(),gameEntity.getNcols())) {					
//    			 System.out.println("Player 1 wins");
//    			 this.dao.updateGame(gameEntity,gameEntity.getGameId());
//    			 gameOver=false;
//    			 System.out.println("Game Over");
//    			 return;}
//	    	 System.out.println("Enter Column player 2");
//	    	 int secondPlayerInput=input.nextInt();	
//	    	 grider.setNcol(secondPlayerInput);
//	    	 if (rowAvailable.rowAvailable1(grider.getGrid(), grider.getNcol() , gameEntity.getNrows()) ){count+=1;
//	    		 int row=selectRow.selectRow1(grider.getGrid(),grider.getNcol(),gameEntity.getNrows());
//	    		 matrixFill1(grider,2,row,secondPlayerInput,gameEntity.getPlayer2(),count);
//	    		 for (int i = (int) ((gameEntity.getNrows()) -1); i > -1; i--) {
//	    			 for (int j = 0; j < gameEntity.getNcols(); j++) {
//	    				System.out.print(gr[i][j]+ " ");}
//	    			 	System.out.println();}
//	    		 if(winner.winner1(grider.getGrid(),gameEntity.getNrows(),gameEntity.getNcols())) {	
//	    			 System.out.println("Player 2 wins");
//	    			 this.dao.updateGame(gameEntity,gameEntity.getGameId());
//	    			 gameOver=false;
//	    			 return;}}}}
//
//	
//	
//	private int countValue(long gameEntity_Id) {
//		int count=0;
//        List<ProgressEntity>l=this.dao.selectProgress(grider,(int)gameEntity_Id);
//        Iterator<ProgressEntity>itr=l.iterator();
//        while(itr.hasNext()) {
//        	long i=itr.next().getNcol();
//        	count+=1;
//        }
//		return count;}
//
//	
//	
//	private void updategameEntityTable(long gameEntity_Id) {
//		this.dao.updateGame(gameEntity,(int)gameEntity_Id);
//	
//}
//}
