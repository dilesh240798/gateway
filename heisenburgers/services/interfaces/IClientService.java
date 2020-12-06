package com.scb.heisenberg.heisenburgers.services.interfaces;

import java.util.List;

import com.scb.heisenberg.heisenburgers.models.Client;

public interface IClientService {

	List<Client> getClients(long rm) throws Exception;
	Client getClientById(long id) throws Exception;
	Client createClient(Client client) throws Exception;
	Client updateClient(long id, Client client) throws Exception;
	List<Client> upcomingBirthdays(long rmid) throws Exception;
}
