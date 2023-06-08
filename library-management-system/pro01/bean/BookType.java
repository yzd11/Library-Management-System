package bean; /**
 * @author 禹治东
 * @version 1.0
 */

/**
 * 图书类别实体
 */
public class BookType {
    //编号
    private int id;
    //图书类别名称
    private String bookTypeName;
    //图书类别描述
    private String bookTypeDesc;

    public BookType() {
    }

    public BookType(int id, String bookTypeName, String bookTypeDesc) {
        this.id = id;
        this.bookTypeName = bookTypeName;
        this.bookTypeDesc = bookTypeDesc;
    }

    @Override
    public String toString() {
        return bookTypeName;
    }

    public BookType(String bookTypeName, String bookTypeDesc) {
        this.bookTypeName = bookTypeName;
        this.bookTypeDesc = bookTypeDesc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookTypeName() {
        return bookTypeName;
    }

    public void setBookTypeName(String bookTypeName) {
        this.bookTypeName = bookTypeName;
    }

    public String getBookTypeDesc() {
        return bookTypeDesc;
    }

    public void setBookTypeDesc(String bookTypeDesc) {
        this.bookTypeDesc = bookTypeDesc;
    }
}
