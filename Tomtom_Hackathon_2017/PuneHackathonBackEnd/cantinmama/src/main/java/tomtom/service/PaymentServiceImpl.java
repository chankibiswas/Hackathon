package tomtom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tomtom.bean.Payment;
import tomtom.dao.PaymentDao;

@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {
	@Autowired
	private PaymentDao paymentDao;

	public List<Payment> getPayments() {
		List<Payment> Payments = paymentDao.getPayments();
		return Payments;
	}

	public Payment getPayment(Long paymentId) {
		Payment payment = paymentDao.getPayment(paymentId);
		return payment;
	}

	public int deletePayment(Long paymentId) {
		return paymentDao.deletePayment(paymentId);
	}

	public int updatePayment(Payment payment) {
		return paymentDao.updatePayment(payment);
	}

	public int createPayment(Payment payment) {
		return paymentDao.insertPayemntHistory(payment);
	}
}
