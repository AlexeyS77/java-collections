import java.util.HashSet;
import java.util.Objects;

public class User {
    private String name;
    private int age;
    private Long userId;

    public User(String name, int age, Long userId) {
        this.name = name;
        this.age = age;
        this.userId = userId;
    }

    HashSet<Book> books = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getAge() == user.getAge() && getName().equals(user.getName()) && getUserId().equals(user.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAge(), getUserId());
    }
}
