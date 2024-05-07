package operation;

import tool.Tool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * ClassName: User
 * Package: operation
 * Description:用户表相关操作
 *
 * @Author 行空XKong
 * @Create 2024/5/7 13:36
 * @Version 1.0
 */
public class UserTable {
    //在用户表中指定id插入数据
    public static void insert() throws SQLException {
        System.out.println("正在使用：“在用户表中指定id插入数据”的功能");
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入id：");
        int id = scanner.nextInt();
        System.out.print("请输入姓名：");
        String name = scanner.next();

        Connection connection = Tool.connectDatabase();
        String sql = "insert into user values(?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.setString(2, name);

        //把 sql 发给服务器，返回值是一个整数，表示影响到的行数
        int n = statement.executeUpdate();
        System.out.println("插入了" + n + "行数据");

        statement.close();
        connection.close();
    }

    //在用户表中自增id插入数据
    public static void autoIncrementInsert() throws SQLException {
        System.out.println("正在使用：“在用户表中自增id插入数据”的功能");
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入姓名：");
        String name = scanner.next();

        Connection connection = Tool.connectDatabase();
        String sql = "insert into user values(null, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, name);

        //把 sql 发给服务器，返回值是一个整数，表示影响到的行数
        int n = statement.executeUpdate();
        System.out.println("插入了" + n + "行数据");

        statement.close();
        connection.close();
    }

    //在用户表中删除指定数据
    public static void delete() throws SQLException {
        System.out.println("正在使用：“在用户表中删除指定数据”的功能");
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入要删除的数据的id：");
        int id = scanner.nextInt();

        Connection connection = Tool.connectDatabase();
        String sql = "delete from user where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        //把 sql 发给服务器，返回值是一个整数，表示影响到的行数
        int n = statement.executeUpdate();
        System.out.println("删除了" + n + "行数据");

        statement.close();
        connection.close();
    }

    //删除用户表中所有数据
    public static void deleteAll() throws SQLException {
        System.out.println("正在使用：“删除用户表中所有数据”的功能");
        Connection connection = Tool.connectDatabase();
        String sql = "delete from user";
        PreparedStatement statement = connection.prepareStatement(sql);

        //把 sql 发给服务器，返回值是一个整数，表示影响到的行数
        int n = statement.executeUpdate();
        System.out.println("删除了" + n + "行数据");

        statement.close();
        connection.close();
    }

    //在用户表中查找指定数据
    public static void select() throws SQLException {
        System.out.println("正在使用：“在用户表中查找指定数据”的功能");
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入要查找的id：");
        int user_id = scanner.nextInt();

        Connection connection = Tool.connectDatabase();
        String sql = "select * from user where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, user_id);

        //执行，ResultSet 表示查询的结果集合（临时表）
        ResultSet resultSet = statement.executeQuery();

        //判断结果集合
        boolean flag = true;
        if (resultSet.next()) {
            //"id" 和 "name" 都是列名，指定了别名就写别名
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            System.out.println("id: " + id + ", name: " + name);
            flag = false;
        }
        if (flag) {
            System.out.println("当前表没有要查找的数据！");
        }

        resultSet.close();
        statement.close();
        connection.close();
    }

    //在用户表中查看全部数据
    public static void selectAll() throws SQLException {
        System.out.println("正在使用：“在用户表中查看全部数据”的功能");
        Connection connection = Tool.connectDatabase();
        String sql = "select * from user";
        PreparedStatement statement = connection.prepareStatement(sql);

        //执行，ResultSet 表示查询的结果集合（临时表）
        ResultSet resultSet = statement.executeQuery();

        //遍历结果集合
        boolean flag = true;
        while (resultSet.next()) {
            //"id" 和 "name" 都是列名，指定了别名就写别名
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            System.out.println("id: " + id + ", name: " + name);
            flag = false;
        }
        if (flag) {
            System.out.println("当前表没有数据！");
        }

        resultSet.close();
        statement.close();
        connection.close();
    }

    //在用户表中修改指定数据
    public static void update() throws SQLException {
        System.out.println("正在使用：“在用户表中修改指定数据”的功能");
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入要修改的数据的id：");
        int user_id = scanner.nextInt();
        System.out.print("请输入修改后的姓名：");
        String name = scanner.next();

        Connection connection = Tool.connectDatabase();
        String sql = "update user set name = ? where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, name);
        statement.setInt(2, user_id);

        //把 sql 发给服务器，返回值是一个整数，表示影响到的行数
        int n = statement.executeUpdate();
        System.out.println("修改了" + n + "行数据");

        statement.close();
        connection.close();
    }
}