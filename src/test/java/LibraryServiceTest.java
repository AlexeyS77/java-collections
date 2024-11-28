import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LibraryServiceTest {

    private LibraryService libraryService;
    private User user1;
    private User user2;
    private Book book1;
    private Book book2;

    @BeforeEach
    void setUp() {
        user1 = mock(User.class);
        user2 = mock(User.class);
        book1 = mock(Book.class);
        book2 = mock(Book.class);

        when(user1.getUserId()).thenReturn(1L);
        when(user2.getUserId()).thenReturn(2L);
        when(book1.getBookId()).thenReturn(101L);
        when(book2.getBookId()).thenReturn(102L);

        ArrayList<User> users = new ArrayList<>(Arrays.asList(user1, user2));
        ArrayList<Book> books = new ArrayList<>(Arrays.asList(book1, book2));

        libraryService = new LibraryService(users, books);
    }

    @Test
    void testGetAllBooks() {
        List<Book> allBooks = libraryService.getAllBooks();
        assertEquals(2, allBooks.size());
        assertTrue(allBooks.contains(book1));
        assertTrue(allBooks.contains(book2));
    }

    @Test
    void testGetAllAvailableBooks() {
        List<Book> availableBooks = libraryService.getAllAvailableBooks();
        assertEquals(2, availableBooks.size());
        assertTrue(availableBooks.contains(book1));
        assertTrue(availableBooks.contains(book2));
    }

    @Test
    void testTakeBook_BookAlreadyTaken() {
        libraryService.takeBook(1L, 101L);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            libraryService.takeBook(2L, 101L);
        });
        assertEquals("Книга уже взята", exception.getMessage());
    }

    @Test
    void testReturnBook_BookNotTakenByUser() {
        libraryService.takeBook(1L, 101L);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            libraryService.returnBook(2L, 101L);
        });
        assertEquals("Книга не была взята данным пользователем", exception.getMessage());
    }

    @Test
    void testTakeBook_UserExistsAndBookExists() {
        libraryService.takeBook(1L, 101L);

        List<Book> userBooks = libraryService.getUserBooks(1L);
        assertEquals(1, userBooks.size());
        assertTrue(userBooks.contains(book1));
    }





}
