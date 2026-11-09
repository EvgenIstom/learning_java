package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;


public class UpdateContactTests extends TestBase {
    @Test
    void canUpdateContact() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData("", "ContactCreatedByUpdateContactTest", "lastname", "address", ""));
        }
        var oldContacts = app.contacts().getContactsList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        var testData = new ContactData().withFirstName("UpdatedContact");
        app.contacts().updateContact(oldContacts.get(index), testData);
        var newContacts = app.contacts().getContactsList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.set(index, testData.withId(oldContacts.get(index).id()));
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }
}
