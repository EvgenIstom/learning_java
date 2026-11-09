package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class DeleteContactTests extends TestBase {

    @Test
    public void canDeleteContact() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData("", "FirstNameByDeleteContact", "LastNameByDeleteContact", "Address DEL 17-19", ""));
        }
        var oldContacts = app.contacts().getContactsList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contacts().deleteContact(oldContacts.get(index));
        var newContacts = app.contacts().getContactsList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Assertions.assertEquals(newContacts, expectedList);
    }


    @Test
    public void canDeleteAllContactsAtOnce() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData("", "FirstNameByDeleteContact", "LastNameByDeleteContact", "Address DEL 17-19", ""));
        }
        app.contacts().deleteAllContacts();
        Assertions.assertEquals(0, app.contacts().getCount());
    }
}