import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * ClassName: Deom1
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author 行空XKong
 * @Create 2024/5/5 0:00
 * @Version 1.0
 */
public class Demo1 {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入id：");
        int id = scanner.nextInt();
        System.out.print("请输入姓名：");
        String name = scanner.next();

        //创建 DataSource
        DataSource dataSource = new MysqlDataSource();
        ((MysqlDataSource) dataSource).setUrl("jdbc:mysql://127.0.0.1:3306/test_24_5_5?" +
                "characterEncoding=utf8&useSSL=false");
        ((MysqlDataSource) dataSource).setUser("root");
        ((MysqlDataSource) dataSource).setPassword("0000");

        //和数据库建立连接
        //getConnection()会抛异常，Connection 记得选 java.sql 的包
        Connection connection = dataSource.getConnection();

        //构造 sql，无需写 ';'
        //String sql = "insert into student values(1, '张三')";
        //String sql = "insert into student values(" + id + ", '" + name + "')";//不优雅也不安全
        String sql = "insert into student values(?, ?)";
        //String sql = "delete from student where id = 1";
        //String sql = "update student set name = '李四' where id = 1";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.setString(2, name);
        System.out.println("sql = " + statement);

        //把 sql 发给服务器，返回值是一个整数，表示影响到的行数
        int n = statement.executeUpdate();
        System.out.println("n = " + n);

        //释放资源，关闭连接，后获取到的资源要先释放
        statement.close();
        connection.close();
    }
}
