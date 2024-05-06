import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * ClassName: Update
 * Package: PACKAGE_NAME
 * Description:使用 JDBC 给数据库修改数据的演示代码
 *
 * @Author 行空XKong
 * @Create 2024/5/6 13:11
 * @Version 1.0
 */
public class Update {
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

        //构造 sql，无需写 ';'
        String sql = "update student set name = '李四' where id = 2";
        PreparedStatement statement = connection.prepareStatement(sql);
        System.out.println("sql = " + statement);
        //把 sql 发给服务器，返回值是一个整数，表示影响到的行数
        int n = statement.executeUpdate();
        System.out.println("n = " + n);
        statement.close();

        connection.close();
    }
}