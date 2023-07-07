package cn.bcf.controller.tenant;

import cn.bcf.dao.tenant.EmployeeDao;
import cn.bcf.entity.tenant.Employee;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/ent/employee")
@Tag(name = "员工管理")
public class EmployeeController {


    @Autowired
    EmployeeDao employeeDao;

    @GetMapping("findAll")
    public List<Employee> findAll() {
        return employeeDao.findAll();
    }

    @PostMapping("saveOrUpdate")
    public Employee saveOrUpdate(@RequestBody Employee request) {
        return employeeDao.save(request);
    }
}