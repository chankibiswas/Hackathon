package tomtom.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tomtom.bean.Merchant;
import tomtom.service.MerchantService;

@RestController
public class MerchantController {

	@Autowired
	private MerchantService merchantService;

	@RequestMapping(value = "/merchant", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Merchant>> Merchants() {

		HttpHeaders headers = new HttpHeaders();
		List<Merchant> merchants = merchantService.getMerchants();

		if (merchants == null) {
			return new ResponseEntity<List<Merchant>>(HttpStatus.NOT_FOUND);
		}
		headers.add("Number Of Records Found", String.valueOf(merchants.size()));
		return new ResponseEntity<List<Merchant>>(merchants, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/merchant/{id}", method = RequestMethod.GET)
	public ResponseEntity<Merchant> getMerchant(@PathVariable("id") Long merchantId) {
		Merchant merchant = merchantService.getMerchant(merchantId);
		if (merchant == null) {
			return new ResponseEntity<Merchant>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Merchant>(merchant, HttpStatus.OK);
	}

	@RequestMapping(value = "/merchant/qr/{id}", method = RequestMethod.GET)
	public ResponseEntity<Merchant> getMerchantByQRCode(@PathVariable("id") String qrcode) {
		Merchant merchant = merchantService.getMerchantByQRCode(qrcode);
		if (merchant == null) {
			return new ResponseEntity<Merchant>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Merchant>(merchant, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/merchant/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Merchant> deleteMerchant(@PathVariable("id") Long merchantId) {
		HttpHeaders headers = new HttpHeaders();
		Merchant merchant = merchantService.getMerchant(merchantId);
		if (merchant == null) {
			return new ResponseEntity<Merchant>(HttpStatus.NOT_FOUND);
		}
		merchantService.deleteMerchant(merchantId);
		headers.add("Merchant Deleted - ", String.valueOf(merchantId));
		return new ResponseEntity<Merchant>(merchant, headers, HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/merchant", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Merchant> createMerchant(@RequestBody Merchant merchant) {
		HttpHeaders headers = new HttpHeaders();
		if (merchant == null) {
			return new ResponseEntity<Merchant>(HttpStatus.BAD_REQUEST);
		}
		merchantService.createMerchant(merchant);
		headers.add("Merchant Created  - ", String.valueOf(merchant.getid()));
		return new ResponseEntity<Merchant>(merchant, headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/merchant/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Merchant> updateMerchant(@PathVariable("id") Long merchantId,
			@RequestBody Merchant merchant) {
		HttpHeaders headers = new HttpHeaders();
		Merchant isExist = merchantService.getMerchant(merchantId);
		if (isExist == null) {
			return new ResponseEntity<Merchant>(HttpStatus.NOT_FOUND);
		} else if (merchant == null) {
			return new ResponseEntity<Merchant>(HttpStatus.BAD_REQUEST);
		}
		merchantService.updateMerchant(merchant);
		headers.add("Merchant Updated  - ", String.valueOf(merchantId));
		return new ResponseEntity<Merchant>(merchant, headers, HttpStatus.OK);
	}

}