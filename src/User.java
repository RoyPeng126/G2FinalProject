import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String id;
    private List<Book> booksForSale;
    private List<Review> reviews;

    public User(String id) {
        this.id = id;
        this.booksForSale = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public List<Book> getBooksForSale() {
        return booksForSale;
    }

    public void addBook(Book book) {
        booksForSale.add(book);
    }

    public void removeBook(Book book) {
        booksForSale.remove(book);
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public static boolean checkLogin(String username, String password) {
        String query = "SELECT COUNT(*) FROM users WHERE id = ? AND password = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next() && rs.getInt(1) == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
