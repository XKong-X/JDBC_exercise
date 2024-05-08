import operation.TableOperation;
import tool.Tool;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * ClassName: Main
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author 行空XKong
 * @Create 2024/5/7 16:30
 * @Version 1.0
 */

public class Main {
    //选择表菜单
    private static int choiceTableMenu() {
        System.out.println("*********请选择********");
        System.out.println("1.用户表");
        System.out.println("2.书籍表");
        System.out.println("3.借阅信息表");
        System.out.println("0.退出");
        System.out.println("**********************");

        Scanner in = new Scanner(System.in);
        System.out.print("请输入你的选择：");
        return in.nextInt();
    }

    public static void main(String[] args) throws SQLException {
        System.out.println("**欢迎使用图书馆管理系统**");
        Tool.login();

        while (true) {
            int choiceTable = choiceTableMenu();
            if (choiceTable == 0) {
                Tool.exit();
            } else if (choiceTable < 0 || choiceTable > 3) {
                System.out.println("输入错误，请重新输入！");
            } else {
                new TableOperation(choiceTable);
            }
        }

        //UserTable.insert();
        //UserTable.autoIncrementInsert();
        //UserTable.select();
        //UserTable.selectAll();
        //UserTable.update();
        //UserTable.delete();
        //UserTable.deleteAll();

        //BookTable.insert();
        //BookTable.autoIncrementInsert();
        //BookTable.select();
        //BookTable.selectAll();
        //BookTable.update();
        //BookTable.delete();
        //BookTable.deleteAll();

        //BorrowInfoTable.insert();
        //BorrowInfoTable.autoIncrementInsert();
        //BorrowInfoTable.delete();
        //BorrowInfoTable.deleteAll();
        //BorrowInfoTable.select();
        //BorrowInfoTable.selectAll();
        //BorrowInfoTable.update();
    }
}