package manager;

import model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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
        var delete = manager.driver.findElement(By.name("delete"));
        scrollToElement(delete);
        click(By.name("delete"));
    }

    private void returnToHomePage() {
        manager.driver.get("http://localhost/addressbook/index.php");
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

    public int getCount() {
        openHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void deleteAllContacts() {
        openHomePage();
        selectAllContacts();
        deleteSelectedContact();
    }

    private void selectAllContacts() {
        var selectAll = manager.driver.findElement(By.id("MassCB"));
        scrollToElement(selectAll);
        click(By.id("MassCB"));
    }
}
