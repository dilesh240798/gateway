package com.scb.heisenberg.heisenburgers.services.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.scb.heisenberg.heisenburgers.models.Client;
import com.scb.heisenberg.heisenburgers.repositories.interfaces.IClientRepository;
import com.scb.heisenberg.heisenburgers.services.interfaces.IClientService;


@Service
public class ClientService implements IClientService {

	private final IClientRepository repository;

	public ClientService(IClientRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public List<Client> getClients(long rm) throws Exception {
		try{
			return this.repository.getClients(rm);
		}catch(Exception e) {
			throw new Exception();
		}
	}

	@Override
	public Client getClientById(long id) throws Exception{
		try{
			return this.repository.getClientById(id);
		}catch(Exception e) {
			throw new Exception();
		}
	}

	@Override
	public Client createClient(Client client)throws Exception {
		try{
			return this.repository.createClient(client);
		}catch(Exception e) {
			throw new Exception();
		}
	}
	
	@Override
	public Client updateClient (long id, Client client) throws Exception{
		try{
			return this.repository.updateClient(id, client);
		}catch(Exception e) {
			throw new Exception();
		}
	}
	
	@Override
	public List<Client> upcomingBirthdays(long rmid)throws Exception{
		try{
			return this.repository.upcomingBirthday(rmid);
		}catch(Exception e) {
			throw new Exception();
		}
	}

}
