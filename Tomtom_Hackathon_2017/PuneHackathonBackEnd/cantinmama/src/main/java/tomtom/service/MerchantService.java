package tomtom.service;

import java.util.List;

import tomtom.bean.Merchant;

public interface MerchantService {
	public List<Merchant> getMerchants();

	public Merchant getMerchant(Long merchantId);

	public int deleteMerchant(Long merchantId);

	public int updateMerchant(Merchant merchant);

	public int createMerchant(Merchant merchant);

	public Merchant getMerchantByQRCode(String qrcode);
}
