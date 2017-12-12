package tomtom.Controller;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

import tomtom.bean.Employee;
import tomtom.bean.Payment;
import tomtom.dao.EmployeeDao;
import tomtom.dao.PaymentDao;

@RestController
public class PaymentContraller {
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    PaymentDao paymentDao;

    @RequestMapping(value = "/payment/internal", method = RequestMethod.POST, produces = "application/json")
    public String makeInternalPayment(@RequestBody Payment payment) {

        HttpHeaders headers = new HttpHeaders();
        Employee e = employeeDao.getEmployee(payment.getUserId());
        if (e == null) {
            return "{\"status\":\"fail\",\n" +
                    "\"description\":\"employee does not exists\"\n" +
                    "}";
        }
        e.setDueAmmount(e.getDueAmmount() + payment.getAmount());
        employeeDao.updateEmployee(e);
        payment.setTransactionStatus("internalTransaction");


        if (paymentDao.insertPayemntHistory(payment) == 0) {
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = "";
            try {
                jsonInString = mapper.writeValueAsString(e);
            } catch (JsonProcessingException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            return jsonInString;

        } else {
            return "{\"status\":\"fail\"}";
        }
    }

    @RequestMapping(value = "/payment/sodexo", method = RequestMethod.POST, produces = "application/json")
    public String makeSodexoPayment(@RequestBody Payment payment) {

        HttpHeaders headers = new HttpHeaders();
        Employee e = employeeDao.getEmployee(payment.getUserId());
        e.setDueAmmount(e.getDueAmmount() - payment.getAmount());
        employeeDao.updateEmployee(e);
        payment.setTransactionStatus("Sodexo");
        if (paymentDao.insertPayemntHistory(payment) == 0) {
            return "{\"status\":\"success\"}";
        } else {
            return "{\"status\":\"fail\"}";
        }
    }

    @RequestMapping(value = "/paymenthistory/{type}/{user_type}/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Payment>> employees(@PathVariable("type") String transactionStatus, @PathVariable("user_type") String userType,
                                                   @PathVariable("id") String id) {
        List<Payment> payment = null;
        HttpHeaders headers = new HttpHeaders();
        if (userType.equalsIgnoreCase("user"))
            payment = paymentDao.getPaymentHistoryUser(transactionStatus, id);
        else
            payment = paymentDao.getPaymentHistoryMerchant(transactionStatus, id);

        if (payment == null) {
            return new ResponseEntity<List<Payment>>(HttpStatus.NOT_FOUND);
        }
        headers.add("Number Of Records Found", String.valueOf(payment.size()));
        return new ResponseEntity<List<Payment>>(payment, headers, HttpStatus.OK);
    }

}
