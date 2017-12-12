package tomtom.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tomtom.bean.Employee;
import tomtom.dao.EmployeeDao;

import java.util.List;

@RestController
public class UserRegistration {
    @Autowired
    private EmployeeDao employeeService;

    @RequestMapping(value = "/register", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Employee>> employees() {
        return  null;

    }



    }
