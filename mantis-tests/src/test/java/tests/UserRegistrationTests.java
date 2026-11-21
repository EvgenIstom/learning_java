package tests;

import org.junit.jupiter.api.Test;

public class UserRegistrationTests extends TestBase {

    @Test
    void canRegisterUser(String username) {
        var email = String.format("%s@localhost", username);
        // создать адрес на почтовом серевере(jameshelper)
        // заполняем форму создания в мантис и отправляем (браузер - класс помощник)
        // ждем почту(mailhelper)
        //извлекаем ссылку из письма
//        проходим по ссылке и завершаем регистраци (браузер)
        // проверяем, что пользователь может залогиниться(httpsessionhelper)
    }
}
