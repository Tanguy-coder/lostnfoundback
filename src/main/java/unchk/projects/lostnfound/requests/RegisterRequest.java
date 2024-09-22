package unchk.projects.lostnfound.requests;

public class RegisterRequest {
    private String name;
    private String username;
    private String password;
    private String telephone;
    private String email;
    private String role;

    public RegisterRequest() {
        super();
    }

    public RegisterRequest(String name, String password, String username, String telephone, String email, String role) {
        this.name = name;
        this.password = password;
        this.username = username;
        this.telephone = telephone;
        this.email = email;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
