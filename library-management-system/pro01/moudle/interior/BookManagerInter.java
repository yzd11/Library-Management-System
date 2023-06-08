/*
 * Created by JFormDesigner on Wed May 31 09:31:36 HKT 2023
 */

package moudle.interior;

import bean.Book;
import bean.BookType;
import service.dao.BookDao;
import service.dao.BookTypeDao;
import service.utils.MyStringUtils;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

/**
 * @author 17946
 */
public class BookManagerInter extends JInternalFrame {
    public BookManagerInter() {
        initComponents();
        fillBookType("search");
        fillBookType("modify");
        fillTable(new Book());
    }
    //初始化下拉框
    public void fillBookType(String type){
        List<BookType> query = new BookTypeDao().query(new BookType());
        if (type == "search"){
            BookType bookType = new BookType();
            bookType.setBookTypeName("请选择...");
            bookType.setId(-1);
            s_bookTypeJcb.addItem(bookType);
        }
        for (BookType bookType:query) {
            if (type == "search"){
                s_bookTypeJcb.addItem(bookType);
            }else if (type == "modify"){
                //
                bookTypeJcb.addItem(bookType);
            }
        }
    }
    //初始化表格数据
    public void fillTable(Book book){
        DefaultTableModel dtm = (DefaultTableModel) bookTable.getModel();
        //表格清空
        dtm.setRowCount(0);
        //进行查询
        List<Book> bookList = new BookDao().query(book);
        //进行list -> vector
        for (Book temp : bookList) {
            //需要先按项取出，在放入vector
            Vector<Object> bookTypeVector = new Vector<>();
            bookTypeVector.add(temp.getId()+"");
            bookTypeVector.add(temp.getBookName());
            bookTypeVector.add(temp.getAuthor());
            bookTypeVector.add(temp.getSex());
            bookTypeVector.add(temp.getPrice()+"");
            bookTypeVector.add(temp.getBookDesc());
            bookTypeVector.add(temp.getBookTypeName());
            dtm.addRow(bookTypeVector);
        }
    }

    //处理鼠标点击查询事件
    private void button1MouseClicked(MouseEvent e) {
        // TODO add your code here
        String bookName = s_bookNameTxt.getText();
        String author = s_autuorTxt.getText();
        BookType bookType = (BookType)s_bookTypeJcb.getSelectedItem();
        int bookTypeId = bookType.getId();
        Book book = new Book(bookName, author, bookTypeId);
        fillTable(book);
    }

    //处理鼠标点击表格事件

    private void bookTableMousePressed(MouseEvent e) {
        // TODO add your code here
        int selectedRow = bookTable.getSelectedRow();
        idTxt.setText("" + bookTable.getValueAt(selectedRow,0));
        bookNameTxt.setText("" + bookTable.getValueAt(selectedRow,1));
        authorTxt.setText("" + bookTable.getValueAt(selectedRow,2));
        String sex = "" + bookTable.getValueAt(selectedRow,3);
        if (sex.equals("男")){
            menJrb.setSelected(true);
        }
        else if (sex.equals("女")){
            femaleJrb.setSelected(true);
        }
        priceTxt.setText("" + bookTable.getValueAt(selectedRow,4));
        bookDescTxt.setText("" + bookTable.getValueAt(selectedRow,5));
        String bookTypeName = "" + bookTable.getValueAt(selectedRow,6);
        int itemCount = bookTypeJcb.getItemCount();
        for (int i = 0;i < itemCount;i++){
            BookType bookType = (BookType)bookTypeJcb.getItemAt(i);
            if (bookType.getBookTypeName().equals(bookTypeName)){
                bookTypeJcb.setSelectedIndex(i);
            }
        }
    }

    //处理鼠标点击修改事件
    private void button2MouseClicked(MouseEvent e) {
        // TODO add your code here
        String id = idTxt.getText();
        if (MyStringUtils.isEmpty(id)){
            JOptionPane.showMessageDialog(null,"请选择修改的书籍");
            return;
        }
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
        Book book = new Book(Integer.parseInt(id), bookName,author, sex, Double.parseDouble(price), bookTypeId, bookDesc);

        int add = new BookDao().update(book);
        if (add > 0){
            JOptionPane.showMessageDialog(null,"修改成功");
            fillTable(new Book());
            resetValue();
            return;
        }
        else {
            JOptionPane.showMessageDialog(null,"修改失败");
            resetValue();
            return;
        }
    }

