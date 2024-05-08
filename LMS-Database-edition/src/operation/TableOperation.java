package operation;

import tool.Tool;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * ClassName: tableOperation
 * Package: operation
 * Description:
 *
 * @Author 行空XKong
 * @Create 2024/5/7 23:10
 * @Version 1.0
 */
public class TableOperation {
    int choiceTable;

    public TableOperation(int choiceTable) throws SQLException {
        this.choiceTable = choiceTable;
        while (true) {
            System.out.println("正在选择操作...");
            int choiceOperation = choiceOperationMenu();
            if (choiceTable == 1) {
                switch (choiceOperation) {
                    case 1:
                        UserTable.insert();
                        break;
                    case 2:
                        UserTable.autoIncrementInsert();
                        break;
                    case 3:
                        UserTable.delete();
                        break;
                    case 4:
                        UserTable.deleteAll();
                        break;
                    case 5:
                        UserTable.select();
                        break;
                    case 6:
                        UserTable.selectAll();
                        break;
                    case 7:
                        UserTable.update();
                        break;
                    case -1:
                        System.out.println("正在返回上一级菜单...");
                        return;
                    case 0:
                        Tool.exit();
                        break;
                    default:
                        System.out.println("输入错误，请重新输入！");
                        break;
                }
            } else if (choiceTable == 2) {
                switch (choiceOperation) {
                    case 1:
                        BookTable.insert();
                        break;
                    case 2:
                        BookTable.autoIncrementInsert();
                        break;
                    case 3:
                        BookTable.delete();
                        break;
                    case 4:
                        BookTable.deleteAll();
                        break;
                    case 5:
                        BookTable.select();
                        break;
                    case 6:
                        BookTable.selectAll();
                        break;
                    case 7:
                        BookTable.update();
                        break;
                    case -1:
                        System.out.println("正在返回上一级菜单...");
                        return;
                    case 0:
                        Tool.exit();
                        break;
                    default:
                        System.out.println("输入错误，请重新输入！");
                        break;
                }
            } else {//3
                switch (choiceOperation) {
                    case 1:
                        BorrowInfoTable.insert();
                        break;
                    case 2:
                        BorrowInfoTable.autoIncrementInsert();
                        break;
                    case 3:
                        BorrowInfoTable.delete();
                        break;
                    case 4:
                        BorrowInfoTable.deleteAll();
                        break;
                    case 5:
                        BorrowInfoTable.select();
                        break;
                    case 6:
                        BorrowInfoTable.selectAll();
                        break;
                    case 7:
                        BorrowInfoTable.update();
                        break;
                    case -1:
                        System.out.println("正在返回上一级菜单...");
                        return;
                    case 0:
                        Tool.exit();
                        break;
                    default:
                        System.out.println("输入错误，请重新输入！");
                        break;
                }
            }
        }
    }

    //选择操作菜单
    private static int choiceOperationMenu() {
        System.out.println("*********请选择********");
        System.out.println("1.指定id插入数据");
        System.out.println("2.自增id插入数据");
        System.out.println("3.删除指定数据");
        System.out.println("4.删除所有数据");
        System.out.println("5.查找指定数据");
        System.out.println("6.查看全部数据");
        System.out.println("7.修改指定数据");
        System.out.println("-1.返回上一级菜单");
        System.out.println("0.退出");
        System.out.println("**********************");

        Scanner in = new Scanner(System.in);
        System.out.print("请输入你的选择：");
        return in.nextInt();
    }
}