import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * ClassName: Add
 * Package: PACKAGE_NAME
 * Description:使用 JDBC 给数据库添加数据的演示代码（包含创建表）
 *
 * @Author 行空XKong
 * @Create 2024/5/6 11:30
 * @Version 1.0
 */
public class Add {
    public static void main(String[] args) throws SQLException {
        //创建 DataSource
        DataSource dataSource = new MysqlDataSource();
        ((MysqlDataSource) dataSource).setUrl("jdbc:mysql://127.0.0.1:3306/JDBC_Demo?" +
                "characterEncoding=utf8&useSSL=false");
        ((MysqlDataSource) dataSource).setUser("root");
        ((MysqlDataSource) dataSource).setPassword("0000");

        //和数据库建立连接
        //getConnection()会抛异常，Connection 记得选 java.sql 的包
        Connection connection = dataSource.getConnection();

        //创建表
        String createTableSql = "create table if not exists student (id int, name varchar(40))";
        PreparedStatement statement = connection.prepareStatement(createTableSql);
        statement.executeUpdate();
        statement.close();

        //构造 sql，无需写 ';'
        //方法一（不够灵活）
        String sql1 = "insert into student values(1, '张三')";
        PreparedStatement statement1 = connection.prepareStatement(sql1);
        //把 sql1 发给服务器，返回值是一个整数，表示影响到的行数
        int n1 = statement1.executeUpdate();
        System.out.println("n1 = " + n1);
        statement1.close();

        //方法二（不优雅也不安全）
        Scanner scanner2 = new Scanner(System.in);
        System.out.print("请输入id：");
        int id2 = scanner2.nextInt();
        System.out.print("请输入姓名：");
        String name2 = scanner2.next();
        String sql2 = "insert into student values(" + id2 + ", '" + name2 + "')";
        PreparedStatement statement2 = connection.prepareStatement(sql2);
        //把 sql2 发给服务器，返回值是一个整数，表示影响到的行数
        int n2 = statement2.executeUpdate();
        System.out.println("n2 = " + n2);
        statement2.close();

        //方法三（优雅，灵活且安全）
        Scanner scanner3 = new Scanner(System.in);
        System.out.print("请输入id：");
        int id3 = scanner3.nextInt();
        System.out.print("请输入姓名：");
        String name3 = scanner3.next();
        String sql3 = "insert into student values(?, ?)";
        PreparedStatement statement3 = connection.prepareStatement(sql3);
        statement3.setInt(1, id3);
        statement3.setString(2, name3);
        //把 sql3 发给服务器，返回值是一个整数，表示影响到的行数
        int n3 = statement3.executeUpdate();
        System.out.println("n3 = " + n3);
        statement3.close();

        connection.close();
    }
}
