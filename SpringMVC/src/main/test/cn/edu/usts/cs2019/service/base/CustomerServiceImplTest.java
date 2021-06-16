package cn.edu.usts.cs2019.service.base;

import cn.edu.usts.cs2019.dao.CustomerDaoImpl;
import cn.edu.usts.cs2019.dao.base.ICustomerDao;
import cn.edu.usts.cs2019.entity.Customer;
import cn.edu.usts.cs2019.service.CustomerServiceImpl;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/** 
* CustomerServiceImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>4月 5, 2021</pre> 
* @version 1.0 
*/ 
public class CustomerServiceImplTest {
    ICustomerService service;

    @Before
public void before() throws Exception {
    // 从容器中获取service Bean.
    ApplicationContext ctx = new ClassPathXmlApplicationContext(
            "applicationContext.xml");
     service = (ICustomerService) ctx.getBean("service");
}


@After
public void after() throws Exception { 
} 

/** 
* 
* Method: setDao(ICustomerDao dao) 
* 
*/ 
@Test
public void testSetDao() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: login(Customer customer) 
* 
*/ 
@Test
public void testLogin() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: insert(List<Customer> list) 
* 
*/ 
@Test
public void testInsertList() throws Exception {




//TODO: Test goes here... 
} 

/** 
* 
* Method: insert(Customer entity) 
* 
*/ 
@Test
public void testInsertEntity() throws Exception { 
//TODO: Test goes here...
    Customer customer = new Customer(3,"syd","123456","12345566789","30250251@qq.com","skd");
    service.insert(customer);
} 

/** 
* 
* Method: update(Customer entity) 
* 
*/ 
@Test
public void testUpdate() throws Exception { 
//TODO: Test goes here...
    Customer customer = new Customer(3,"wm","123456","12345566789","30250251@qq.com","skd");
    service.saveOrUpdate(customer);
} 

/** 
* 
* Method: delete(Customer entity) 
* 
*/ 
@Test
public void testDelete() throws Exception { 
//TODO: Test goes here...
    Customer customer = new Customer(3,"wm","123456","12345566789","30250251@qq.com","skd");
    service.delete(customer);
} 

/** 
* 
* Method: findByID(Integer id) 
* 
*/ 
@Test
public void testFindByID() throws Exception {

//TODO: Test goes here...


    System.out.println(service.findByID(2).getName());

} 

/** 
* 
* Method: saveOrUpdate(Customer entity) 
* 
*/ 
@Test
public void testSaveOrUpdate() throws Exception { 
//TODO: Test goes here... 
} 


} 
