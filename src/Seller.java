class Seller {
    private String username;
    private String password;
    // Other seller attributes and methods can be added as needed

    public Seller(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getter methods
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}