package com.innovationchef.model;

import javax.persistence.*;

public class ProgressEntityPK {

    @Id
    private String userId;

    @Id
    private String gameId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }
}
