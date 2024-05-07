package operation;

import tool.Tool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * ClassName: BookTable
 * Package: operation
 * Description:书籍表相关操作
 *
 * @Author 行空XKong
 * @Create 2024/5/7 16:24
 * @Version 1.0
 */
public class BookTable {
    //在书籍表中指定id插入数据
    public static void insert() throws SQLException {
        System.out.println("正在使用：“在书籍表中指定id插入数据”的功能");
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入id：");
        int id = scanner.nextInt();
        System.out.print("请输入书名：");
        String name = scanner.next();
        System.out.print("请输入作者：");
        String author = scanner.next();
        System.out.print("请输入价格：");
        double price = scanner.nextDouble();
        System.out.print("请输入分类：");
        String type = scanner.next();

        Connection connection = Tool.connectDatabase();
        String sql = "insert into book values(?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.setString(2, name);
        statement.setString(3, author);
        statement.setDouble(4, price);
        statement.setString(5, type);

        //把 sql 发给服务器，返回值是一个整数，表示影响到的行数
        int n = statement.executeUpdate();
        System.out.println("插入了" + n + "行数据");

        statement.close();
        connection.close();
    }

    //在书籍表中自增id插入数据
    public static void autoIncrementInsert() throws SQLException {
        System.out.println("正在使用：“在书籍表中自增id插入数据”的功能");
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入书名：");
        String name = scanner.next();
        System.out.print("请输入作者：");
        String author = scanner.next();
        System.out.print("请输入价格：");
        double price = scanner.nextDouble();
        System.out.print("请输入分类：");
        String type = scanner.next();

        Connection connection = Tool.connectDatabase();
        String sql = "insert into book values(null, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, name);
        statement.setString(2, author);
        statement.setDouble(3, price);
        statement.setString(4, type);

        //把 sql 发给服务器，返回值是一个整数，表示影响到的行数
        int n = statement.executeUpdate();
        System.out.println("插入了" + n + "行数据");

        statement.close();
        connection.close();
    }

    //在书籍表中删除指定数据
    public static void delete() throws SQLException {
        System.out.println("正在使用：“在书籍表中删除指定数据”的功能");
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入要删除的数据的id：");
        int book_id = scanner.nextInt();

        Connection connection = Tool.connectDatabase();
        String sql = "delete from book where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, book_id);

        //把 sql 发给服务器，返回值是一个整数，表示影响到的行数
        int n = statement.executeUpdate();
        System.out.println("删除了" + n + "行数据");

        statement.close();
        connection.close();
    }

    //删除书籍表中所有数据
    public static void deleteAll() throws SQLException {
        System.out.println("正在使用：“删除书籍表中所有数据”的功能");
        Connection connection = Tool.connectDatabase();
        String sql = "delete from book";
        PreparedStatement statement = connection.prepareStatement(sql);

        //把 sql 发给服务器，返回值是一个整数，表示影响到的行数
        int n = statement.executeUpdate();
        System.out.println("删除了" + n + "行数据");

        statement.close();
        connection.close();
    }

    //在书籍表中查找指定数据
    public static void select() throws SQLException {
        System.out.println("正在使用：“在书籍表中查找指定数据”的功能");
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入要查找的书名：");
        String book_name = scanner.next();

        Connection connection = Tool.connectDatabase();
        String sql = "select * from book where name = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, book_name);

        //执行，ResultSet 表示查询的结果集合（临时表）
        ResultSet resultSet = statement.executeQuery();

        //遍历结果集合
        boolean flag = true;
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String author = resultSet.getString("author");
            double price = resultSet.getDouble("price");
            String type = resultSet.getString("type");
            System.out.println("id: " + id + ", name: " + name + ", author: " +
                    author + ", price: " + price + ", type: " + type);
            flag = false;
        }
        if (flag) {
            System.out.println("当前表没有要查找的数据！");
        }

        resultSet.close();
        statement.close();
        connection.close();
    }

    //在书籍表中查看全部数据
    public static void selectAll() throws SQLException {
        System.out.println("正在使用：“在书籍表中查看全部数据”的功能");
        Connection connection = Tool.connectDatabase();
        String sql = "select * from book";
        PreparedStatement statement = connection.prepareStatement(sql);

        //执行，ResultSet 表示查询的结果集合（临时表）
        ResultSet resultSet = statement.executeQuery();

        //遍历结果集合
        boolean flag = true;
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String author = resultSet.getString("author");
            double price = resultSet.getDouble("price");
            String type = resultSet.getString("type");
            System.out.println("id: " + id + ", name: " + name + ", author: " +
                    author + ", price: " + price + ", type: " + type);
            flag = false;
        }
        if (flag) {
            System.out.println("当前表没有数据！");
        }

        resultSet.close();
        statement.close();
        connection.close();
    }

    //在书籍表中修改指定数据
    public static void update() throws SQLException {
        System.out.println("正在使用：“在书籍表中修改指定数据”的功能");
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入要修改的数据的id：");
        int book_id = scanner.nextInt();
        System.out.print("请输入修改后的书名：");
        String name = scanner.next();
        System.out.print("请输入修改后的作者：");
        String author = scanner.next();
        System.out.print("请输入修改后的价格：");
        double price = scanner.nextDouble();
        System.out.print("请输入修改后的分类：");
        String type = scanner.next();

        Connection connection = Tool.connectDatabase();
        String sql = "update book set name = ?, author = ?, price = ?, type = ? where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, name);
        statement.setString(2, author);
        statement.setDouble(3, price);
        statement.setString(4, type);
        statement.setInt(5, book_id);

        //把 sql 发给服务器，返回值是一个整数，表示影响到的行数
        int n = statement.executeUpdate();
        System.out.println("修改了" + n + "行数据");

        statement.close();
        connection.close();
    }
}