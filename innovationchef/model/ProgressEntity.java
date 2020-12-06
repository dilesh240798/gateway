package com.innovationchef.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
//@IdClass(ProgressEntityPK.class)
@Table(name = "gridstorage")
public class ProgressEntity implements Serializable{

	public ProgressEntity() {}
	
	public ProgressEntity(long matchId,long playerId,int move_num,long nrow,long ncol, String timeS) {
		this.playerId=playerId;
		this.move_num=move_num;
		this.nrow=nrow;
		this.ncol=ncol;
		this.timeS=timeS;
	}
	
    @Id
    @Column(name = "match_id")
    private long matchId;

    @Column(name = "player_id")
    private long playerId;
    
    @Id
    @Column(name = "move_number")
    private int move_num;

    @Column(name = "row_index")
    private long nrow;

    @Column(name = "column_index")
    private long ncol;

    @Column(name = "time")
    private String timeS;
    
    @Transient
    private GameEntity gameEntity;
    
    @Transient
    private long[][] grid;
    
    public GameEntity getGameEntity() {
    	return gameEntity;
    }
    
    public void setGameEntity(GameEntity gameEntity) {
    	this.gameEntity=gameEntity;
    }

	public long getMatchId() {
		return matchId;
	}

	public void setMatchId(long matchId) {
		this.matchId = matchId;
	}

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public int getMove_num() {
		return move_num;
	}

	public void setMove_num(int move_num) {
		this.move_num = move_num;
	}

	public long getNrow() {
		return nrow;
	}

	public void setNrow(int nrow) {
		this.nrow = nrow;
	}

	public long getNcol() {
		return ncol;
	}

	public void setNcol(int ncol) {
		this.ncol = ncol;
	}

	public String getTimeS() {
		return timeS;
	}

	public void setTimeS(String timeS) {
		this.timeS = timeS;
	}
	public long[][] getGrid() {
		return grid;
	}
	public void setGrid(long[][] ls) {
		this.grid = ls;
	}
    
}
