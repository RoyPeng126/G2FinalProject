import java.sql.*;

public class UserDAO {
    private String currentUserID;

    public void setCurrentUserID(String currentUserID) {
        this.currentUserID = currentUserID;
    }

    public String getCurrentUserID() {
        return currentUserID;
    }

    public void addUser(String studentID, String password) {
        String query = "INSERT INTO user (studentID, password) VALUES (?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, studentID);
            stmt.setString(2, password);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkUser(String studentID, String password) {
        String query = "SELECT * FROM user WHERE studentID = ? AND password = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, studentID);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                setCurrentUserID(studentID);
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void addBook(String bookName, String author, String edition, String sellerID) {
        String query = "INSERT INTO book (bookname, author, edition, sellerID, status) VALUES (?, ?, ?, ?, 'Available')";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, bookName);
            stmt.setString(2, author);
            stmt.setString(3, edition);
            stmt.setString(4, sellerID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void purchaseBook(String sellerID, String bookName) {
        String query = "UPDATE book SET status = 'Sold' WHERE sellerID = ? AND bookname = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, sellerID);
            stmt.setString(2, bookName);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBuyerID(String sellerID, String bookName, String buyerID) {
        String query = "UPDATE book SET buyerID = ? WHERE sellerID = ? AND bookname = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, buyerID);
            stmt.setString(2, sellerID);
            stmt.setString(3, bookName);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBookStatus(String sellerID, String bookName) {
        String query = "UPDATE book SET status = 'Updated' WHERE sellerID = ? AND bookname = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, sellerID);
            stmt.setString(2, bookName);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addReview(String sellerID, String reviewText) {
        String query = "INSERT INTO reviews (sellerID, review) VALUES (?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, sellerID);
            stmt.setString(2, reviewText);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
