package cn.edu.usts.cs2019.dao;

import cn.edu.usts.cs2019.dao.base.ICustomerDao;
import cn.edu.usts.cs2019.dao.base.IUserDao;
import cn.edu.usts.cs2019.entity.Customer;
import cn.edu.usts.cs2019.entity.User;
import cn.edu.usts.cs2019.jdbc.MySQLDBHelper;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements ICustomerDao {
    private List<Customer> list = new ArrayList<Customer>();
    /**
     * Description: 构造方法
     */
    public CustomerDaoImpl() {
        // 专为内存数据对象设计：初始化

    }

    /**
     * @description:
     * @param: [user]
     * @return: 成功，返回 cn.edu.usts.cs2018.entity.User对象，否则为null.
     */
    @Override
    public Customer login(Customer customer) {
        Customer result = null;


        return result;
    }

    // 批量插入数据
    @Override
    public boolean insert(List<Customer> list) {
        boolean result = true ;

        return result;
    }

    // 插入数据
    @Override
    public boolean insert(Customer entity) {
        int result=0;

        // 准备SQL语句，不同的功能，这个语句不同
        String strSQL = String.format("INSERT INTO Customer (id, name, password, telphone, address,company )" +
                        "VALUES(%d, '%s', '%s', '%s', '%s', '%s');" ,
                entity.getId(), entity.getName(),
                entity.getPassword(), entity.getTelphone(),
                entity.getAddress(), entity.getCompany()
        );
        MySQLDBHelper.executeNonQuery(strSQL);
        // 返回处理结果
        return (result == 0) ? false : true;
    }

    // 更新
    public boolean update(Customer entity) {
        return saveOrUpdate(entity) ;
    }

    // 删除
    @Override
    public boolean delete(Customer entity) {
        int result=0;

        // 准备SQL语句，不同的功能，这个语句不同
        String strSQL = String.format("DELETE FROM Customer where name = '%s'" ,
                entity.getName()
        );
        MySQLDBHelper helper = new MySQLDBHelper();
        result = helper.executeNonQuery(strSQL);
        // 返回处理结果
        return (result == 0) ? false : true;

    }

    @Override
    public Customer findByID(Integer integer) {


        // 准备SQL语句，不同的功能，这个语句不同
        String strSQL = "select * from Customer";
        Customer customer = null;
        ResultSet rs=null;
    try {
        rs = MySQLDBHelper.executeQuery(strSQL);
        while (rs.next()) {
            if (rs.getInt("id") == integer) {
                customer = new Customer(rs.getInt("id"), rs.getString("name"), rs.getString("telphone"), rs.getString("address"), rs.getString("company"), rs.getString("password"));
            }

        }
    } catch (SQLException e) {
        e.printStackTrace();
    }



        // 返回处理结果
        return customer;


    }

    @Override
    public boolean saveOrUpdate(Customer customer) {
        boolean flag = false;
        int result = 0;
        // 准备SQL语句，不同的功能，这个语句不同
        String strSQL = "update Customer set name=?,password=?,telphone=?,address=?,company=? "+"where id=?" ;



        ResultSet rs = null;




            //　创建Statement对象
            result =  MySQLDBHelper.executeNonQuery(strSQL,customer.getName(),customer.getPassword(),customer.getTelphone(),customer.getAddress(),customer.getCompany(),customer.getId());



            // 执行SQL语句

            // 其他处理代码


        // 返回处理结果



    if(result!=0){
        flag=true;
    }
    return flag;

    }
}
