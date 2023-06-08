/*
 * Created by JFormDesigner on Thu Jun 01 08:59:33 HKT 2023
 */

package moudle.start;

import bean.Admin;
import bean.User;
import service.dao.AdminDao;
import service.dao.UserDao;
import service.utils.MyStringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author 17946
 */
public class Register extends JFrame {
    String verifyCurrent ;
    public Register() {
        initComponents();
        // 设置窗口的大小
        setSize(800, 600);

        // 获取屏幕的宽度和高度
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        // 计算窗口的x坐标和y坐标
        int x = (screenWidth - getWidth()) / 2;
        int y = (screenHeight - getHeight()) / 2;

        // 设置窗口的位置
        setLocation(x, y);
    }
    public Register(String verifyCurrent) {

        initComponents();
        // 设置窗口的大小
        setSize(800, 600);

        // 获取屏幕的宽度和高度
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        // 计算窗口的x坐标和y坐标
        int x = (screenWidth - getWidth()) / 2;
        int y = (screenHeight - getHeight()) / 2;

        // 设置窗口的位置
        setLocation(x, y);
        this.verifyCurrent = verifyCurrent;
    }

    //处理鼠标点击注册事件
    private void button1MouseClicked(MouseEvent e) {
        // TODO add your code here
        String userName = userNameTxt.getText();
        String password = passwordTxt.getText();
        String userDesc = userDescTxt.getText();
        if (MyStringUtils.isEmpty(userName)){
            JOptionPane.showMessageDialog(null,"用户名为空");
            return;
        }
        if (MyStringUtils.isEmpty(password)){
            JOptionPane.showMessageDialog(null,"密码为空");
            return;
        }
        String verifyAdmin = "我是IKUN";
        String verifyUser = "我是小黑子";
        //判定用户身份
        if (verifyCurrent.equals(verifyAdmin)){
            Admin admin = new Admin(userName, password, userDesc);
            int add = new AdminDao().add(admin);
            if (add > 0){
                JOptionPane.showMessageDialog(null,"添加成功");
                dispose();
                return;
            }
            else {
                JOptionPane.showMessageDialog(null,"添加失败");
                return;
            }
        }else if (verifyCurrent.equals(verifyUser)){
            User user = new User(userName, password, userDesc);
            int add = new UserDao().add(user);
            if (add > 0){
                JOptionPane.showMessageDialog(null,"添加成功");
                dispose();
                return;
            }
            else {
                JOptionPane.showMessageDialog(null,"添加失败");
                return;
            }
        }
    }

    //处理鼠标点击重置事件
    private void button2MouseClicked(MouseEvent e) {
        // TODO add your code here
        userNameTxt.setText("");
        passwordTxt.setText("");
        userDescTxt.setText("");
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        label2 = new JLabel();
        userNameTxt = new JTextField();
        label3 = new JLabel();
        passwordTxt = new JTextField();
        label4 = new JLabel();
        userDescTxt = new JTextField();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        setTitle("\u6ce8\u518c");
        var contentPane = getContentPane();

        //---- label1 ----
        label1.setText("\u6b22\u8fce\u4f7f\u7528\u56fe\u4e66\u7ba1\u7406\u7cfb\u7edf");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setFont(new Font("\u96b6\u4e66", Font.BOLD, 22));

        //---- label2 ----
        label2.setText("\u7528\u6237\u540d\uff1a");
        label2.setFont(new Font("\u96b6\u4e66", Font.BOLD, 16));

        //---- label3 ----
        label3.setText("\u5bc6\u7801\uff1a");
        label3.setFont(new Font("\u96b6\u4e66", Font.BOLD, 16));

        //---- label4 ----
        label4.setText("\u4e2a\u4eba\u4ecb\u7ecd\uff1a");
        label4.setFont(new Font("\u96b6\u4e66", Font.BOLD, 16));

        //---- button1 ----
        button1.setText("\u6ce8\u518c");
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button1MouseClicked(e);
            }
        });

        //---- button2 ----
        button2.setText("\u91cd\u7f6e");
        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button2MouseClicked(e);
            }
        });

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(171, 171, 171)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(label1, GroupLayout.PREFERRED_SIZE, 457, GroupLayout.PREFERRED_SIZE)
                                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(label2, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label4, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
                                    .addGap(89, 89, 89)
                                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(userNameTxt, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                                        .addComponent(passwordTxt, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                                        .addComponent(userDescTxt, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE))))
                            .addContainerGap(170, Short.MAX_VALUE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(0, 57, Short.MAX_VALUE)
                            .addComponent(button1)
                            .addGap(147, 147, 147)
                            .addComponent(button2)
                            .addGap(267, 267, 267))))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(32, 32, 32)
                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                    .addGap(33, 33, 33)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                        .addComponent(userNameTxt, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
                    .addGap(61, 61, 61)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                        .addComponent(passwordTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(57, 57, 57)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(userDescTxt, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(13, 13, 13)
                            .addComponent(label4, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(button1)
                        .addComponent(button2))
                    .addGap(53, 53, 53))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JLabel label2;
    private JTextField userNameTxt;
    private JLabel label3;
    private JTextField passwordTxt;
    private JLabel label4;
    private JTextField userDescTxt;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
