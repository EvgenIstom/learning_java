package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;


public class CreateContactTests extends TestBase {

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();
        for (var firstName : List.of("", "first name")) {
            for (var lastName : List.of("", "last name")) {
                for (var address : List.of("", "address"))
                    result.add(new ContactData(firstName, lastName, address));
            }
        }
        for (int i = 1; i <= 6; i++) {
            result.add(new ContactData(randomString(i), randomString(i), randomString(i)));
        }
        return result;
    }

    public static List<ContactData> negativeContactProvider() {
        var result = new ArrayList<ContactData>(List.of(
                new ContactData("hello contact'", "", "")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateContacts(ContactData contact) {
        int contactCount = app.contacts().getCount();
        app.contacts().createContact(contact);
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount + 1, newContactCount);
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void canNotCreateContacts(ContactData contact) {
        int contactCount = app.contacts().getCount();
        app.contacts().createContact(contact);
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount, newContactCount);
    }

}

