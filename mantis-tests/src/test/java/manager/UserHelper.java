package manager;

import model.UserData;
import org.openqa.selenium.By;

public class UserHelper extends HelperBase{
    public UserHelper(ApplicationManager manager) {
        super(manager);
    }


    public void registerUser(UserData user) {
        openUserRegistrationPage();
        fillRegistrationForm(user);
        submitRegistrationForm();
    }

    public void openUserRegistrationPage() {
        click(By.linkText("Signup for a new account"));
    }

    private void submitRegistrationForm() {
        click(By.cssSelector("input[type=submit]"));
    }

    private void fillRegistrationForm(UserData user) {
        type(By.name("email"), user.email());
        type(By.name("username"), user.username());
    }

    public void completeRegistration(UserData user) {
        type(By.name("realname"), user.username());
        type(By.name("password"), user.password());
        type(By.name("password_confirm"), user.password());
        click(By.cssSelector("button[type='submit']"));
    }



}
