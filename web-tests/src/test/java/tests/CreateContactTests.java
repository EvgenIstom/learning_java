package tests;

import common.CommonFunctions;
import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class CreateContactTests extends TestBase {

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();
//        for (var firstName : List.of("", "first name")) {
//            for (var lastName : List.of("", "last name")) {
//                for (var address : List.of("", "address"))
//                    result.add(new ContactData()
//                        .withFirstName(firstName)
//                        .withLastName(lastName)
//                        .withAddress(address)
//                        .withPhoto("src/test/resources/images/random-person.jpeg"));
//            }
//        }
        for (int i = 1; i <= 6; i++) {
            result.add(new ContactData()
                    .withFirstName(CommonFunctions.randomString(i))
                    .withLastName(CommonFunctions.randomString(i))
                    .withAddress(CommonFunctions.randomString(i))
                    .withPhoto(randomFile("src/test/resources/images")));
        }
        return result;
    }

    public static List<ContactData> negativeContactProvider() {
        var result = new ArrayList<ContactData>(List.of(
                new ContactData("", "hello contact'", "", "", "")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateContacts(ContactData contact) {
        var oldContacts = app.contacts().getContactsList();
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getContactsList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);

        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(newContacts.get(newContacts.size() - 1).id()));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
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

