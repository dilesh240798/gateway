package com.scb.heisenberg.heisenburgers.repositories.interfaces;

import java.util.List;
import java.util.Map;

import com.scb.heisenberg.heisenburgers.models.Notification;
import com.scb.heisenberg.heisenburgers.viewmodel.NotificationCreateViewModel;

public interface INotificationRepository {
	  
	void uploadNotification(List<NotificationCreateViewModel> notification);
	void updateNotification(long id);
	List<Notification> upcommingNotification(long rmid);
	List<Notification> getNotificationByMonth(long rmid);
	List<Notification> getSummary(long rmid) throws Exception;
	List<Notification> getClientNotification(long clientId, int month, int year);
	void updateNotificationStatus() throws Exception;
	Map<String, Integer> rmTrackingFunctions(long rmid);
}
