package tomtom.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import tomtom.bean.Payment;

@Repository("PaymentDao")
public class PaymentDaoImpl implements PaymentDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Payment> getPayments() {
		List<Payment> payments = null;

		try {
			payments = jdbcTemplate.query("SELECT * FROM trn_Payment",
					new BeanPropertyRowMapper<Payment>(Payment.class));
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return payments;
	}

	public Payment getPayment(Long PaymentId) {
		Payment Payment = null;
		try {
			Payment = jdbcTemplate.queryForObject("SELECT * FROM trn_Payment WHERE Payment_id = ?",
					new Object[] { PaymentId }, new BeanPropertyRowMapper<Payment>(Payment.class));
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return Payment;

	}

	public int deletePayment(Long PaymentId) {
		int count = jdbcTemplate.update("DELETE from trn_Payment WHERE Payment_id = ?", new Object[] { PaymentId });
		return count;
	}

	public int updatePayment(Payment Payment) {
		/*
		 * int count = jdbcTemplate.update(
		 * "UPDATE trn_Payment set first_name = ? , last_name = ? , age = ? where Payment_id = ?"
		 * , new Object[] { Payment.getFirstName(), Payment.getLastName(),
		 * Payment.getEmail(), Payment.getPaymentId() });
		 */
		return 0;
	}

	public int insertPayemntHistory(Payment Payment) {
		int count = jdbcTemplate.update("INSERT INTO payment(user_id,merchant_id,transaction_date, amount, transaction_status)VALUES(?,?,?,?,?)",
				new Object[] { Payment.getUserId(), Payment.getMerchantId(),Payment.getTransactionDate(),Payment.getAmount(),Payment.getTransactionStatus()});

		return 0;
	}

	@Override
	public List<Payment> getPaymentHistoryUser(String transactionStatus,String userId) {
		// TODO Auto-generated method stub
		List<Payment> payments = null;

		try {
			payments = jdbcTemplate.query("SELECT * FROM payment where transaction_status=? and user_id=? ",
					new Object[] { transactionStatus,Integer.parseInt(userId)},
					new BeanPropertyRowMapper<Payment>(Payment.class));
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return payments;
	}

	@Override
	public List<Payment> getPaymentHistoryMerchant(String transactionStatus,String userId) {
		List<Payment> payments = null;
		try {
			payments = jdbcTemplate.query("SELECT * FROM payment where transaction_status=? and merchant_id=? ",
					new Object[] { transactionStatus,Integer.parseInt(userId)},
					new BeanPropertyRowMapper<Payment>(Payment.class));
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return payments;
	}

}