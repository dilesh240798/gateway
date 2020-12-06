
package com.innovationchef.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "matchtable")
public class GameEntity implements Serializable {

	public GameEntity() {}
	
	public GameEntity(long gameId, int player1,int player2, int nrows, int ncols, String status) {
		this.gameId=gameId;
		this.player1=player1;
		this.player2=player2;
		this.nrows=nrows;
		this.ncols=ncols;
		this.status=status;
	}
	
    @Id
    @Column(name = "match_id")
    private long gameId;

    @Column(name = "first_user")
    private int player1;
    
    @Column(name = "second_user")
    private int player2;

	@Column(name = "rows")
    private int nrows;

    @Column(name = "columns")
    private int ncols;

    @Column(name = "status")
    private String status;

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId2) {
        this.gameId = gameId2;
    }

    public int getNrows() {
        return nrows;
    }

    public void setNrows(int nrows) {
        this.nrows = nrows;
    }

    public int getNcols() {
        return ncols;
    }

    public void setNcols(int ncols) {
        this.ncols = ncols;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPlayer1() {
		return player1;
	}

	public void setPlayer1(int player1) {
		this.player1 = player1;
	}

	public int getPlayer2() {
		return player2;
	}

	public void setPlayer2(int player2) {
		this.player2 = player2;
	}

}
