package manager;

import model.ContactData;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

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


    public void deleteContact(ContactData contact) {
        openHomePage();
        selectContact(contact);
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

    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
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

    public List<ContactData> getContactsList() {
        openHomePage();
        var contacts = new ArrayList<ContactData>();
        var rows = manager.driver.findElements(By.cssSelector("#maintable tr[name='entry']"));
        for (var row : rows) {
            var firstName = row.findElement(By.cssSelector("td:nth-child(3)")).getText();
            var lastName = row.findElement(By.cssSelector("td:nth-child(2)")).getText();
            var address = row.findElement(By.cssSelector("td:nth-child(4)")).getText();
            var checkbox = row.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("id");
            contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName).withAddress(address));
        }
        return contacts;
    }

    public void updateContact(ContactData contact, ContactData updatedContact) {
        openHomePage();
        initUpdatingContact(contact);
        fillContactForm(updatedContact);
        submitContactUpdate();
        returnToHomePage();
    }

    private void submitContactUpdate() {
        click(By.name("update"));
    }

    private void initUpdatingContact(ContactData contact) {
        var pencil = manager.driver.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", contact.id())));
        scrollToElement(pencil);
        click(By.cssSelector(String.format("a[href='edit.php?id=%s']", contact.id())));
    }
}

//<a href="edit.php?id=129"><img src="icons/pencil.png" title="Edit" alt="Edit"></a>
