package moudle.interior;/*
 * Created by JFormDesigner on Tue May 30 11:04:44 HKT 2023
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author 17946
 */
public class Yzd11Inter extends JInternalFrame {
    public Yzd11Inter() {
        initComponents();
    }

    //添加访问事件

    private void label2MouseClicked(MouseEvent e) {
        // TODO add your code here
        Desktop desktop = Desktop.getDesktop();
        try {
            //访问我的github主页
            URI uri = new URI("https://yzd11.github.io/"); //创建URI统一资源标识符
            desktop.browse(uri); //使用默认浏览器打开超链接
        } catch (URISyntaxException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - yzd
        label2 = new JLabel();
        label1 = new JLabel();

        //======== this ========
        setVisible(true);
        setClosable(true);
        setIconifiable(true);
        setTitle("\u5173\u4e8eyzd11");
        var contentPane = getContentPane();

        //---- label2 ----
        label2.setIcon(new ImageIcon(getClass().getResource("/img/Quantum.png")));
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                label2MouseClicked(e);
            }
        });

        //---- label1 ----
        label1.setText(" \u6b22\u8fce\u8bbf\u95ee\u6211\u7684Blog");
        label1.setFont(new Font("\u534e\u6587\u96b6\u4e66", Font.BOLD | Font.ITALIC, 26));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(256, 256, 256)
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(146, 146, 146)
                            .addComponent(label2, GroupLayout.PREFERRED_SIZE, 455, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(199, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label2, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)
                    .addGap(227, 227, 227))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - yzd
    private JLabel label2;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
