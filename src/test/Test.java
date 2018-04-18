import com.imen.wms.dao.impl.EmployeeDAOImpl;
import com.imen.wms.domain.Employee;
import com.imen.wms.service.impl.EmployeeServiceImpl;
import com.imen.wms.service.impl.SystemMenuServiceImpl;

import java.util.List;

public class Test {
    @org.junit.Test
    public void test(){
        EmployeeServiceImpl employeeService=new EmployeeServiceImpl();
        List<Employee> list=employeeService.listAll();
        for (Employee employee : list) {
            System.out.println(employee);
        }
    }
    @org.junit.Test
    public void test2(){

        SystemMenuServiceImpl systemMenuService=new SystemMenuServiceImpl();
        System.out.println(systemMenuService.hasChidMenu(1L));
    }
}
