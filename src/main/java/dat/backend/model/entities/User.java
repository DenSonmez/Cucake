package dat.backend.model.entities;

import java.util.Objects;

public class User
{
    private String username;
    private String password;
    private String role;
    private int saldo;
    private String email;


    public User(String username, String password, String role, int saldo, String email) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.saldo = saldo;
        this.email = email;

    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username.equals(user.username) && password.equals(user.password) && role.equals(user.role) && email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, role, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", saldo=" + saldo +
                ", email='" + email + '\'' +
                '}';
    }
}
