package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginTests extends TestBase {

    @Test
    void canLogin() {
        app.http().login(app.property("web.username"), app.property("web.password"));
        Assertions.assertTrue(app.http().isLoggedIn());
    }
}
