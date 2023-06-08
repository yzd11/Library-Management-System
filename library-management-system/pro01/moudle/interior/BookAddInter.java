/*
 * Created by JFormDesigner on Tue May 30 22:10:40 HKT 2023
 */

package moudle.interior;

import bean.Book;
import bean.BookType;
import service.dao.BookDao;
import service.dao.BookTypeDao;
import service.utils.MyStringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * @author 17946
 */
public class BookAddInter extends JInternalFrame {
    public BookAddInter() {
        initComponents();
        fillBookType();
    }
    //初始化图书类型下拉框
    public void fillBookType(){
        List<BookType> query = new BookTypeDao().query(new BookType());
        for (BookType bookType:query) {
            bookTypeJcb.addItem(bookType);
        }
    }

    //处理图书添加事件
    private void button1MouseClicked(MouseEvent e) {
        // TODO add your code here
        String bookName = bookNameTxt.getText();
        String author = authorTxt.getText();
        String price = priceTxt.getText();
        String bookDesc = bookDescTxt.getText();

        if (MyStringUtils.isEmpty(bookName)){
            JOptionPane.showMessageDialog(null,"图书名称为空");
            return;
        }
        if (MyStringUtils.isEmpty(author)){
            JOptionPane.showMessageDialog(null,"作者名称为空");
            return;
        }
        if (MyStringUtils.isEmpty(price)){
            JOptionPane.showMessageDialog(null,"价格为空");
            return;
        }

        String sex = "";
        if (menJrb.isSelected()){
            sex = "男";
        }
        else if (femaleJrb.isSelected()){
            sex = "女";
        }

        BookType bookType = (BookType) bookTypeJcb.getSelectedItem();
        int bookTypeId = bookType.getId();
        Book book = new Book(bookName, author, sex, Double.parseDouble(price), bookTypeId, bookDesc);
        int add = new BookDao().add(book);
        if (add > 0){
            JOptionPane.showMessageDialog(null,"添加成功");
            resetValue();
            return;
        }
        else {
            JOptionPane.showMessageDialog(null,"添加失败");
            resetValue();
            return;
        }
    }

    //重置表单
    public void resetValue(){
        bookNameTxt.setText("");
        authorTxt.setText("");
        priceTxt.setText("");
        menJrb.setSelected(true);
        bookDescTxt.setText("");
        if (bookTypeJcb.getItemCount() > 0){
            bookTypeJcb.setSelectedIndex(0);
        }
    }

    //处理鼠标点击重置事件
    private void button2MouseClicked(MouseEvent e) {
        // TODO add your code here
        resetValue();
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        label2 = new JLabel();
        bookNameTxt = new JTextField();
        authorTxt = new JTextField();
        label3 = new JLabel();
        menJrb = new JRadioButton();
        femaleJrb = new JRadioButton();
        label4 = new JLabel();
        priceTxt = new JTextField();
        label5 = new JLabel();
        bookTypeJcb = new JComboBox();
        label6 = new JLabel();
        bookDescTxt = new JTextField();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        setVisible(true);
        setTitle("\u56fe\u4e66\u6dfb\u52a0");
        setClosable(true);
        setIconifiable(true);
        var contentPane = getContentPane();

        //---- label1 ----
        label1.setText("\u56fe\u4e66\u540d\u79f0\uff1a");
        label1.setFont(new Font("\u96b6\u4e66", Font.BOLD, 16));

        //---- label2 ----
        label2.setText("\u56fe\u4e66\u4f5c\u8005\uff1a");
        label2.setFont(new Font("\u96b6\u4e66", Font.BOLD, 16));

        //---- label3 ----
        label3.setText("\u4f5c\u8005\u6027\u522b\uff1a");
        label3.setFont(new Font("\u96b6\u4e66", Font.BOLD, 16));

        //---- menJrb ----
        menJrb.setText("\u7537");
        menJrb.setFont(new Font("\u96b6\u4e66", Font.BOLD, 16));
        menJrb.setSelected(true);

        //---- femaleJrb ----
        femaleJrb.setText("\u5973");
        femaleJrb.setFont(new Font("\u96b6\u4e66", Font.BOLD, 16));

        //---- label4 ----
        label4.setText("\u56fe\u4e66\u4ef7\u683c\uff1a");
        label4.setFont(new Font("\u96b6\u4e66", Font.BOLD, 16));

        //---- label5 ----
        label5.setText("\u56fe\u4e66\u7c7b\u522b\uff1a");
        label5.setFont(new Font("\u96b6\u4e66", Font.BOLD, 16));

        //---- label6 ----
        label6.setText("\u56fe\u4e66\u63cf\u8ff0\uff1a");
        label6.setFont(new Font("\u96b6\u4e66", Font.BOLD, 16));

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
                    .addGap(116, 116, 116)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(label5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(label1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(label3, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(label6, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(51, 51, 51)
                            .addComponent(button1)
                            .addGap(150, 150, 150)
                            .addComponent(button2))
                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addGroup(contentPaneLayout.createSequentialGroup()
                                        .addComponent(menJrb, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(femaleJrb))
                                    .addComponent(bookNameTxt))
                                .addGap(61, 61, 61)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(label4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(label2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(authorTxt, GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                                    .addComponent(priceTxt, GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)))
                            .addComponent(bookTypeJcb, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
                            .addComponent(bookDescTxt, GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)))
                    .addContainerGap(189, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(43, 43, 43)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                        .addComponent(authorTxt, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
                        .addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
                    .addGap(60, 60, 60)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(menJrb, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(femaleJrb)
                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label4, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
                        .addComponent(priceTxt, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
                    .addGap(51, 51, 51)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label5, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                        .addComponent(bookTypeJcb, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                    .addGap(56, 56, 56)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(label6, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                        .addComponent(bookDescTxt, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(button2)
                        .addComponent(button1))
                    .addGap(60, 60, 60))
        );

        //---- buttonGroup1 ----
        var buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(menJrb);
        buttonGroup1.add(femaleJrb);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JLabel label2;
    private JTextField bookNameTxt;
    private JTextField authorTxt;
    private JLabel label3;
    private JRadioButton menJrb;
    private JRadioButton femaleJrb;
    private JLabel label4;
    private JTextField priceTxt;
    private JLabel label5;
    private JComboBox bookTypeJcb;
    private JLabel label6;
    private JTextField bookDescTxt;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
