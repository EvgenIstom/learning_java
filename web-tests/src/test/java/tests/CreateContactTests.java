package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class CreateContactTests extends TestBase {

    @Test
    public void CanCreateContact() {
        app.contacts().createContact(new ContactData("FirstNameByCreateContact", "LastNameByCreateContact", "Address 17-19"));
    }

}