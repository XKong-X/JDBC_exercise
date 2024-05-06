import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * ClassName: Delete
 * Package: PACKAGE_NAME
 * Description:使用 JDBC 给数据库删除数据的演示代码（包含删除表和删除库）
 *
 * @Author 行空XKong
 * @Create 2024/5/6 12:22
 * @Version 1.0
 */
public class Delete {
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

        //删除表中数据
        String sql1 = "delete from student where id = 1";
        PreparedStatement statement1 = connection.prepareStatement(sql1);
        //把 sql1 发给服务器，返回值是一个整数，表示影响到的行数
        int n1 = statement1.executeUpdate();
        System.out.println("n1 = " + n1);
        statement1.close();

        //删除表
        String dropTableSql = "drop table if exists student";
        PreparedStatement statement2 = connection.prepareStatement(dropTableSql);
        statement2.executeUpdate();
        statement2.close();

        //删除库
        String deleteTableSql = "drop database if exists jdbc_demo";
        PreparedStatement statement3 = connection.prepareStatement(deleteTableSql);
        statement3.executeUpdate();
        statement3.close();

        connection.close();
    }
}
