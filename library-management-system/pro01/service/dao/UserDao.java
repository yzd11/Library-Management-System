package service.dao;

import bean.User;

/**
 * @author 禹治东
 * @version 1.0
 */

public class UserDao extends BasicDao {
    //登录验证
    public User login(User user){
        User resultUser = null;
        String sql = "SELECT * FROM t_user WHERE userName = ? AND password = ?";
        Object[] params = {user.getUserName(),user.getPassword()};
        resultUser = (User) super.querySingle(sql, User.class, params);
        return resultUser;
    }
    //验证是否存在用户
    public User loginExistence(User user){
        User resultUser = null;
        String sql = "SELECT * FROM t_user WHERE userName = ?";
        Object[] params = {user.getUserName()};
        resultUser = (User) super.querySingle(sql, User.class, params);
        return resultUser;
    }
    //修改信息
    public int update(User user){
        String sql = "UPDATE t_user SET userName = ? ,password = ? ,userDesc = ? where id = ?";
        Object[] params = {user.getUserName(),user.getPassword(),user.getUserDesc(),user.getId()};
        return update(sql,params);
    }
    //注销用户
    public int delete(int id){
        String sql = "DELETE FROM t_user WHERE id = ?";
        Object[] params = {id};
        return update(sql,params);
    }
    //添加用户
    public int add(User user){
        String sql = "INSERT INTO t_user VALUES (null,?,?,?)";
        Object[] params = {user.getUserName(),user.getPassword(),user.getUserDesc()};
        return update(sql,params);
    }
}

