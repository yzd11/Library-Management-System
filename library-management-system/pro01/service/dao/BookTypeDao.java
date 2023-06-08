package service.dao;

import bean.BookType;
import service.utils.MyStringUtils;

import java.util.List;

/**
 * @author 禹治东
 * @version 1.0
 */
public class BookTypeDao extends BasicDao{
    //图书类别添加
    public int add(BookType bookType){
        String sql = "INSERT INTO t_bookType(bookTypeName,bookTypeDesc) VALUES (?,?)";
        Object[] params = {bookType.getBookTypeName(),bookType.getBookTypeDesc()};
        return super.update(sql,params);
    }
    //图书类别查询
    public List<BookType> query(BookType bookType){
        StringBuffer sql = new StringBuffer("SELECT * FROM t_bookType");
        if (MyStringUtils.isNotEmpty(bookType.getBookTypeName()) == true){
            sql.append(" WHERE bookTypeName LIKE '%"+bookType.getBookTypeName()+"%'");
        }
        return super.queryMulti(new String(sql),bookType.getClass());
    }
    //图书类别删除
    public int delete(int id){
        String sql = "DELETE FROM t_bookType WHERE id = ?";
        Object[] params = {id};
        return super.update(sql,params);
    }
    //图书类型修改
    public int update(BookType bookType){
        String sql = "UPDATE t_bookType SET bookTypeName = ? ,bookTypeDesc = ? where id = ?";
        Object[] params = {bookType.getBookTypeName(),bookType.getBookTypeDesc(),bookType.getId()};
        return super.update(sql,params);
    }
}
