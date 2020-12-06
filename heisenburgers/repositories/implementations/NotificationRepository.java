package com.scb.heisenberg.heisenburgers.repositories.implementations;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.scb.heisenberg.heisenburgers.models.Notification;
import com.scb.heisenberg.heisenburgers.repositories.interfaces.INotificationRepository;
import com.scb.heisenberg.heisenburgers.utils.NotificationRowMapper;
import com.scb.heisenberg.heisenburgers.viewmodel.NotificationCreateViewModel;

@Repository
@Transactional
public class NotificationRepository implements INotificationRepository {
	private final JdbcTemplate jdbcTemplate;

	public NotificationRepository(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void uploadNotification(List<NotificationCreateViewModel> notification) {
		for (int i = 0; i < notification.size(); i++) {
			String sql = "INSERT INTO notification ( rm_id, cif, notification_type, start_date, due_date, review_status, review_completed, loan_id, loan_currency, loan_amount, loan_tenure, loan_installment_amount, loan_maturity_date, loan_interest_rate, loan_type, deposit_id, deposit_currency, deposit_amount, deposit_tenure, deposit_interest_rate, kyc_document_type, kyc_document_type_id)    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			this.jdbcTemplate.update(sql, notification.get(i).getRmId(), notification.get(i).getCif(),
					notification.get(i).getNotificationType(), notification.get(i).getStartDate(),
					notification.get(i).getDueDate(), notification.get(i).getReviewStatus(),
					notification.get(i).getReviewCompleted(), notification.get(i).getLoanId(),
					notification.get(i).getLoanCurrency(), notification.get(i).getLoanAmount(),
					notification.get(i).getLoanTenure(), notification.get(i).getLoanInstallmentAmount(),
					notification.get(i).getLoanMaturityDate(), notification.get(i).getLoanInterestRate(),
					notification.get(i).getLoanType(), notification.get(i).getDepositID(),
					notification.get(i).getDepositCurrency(), notification.get(i).getDepositAmount(),
					notification.get(i).getDepositTenure(), notification.get(i).getDepositInterestRate(),
					notification.get(i).getKycDocumentType(), notification.get(i).getKycDocumentTypeID());
		}
	}

	@Override
	public void updateNotification(long id) {
		String sql = "UPDATE notification SET review_date=?, review_status=?, review_completed=? WHERE notification_id=?";
		long d = System.currentTimeMillis();
		Date date = new Date(d);
		this.jdbcTemplate.update(sql, date, "Complete", "Yes", id);
	}

	@Override
	public List<Notification> getClientNotification(long clientId, int month, int year) {
		String sql = "SELECT * FROM notification  WHERE cif=? AND EXTRACT (MONTH FROM due_date)=? AND EXTRACT (YEAR FROM due_date)=?";
		RowMapper<Notification> rowMapper = new NotificationRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper, clientId, month, year);
	}

	// upcoming notifications for 7 days All type
	@Override
	public List<Notification> upcommingNotification(long rmid) {

		String sql = "SELECT * FROM notification WHERE rm_id=? AND review_date IS NULL AND abs(due_date::DATE-CURRENT_DATE::DATE) <= 7";

		RowMapper<Notification> rowMapper = new NotificationRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper, rmid);
	}

	// Notifications for every month of every year.
	@Override
	public List<Notification> getNotificationByMonth(long rmid) {
		String sql = "SELECT * FROM notification WHERE rm_id=?";
		RowMapper<Notification> rowMapper = new NotificationRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper, rmid);
	}

	@Override
	public List<Notification> getSummary(long rmid) {
		String sql = "SELECT * FROM notification WHERE rm_id=?";
		RowMapper<Notification> rowMapper = new NotificationRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper, rmid);
	}

	@Override
	public void updateNotificationStatus() {
		String sql = "UPDATE notification SET  review_status='Overdue' WHERE due_date<current_date AND review_completed='No'";
		this.jdbcTemplate.update(sql);
	}

	@Override
	public Map<String, Integer> rmTrackingFunctions(long rmid) {
		String completedsql = "SELECT * from notification where rm_id=? AND review_date::DATE <= due_date::DATE";
		String pastduesql = "SELECT * FROM notification WHERE rm_id =? AND review_date is null AND due_date::DATE<CURRENT_DATE::DATE";
		String pastDueCompletedsql = "SELECT * FROM notification WHERE rm_id = ? AND review_date is not null AND review_date::DATE>=due_date::DATE";
		String actionsleftsql = "SELECT * FROM notification WHERE rm_id=? AND due_date::DATE>CURRENT_DATE::DATE AND review_completed='No' ";
		RowMapper<Notification> rowMapper = new NotificationRowMapper();
		List<Notification> completed = jdbcTemplate.query(completedsql, rowMapper, rmid);
		List<Notification> pastdue = jdbcTemplate.query(pastduesql, rowMapper, rmid);
		List<Notification> pastdueCompleted = jdbcTemplate.query(pastDueCompletedsql, rowMapper, rmid);
		List<Notification> actionsleft = jdbcTemplate.query(actionsleftsql, rowMapper, rmid);
		Map<String, Integer> actionsList = new HashMap<>();
		actionsList.put("actionsleft", actionsleft.size());
		actionsList.put("completedontime", completed.size());
		actionsList.put("pastduecompleted", pastdueCompleted.size());
		actionsList.put("pastdue", pastdue.size());

		return actionsList;
	}

}
