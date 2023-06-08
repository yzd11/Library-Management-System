import moudle.start.Login;

import javax.swing.*;

/**
 * @author 禹治东
 * @version 1.0
 */
public class App {
    public static void main(String[] args) {
        Login login = new Login();
        login.setVisible(true);
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
