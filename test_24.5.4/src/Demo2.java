import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ClassName: Demo2
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author 行空XKong
 * @Create 2024/5/5 17:50
 * @Version 1.0
 */
public class Demo2 {
    public static void main(String[] args) throws SQLException {
        DataSource dataSource = new MysqlDataSource();
        ((MysqlDataSource) dataSource).setURL("jdbc:mysql://127.0.0.1:3306/test_24_5_5?" +
                "characterEncoding=utf8&useSSL=false");
        ((MysqlDataSource) dataSource).setUser("root");
        ((MysqlDataSource) dataSource).setPassword("0000");
        Connection connection = dataSource.getConnection();
        String sql = "select * from student";
        PreparedStatement statement = connection.prepareStatement(sql);

        //执行
        //ResultSet 表示查询的结果集合（临时表）
        ResultSet resultSet = statement.executeQuery();

        //遍历结果结合
        while (resultSet.next()) {
            //"id" 和 "name" 都是列名，指定了别名就写别名
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            System.out.println("id: " + id + ", name: " + name);
        }

        //释放资源
        resultSet.close();
        statement.close();
        connection.close();
    }
}
