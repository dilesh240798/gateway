package com.scb.heisenberg.heisenburgers.repositories.interfaces;

import java.util.List;

import com.scb.heisenberg.heisenburgers.models.RM;
import com.scb.heisenberg.heisenburgers.viewmodel.RegisterViewModel;


public interface IRmRepository {


	List<RM> getAllMyRm(long rmtlid);

	RM updateRmProfile(long rmid, RM rm) ;
	
	void createNewRm(RM rm);

	RM getMyData(long rmid);

	RM login(long id, String token);

	void logout(long id);


}