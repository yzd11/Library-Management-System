/*
 * Created by JFormDesigner on Tue May 30 16:11:29 HKT 2023
 */

package moudle.interior;

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
public class BookTypeManageInter extends JInternalFrame {
    public BookTypeManageInter() {
        initComponents();
        fillTable(new BookType());
    }

    //处理查询事件
    private void button1MouseClicked(MouseEvent e) {
        // TODO add your code here
        String bookTypeNameTXT = this.s_bookTypeNameTXT.getText();
        BookType bookType = new BookType();
        bookType.setBookTypeName(bookTypeNameTXT);
        fillTable(bookType);
    }

    //处理鼠标点击表格行事件
    private void bookTypeTableMousePressed(MouseEvent e) {
        // TODO add your code here
        //获取点击的行号
        int selectedRow = bookTypeTable.getSelectedRow();
        //设置内容
        idTXT.setText(""+bookTypeTable.getValueAt(selectedRow,0));
        bookTypeNameTXT.setText(""+bookTypeTable.getValueAt(selectedRow,1));
        bookTypeDescTXT.setText(""+bookTypeTable.getValueAt(selectedRow,2));
    }

    //处理鼠标点击修改事件
    private void button2MouseClicked(MouseEvent e) {
        // TODO add your code here
        String id = this.idTXT.getText();
        String bookTypeName = this.bookTypeNameTXT.getText();
        String bookTypeDesc = this.bookTypeDescTXT.getText();
        //id不可为空
        if(MyStringUtils.isEmpty(id)){
            JOptionPane.showMessageDialog(null,"请选择修改内容");
            return;
        }
        //名称类别不可为空
        if (MyStringUtils.isEmpty(bookTypeName)){
            JOptionPane.showMessageDialog(null,"名称类别为空");
            return;
        }
        //修改
        BookType bookType = new BookType(Integer.parseInt(id), bookTypeName, bookTypeDesc);
        int update = new BookTypeDao().update(bookType);
        if(update > 0){
            JOptionPane.showMessageDialog(null,"修改成功");
            //重置表单
            resetValue();
            //刷新表格
            this.fillTable(new BookType());
        }
        else {
            JOptionPane.showMessageDialog(null,"修改失败");
        }
    }

