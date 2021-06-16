package cn.edu.usts.cs2019.jdbc;
//java版本DBHelperMySQ
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

public final class MySQLDBHelper {
    private static Connection conn = null;
    private static String dbName = "";	// 数据库名,如：mysql.
    private static String driver = "";	// 数据库驱动程序名,如:com.mysql.jdbc.Driver.
    private static String url = "";
    private static String host = "";	// 数据库服务器主机(或IP)地址
    private static String port = "";
    private static String userName = "";
    private static String password = "";
    private static String defaultDB = "";	// 默认数据库

    private static Properties prop = null;
    private static InputStream fis = null;	// 输入流对象

    //加载驱动，只需要一次，用静态代码块
    static
    {
        try
        {
            //从dbconfig.properties文件中获取配置信息
            prop = new Properties();
            fis=MySQLDBHelper.class.getClassLoader().getResourceAsStream("dbconfig.properties");

            System.out.println(fis);
            prop.load(fis);

            dbName = prop.getProperty("dbDataBase");// 数据库名(如：mysql)
            driver = prop.getProperty("dbDriver");	// 数据库驱动程序名,如:com.mysql.jdbc.Driver
            host = prop.getProperty("dbHost");
            port = prop.getProperty("dbPort");
            userName = prop.getProperty("dbUserName");
            password = prop.getProperty("dbPassword");
            defaultDB = prop.getProperty("defaultDB"); // 访问的默认数据库
            //构造URL
            url = "jdbc:" + dbName +"://" + host + ":" + port + "/" + defaultDB +
                "?characterEncoding=utf8&useSSL=false&serverTimezone=UTC";
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try{
                fis.close();
            }
            catch(IOException e) {
                e.printStackTrace();
            }
            fis = null;	//垃圾回收
        }
    }

    private static Connection connInstance = null ;

