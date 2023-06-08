package bean;

/**
 * @author 禹治东
 * @version 1.0
 */
//管理员实体
public class Admin {
    //用户编号
    private int id;
    //用户名称
    private String userName;
    //用户密码
    private String password;
    //个人介绍
    private String userDesc;

    public Admin(int id, String userName, String password, String userDesc) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.userDesc = userDesc;
    }


    public Admin(String userName, String password, String userDesc) {
        this.userName = userName;
        this.password = password;
        this.userDesc = userDesc;
    }

    public String getUserDesc() {
        return userDesc;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }

    public Admin(int id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public Admin(String userName) {
        this.userName = userName;
    }

    public Admin(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public Admin() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
