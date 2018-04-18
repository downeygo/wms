import com.imen.wms.domain.Employee;
import com.imen.wms.service.IEmployeeService;
import com.imen.wms.service.ISystemMenuService;
import com.imen.wms.service.impl.SystemMenuServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class App {
    @Autowired
    IEmployeeService employeeService;
    @Autowired
    ISystemMenuService systemMenuService;
    @Test
    public void test1()throws Exception{
        for (int i = 0; i < 10; i++) {
            Employee employee=new Employee();
            employee.setName("test"+i);
            employee.setPassword("123456");
            employeeService.save(employee);
        }
    }

    @Test
    public void test2()throws Exception{
        //System.out.println(employeeService.checkLogin("imlent","123456"));
        //SystemMenuServiceImpl systemMenuService=new SystemMenuServiceImpl();
        //System.out.println(systemMenuService.hasChidMenu(1L));
        System.out.println(systemMenuService.listMenu(8L));
    }

}
