package operation;

import tool.Tool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * ClassName: BorrowInfoTable
 * Package: operation
 * Description:借阅信息表相关操作
 *
 * @Author 行空XKong
 * @Create 2024/5/7 20:19
 * @Version 1.0
 */
public class BorrowInfoTable {
    //在借阅信息表中指定id插入数据
    public static void insert() throws SQLException {
        System.out.println("正在使用：“在借阅信息表中指定id插入数据”的功能");
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入id：");
        int id = scanner.nextInt();
        System.out.print("请输入书籍id：");
        int borrow_info_book_id = scanner.nextInt();
        System.out.print("请输入用户id：");
        int borrow_info_user_id = scanner.nextInt();

        Connection connection = Tool.connectDatabase();
        String sql = "insert into borrow_info values(?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.setInt(2, borrow_info_book_id);
        statement.setInt(3, borrow_info_user_id);

        //把 sql 发给服务器，返回值是一个整数，表示影响到的行数
        int n = statement.executeUpdate();
        System.out.println("插入了" + n + "行数据");

        statement.close();
        connection.close();
    }

    //在借阅信息表中自增id插入数据
    public static void autoIncrementInsert() throws SQLException {
        System.out.println("正在使用：“在借阅信息表中自增id插入数据”的功能");
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入书籍id：");
        int borrow_info_book_id = scanner.nextInt();
        System.out.print("请输入用户id：");
        int borrow_info_user_id = scanner.nextInt();

        Connection connection = Tool.connectDatabase();
        String sql = "insert into borrow_info values(null, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, borrow_info_book_id);
        statement.setInt(2, borrow_info_user_id);

        //把 sql 发给服务器，返回值是一个整数，表示影响到的行数
        int n = statement.executeUpdate();
        System.out.println("插入了" + n + "行数据");

        statement.close();
        connection.close();
    }

    //在借阅信息表中删除指定数据
    public static void delete() throws SQLException {
        System.out.println("正在使用：“在借阅信息表中删除指定数据”的功能");
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入要删除的数据的id：");
        int borrow_info_id = scanner.nextInt();

        Connection connection = Tool.connectDatabase();
        String sql = "delete from borrow_info where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, borrow_info_id);

        //把 sql 发给服务器，返回值是一个整数，表示影响到的行数
        int n = statement.executeUpdate();
        System.out.println("删除了" + n + "行数据");

        statement.close();
        connection.close();
    }

    //删除借阅信息表中所有数据
    public static void deleteAll() throws SQLException {
        System.out.println("正在使用：“删除借阅信息表中所有数据”的功能");
        Connection connection = Tool.connectDatabase();
        String sql = "delete from borrow_info";
        PreparedStatement statement = connection.prepareStatement(sql);

        //把 sql 发给服务器，返回值是一个整数，表示影响到的行数
        int n = statement.executeUpdate();
        System.out.println("删除了" + n + "行数据");

        statement.close();
        connection.close();
    }

    //在借阅信息表中查找指定数据
    public static void select() throws SQLException {
        System.out.println("正在使用：“在借阅信息表中查找指定数据”的功能");
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入要查找的用户id：");
        int borrow_info_user_id = scanner.nextInt();

        Connection connection = Tool.connectDatabase();
        String sql = "select * from borrow_info where user_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, borrow_info_user_id);

        //执行，ResultSet 表示查询的结果集合（临时表）
        ResultSet resultSet = statement.executeQuery();

        //遍历结果集合
        boolean flag = true;
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int book_id = resultSet.getInt("book_id");
            int user_id = resultSet.getInt("user_id");
            System.out.println("id: " + id + ", book_id: " + book_id + ", user_id: " + user_id);
            flag = false;
        }
        if (flag) {
            System.out.println("当前表没有要查找的数据！");
        }

        resultSet.close();
        statement.close();
        connection.close();
    }

    //在借阅信息表中查看全部数据
    public static void selectAll() throws SQLException {
        System.out.println("正在使用：“在借阅信息表中查看全部数据”的功能");

        Connection connection = Tool.connectDatabase();
        String sql = "select * from borrow_info";
        PreparedStatement statement = connection.prepareStatement(sql);

        //执行，ResultSet 表示查询的结果集合（临时表）
        ResultSet resultSet = statement.executeQuery();

        //遍历结果集合
        boolean flag = true;
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int book_id = resultSet.getInt("book_id");
            int user_id = resultSet.getInt("user_id");
            System.out.println("id: " + id + ", book_id: " + book_id + ", user_id: " + user_id);
            flag = false;
        }
        if (flag) {
            System.out.println("当前表没有数据！");
        }

        resultSet.close();
        statement.close();
        connection.close();
    }

    //在借阅信息表中修改指定数据
    public static void update() throws SQLException {
        System.out.println("正在使用：“在借阅信息表中修改指定数据”的功能");
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入要修改的数据的id：");
        int borrow_info_id = scanner.nextInt();
        System.out.print("请输入要修改的数据的id：");
        int book_id = scanner.nextInt();
        System.out.print("请输入要修改的数据的id：");
        int user_id = scanner.nextInt();

        Connection connection = Tool.connectDatabase();
        String sql = "update borrow_info set book_id = ?, user_id = ? where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, book_id);
        statement.setInt(2, user_id);
        statement.setInt(3, borrow_info_id);

        //把 sql 发给服务器，返回值是一个整数，表示影响到的行数
        int n = statement.executeUpdate();
        System.out.println("修改了" + n + "行数据");

        statement.close();
        connection.close();
    }
}
