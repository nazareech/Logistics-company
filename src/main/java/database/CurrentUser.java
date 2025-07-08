package database;

public class CurrentUser {
private static CurrentUser instance;

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private int roleId;
    private String gender;

    public CurrentUser() {
    }

    // Приватний конструктор, щоб заборонити створення нових об'єктів
    public void initUser(String name, String lastName,
                String username, String password,
                String email, int roleId,
                String gender) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roleId = roleId;
        this.gender = gender;
        this.firstName = name;
        this.lastName = lastName;
    }

    // Метод для отримання єдиного екземпляра
    public static synchronized CurrentUser getInstance() {
        if (instance == null) {
            instance = new CurrentUser();
        }
        return instance;
    }

    // Getter для імені
    public String getName() {
        return firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getRoleId() {
        return roleId;
    }

    public String getGender() {
        return gender;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Метод для скидання даних (на випадок виходу користувача)
    public static void resetInstance() {
        instance = null;
    }

}
