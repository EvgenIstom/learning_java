package tests;

import common.CommonFunctions;
import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.time.Duration;
import java.util.regex.Pattern;

public class UserRegistrationTests extends TestBase {

    @Test
    void canRegisterUser() {
        var username = "user_" + CommonFunctions.randomString(10);
        var user = new UserData()
                .withUsername(username)
                .withEmail(username + "@localhost")
                .withPassword(CommonFunctions.randomString(10));

        app.jamesCli().addUser(user.email(), "password");        // создать адрес на почтовом серевере(jameshelper)

        app.user().registerUser(user);// заполняем форму создания в мантис и отправляем (браузер - класс помощник-создать)

        var messages = app.mail().receive(user.email(), "password", Duration.ofSeconds(10));
        Assertions.assertEquals(1, messages.size());

        var text = messages.get(0).content();
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);

        if (matcher.find()) {
            var url = text.substring(matcher.start(), matcher.end());
            app.driver().get(url);//        проходим по ссылке и завершаем регистраци (браузер)
            app.user().completeRegistration(user);
        }
        app.http().login(user.username(), user.password());
        Assertions.assertTrue(app.http().isLoggedIn()); // проверяем, что пользователь может залогиниться(httpsessionhelper)
    }
}
