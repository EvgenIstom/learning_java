package manager;

import manager.hbm.ContactRecord;
import manager.hbm.GroupRecord;
import model.ContactData;
import model.GroupData;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HibernateHelper extends HelperBase {

    private SessionFactory sessionFactory;


    public HibernateHelper(ApplicationManager manager) {
        super(manager);
        var props = manager.properties;
        sessionFactory = new Configuration()
                .addAnnotatedClass(ContactRecord.class)
                .addAnnotatedClass(GroupRecord.class)
                .setProperty(AvailableSettings.JAKARTA_JDBC_URL, props.getProperty("db.url"))
                .setProperty(AvailableSettings.JAKARTA_JDBC_USER, props.getProperty("db.username"))
                .setProperty(AvailableSettings.JAKARTA_JDBC_PASSWORD, props.getProperty("db.password"))
                .buildSessionFactory();
    }

    static List<GroupData> convertList(List<GroupRecord> records) {
        return records.stream().map(HibernateHelper::convert).collect(Collectors.toList());
    }

    private static @NonNull GroupData convert(GroupRecord record) {
        return new GroupData(
                "" + record.id, record.name, record.header, record.footer
        );
    }

    private static @NonNull GroupRecord convert(GroupData data) {
        var id = data.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new GroupRecord(
                Integer.parseInt(id), data.name(), data.header(), data.footer()
        );
    }

    public List<GroupData> getGroupList() {
        return convertList(sessionFactory.fromSession(session -> {
            return  session.createQuery("from GroupRecord", GroupRecord.class).list();
        }));
    }

    public long getGroupCount() {
        return sessionFactory.fromSession(session -> {
            return  session.createQuery("select count(*) from GroupRecord", Long.class).getSingleResult();
        });
    }

    public void createGroup(GroupData groupData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convert(groupData));
            session.getTransaction().commit();
        });
    }

    static List<ContactData> convertContactList(List<ContactRecord> records) {
        return records.stream().map(HibernateHelper::convert).collect(Collectors.toList());
    }

    private static @NonNull ContactData convert(ContactRecord record) {
        return new ContactData().withId("" + record.id)
                .withFirstName(record.firstname)
                .withMiddleName(record.middlename)
                .withLastName(record.lastname)
                .withAddress(record.address)
                .withHome(record.home)
                .withMobile(record.mobile)
                .withWork(record.work)
                .withSecondary(record.phone2)
                .withEmail(record.email)
                .withEmail2(record.email2)
                .withEmail3(record.email3);
    }

    private static @NonNull ContactRecord convert(ContactData data) {
        var id = data.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new ContactRecord(
                Integer.parseInt(id), data.firstName(), data.lastName(), data.address()
        );
    }

    public List<ContactData> getContactList() {
        return convertContactList(sessionFactory.fromSession(session -> {
            return  session.createQuery("from ContactRecord", ContactRecord.class).list();
        }));
    }

    public long getContactCount() {
        return sessionFactory.fromSession(session -> {
            return  session.createQuery("select count(*) from ContactRecord", Long.class).getSingleResult();
        });
    }

    public void createContact(ContactData contactData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convert(contactData));
            session.getTransaction().commit();
        });
    }

    public List<ContactData> getContactsInGroup(GroupData group) {
        return sessionFactory.fromSession(session -> {
            return convertContactList(session.find(GroupRecord.class, group.id()).contacts);
        });
    }
}
