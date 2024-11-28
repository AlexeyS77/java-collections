import java.util.*;


public class LibraryService {
    private Map<Long, User> idToUserMap;
    private Map<Long, Book> idToBookMap;
    private Map<Long, Long> takenBookMap; // bookId -> userId
    private Map<Long, Set<Long>> userTakenBooksMap; // userId -> set of bookIds


    public LibraryService(ArrayList<User> users, ArrayList<Book> books) {
        idToUserMap = new HashMap<>();
        idToBookMap = new HashMap<>();
        takenBookMap = new HashMap<>();
        userTakenBooksMap = new HashMap<>();

        for (User user : users) {
            idToUserMap.put(user.getUserId(), user);
            userTakenBooksMap.put(user.getUserId(), new HashSet<>());
        }

        for (Book book : books) {
            idToBookMap.put(book.getBookId(), book);
        }
    }
    //возвращает список всех книг
    public List<Book> getAllBooks() {
        return new ArrayList<>(idToBookMap.values());
    }

    //возвращает список доступных книг (не на руках у пользователей)
    public List<Book> getAllAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        for (Book book : idToBookMap.values()) {
            if (!takenBookMap.containsKey(book.getBookId())) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }
    // возвращает список книг, взятых пользователем
    public List<Book> getUserBooks(Long userId) throws NoSuchElementException {
        if (!idToUserMap.containsKey(userId)) {
            throw new NoSuchElementException("Пользователь не найден");
        }

        Set<Long> bookIds = userTakenBooksMap.get(userId);
        List<Book> userBooks = new ArrayList<>();

        for (Long bookId : bookIds) {
            userBooks.add(idToBookMap.get(bookId));
        }

        return userBooks;
    }
    //пользователь берет книгу
    public void takeBook(Long userId, Long bookId) throws NoSuchElementException, IllegalStateException {
        if (!idToUserMap.containsKey(userId)) {
            throw new NoSuchElementException("Пользователь не найден");
        }

        if (!idToBookMap.containsKey(bookId)) {
            throw new NoSuchElementException("Книга не найдена");
        }

        if (takenBookMap.containsKey(bookId)) {
            throw new IllegalStateException("Книга уже взята");
        }

        takenBookMap.put(bookId, userId);
        userTakenBooksMap.get(userId).add(bookId);
    }
    // возвращает список всех книг
    public void returnBook(Long userId, Long bookId) throws NoSuchElementException, IllegalStateException {
        if (!idToUserMap.containsKey(userId)) {
            throw new NoSuchElementException("Пользователь не найден");
        }

        if (!idToBookMap.containsKey(bookId)) {
            throw new NoSuchElementException("Книга не найдена");
        }
        if (!takenBookMap.containsKey(bookId) || !takenBookMap.get(bookId).equals(userId)) {
            throw new IllegalStateException("Книга не была взята данным пользователем");
        }

        takenBookMap.remove(bookId);
        userTakenBooksMap.get(userId).remove(bookId);
    }
    
}
