package com.innovationchef;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.innovationchef.model.ProgressEntity;
import com.innovationchef.repository.DatabaseAccessObject;

@Component
public class Progress {

    @Autowired
    private DatabaseAccessObject dao;

    @Value("${connect4.grid.row}")
    private String row;

    @Value("${connect4.grid.col}")
    private String col;


    @Transactional
    public void run() {
        runInternally();
    }


    public void runInternally() {
        System.out.println(row);
        System.out.println(col);
        this.dao.run();

        ProgressEntity progressEntity = new ProgressEntity();
        progressEntity.setMatchId(96);
        progressEntity.setPlayerId(1001);
        progressEntity.setMove_num(1);
        progressEntity.setNrow(0);
        progressEntity.setNcol(5);
        progressEntity.setTimeS("");

        this.dao.saveProgressObject(progressEntity);
    }



	
}
