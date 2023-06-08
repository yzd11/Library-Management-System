package bean;

import java.sql.Timestamp;
//借阅记录实体
/**
 * @author 禹治东
 * @version 1.0
 */
public class Borrow {
    //借阅编号
    private int id;
    //用户编号
    private int user_id;

    public Borrow(int user_id) {
        this.user_id = user_id;
    }

    //图书编号
    private int book_id;
    //借阅时间
    private Timestamp borrow_date;
    //归还时间
    private Timestamp return_date;

    public Timestamp getBorrow_date() {
        return borrow_date;
    }

    public void setBorrow_date(Timestamp borrow_date) {
        this.borrow_date = borrow_date;
    }

    public Timestamp getReturn_date() {
        return return_date;
    }

    public void setReturn_date(Timestamp return_date) {
        this.return_date = return_date;
    }

    public Borrow(int user_id, int book_id) {
        this.user_id = user_id;
        this.book_id = book_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public Borrow(int id, int user_id, int book_id) {
        this.id = id;
        this.user_id = user_id;
        this.book_id = book_id;
    }

    public Borrow() {
    }
}
