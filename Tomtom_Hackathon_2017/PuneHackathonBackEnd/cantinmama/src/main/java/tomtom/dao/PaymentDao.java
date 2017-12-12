package tomtom.dao;

import java.util.List;

import tomtom.bean.Payment;

public interface PaymentDao {
	public List<Payment> getPayments();

	public Payment getPayment(Long PaymentId);

	public int deletePayment(Long PaymentId);

	public int updatePayment(Payment Payment);

	public int insertPayemntHistory(Payment Payment);

	public List<Payment> getPaymentHistoryUser(String transactionStatus,String userId);
	public List<Payment> getPaymentHistoryMerchant(String transactionStatus,String userId);
}