package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;
import java.util.Comparator;

public class InteractionContactGroupTests extends TestBase {

    @Test
    void canAddContactToGroup() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "CreatedByInteractionTest", "lastname", "address", ""));
        }
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "CreatedByInteractionTest", "header", "footer"));
        }

        var rnd = new Random();

        var groups = app.hbm().getGroupList();
        var indexGroup = rnd.nextInt(groups.size());
        var group = groups.get(indexGroup);

        var contacts = app.hbm().getContactList();
        var indexContact = rnd.nextInt(contacts.size());
        var contact = contacts.get(indexContact);

        if (app.hbm().getContactsInGroup(group).contains(contact)) {
            app.contacts().removeFromGroup(contact, group);
        }

        var oldContactsInGroup = app.hbm().getContactsInGroup(group);
        app.contacts().addToGroup(contact, group);
        var newContactsInGroup = app.hbm().getContactsInGroup(group);
        var expectedList = new ArrayList<>(oldContactsInGroup);
        expectedList.add(contact);
        Comparator<ContactData> compareById = (o1, o2) ->
                Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        newContactsInGroup.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newContactsInGroup, expectedList);

    }

    @Test
    void canRemoveContactFromGroup() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "CreatedByInteractionTest", "lastname", "address", ""));
        }
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "CreatedByInteractionTest", "header", "footer"));
        }

        var rnd = new Random();

        var groups = app.hbm().getGroupList();
        var indexGroup = rnd.nextInt(groups.size());
        var group = groups.get(indexGroup);

        var contacts = app.hbm().getContactList();
        var indexContact = rnd.nextInt(contacts.size());
        var contact = contacts.get(indexContact);

        if (!app.hbm().getContactsInGroup(group).contains(contact)) {
            app.contacts().addToGroup(contact, group);
        }

        var oldContactsInGroup = app.hbm().getContactsInGroup(group);
        app.contacts().removeFromGroup(contact, group);
        var newContactsInGroup = app.hbm().getContactsInGroup(group);
        var expectedList = new ArrayList<>(oldContactsInGroup);
        expectedList.remove(contact);
        Comparator<ContactData> compareById = (o1, o2) ->
                Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        newContactsInGroup.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newContactsInGroup, expectedList);
    }

}
