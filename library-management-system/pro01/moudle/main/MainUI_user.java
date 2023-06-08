/*
 * Created by JFormDesigner on Thu Jun 01 11:01:33 HKT 2023
 */

package moudle.main;

import bean.User;
import moudle.interior.BookBorrowInter;
import moudle.interior.BorrowingRecordInter;
import moudle.interior.Yzd11Inter;
import service.dao.UserDao;
import service.utils.MyStringUtils;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author 17946
 */
public class MainUI_user extends JFrame {
    Timer timer;
    public MainUI_user() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
    }
    public MainUI_user(User user) {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        fillInformation(user);
    }
    //初始化个人信息
    public void fillInformation(User user){
        idTxt.setText(user.getId()+"");
        userNameShowTxt.setText(user.getUserName());
        userNameTxt.setText(user.getUserName());
        passwordTxt.setText(user.getPassword());
        String userDesc = user.getUserDesc();
        if (MyStringUtils.isEmpty(userDesc)){
            userDescTxt.setText("该用户很懒，什么介绍都没有");
        }else {
            userDescTxt.setText(user.getUserDesc());
        }
        // 初始化时间
        String startTimeStr = "2023-06-01 10:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startTime = null;
        try {
            startTime = sdf.parse(startTimeStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        final long startTimeMillis = startTime.getTime();
        // 创建Timer对象，每隔1秒钟更新一次已运行时间
        //使用了lambda表达式简化代码，提高可维护性
        timer = new Timer(1000, e -> {
            long currentTime = System.currentTimeMillis(); // 获取当前时间
            long elapsedTime = currentTime - startTimeMillis; // 计算已运行时间
            String timeStr = formatDuration(elapsedTime); // 将已运行时间格式化为天、时、分、秒的格式
            timeTxt.setText("已运行时间：" + timeStr); // 将已运行时间设置到界面上
        });
        timer.start();
    }
    //时间转换
    private String formatDuration(long duration) {
        long days = TimeUnit.MILLISECONDS.toDays(duration);
        duration -= TimeUnit.DAYS.toMillis(days);
        long hours = TimeUnit.MILLISECONDS.toHours(duration);
        duration -= TimeUnit.HOURS.toMillis(hours);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(duration);
        duration -= TimeUnit.MINUTES.toMillis(minutes);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(duration);
        return String.format("%d天：%02d时：%02d分：%02d秒", days, hours, minutes, seconds);
    }

    //处理鼠标点击修改事件
    private void button1MouseClicked(MouseEvent e) {
        // TODO add your code here
        String id = idTxt.getText();
        String userName = userNameTxt.getText();
        String password = passwordTxt.getText();
        String userDesc = userDescTxt.getText();
        if (MyStringUtils.isEmpty(userName)){
            JOptionPane.showMessageDialog(null,"用户名称为空");
            return;
        }
        if (MyStringUtils.isEmpty(password)){
            JOptionPane.showMessageDialog(null,"密码为空");
            return;
        }
        User user = new User(Integer.parseInt(id),userName, password, userDesc);
        int update = new UserDao().update(user);
        if (update > 0){
            JOptionPane.showMessageDialog(null,"修改成功");
            fillInformation(user);
            return;
        }else {
            JOptionPane.showMessageDialog(null,"修改失败");
            return;
        }
    }

    //处理鼠标点击注销事件
    private void button2MouseClicked(MouseEvent e) {
        // TODO add your code here
        String id = idTxt.getText();
        int i = JOptionPane.showConfirmDialog(null, "确定要删除吗?");
        if(i == 0){
            int delete = new UserDao().delete(Integer.parseInt(id));
            if (delete > 0){
                JOptionPane.showMessageDialog(null,"注销成功");
                dispose();
            }
            else {
                JOptionPane.showMessageDialog(null,"注销失败");
            }
        }
    }



    //处理安全退出事件
    private void menuItem6(ActionEvent e) {
        // TODO add your code here
        int result = JOptionPane.showConfirmDialog(null,"是否退出");
        //是 0 否 1 取消 2
        if (result == 0){
            timer.stop(); // 停止定时器
            Thread.currentThread().interrupt(); // 中断线程
            dispose();
        }
    }

    //处理关于yzd11事件
    private void menuItem1(ActionEvent e) {
        // TODO add your code here
        Yzd11Inter yzd11inter = new Yzd11Inter();
        //设置中心位置
        setInternalFramePosition(table,yzd11inter);
    }
    //内部窗口大小与位置设置
    public static void setInternalFramePosition(JDesktopPane desktopPane, JInternalFrame internalFrame) {
        //设置大小
        internalFrame.setSize(800,600);
        // 获取JDesktopPane的宽度和高度
        int desktopWidth = desktopPane.getWidth();
        int desktopHeight = desktopPane.getHeight();

        // 获取JInternalFrame的宽度和高度
        int frameWidth = internalFrame.getWidth();
        int frameHeight = internalFrame.getHeight();

        // 计算JInternalFrame的x坐标和y坐标
        int x = (desktopWidth - frameWidth) / 2;
        int y= (desktopHeight - frameHeight) / 2;

        // 设置JInternalFrame的位置
        internalFrame.setLocation(x, y);
        internalFrame.setVisible(true);
        desktopPane.add(internalFrame);
        //将窗口置顶
        internalFrame.toFront();
    }

    //图书借阅
    private void menuItem5(ActionEvent e) {
        // TODO add your code here
        String id = idTxt.getText();
        BookBorrowInter bookBorrow = new BookBorrowInter(id);
        //设置中心位置
        setInternalFramePosition(table,bookBorrow);
    }

    //借阅记录
    private void menuItem4(ActionEvent e) {
        // TODO add your code here
        String id = idTxt.getText();
        BorrowingRecordInter borrowingRecordInter = new BorrowingRecordInter(id);
        //设置中心位置
        setInternalFramePosition(table,borrowingRecordInter);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - yzd
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menu4 = new JMenu();
        menuItem4 = new JMenuItem();
        menuItem5 = new JMenuItem();
        menuItem6 = new JMenuItem();
        menu2 = new JMenu();
        menuItem1 = new JMenuItem();
        table = new JDesktopPane();
        label1 = new JLabel();
        userNameShowTxt = new JTextField();
        panel1 = new JPanel();
        label2 = new JLabel();
        userNameTxt = new JTextField();
        label3 = new JLabel();
        passwordTxt = new JTextField();
        label4 = new JLabel();
        userDescTxt = new JTextField();
        button1 = new JButton();
        button2 = new JButton();
        label5 = new JLabel();
        idTxt = new JTextField();
        label6 = new JLabel();
        timeTxt = new JTextField();

        //======== this ========
        setTitle("\u56fe\u4e66\u7ba1\u7406\u7cfb\u7edf");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText("\u57fa\u672c\u6570\u636e\u7ef4\u62a4");

                //======== menu4 ========
                {
                    menu4.setText("\u56fe\u4e66\u7ba1\u7406");

                    //---- menuItem4 ----
                    menuItem4.setText("\u501f\u9605\u8bb0\u5f55");
                    menuItem4.addActionListener(e -> menuItem4(e));
                    menu4.add(menuItem4);

                    //---- menuItem5 ----
                    menuItem5.setText("\u56fe\u4e66\u501f\u9605");
                    menuItem5.addActionListener(e -> menuItem5(e));
                    menu4.add(menuItem5);
                }
                menu1.add(menu4);

                //---- menuItem6 ----
                menuItem6.setText("\u5b89\u5168\u9000\u51fa");
                menuItem6.addActionListener(e -> menuItem6(e));
                menu1.add(menuItem6);
            }
            menuBar1.add(menu1);

            //======== menu2 ========
            {
                menu2.setText("\u5173\u4e8e\u6211\u4eec");

                //---- menuItem1 ----
                menuItem1.setText("\u5173\u4e8eyzd11");
                menuItem1.addActionListener(e -> menuItem1(e));
                menu2.add(menuItem1);
            }
            menuBar1.add(menu2);
        }
        setJMenuBar(menuBar1);

        //======== table ========
        {

            //---- label1 ----
            label1.setIcon(new ImageIcon(getClass().getResource("/img/\u61d2\u7f8a\u7f8a.jpg")));
            table.add(label1, JLayeredPane.DEFAULT_LAYER);
            label1.setBounds(15, 40, 250, 260);

            //---- userNameShowTxt ----
            userNameShowTxt.setEditable(false);
            userNameShowTxt.setFont(new Font("\u96b6\u4e66", Font.BOLD, 22));
            userNameShowTxt.setHorizontalAlignment(SwingConstants.CENTER);
            userNameShowTxt.setEnabled(false);
            userNameShowTxt.setDisabledTextColor(Color.black);
            table.add(userNameShowTxt, JLayeredPane.DEFAULT_LAYER);
            userNameShowTxt.setBounds(15, 320, 245, 40);

            //======== panel1 ========
            {
                panel1.setBorder(new TitledBorder(null, "\u4e2a\u4eba\u4fe1\u606f", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.ABOVE_TOP,
                    new Font("\u96b6\u4e66", Font.BOLD, 16)));
                panel1.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new
                javax. swing. border. EmptyBorder( 0, 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax
                . swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java
                .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt
                . Color. red) ,panel1. getBorder( )) ); panel1. addPropertyChangeListener (new java. beans.
                PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("bord\u0065r" .
                equals (e .getPropertyName () )) throw new RuntimeException( ); }} );

                //---- label2 ----
                label2.setText("\u7528\u6237\u540d\uff1a");
                label2.setFont(new Font("\u96b6\u4e66", Font.BOLD, 14));

                //---- label3 ----
                label3.setText("\u5bc6\u7801\uff1a");
                label3.setFont(new Font("\u96b6\u4e66", Font.BOLD, 14));

                //---- label4 ----
                label4.setText("\u4ecb\u7ecd\uff1a");
                label4.setFont(new Font("\u96b6\u4e66", Font.BOLD, 14));

                //---- button1 ----
                button1.setText("\u4fee\u6539");
                button1.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button1MouseClicked(e);
                    }
                });

                //---- button2 ----
                button2.setText("\u6ce8\u9500");
                button2.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button2MouseClicked(e);
                    }
                });

                //---- label5 ----
                label5.setText("ID:");
                label5.setFont(new Font("\u96b6\u4e66", Font.BOLD, 14));

                //---- idTxt ----
                idTxt.setEditable(false);

                GroupLayout panel1Layout = new GroupLayout(panel1);
                panel1.setLayout(panel1Layout);
                panel1Layout.setHorizontalGroup(
                    panel1Layout.createParallelGroup()
                        .addGroup(panel1Layout.createSequentialGroup()
                            .addGap(44, 44, 44)
                            .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addGroup(panel1Layout.createSequentialGroup()
                                    .addComponent(label5, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(idTxt, GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE))
                                .addGroup(panel1Layout.createSequentialGroup()
                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(label3, GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                                        .addComponent(label4, GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                                        .addComponent(label2, GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE))
                                    .addGap(35, 35, 35)
                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(panel1Layout.createSequentialGroup()
                                            .addComponent(button1)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(button2))
                                        .addComponent(passwordTxt, GroupLayout.Alignment.TRAILING)
                                        .addComponent(userDescTxt, GroupLayout.PREFERRED_SIZE, 448, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(userNameTxt)))))
                );
                panel1Layout.setVerticalGroup(
                    panel1Layout.createParallelGroup()
                        .addGroup(panel1Layout.createSequentialGroup()
                            .addGap(12, 12, 12)
                            .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label5, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                                .addComponent(idTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label2, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addComponent(userNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label3, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                .addComponent(passwordTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(24, 24, 24)
                            .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label4, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                                .addComponent(userDescTxt, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                            .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(button2)
                                .addComponent(button1))
                            .addGap(50, 50, 50))
                );
            }
            table.add(panel1, JLayeredPane.DEFAULT_LAYER);
            panel1.setBounds(265, 50, 760, 510);

            //---- label6 ----
            label6.setText("\u9879\u76ee\u5df2\u8fd0\u884c\uff1a");
            label6.setFont(new Font("\u96b6\u4e66", Font.BOLD, 14));
            table.add(label6, JLayeredPane.DEFAULT_LAYER);
            label6.setBounds(85, 370, 95, 29);

            //---- timeTxt ----
            timeTxt.setFont(new Font("\u96b6\u4e66", Font.BOLD, 12));
            timeTxt.setEditable(false);
            timeTxt.setEnabled(false);
            timeTxt.setDisabledTextColor(Color.black);
            table.add(timeTxt, JLayeredPane.DEFAULT_LAYER);
            timeTxt.setBounds(15, 400, 245, 40);
        }
        contentPane.add(table, BorderLayout.CENTER);
        setSize(1045, 625);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - yzd
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenu menu4;
    private JMenuItem menuItem4;
    private JMenuItem menuItem5;
    private JMenuItem menuItem6;
    private JMenu menu2;
    private JMenuItem menuItem1;
    private JDesktopPane table;
    private JLabel label1;
    private JTextField userNameShowTxt;
    private JPanel panel1;
    private JLabel label2;
    private JTextField userNameTxt;
    private JLabel label3;
    private JTextField passwordTxt;
    private JLabel label4;
    private JTextField userDescTxt;
    private JButton button1;
    private JButton button2;
    private JLabel label5;
    private JTextField idTxt;
    private JLabel label6;
    private JTextField timeTxt;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
