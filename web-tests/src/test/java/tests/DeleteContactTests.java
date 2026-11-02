package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeleteContactTests extends TestBase {

    @Test
    public void canDeleteContact() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData("FirstNameByDeleteContact", "LastNameByDeleteContact", "Address DEL 17-19"));
        }
        int contactCount = app.contacts().getCount();
        app.contacts().deleteContact();
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount - 1, newContactCount);
    }


    @Test
    public void canDeleteAllContactsAtOnce() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData("FirstNameByDeleteContact", "LastNameByDeleteContact", "Address DEL 17-19"));
        }
        app.contacts().deleteAllContacts();
        Assertions.assertEquals(0, app.contacts().getCount());
    }
}