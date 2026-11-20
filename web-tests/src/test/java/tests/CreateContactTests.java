package tests;

import common.CommonFunctions;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;



public class CreateContactTests extends TestBase {

    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>();
//        for (var firstName : List.of("", "first name")) {
//            for (var lastName : List.of("", "last name")) {
//                for (var address : List.of("", "address"))
//                    result.add(new ContactData()
//                        .withFirstName(firstName)
//                        .withLastName(lastName)
//                        .withAddress(address)
//                        .withPhoto("src/test/resources/images/random-person.jpeg"));
//            }
//        }
        var xml = Files.readString(Paths.get("../contacts.xml"));
        ObjectMapper xmlMapper = new XmlMapper();
        var value = xmlMapper.readValue(
                xml,
                new TypeReference<List<ContactData>>() {}
        );
        result.addAll(value);
        return result;
    }

    public static List<ContactData> negativeContactProvider() {
        var result = new ArrayList<ContactData>(List.of(
                new ContactData("", "hello contact'", "", "", "", "", "", "", "", "", "", "", "")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateContacts(ContactData contact) {
        var oldContacts = app.hbm().getContactList();
        app.contacts().createContact(contact);
        var newContacts = app.hbm().getContactList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);

        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(newContacts.get(newContacts.size() - 1).id()));
        expectedList.sort(compareById);

        //не показываем фото, сравниваем только то, что на странице
        var actual = newContacts.stream().map(this::withoutPhoto).toList();
        var expected = expectedList.stream().map(this::withoutPhoto).toList();

        Assertions.assertEquals(actual, expected);
    }



    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void canNotCreateContacts(ContactData contact) {
        long contactCount = app.hbm().getContactCount();
        app.contacts().createContact(contact);
        long newContactCount = app.hbm().getContactCount();
        Assertions.assertEquals(contactCount, newContactCount);
    }

    @Test
    public void canCreateContactInGroup() {
        var contact = new ContactData()
                .withFirstName(CommonFunctions.randomString(10))
                .withFirstName(CommonFunctions.randomString(10))
                .withFirstName(CommonFunctions.randomString(10));
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "GroupCreatedByDeleteGroupTest", "header", "footer"));
        }
        var group = app.hbm().getGroupList().get(0);
        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().createContact(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());

    }
}

