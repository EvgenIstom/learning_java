package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class DeleteContactTests extends TestBase {

    @Test
    public void CanDeleteContact() {
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactData("FirstNameByDeleteContact", "LastNameByDeleteContact", "Address DEL 17-19"));
        }

        app.contacts().deleteContact();
    }

}