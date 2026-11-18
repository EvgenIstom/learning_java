package manager;

import manager.hbm.GroupRecord;
import model.ContactData;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

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
        attach(By.name("photo"), contact.photo());
    }

//    private void initUpdatingContact() {
//        click(By.("edit"));
//    }

    public void createContact(ContactData contact, GroupData group) {
        openAddContactPage();
        fillContactForm(contact);
        selectGroup(group);
        submitContactCreation();
        returnToHomePage();
    }

    private void selectGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }

    private void selectContact(ContactData contact) {
        var selectContact = manager.driver.findElement(By.cssSelector(String.format("input[value='%s']", contact.id())));
        scrollToElement(selectContact);
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

    public void addToGroup(ContactData contact, GroupData group) {
        openHomePage();
        selectContact(contact);
        selectAddToGroup(group);
        submitAddToGroup();
        returnToHomePage();
    }

    public void removeFromGroup(ContactData contact, GroupData group) {
        openHomePage();
        selectRemoveFromGroup(group);
        selectContact(contact);
        submitRemoveFromGroup();
        returnToHomePage();
    }

    private void selectRemoveFromGroup(GroupData group) {
        Select filter = new Select(manager.driver.findElement(By.name("group")));
        filter.selectByValue(group.id());
    }

    private void submitRemoveFromGroup() {
        click(By.name("remove"));
    }


    private void selectAddToGroup(GroupData group) {
        Select filter = new Select(manager.driver.findElement(By.name("to_group")));
        filter.selectByValue(group.id());
    }

    private void submitAddToGroup() {
        click(By.name("add"));
    }
}

