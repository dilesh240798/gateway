package com.innovationchef;

import com.innovationchef.model.GameEntity;
import com.innovationchef.model.ProgressEntity;
import com.innovationchef.repository.DatabaseAccessObject;


import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class Game {

    @Autowired
    private DatabaseAccessObject dao;
    GameEntity gameEntity= new GameEntity();
    ProgressEntity grider=new ProgressEntity();

    @Value("${connect4.grid.row}")
    private String row;

    @Value("${connect4.grid.col}")
    private String col;


    @Transactional
    public void run() {
    	ProgressEntity gride= new ProgressEntity();
        runInternally(gride);
    }

    public void runInternally(ProgressEntity gride) {
    	this.dao.run();
    	grider.setMatchId(88);
    	grider.setPlayerId(1);
    	grider.setMove_num(1);
    	grider.setNrow(1);
    	grider.setNcol(0);
    	grider.setTimeS("second");
    	grider.setGrid(grider.getGrid());
    	grider.setGameEntity(grider.getGameEntity());
    	this.dao.saveProgressObject(grider);
    	
    }
}