    /**
     * 获取数据库连接
     *
     * @param
     * @return Connection
     */
    public static Connection getConnection() {
        if(conn == null) {
            try {
                // 加载数据库驱动程序
                Class.forName(driver);

                if (null == conn) {
                    conn = DriverManager.getConnection(url, userName, password);

                }
            } catch (ClassNotFoundException e) {
                System.out.println("Sorry,can't find the Driver!");
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return conn;
    }

    /**
     * 执行增删改【Insert、Delete、Update】
     *
     * @param sql SQL语句
     * @return int 影响的记录数
     */
    public static int executeNonQuery(String sql) {
        int result = 0;
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            result = stmt.executeUpdate(sql);
        }
        catch (SQLException err) {
            err.printStackTrace();
            free(null, stmt, null);
        }
        finally {
            free(null, stmt, null);
        }
        return result;
    }

    /**
     * 执行增删改【Insert、Delete、Update】,使用PreparedStatement对象
     *
     * @param sql	带参数的SQL语句
     * @param obj	PreparedStatement对象所需参数
     * @return int	影响的记录数
     */
    public static int executeNonQuery(String sql, Object... obj) {
        int result = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);

            for (int i = 0; i < obj.length; i++) {
                pstmt.setObject(i + 1, obj[i]);
            }
            result = pstmt.executeUpdate();
        }
        catch (SQLException err) {
            err.printStackTrace();
            free(null, pstmt, null);
        }
        finally {
            free(null, pstmt, null);
        }
        return result;
    }

    /**
     * 查询Query【Select】
     *
     * @param sql  Select语句
     * @return ResultSet　结果集
     */
    public static ResultSet executeQuery(String sql) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        }
        catch (SQLException err) {
            err.printStackTrace();
            free(rs, stmt, null);
        }

        return rs;
    }

    /**
     * 查询Query【Select】，使用PreparedStatement对象
     *
     * @param sql	带参数的Select语句
     * @param obj　执行PreparedStatement对象所需的参数
     * @return ResultSet
     */

    public static ResultSet executeQuery(String sql, Object... obj) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);

            for (int i = 0; i < obj.length; i++) {
                pstmt.setObject(i + 1, obj[i]);
            }
            rs = pstmt.executeQuery();
        }
        catch (SQLException err) {
            err.printStackTrace();
            free(rs, pstmt, null);
        }
        return rs;
    }
    /**
     * 查询Query【Select】，使用PreparedStatement对象
     *
     * @param sql	带参数的Select语句
     * @param obj　执行PreparedStatement对象所需的参数
     * @return List   查询结果（记录）以<字段名,值>格式，存储到List中。
     */
    public static List<Map<String,Object>> executeQuery2List(String sql, Object... obj) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Map<String,Object>> list = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);

            for (int i = 0; i < obj.length; i++) {
                pstmt.setObject(i + 1, obj[i]);
            }
            rs = pstmt.executeQuery();
            list = rsToList(rs);
        }
        catch (SQLException err) {
            err.printStackTrace();
        }
        finally {
            free(rs, pstmt, null);
        }
        return list;
    }
    /**
     * 判断记录是否存在
     *
     * @param sql
     * @return Boolean
     */
    public static Boolean isExist(String sql) {
        boolean result = false;

        ResultSet rs = null;

        try {
            rs = executeQuery(sql);
            rs.last();
            int count = rs.getRow();
            if (count > 0) {
                result = true;
            }
        }
        catch (SQLException err) {
            err.printStackTrace();
            free(rs);
        }
        finally {
            free(rs);
        }

        return result;
    }

    /**
     * 判断记录是否存在
     *
     * @param sql
     * @return Boolean
     */
    public static Boolean isExist(String sql, Object... obj) {
        boolean result = false;

        ResultSet rs = null;

        try {
            rs = executeQuery(sql, obj);
            rs.last();
            int count = rs.getRow();
            if (count > 0) {
                result = true;
            }
        }
        catch (SQLException err) {
            err.printStackTrace();
            free(rs);
        }
        finally {
            free(rs);
        }

        return result;
    }

    /**
     * 获取查询记录的总行数
     *
     * @param sql
     * @return int
     */
    public static int getCount(String sql) {
        int result = 0;
        ResultSet rs = null;

        try {
            rs = executeQuery(sql);
            rs.last();
            result = rs.getRow();
        }
        catch (SQLException err) {
            free(rs);
            err.printStackTrace();
        }
        finally {
            free(rs);
        }

        return result;
    }

    /**
     * 获取查询记录的总行数
     *
     * @param sql
     * @param obj
     * @return int
     */
    public static int getCount(String sql, Object... obj) {
        int result = 0;
        ResultSet rs = null;

        try {
            rs = executeQuery(sql, obj);
            rs.last();
            result = rs.getRow();
        }
        catch (SQLException err) {
            err.printStackTrace();
        }
        finally {
            free(rs);
        }

        return result;
    }

    /**
     * 释放【ResultSet】资源
     *
     * @param rs
     */
    public static void free(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        }
        catch (SQLException err) {
            err.printStackTrace();
        }
    }

    /**
     * 释放【Statement】资源
     *
     * @param st
     */
    public static void free(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        }
        catch (SQLException err) {
            err.printStackTrace();
        }
    }

    /**
     * 释放【Connection】资源
     *
     * @param conn
     */
    public static void free(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        }
        catch (SQLException err) {
            err.printStackTrace();
        }
    }

    /**
     * 释放所有数据资源
     *
     * @param rs
     * @param st
     * @param conn
     */
    public static void free(ResultSet rs, Statement st, Connection conn) {
        free(rs);
        free(st);
        free(conn);
    }

    /**
     * 将实体结果集对象转换为map对象结果集
     * @param rs 实体结果集对象
     * @return map对象结果集
     * @throws SQLException
     */
    public static List<Map<String,Object>> rsToList(ResultSet rs) throws SQLException {
        if (rs == null)
            return Collections.EMPTY_LIST;

        ResultSetMetaData md = rs.getMetaData();    //得到结果集(rs)的结构信息，如字段数、字段名等
        int columnCount = md.getColumnCount();      //返回此 ResultSet 对象中的列数

        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();   // 创建返回List对象
        Map<String,Object> rowData = new HashMap<String,Object>();
        while (rs.next()) {
            rowData = new HashMap<String,Object>(columnCount);      //初始化Map容量
            //创建当前记录的数据（加入当前记录），每个记录为<字段名,值>
            for (int i = 1; i <= columnCount; i++) {
                rowData.put(md.getColumnLabel(i), rs.getObject(i));
            }
            list.add(rowData);      //加入当前记录到List
        }
        return list;
    }

}