    //处理鼠标点击删除事件
    private void button3MouseClicked(MouseEvent e) {
        // TODO add your code here
        String id = this.idTXT.getText();
        //id不可为空
        if(MyStringUtils.isEmpty(id)){
            JOptionPane.showMessageDialog(null,"请选择删除内容");
            return;
        }
        int i = JOptionPane.showConfirmDialog(null, "确定要删除吗?");
        if(i == 0){
            boolean flag = new BookDao().existBookByBookTypeId(id);
            if (flag){
                JOptionPane.showMessageDialog(null,"当前类型下存在图书，无法删除");
                return;
            }
            int delete = new BookTypeDao().delete(Integer.parseInt(id));
            if (delete > 0){
                JOptionPane.showMessageDialog(null,"删除成功");
                //重置表单
                resetValue();
                //刷新表格
                this.fillTable(new BookType());
            }
            else {
                JOptionPane.showMessageDialog(null,"删除失败");
            }
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        scrollPane1 = new JScrollPane();
        bookTypeTable = new JTable();
        label1 = new JLabel();
        s_bookTypeNameTXT = new JTextField();
        button1 = new JButton();
        panel1 = new JPanel();
        label2 = new JLabel();
        idTXT = new JTextField();
        label3 = new JLabel();
        bookTypeNameTXT = new JTextField();
        label4 = new JLabel();
        bookTypeDescTXT = new JTextField();
        button2 = new JButton();
        button3 = new JButton();

        //======== this ========
        setVisible(true);
        setTitle("\u56fe\u4e66\u7c7b\u522b\u7ba1\u7406");
        setClosable(true);
        setIconifiable(true);
        var contentPane = getContentPane();

        //======== scrollPane1 ========
        {

            //---- bookTypeTable ----
            bookTypeTable.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                    "\u7f16\u53f7", "\u56fe\u4e66\u7c7b\u578b\u540d\u79f0", "\u56fe\u4e66\u7c7b\u578b\u63cf\u8ff0"
                }
            ) {
                boolean[] columnEditable = new boolean[] {
                    false, true, false
                };
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return columnEditable[columnIndex];
                }
            });
            bookTypeTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    bookTypeTableMousePressed(e);
                }
            });
            scrollPane1.setViewportView(bookTypeTable);
        }

        //---- label1 ----
        label1.setText("\u56fe\u4e66\u7c7b\u578b\u540d\u79f0\uff1a");
        label1.setFont(new Font("\u96b6\u4e66", Font.BOLD, 14));

        //---- button1 ----
        button1.setText("\u67e5\u8be2");
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button1MouseClicked(e);
            }
        });

        //======== panel1 ========
        {
            panel1.setBorder(new TitledBorder(null, "\u8868\u5355\u64cd\u4f5c", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.ABOVE_TOP,
                new Font("\u96b6\u4e66", Font.BOLD, 16)));

            //---- label2 ----
            label2.setText("\u7f16\u53f7\uff1a");
            label2.setFont(new Font("\u96b6\u4e66", Font.BOLD, 16));

            //---- idTXT ----
            idTXT.setEditable(false);

            //---- label3 ----
            label3.setText("\u56fe\u4e66\u7c7b\u522b\u540d\u79f0\uff1a");
            label3.setFont(new Font("\u96b6\u4e66", Font.BOLD, 16));

            //---- label4 ----
            label4.setText("\u63cf\u8ff0\uff1a");
            label4.setFont(new Font("\u96b6\u4e66", Font.BOLD, 16));

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

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(label4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addGroup(panel1Layout.createSequentialGroup()
                                    .addComponent(idTXT, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
                                    .addGap(40, 40, 40)
                                    .addComponent(label3)
                                    .addGap(28, 28, 28)
                                    .addComponent(bookTypeNameTXT, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE))
                                .addComponent(bookTypeDescTXT))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(button2)
                                .addGap(120, 120, 120)
                                .addComponent(button3)))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label2, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                            .addComponent(idTXT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label3, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                            .addComponent(bookTypeNameTXT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label4, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                            .addComponent(bookTypeDescTXT, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(button2)
                            .addComponent(button3))
                        .addContainerGap(20, Short.MAX_VALUE))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(109, 109, 109)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 554, GroupLayout.PREFERRED_SIZE)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(s_bookTypeNameTXT, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
                            .addGap(85, 85, 85)
                            .addComponent(button1))
                        .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap(132, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(37, 37, 37)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                        .addComponent(s_bookTypeNameTXT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(button1))
                    .addGap(33, 33, 33)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
                    .addGap(29, 29, 29)
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(45, Short.MAX_VALUE))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }
    //表格初始化
    public void fillTable(BookType bookType){
        DefaultTableModel dtm = (DefaultTableModel) bookTypeTable.getModel();
        //表格清空
        dtm.setRowCount(0);
        //进行查询
        List<BookType> bookTypeList = new BookTypeDao().query(bookType);
        //进行list -> vector
        for (BookType temp : bookTypeList) {
            //需要先按项取出，在放入vector
            Vector<Object> bookTypeVector = new Vector<>();
            bookTypeVector.add(temp.getId());
            bookTypeVector.add(temp.getBookTypeName());
            bookTypeVector.add(temp.getBookTypeDesc());
            dtm.addRow(bookTypeVector);
        }
    }
    //重置表单
    public void resetValue(){
        this.idTXT.setText("");
        this.bookTypeNameTXT.setText("");
        this.bookTypeDescTXT.setText("");
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JScrollPane scrollPane1;
    private JTable bookTypeTable;
    private JLabel label1;
    private JTextField s_bookTypeNameTXT;
    private JButton button1;
    private JPanel panel1;
    private JLabel label2;
    private JTextField idTXT;
    private JLabel label3;
    private JTextField bookTypeNameTXT;
    private JLabel label4;
    private JTextField bookTypeDescTXT;
    private JButton button2;
    private JButton button3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
