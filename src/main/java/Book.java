import java.util.Objects;

public class Book {
    private String title;
    private String author;
    private int year;
    private Long bookId;

    public Book(String title, String author, int year, Long bookId) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.bookId = bookId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return getYear() == book.getYear() && getTitle().equals(book.getTitle()) && getAuthor().equals(book.getAuthor()) && getBookId().equals(book.getBookId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getAuthor(), getYear(), getBookId());
    }
}