    //重置表单
    public void resetValue(){
        idTxt.setText("");
        bookNameTxt.setText("");
        authorTxt.setText("");
        priceTxt.setText("");
        menJrb.setSelected(true);
        bookDescTxt.setText("");
        if (bookTypeJcb.getItemCount() > 0){
            bookTypeJcb.setSelectedIndex(0);
        }
    }

    //处理鼠标点击删除事件
    private void button3MouseClicked(MouseEvent e) {
        // TODO add your code here
        String id = this.idTxt.getText();
        //id不可为空
        if(MyStringUtils.isEmpty(id)){
            JOptionPane.showMessageDialog(null,"请选择删除内容");
            return;
        }
        int i = JOptionPane.showConfirmDialog(null, "确定要删除吗?");
        if(i == 0){
            int delete = new BookDao().delete(Integer.parseInt(id));
            if (delete > 0){
                JOptionPane.showMessageDialog(null,"删除成功");
                //重置表单
                resetValue();
                //刷新表格
                this.fillTable(new Book());
            }
            else {
                JOptionPane.showMessageDialog(null,"删除失败");
            }
        }
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        scrollPane1 = new JScrollPane();
        bookTable = new JTable();
        panel1 = new JPanel();
        label1 = new JLabel();
        s_bookNameTxt = new JTextField();
        label2 = new JLabel();
        s_autuorTxt = new JTextField();
        label3 = new JLabel();
        s_bookTypeJcb = new JComboBox();
        button1 = new JButton();
        panel2 = new JPanel();
        label4 = new JLabel();
        idTxt = new JTextField();
        label5 = new JLabel();
        bookNameTxt = new JTextField();
        label6 = new JLabel();
        menJrb = new JRadioButton();
        femaleJrb = new JRadioButton();
        label7 = new JLabel();
        priceTxt = new JTextField();
        label8 = new JLabel();
        authorTxt = new JTextField();
        label9 = new JLabel();
        bookTypeJcb = new JComboBox();
        bookDescTxt = new JTextField();
        label10 = new JLabel();
        button2 = new JButton();
        button3 = new JButton();

        //======== this ========
        setVisible(true);
        setTitle("\u56fe\u4e66\u7ba1\u7406");
        setClosable(true);
        setIconifiable(true);
        var contentPane = getContentPane();

        //======== scrollPane1 ========
        {

            //---- bookTable ----
            bookTable.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                    "\u7f16\u53f7", "\u56fe\u4e66\u540d\u79f0", "\u56fe\u4e66\u4f5c\u8005", "\u4f5c\u8005\u6027\u522b", "\u56fe\u4e66\u4ef7\u683c", "\u56fe\u4e66\u63cf\u8ff0", "\u56fe\u4e66\u7c7b\u522b"
                }
            ) {
                boolean[] columnEditable = new boolean[] {
                    false, true, true, true, true, true, false
                };
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return columnEditable[columnIndex];
                }
            });
            bookTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    bookTableMousePressed(e);
                }
            });
            scrollPane1.setViewportView(bookTable);
        }

        //======== panel1 ========
        {
            panel1.setBorder(new TitledBorder(null, "\u641c\u7d22", TitledBorder.LEADING, TitledBorder.ABOVE_TOP,
                new Font("\u96b6\u4e66", Font.BOLD, 16)));

            //---- label1 ----
            label1.setText("\u56fe\u4e66\u540d\u79f0\uff1a");
            label1.setFont(new Font("\u96b6\u4e66", Font.BOLD, 14));

            //---- label2 ----
            label2.setText("\u56fe\u4e66\u4f5c\u8005\uff1a");
            label2.setFont(new Font("\u96b6\u4e66", Font.BOLD, 14));

            //---- label3 ----
            label3.setText("\u56fe\u4e66\u7c7b\u522b\uff1a");
            label3.setFont(new Font("\u96b6\u4e66", Font.BOLD, 14));

            //---- button1 ----
            button1.setText("\u67e5\u8be2");
            button1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button1MouseClicked(e);
                }
            });

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(label1)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(s_bookNameTxt, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(label2)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(s_autuorTxt, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(s_bookTypeJcb, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(button1)
                        .addGap(31, 31, 31))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(s_bookNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(s_autuorTxt)
                            .addComponent(label3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(s_bookTypeJcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(button1))
                        .addContainerGap())
            );
        }

        //======== panel2 ========
        {
            panel2.setBorder(new TitledBorder(null, "\u8868\u5355\u64cd\u4f5c", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.ABOVE_TOP,
                new Font("\u96b6\u4e66", Font.BOLD, 16)));

            //---- label4 ----
            label4.setText("\u7f16\u53f7\uff1a");
            label4.setFont(new Font("\u96b6\u4e66", Font.BOLD, 14));

            //---- idTxt ----
            idTxt.setEditable(false);

            //---- label5 ----
            label5.setText("\u56fe\u4e66\u540d\u79f0\uff1a");
            label5.setFont(new Font("\u96b6\u4e66", Font.BOLD, 14));

            //---- label6 ----
            label6.setText("\u4f5c\u8005\u6027\u522b\uff1a");
            label6.setFont(new Font("\u96b6\u4e66", Font.BOLD, 14));

            //---- menJrb ----
            menJrb.setText("\u7537");
            menJrb.setFont(new Font("\u96b6\u4e66", Font.BOLD, 14));
            menJrb.setSelected(true);

            //---- femaleJrb ----
            femaleJrb.setText("\u5973");
            femaleJrb.setFont(new Font("\u96b6\u4e66", Font.BOLD, 14));

            //---- label7 ----
            label7.setText("\u4ef7\u683c\uff1a");
            label7.setFont(new Font("\u96b6\u4e66", Font.BOLD, 14));

            //---- label8 ----
            label8.setText("\u56fe\u4e66\u4f5c\u8005\uff1a");
            label8.setFont(new Font("\u96b6\u4e66", Font.BOLD, 14));

            //---- label9 ----
            label9.setText("\u56fe\u4e66\u7c7b\u522b\uff1a");
            label9.setFont(new Font("\u96b6\u4e66", Font.BOLD, 14));

            //---- label10 ----
            label10.setText("\u56fe\u4e66\u63cf\u8ff0\uff1a");
            label10.setFont(new Font("\u96b6\u4e66", Font.BOLD, 14));

            //---- button2 ----
            button2.setText("\u4fee\u6539");
            button2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button2MouseClicked(e);
                }
            });

            //---- button3 ----
            button3.setText("\u5220\u9664");
            button3.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button3MouseClicked(e);
                }
            });

            GroupLayout panel2Layout = new GroupLayout(panel2);
            panel2.setLayout(panel2Layout);
            panel2Layout.setHorizontalGroup(
                panel2Layout.createParallelGroup()
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(label4)
                                    .addComponent(label7))
                                .addGap(8, 8, 8)
                                .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(priceTxt, GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                                    .addComponent(idTxt, GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE))
                                .addGap(28, 28, 28)
                                .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(label5)
                                    .addComponent(label8))
                                .addGap(18, 18, 18)
                                .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(authorTxt, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addComponent(label9)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(bookTypeJcb, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addComponent(label6)
                                        .addGap(12, 12, 12)
                                        .addComponent(menJrb, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(femaleJrb, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))))
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addComponent(label10, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bookDescTxt, GroupLayout.PREFERRED_SIZE, 489, GroupLayout.PREFERRED_SIZE)))
                        .addGap(37, 37, 37)
                        .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(button2)
                            .addComponent(button3))
                        .addGap(58, 58, 58))
            );
            panel2Layout.setVerticalGroup(
                panel2Layout.createParallelGroup()
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label4, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                            .addComponent(idTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(femaleJrb)
                            .addComponent(menJrb)
                            .addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label5, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label6, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(bookTypeJcb, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label7, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                            .addComponent(priceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(authorTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label8, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label9, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(panel2Layout.createParallelGroup()
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(label10, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(bookDescTxt, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addComponent(button2)
                                        .addGap(43, 43, 43)
                                        .addComponent(button3)))
                                .addGap(48, 48, 48))))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(panel1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(scrollPane1, GroupLayout.Alignment.TRAILING)
                        .addComponent(panel2, GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE))
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(45, 45, 45)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(panel2, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(14, Short.MAX_VALUE))
        );

        //---- buttonGroup1 ----
        var buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(menJrb);
        buttonGroup1.add(femaleJrb);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JScrollPane scrollPane1;
    private JTable bookTable;
    private JPanel panel1;
    private JLabel label1;
    private JTextField s_bookNameTxt;
    private JLabel label2;
    private JTextField s_autuorTxt;
    private JLabel label3;
    private JComboBox s_bookTypeJcb;
    private JButton button1;
    private JPanel panel2;
    private JLabel label4;
    private JTextField idTxt;
    private JLabel label5;
    private JTextField bookNameTxt;
    private JLabel label6;
    private JRadioButton menJrb;
    private JRadioButton femaleJrb;
    private JLabel label7;
    private JTextField priceTxt;
    private JLabel label8;
    private JTextField authorTxt;
    private JLabel label9;
    private JComboBox bookTypeJcb;
    private JTextField bookDescTxt;
    private JLabel label10;
    private JButton button2;
    private JButton button3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
