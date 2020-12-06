package com.innovationchef;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innovationchef.model.GameEntity;
import com.innovationchef.model.ProgressEntity;
import com.innovationchef.repository.DatabaseAccessObject;

@RestController
@RequestMapping("api/v1/game")
public class ShowMatrix {

	private final Logger logger= LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private DatabaseAccessObject dao;
	
	GameEntity gameEntity=new GameEntity();
	ProgressEntity grider=new ProgressEntity();

	
	@PostMapping("/play")
	@Transactional
	public void startGame(@RequestBody GameEntity gameEntity1) {
		
		int gameId=(int) gameEntity1.getGameId();
		int firstPlayerInput= gameEntity1.getPlayer1();
		int SecondPlayerInput=gameEntity1.getPlayer2();
		int Rows=gameEntity1.getNrows();
		int Cols=gameEntity1.getNcols();
	
   	 	logger.info("Id:"+String.valueOf(gameId)+" Player-1:"+String.valueOf(firstPlayerInput)+" Player-2:"+String.valueOf(SecondPlayerInput)+" Rows"+String.valueOf(Rows)+" Columns"+String.valueOf(Cols));
   	 	
   	 	gameEntity.setGameId(gameId);
		gameEntity.setPlayer1(firstPlayerInput);
		gameEntity.setPlayer2(SecondPlayerInput);
		gameEntity.setNrows(Rows);
		gameEntity.setNcols(Cols);
		gameEntity.setStatus("open");
		
   	 	List<GameEntity>resultList=this.dao.startGameSelect(gameEntity,gameId);
   	 	Iterator<GameEntity> itr= resultList.iterator();
   	 	
   	 	if(! itr.hasNext()) {
   	 		
			gameEntity.setGameId(gameId);
			gameEntity.setPlayer1(firstPlayerInput);
			gameEntity.setPlayer2(SecondPlayerInput);
			gameEntity.setNrows(Rows);
			gameEntity.setNcols(Cols);
			gameEntity.setStatus("open");
			
			this.dao.startGameInsert(gameEntity);
			System.out.println("Start New Game");
			grider.setMatchId(gameId);
			showMatrix(gameEntity,gameId);return;}
   	 	
		else {
			
			grider.setMatchId(gameId);
			showMatrix(gameEntity,gameId);
			
			long[][] gr= grider.getGrid();
			gameEntity.setGameId(gameId);
			gameEntity.setPlayer1(firstPlayerInput);
			gameEntity.setPlayer2(SecondPlayerInput);
			gameEntity.setNrows(Rows);
			gameEntity.setNcols(Cols);
			gameEntity.setStatus("open");
			System.out.println("Previous Game Restarts");
			
		    List<ProgressEntity>l=this.dao.selectProgress(grider,(int)gameId);
		    Iterator<ProgressEntity>itr1=l.iterator();
		    ProgressEntity last= new ProgressEntity();
			while(itr1.hasNext()) {
				
				ProgressEntity p=itr1.next();
				
				if((int)p.getPlayerId()==(int)(gameEntity.getPlayer1())) {	
					gr[(int) p.getNrow()][(int) p.getNcol()]= 1;}
				
				else {gr[(int) p.getNrow()][(int) p.getNcol()]= 2;}last=p;}
			
			grider.setMove_num(last.getMove_num());
			grider.setGrid(gr);
			grider.getGrid();
			showupMatrix(grider.getGrid());}}
	
	
	@PostMapping("/move")
	@Transactional
	public void matrixFill2(ProgressEntity gride,@RequestBody ProgressEntity grider2) {
		
		ProgressEntity p=new ProgressEntity();
		int playerId=(int) grider2.getPlayerId();
		int matchid=(int) grider2.getMatchId();
		int input=(int) grider2.getNcol();
		int token=(int)playerId;
		
		p.setMatchId(matchid);
		p.setPlayerId(playerId);
		int row=selectRow.selectRow1(grider.getGrid(),input,gameEntity.getNrows());
		p.setNrow(row);
		
		p.setNcol(input);
		int count =countValue(grider,matchid);
		p.setMove_num(count+1);
		grider.setMove_num(count+1);
		
		p.setTimeS("second");
        p.setGrid(grider.getGrid());
        p.setGameEntity(grider.getGameEntity());
		this.dao.saveProgressObject(p);
		grider.getGrid()[row][input]=token;
		showupMatrix(grider.getGrid());
		
   	 	logger.info("Id:"+String.valueOf(matchid)+" Player"+String.valueOf(playerId)+" Move Number:"+String.valueOf(count+1)+" Row Index"+String.valueOf(row)+" Column Index"+String.valueOf(input));
		
		if(winner.winner1(grider.getGrid(),gameEntity.getNrows(),gameEntity.getNcols())) {	
			
			 System.out.println("Player "+String.valueOf(playerId)  +" wins");
			 logger.info("Winner Id: "+String.valueOf(playerId));
			 this.dao.updateGame(gameEntity,gameEntity.getGameId());}}

	
	public Object showMatrix(GameEntity gameEntity,long gameEntity_Id) {
		
		List<GameEntity>rs=this.dao.showMatrixSelect(gameEntity,gameEntity_Id);
	    Iterator<GameEntity> itr= rs.iterator();
	    if(itr.hasNext()) {
	        	
	       	long row =gameEntity.getNrows();
	       	long col =gameEntity.getNcols();
			long [][]gr=new long[(int)row][(int)col];
			grider.setGrid(gr);}
	        
	    return showupMatrix(grider.getGrid());}


	public Object showupMatrix(long [][] gr) {
		for (int i = (int) (gr.length-1); i > -1; i--) { 
        for (int j = 0; j < gr[0].length; j++) { 
        System.out.print(gr[i][j]+ " ");}System.out.println(); }
		return gr;}
	

	public int countValue(ProgressEntity grider,long gameEntity_Id) {
		int count=0;
        List<ProgressEntity>l=this.dao.selectProgress(grider,(int)gameEntity_Id);
        Iterator<ProgressEntity>itr=l.iterator();
        while(itr.hasNext()) {
        	long i=itr.next().getNcol();
        	count+=1;}
		return count;}

}
