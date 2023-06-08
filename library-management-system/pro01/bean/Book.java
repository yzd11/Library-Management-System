package bean;

/**
 * @author 禹治东
 * @version 1.0
 */

/**
 * 图书实体
 */
public class Book {
    //图书编号
    private int id;
    //图书名称
    private String bookName;
    //图书作者
    private String author;
    //作者性别
    private String sex;

    public Book(String bookName) {
        this.bookName = bookName;
    }

    public Book(int id, String bookName, String author, String sex, double price, Integer bookTypeId, String bookDesc) {
        this.id = id;
        this.bookName = bookName;
        this.author = author;
        this.sex = sex;
        this.price = price;
        this.bookTypeId = bookTypeId;
        this.bookDesc = bookDesc;
    }

    //图书价格
    private double price;
    //图书类型Id
    private Integer bookTypeId;
    //图书类型名称
    private String bookTypeName;
    //图书内容描述
    private String bookDesc;

    public Book(int id) {
        this.id = id;
    }

    public Book(String bookName, String author, Integer bookTypeId) {
        this.bookName = bookName;
        this.author = author;
        this.bookTypeId = bookTypeId;
    }

    public Book(String bookName, String author, String sex, double price, Integer bookTypeId, String bookDesc) {
        this.bookName = bookName;
        this.author = author;
        this.sex = sex;
        this.price = price;
        this.bookTypeId = bookTypeId;
        this.bookDesc = bookDesc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getBookTypeId() {
        return bookTypeId;
    }

    public void setBookTypeId(Integer bookTypeId) {
        this.bookTypeId = bookTypeId;
    }

    public String getBookTypeName() {
        return bookTypeName;
    }

    public void setBookTypeName(String bookTypeName) {
        this.bookTypeName = bookTypeName;
    }

    public String getBookDesc() {
        return bookDesc;
    }

    public void setBookDesc(String bookDesc) {
        this.bookDesc = bookDesc;
    }

    public Book() {
    }

    public Book(int id, String bookName, String author, String sex, double price, Integer bookTypeId, String bookTypeName, String bookDesc) {
        this.id = id;
        this.bookName = bookName;
        this.author = author;
        this.sex = sex;
        this.price = price;
        this.bookTypeId = bookTypeId;
        this.bookTypeName = bookTypeName;
        this.bookDesc = bookDesc;
    }
}
