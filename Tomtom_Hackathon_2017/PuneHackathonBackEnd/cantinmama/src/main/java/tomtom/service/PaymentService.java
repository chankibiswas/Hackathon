package tomtom.service;

import java.util.List;

import tomtom.bean.Payment;

public interface PaymentService {
	public List<Payment> getPayments();

	public Payment getPayment(Long paymentId);

	public int deletePayment(Long paymentId);

	public int updatePayment(Payment payment);

	public int createPayment(Payment Ppayment);
}
