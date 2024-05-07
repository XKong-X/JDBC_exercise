package tool;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import operation.UserTable;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * ClassName: tool.EstablishConnection
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author 行空XKong
 * @Create 2024/5/7 14:45
 * @Version 1.0
 */
public class Tool {
    //建立连接
    public static Connection connectDatabase() {
        System.out.println("正在与数据库建立连接...");
        //创建 DataSource
        DataSource dataSource = new MysqlDataSource();
        ((MysqlDataSource) dataSource).setUrl("jdbc:mysql://127.0.0.1:3306/" +
                "Library_management_system?characterEncoding=utf8&useSSL=false");
        ((MysqlDataSource) dataSource).setUser("root");
        ((MysqlDataSource) dataSource).setPassword("0000");

        //和数据库建立连接
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("建立连接失败！", e);
        }

        return connection;
    }

    //登录
    public static void login() throws SQLException {
        System.out.println("正在登录...");
        createSql();
        Scanner in = new Scanner(System.in);
        System.out.print("请输入id：");
        int user_id = in.nextInt();
        Connection connection = Tool.connectDatabase();
        String sql = "select * from user where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, user_id);

        //执行，ResultSet 表示查询的结果集合（临时表）
        ResultSet resultSet = statement.executeQuery();

        //判断结果集合
        boolean flag = true;
        if (resultSet.next()) {
            //"id" 是列名，指定了别名就写别名
            int id = resultSet.getInt("id");
            if (id == user_id) {
                flag = false;
                System.out.println("登录成功！");
            }
        }
        if (flag) {
            System.out.println("没有该用户，请先注册！");
            UserTable.insert();
            System.out.println("注册成功！");
        }

        resultSet.close();
        statement.close();
        connection.close();
    }

    //构建数据库
    private static void createSql() throws SQLException {
        Connection connection = Tool.connectDatabase();

        System.out.println("正在构建数据库...");

        //创建用户表
        //System.out.println("正在创建用户表（假如该表不存在）...");
        String sql = "create table if not exists user (id int primary key auto_increment, name varchar(40))";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.executeUpdate();

        //创建书籍信息表
        //System.out.println("正在创建书籍信息表（假如该表不存在）...");
        sql = "create table if not exists book (id int primary key auto_increment, name varchar(40), " +
                "author varchar(40), price double, type varchar(10))";
        statement = connection.prepareStatement(sql);
        statement.executeUpdate();

        //创建借阅信息表
        //System.out.println("正在创建借阅信息表（假如该表不存在）...");
        sql = "create table if not exists borrow_info (id int primary key auto_increment, book_id int, user_id int, " +
                "foreign key (book_id) references book (id), foreign key (user_id) references user (id))";
        statement = connection.prepareStatement(sql);
        statement.executeUpdate();

        connection.close();
    }
}