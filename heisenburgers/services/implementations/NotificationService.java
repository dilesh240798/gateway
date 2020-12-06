package com.scb.heisenberg.heisenburgers.services.implementations;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.scb.heisenberg.heisenburgers.models.Notification;
import com.scb.heisenberg.heisenburgers.repositories.interfaces.INotificationRepository;
import com.scb.heisenberg.heisenburgers.services.interfaces.INotificationService;
import com.scb.heisenberg.heisenburgers.viewmodel.NotificationCreateViewModel;

@Service
public class NotificationService implements INotificationService {
	private final INotificationRepository repository;
	
	
	
	public NotificationService(INotificationRepository repository) {
		super();
		this.repository = repository;
	}

	public void uploadNotification(List<NotificationCreateViewModel> notification) {
		this.repository.uploadNotification(notification);

    }
	public List<Notification> getClientNotification( long clientId, int month, int year) throws Exception {
		
		try{
		return this.repository.getClientNotification(clientId,month,year);
		}
		catch(Exception e) {
			throw new Exception();
		}

    } 
	  
	public List<Notification> getSummary(long rmid) throws Exception{
			try {
				return this.repository.getSummary(rmid);
			} catch (Exception e) {
				throw new Exception();
			}
		} 
	 
	  
	public void updateNotification(long id) throws Exception {
		try {
		this.repository.updateNotification(id);
		} catch(Exception e) {
			throw new Exception();
		}
	}
	
	public void updateNotificationStatus() throws Exception {
		// TODO Auto-generated method stub
		 try {
			this.repository.updateNotificationStatus();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception();
		}
		
	} 
	 
	public List<Notification> upcommingNotification(long rmid) throws Exception{
		try {
		return this.repository.upcommingNotification(rmid);
		} catch(Exception e) {
			throw new Exception();
		}
	}
	
	public List<Notification> getNotificationByMonth(long rmid) throws Exception{
		try {
		return this.repository.getNotificationByMonth(rmid);
		} catch(Exception e) {
			throw new Exception();
		}
	}
	
	public Map<String,Integer> rmTrackingFunctions(long rmid) throws Exception{
		try {
		return this.repository.rmTrackingFunctions(rmid);
		} catch(Exception e) {
			throw new Exception();
		}
	}
	}

