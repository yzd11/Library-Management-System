package service.dao;

import bean.Book;
import service.utils.MyStringUtils;

import java.util.List;

/**
 * @author 禹治东
 * @version 1.0
 */
public class BookDao extends BasicDao{
    //添加图书
    public int add(Book book){
        //表名后没有描述时VALUES一定要全部写上
        String sql = "INSERT INTO t_book VALUES (null,?,?,?,?,?,?)";
        Object[] params = {book.getBookName(),book.getAuthor(),book.getSex(),book.getPrice(),book.getBookTypeId(),book.getBookDesc()};
        return super.update(sql,params);
    }
    //查询图书
    public List<Book> query(Book book){
        String sql = "SELECT b.id, b.bookName, b.author, b.sex, b.price, b.bookDesc, bt.bookTypeName \n" +
                "FROM t_book b JOIN t_bookType bt ON b.bookTypeId = bt.id";
        if (MyStringUtils.isNotEmpty(book.getBookName())){
            sql = sql + " AND b.bookName LIKE '%" + book.getBookName() + "%'";
        }
        if (MyStringUtils.isNotEmpty(book.getAuthor())){
            sql = sql + " AND b.author LIKE '%" + book.getAuthor() + "%'";
        }
        if (book.getBookTypeId() != null && book.getBookTypeId() != -1){
            sql = sql + " AND b.bookTypeId = " + book.getBookTypeId();
        }
        return super.queryMulti(sql,book.getClass());
    }
    //删除图书
    public int delete(int id){
        String sql = "DELETE FROM t_book WHERE id = ?";
        Object[] params = {id};
        return update(sql,params);
    }
    //修改图书
    public int update(Book book){
        String sql = "UPDATE t_book SET bookName = ? ,author = ? ,sex = ? ,price = ? ,bookTypeId = ? ,bookDesc = ? where id = ?";
        Object[] params = {book.getBookName(),book.getAuthor(),book.getSex(),book.getPrice(),book.getBookTypeId(),book.getBookDesc(),book.getId()};
        return update(sql,params);
    }
    //判定指定类别下图书是否存在
    public boolean existBookByBookTypeId(String bookTypeId){
        String sql = "SELECT * FROM t_book WHERE bookTypeId = ?";
        Object[] params = {bookTypeId};
        List list = super.queryMulti(sql, Book.class, params);
        if (list != null){
            return true;
        }else {
            return false;
        }
    }
    //按照id查找图书
    public Book query(int id){
        String sql = "SELECT * FROM t_book WHERE id = ?";
        Object[] params = {id};
        return (Book) querySingle(sql,Book.class,params);
    }
}
