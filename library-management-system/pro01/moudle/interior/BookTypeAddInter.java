package moudle.interior;
/*
 * Created by JFormDesigner on Tue May 30 14:01:55 HKT 2023
 */


import java.awt.*;
import bean.BookType;
import service.dao.BookTypeDao;
import service.utils.MyStringUtils;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 * @author 17946
 */
public class BookTypeAddInter extends JInternalFrame {
    public BookTypeAddInter() {
        initComponents();
    }

    //处理重置事件
    private void button2MouseClicked(MouseEvent e) {
        // TODO add your code here
        this.bookTypeNameTXT.setText("");
        this.bookTypeDescTXT.setText("");
    }

    //处理添加事件
    private void button1MouseClicked(MouseEvent e) {
        // TODO add your code here
        String bookTypeName = this.bookTypeNameTXT.getText();
        String bookTypeDesc = this.bookTypeDescTXT.getText();
        if(MyStringUtils.isEmpty(bookTypeName)){
            JOptionPane.showMessageDialog(null,"图书类型名称为空");
            return;
        }
        BookType bookType = new BookType(bookTypeName, bookTypeDesc);
        int n = new BookTypeDao().add(bookType);
        if (n > 0){
            JOptionPane.showMessageDialog(null,"添加成功");
            this.button2MouseClicked(e);
        }else {
            JOptionPane.showMessageDialog(null,"添加失败");
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        label2 = new JLabel();
        bookTypeNameTXT = new JTextField();
        bookTypeDescTXT = new JTextField();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        setVisible(true);
        setClosable(true);
        setTitle("\u56fe\u4e66\u7c7b\u522b\u6dfb\u52a0");
        setIconifiable(true);
        var contentPane = getContentPane();

        //---- label1 ----
        label1.setText("\u56fe\u4e66\u7c7b\u522b\u540d\u79f0\uff1a");
        label1.setFont(new Font("\u96b6\u4e66", Font.BOLD, 18));

        //---- label2 ----
        label2.setText("\u56fe\u4e66\u7c7b\u522b\u63cf\u8ff0\uff1a");
        label2.setFont(new Font("\u96b6\u4e66", Font.BOLD, 18));

        //---- button1 ----
        button1.setText("\u6dfb\u52a0");
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
                    .addGap(69, 69, 69)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(label1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(label2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(55, 55, 55)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(bookTypeNameTXT, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 295, Short.MAX_VALUE))
                                .addComponent(bookTypeDescTXT, GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE))
                            .addContainerGap())
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(button1, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 182, Short.MAX_VALUE)
                            .addComponent(button2)
                            .addGap(200, 200, 200))))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(40, 40, 40)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(bookTypeNameTXT))
                    .addGap(38, 38, 38)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
                        .addComponent(bookTypeDescTXT, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(button2, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
                        .addComponent(button1, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
                    .addGap(179, 179, 179))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JLabel label2;
    private JTextField bookTypeNameTXT;
    private JTextField bookTypeDescTXT;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
