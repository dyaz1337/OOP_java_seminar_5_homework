public class User {
    private String name;
    private String username;
    private String password;

    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

import java.util.Scanner;

public class UserView {
    private Scanner scanner = new Scanner(System.in);

    public User register() {
        System.out.println("Enter your name:");
        String name = scanner.nextLine();
        System.out.println("Enter your username:");
        String username = scanner.nextLine();
        System.out.println("Enter your password:");
        String password = scanner.nextLine();
        return new User(name, username, password);
    }

    public String login() {
        System.out.println("Enter your username:");
        return scanner.nextLine();
    }

    public String changePassword() {
        System.out.println("Enter your new password:");
        return scanner.nextLine();
    }
}

public class UserPresenter {
    private User model;
    private UserView view;

    public UserPresenter(User model, UserView view) {
        this.model = model;
        this.view = view;
    }

    public void register() {
        User user = view.register();
        model = user;
    }

    public void login() {
        String username = view.login();
        if (!username.equals(model.getUsername())) {
            System.out.println("Invalid username");
            return;
        }
        String password = view.changePassword();
        if (!password.equals(model.getPassword())) {
            System.out.println("Invalid password");
            return;
        }
        System.out.println("Login successful");
    }

    public void changePassword() {
        String password = view.changePassword();
        model.setPassword(password);
        System.out.println("Password changed");
    }
}

public class UserManagementApp {
    public static void main(String[] args) {
        User model = new User("", "", "");
        UserView view = new UserView();
        UserPresenter presenter = new UserPresenter(model, view);

        presenter.register();
        presenter.login();
        presenter.changePassword();
    }
}