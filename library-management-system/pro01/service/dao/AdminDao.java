package service.dao;

import bean.Admin;

/**
 * @author 禹治东
 * @version 1.0
 */

public class AdminDao extends BasicDao {
    //登录验证
    public Admin login(Admin admin){
        Admin resultAdmin = null;
        String sql = "SELECT * FROM t_admin WHERE userName = ? AND password = ?";
        Object[] params = {admin.getUserName(),admin.getPassword()};
        resultAdmin = (Admin) super.querySingle(sql, Admin.class, params);
        return resultAdmin;
    }
    //验证是否存在用户
    public Admin loginExistence(Admin admin){
        Admin resultAdmin = null;
        String sql = "SELECT * FROM t_admin WHERE userName = ?";
        Object[] params = {admin.getUserName()};
        resultAdmin = (Admin) super.querySingle(sql, Admin.class, params);
        return resultAdmin;
    }
    //修改信息
    public int update(Admin admin){
        String sql = "UPDATE t_admin SET userName = ? ,password = ? ,userDesc = ? where id = ?";
        Object[] params = {admin.getUserName(),admin.getPassword(),admin.getUserDesc(),admin.getId()};
        return update(sql,params);
    }
    //注销用户
    public int delete(int id){
        String sql = "DELETE FROM t_admin WHERE id = ?";
        Object[] params = {id};
        return update(sql,params);
    }
    //添加用户
    public int add(Admin admin){
        String sql = "INSERT INTO t_admin VALUES (null,?,?,?)";
        Object[] params = {admin.getUserName(),admin.getPassword(),admin.getUserDesc()};
        return update(sql,params);
    }
}

