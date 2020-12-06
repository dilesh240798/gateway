package com.scb.heisenberg.heisenburgers.repositories.interfaces;

import java.util.List;

import com.scb.heisenberg.heisenburgers.models.Client;

public interface IClientRepository {

	List<Client> getClients(long rm);
	Client getClientById(long id);
	Client createClient(Client client);
	Client updateClient(long id, Client client);
	List<Client> upcomingBirthday(long rmid);
}

