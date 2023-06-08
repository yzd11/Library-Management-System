/*
 * Created by JFormDesigner on Thu Jun 01 09:30:11 HKT 2023
 */

package moudle.start;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author 17946
 */
public class Verify extends JFrame {
    public Verify() {
        initComponents();
        // 设置窗口的大小
        setSize(405, 170);

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

    //处理口令验证
    private void verifyUtils() {
        // TODO add your code here
        String verifyAdmin = "Admin";
        String verifyUser = "User";
        String verifyCurrent = verifyTxt.getText();
        if (verifyCurrent.equals(verifyAdmin)){
            dispose();
            Register register = new Register(verifyAdmin);
//        register.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            register.setVisible(true);
        }else if (verifyCurrent.equals(verifyUser)){
            dispose();
            Register register = new Register(verifyUser);
//        register.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            register.setVisible(true);
        }
        else {
            JOptionPane.showMessageDialog(null,"口令有误");
        }
    }

    //处理回车事件
    private void verifyTxtKeyPressed(KeyEvent e) {
        // TODO add your code here
        int keyCode = e.getKeyCode();
        //当输入回车时
        if(keyCode == KeyEvent.VK_ENTER){
            verifyUtils();
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        verifyTxt = new JTextField();

        //======== this ========
        setTitle("\u9a8c\u8bc1");
        var contentPane = getContentPane();

        //---- label1 ----
        label1.setText("\u8bf7\u8f93\u5165\u53e3\u4ee4\uff1a");
        label1.setFont(new Font("\u96b6\u4e66", Font.BOLD, 14));

        //---- verifyTxt ----
        verifyTxt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                verifyTxtKeyPressed(e);
            }
        });

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(17, 17, 17)
                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(verifyTxt, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(58, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(44, 44, 44)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                        .addComponent(verifyTxt, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(50, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    //
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JTextField verifyTxt;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
