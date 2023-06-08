package service.dao;

import bean.Book;
import bean.Borrow;
import service.utils.MyStringUtils;

import java.util.List;

/**
 * @author 禹治东
 * @version 1.0
 */
public class BorrowDao extends BasicDao{
    //图书借阅
    public int add(Borrow borrow){
        String sql = "INSERT INTO t_borrow(user_id,book_id) VALUES (?,?)";
        Object[] params = {borrow.getUser_id(),borrow.getBook_id()};
        return super.update(sql,params);
    }
    //图书归还
    public int delete(int id){
        String sql = "DELETE FROM t_borrow WHERE id = ?";
        Object[] params = {id};
        return update(sql,params);
    }
    //借阅查询
    public List<Borrow> query(Borrow borrow,Book book,String user_id){
        String sql = "SELECT b.id, b.user_id,b.book_id,b.borrow_date\n" +
                "FROM t_borrow b\n" +
                "JOIN t_book bk ON b.book_id = bk.id\n" +
                "JOIN t_user u ON b.user_id = u.id\n" +
                "JOIN t_bookType bt ON bk.bookTypeId = bt.id\n"+
                "WHERE b.user_id = "+user_id;
        if (MyStringUtils.isNotEmpty(book.getBookName())){
            sql = sql + " AND bk.bookName LIKE '%" + book.getBookName() + "%'";
        }
        if (MyStringUtils.isNotEmpty(book.getAuthor())){
            sql = sql + " AND bk.author LIKE '%" + book.getAuthor() + "%'";
        }
        if (book.getBookTypeId() != null && book.getBookTypeId() != -1){
            sql = sql + " AND bk.bookTypeId = " + book.getBookTypeId();
        }
        return super.queryMulti(sql,borrow.getClass());
    }
}
