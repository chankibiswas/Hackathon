package tomtom.dao;

import java.sql.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import tomtom.bean.Merchant;

@Repository("MerchantDao")
public class MerchantDaoImpl implements MerchantDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Merchant> getMerchants() {
		List<Merchant> Merchants = null;

		try {
			Merchants = jdbcTemplate.query("SELECT * FROM trn_Merchant",
					new BeanPropertyRowMapper<Merchant>(Merchant.class));
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return Merchants;
	}

	public Merchant getMerchant(Long merchantId) {
		Merchant merchant = null;
		try {
			merchant = jdbcTemplate.queryForObject("SELECT * FROM merchant WHERE id = ?",
					new Object[] { merchantId }, new BeanPropertyRowMapper<Merchant>(Merchant.class));
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return merchant;

	}

	public int deleteMerchant(Long MerchantId) {
		int count = jdbcTemplate.update("DELETE from trn_Merchant WHERE Merchant_id = ?", new Object[] { MerchantId });
		return count;
	}

	public int updateMerchant(Merchant Merchant) {
		int count = jdbcTemplate.update(
				"UPDATE trn_Merchant set first_name = ? , last_name = ? , age = ? where Merchant_id = ?",
				new Object[] { Merchant.getUserFirstName(), Merchant.getUserLastName(), Merchant.getEmail(),
						Merchant.getid() });
		return count;
	}

	public int createMerchant(Merchant merchant) {
		int count = jdbcTemplate.update(
				"INSERT INTO merchant(email,user_first_name, user_last_name, registration_date,merchant_qr_code)VALUES(?,?,?,?,?)",
				new Object[] { merchant.getEmail(), merchant.getUserFirstName(), merchant.getUserLastName(),
						new Date(System.currentTimeMillis()), merchant.getMerchantQrCode() });
		return count;
	}

	@Override
	public Merchant getMerchantByQRCode(String qrcode) {
		Merchant merchant = null;
		try {
			merchant = jdbcTemplate.queryForObject("SELECT * FROM merchant WHERE merchant_qr_code = ?",
					new Object[] { qrcode }, new BeanPropertyRowMapper<Merchant>(Merchant.class));
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return merchant;
	}

}