package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class DeleteContactTests extends TestBase {

    @Test
    public void canDeleteContact() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "FirstNameByDeleteContact", "", "LastNameByDeleteContact", "Address DEL 17-19", "", "", "", "", ""));
        }
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contacts().deleteContact(oldContacts.get(index));
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Assertions.assertEquals(newContacts, expectedList);
    }


    @Test
    public void canDeleteAllContactsAtOnce() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "FirstNameByDeleteContact", "", "LastNameByDeleteContact", "Address DEL 17-19", "", "", "", "", ""));
        }
        app.contacts().deleteAllContacts();
        Assertions.assertEquals(0, app.contacts().getCount());
    }
}