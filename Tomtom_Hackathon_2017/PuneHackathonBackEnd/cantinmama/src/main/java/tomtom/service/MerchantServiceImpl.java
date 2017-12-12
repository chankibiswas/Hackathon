package tomtom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tomtom.bean.Merchant;
import tomtom.dao.MerchantDao;

@Service("merchantService")
public class MerchantServiceImpl implements MerchantService {
	@Autowired
	private MerchantDao merchantDao;

	public List<Merchant> getMerchants() {
		List<Merchant> merchants = merchantDao.getMerchants();
		return merchants;
	}

	public Merchant getMerchant(Long merchantId) {
		Merchant merchant = merchantDao.getMerchant(merchantId);
		return merchant;
	}

	public int deleteMerchant(Long MerchantId) {
		return merchantDao.deleteMerchant(MerchantId);
	}

	public int updateMerchant(Merchant Merchant) {
		return merchantDao.updateMerchant(Merchant);
	}

	public int createMerchant(Merchant merchant) {
		return merchantDao.createMerchant(merchant);
	}

	@Override
	public Merchant getMerchantByQRCode(String qrcode) {
		Merchant merchant = merchantDao.getMerchantByQRCode(qrcode);
		return merchant;
	}
}
