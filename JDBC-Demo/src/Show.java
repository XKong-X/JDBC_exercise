import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ClassName: Show
 * Package: PACKAGE_NAME
 * Description:使用 JDBC 让数据库显示数据的演示代码（不包含显示表和显示库）
 *
 * @Author 行空XKong
 * @Create 2024/5/6 12:44
 * @Version 1.0
 */
public class Show {
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

        //构造 sql1，无需写 ';'
        String sql1 = "select * from student";
        PreparedStatement statement1 = connection.prepareStatement(sql1);
        System.out.println("sql1 = " + statement1);//查看打包好的 sql1 语句
        //执行，ResultSet 表示查询的结果集合（临时表）
        ResultSet resultSet1 = statement1.executeQuery();
        //遍历结果集合
        while (resultSet1.next()) {
            //"id" 和 "name" 都是列名，指定了别名就写别名
            int id = resultSet1.getInt("id");
            String name = resultSet1.getString("name");
            System.out.println("id: " + id + ", name: " + name);
        }
        resultSet1.close();
        statement1.close();

        connection.close();
    }
}
