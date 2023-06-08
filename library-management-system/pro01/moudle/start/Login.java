package moudle.start;

import bean.Admin;
import bean.User;
import moudle.main.MainUI;
import moudle.main.MainUI_user;
import service.dao.AdminDao;
import service.dao.UserDao;
import service.utils.MyStringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/*
 * Created by JFormDesigner on Mon May 29 20:58:47 HKT 2023
 */



/**
 * @author 17946
 */
public class Login extends JFrame{
    public Login() {
        initComponents();
    }

    //重置事件处理
    private void button2MouseClicked(MouseEvent e) {
        // TODO add your code here
        this.userNameTXT.setText("");
        this.passwordTXT.setText("");
    }
    //登录事件处理
    public void button1MouseClicked(MouseEvent e) {
        // TODO add your code here
        loginUtils();
    }
    //回车事件处理
    private void passwordTXTKeyPressed(KeyEvent e) {
        // TODO add your code here
        int keyCode = e.getKeyCode();
        //当输入回车时
        if(keyCode == KeyEvent.VK_ENTER){
            loginUtils();
        }
    }

    //登录验证
    public void loginUtils(){
        String userName = this.userNameTXT.getText();
        String password = new String(this.passwordTXT.getPassword());
        //对用户名和密码是否为空判定
        if(MyStringUtils.isEmpty(userName)){
            JOptionPane.showMessageDialog(null,"用户名不为空!");
            return;
        }
        if (MyStringUtils.isEmpty(password)){
            JOptionPane.showMessageDialog(null,"密码不为空!");
            return;
        }
        //对用户登录进行验证
        //管理员
        Admin admin = new Admin(userName,password);
        Admin adminCurrent = new AdminDao().login(admin);
        if (adminCurrent != null){
//            JOptionPane.showMessageDialog(null,"登录成功");
            //对当前窗体进行销毁
            dispose();
            MainUI mainUI = new MainUI(adminCurrent);
            mainUI.setVisible(true);
            mainUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }else {
            //对用户是否存在进行判定
            admin = new Admin(userName);
            adminCurrent = new AdminDao().loginExistence(admin);
            int flag = 0;
            if (adminCurrent == null){
                //普通用户
                User user = new User(userName,password);
                User userCurrent = new UserDao().login(user);
                if (userCurrent != null){
                    dispose();
                    MainUI_user mainUI_user = new MainUI_user(userCurrent);
                    mainUI_user.setVisible(true);
                    mainUI_user.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    flag++;
                }else {
                    user = new User(userName);
                    userCurrent = new UserDao().loginExistence(user);
                    if (userCurrent == null){
                        JOptionPane.showMessageDialog(null,"用户不存在");
                        flag++;
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"用户名密码错误");
                        flag++;
                    }
                }
                //避免重复判断
                if (flag == 0){
                    JOptionPane.showMessageDialog(null,"用户不存在");
                }
            }
            else {
                //避免重复判断
                if (flag == 0){
                    JOptionPane.showMessageDialog(null,"用户名密码错误");
                }
            }
        }

    }

    //处理注册事件
    private void label4MousePressed(MouseEvent e) {

        // TODO add your code here
        Verify verify = new Verify();
        verify.setVisible(true);
    }

    private void initComponents() {
        //设置JFrame居中显示
        this.setLocationRelativeTo(null);
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - yzd
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        userNameTXT = new JTextField();
        passwordTXT = new JPasswordField();
        button1 = new JButton();
        button2 = new JButton();
        label4 = new JLabel();

        //======== this ========
        setTitle("\u767b\u5f55");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        var contentPane = getContentPane();

        //---- label1 ----
        label1.setText(" \u56fe\u4e66\u7ba1\u7406\u7cfb\u7edf");
        label1.setIcon(new ImageIcon(getClass().getResource("/img/book.png")));
        label1.setFont(new Font("\u534e\u6587\u5f69\u4e91", Font.BOLD | Font.ITALIC, 20));

        //---- label2 ----
        label2.setText("\u7528\u6237\u540d:");
        label2.setFont(new Font("\u96b6\u4e66", Font.BOLD, 16));

        //---- label3 ----
        label3.setText("\u5bc6\u7801:");
        label3.setFont(new Font("\u96b6\u4e66", Font.BOLD, 16));

        //---- passwordTXT ----
        passwordTXT.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                passwordTXTKeyPressed(e);
            }
        });

        //---- button1 ----
        button1.setText("\u767b\u5f55");
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

        //---- label4 ----
        label4.setText("\u7acb\u5373\u6ce8\u518c");
        label4.setFont(new Font("\u96b6\u4e66", Font.PLAIN, 12));
        label4.setForeground(new Color(0x0099ff));
        label4.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                label4MousePressed(e);
            }
        });

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(84, 84, 84)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(button1)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(89, 89, 89)
                                    .addComponent(label4, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
                                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(button2)
                                    .addGap(47, 47, 47))))
                        .addComponent(label2)
                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(passwordTXT, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
                            .addGroup(contentPaneLayout.createSequentialGroup()
                                .addComponent(label3)
                                .addGap(46, 46, 46)
                                .addComponent(userNameTXT, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap(12, Short.MAX_VALUE))
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap(114, Short.MAX_VALUE)
                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
                    .addGap(87, 87, 87))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                    .addGap(30, 30, 30)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label2)
                        .addComponent(userNameTXT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(36, 36, 36)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label3)
                        .addComponent(passwordTXT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(button1)
                        .addComponent(button2))
                    .addGap(18, 18, 18)
                    .addComponent(label4)
                    .addGap(42, 42, 42))
        );
        setSize(370, 340);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - yzd
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JTextField userNameTXT;
    private JPasswordField passwordTXT;
    private JButton button1;
    private JButton button2;
    private JLabel label4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
