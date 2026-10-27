package manager;

import model.ContactData;
import model.GroupData;
import org.openqa.selenium.By;

public class ContactHelper extends HelperBase{

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void openAddContactPage() {
        if (!manager.isElementPresent(By.name("firstname"))) {
            click(By.linkText("add new"));
        }
    }

    public void openHomePage() {
        if (!manager.isElementPresent(By.name("searchstring"))) {
            click(By.linkText("home"));
        }
    }

    public void createContact(ContactData contact) {
        openAddContactPage();
        fillContactForm(contact);
        submitContactCreation();
        returnToHomePage();
    }


    public void deleteContact() {
        openHomePage();
        selectContact();
        deleteSelectedContact();
        returnToHomePage();
    }

    private void deleteSelectedContact() {
        click(By.name("delete"));
    }

    public boolean isContactPresent() {
        openHomePage();
        return manager.isElementPresent(By.name("selected[]"));
    }

    private void returnToHomePage() {
        click(By.linkText("home"));
    }

    private void returnToAddContactPage() {
        click(By.linkText("add new"));
    }

    private void submitContactCreation() {
        click(By.name("submit"));
    }

    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstName());
        type(By.name("lastname"), contact.lastName());
        type(By.name("address"), contact.address());
    }

//    private void initUpdatingContact() {
//        click(By.("edit"));
//    }

    private void selectContact() {
        click(By.name("selected[]"));
    }


}
