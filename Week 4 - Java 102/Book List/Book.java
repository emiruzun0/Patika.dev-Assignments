public class Book {
    private String name;
    private int pageNum;
    private String writerName;
    private String publishDate;

    public Book(String name, int pageNum, String writerName, String publishDate) {
        this.name = name;
        this.pageNum = pageNum;
        this.writerName =writerName;
        this.publishDate = publishDate;
    }

    public String getName() {
        return name;
    }

    public int getPageNum() {
        return pageNum;
    }

    public String getWriterName() {
        return writerName;
    }

    public String getPublishDate() {
        return publishDate;
    }
}
