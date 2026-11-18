package manager;

import model.GroupData;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JdbcHelper extends HelperBase {

    public JdbcHelper(ApplicationManager manager) {super(manager);}

    public List<GroupData> getGroupList() throws SQLException {
        var groups = new ArrayList<GroupData>();
        var props = manager.properties;
        try (var conn = DriverManager.getConnection(props.getProperty("db.url"),
                props.getProperty("db.username"),
                props.getProperty("db.password"));
            var statement = conn.createStatement();
            var result = statement.executeQuery("SELECT group_id, group_name, group_header, group_footer FROM group_list"))
             {
                    while (result.next()){
                        groups.add(new GroupData()
                                .withId(result.getString("group_id"))
                                .withName(result.getString("group_name"))
                                .withHeader(result.getString("group_header"))
                                .withFooter(result.getString("group_footer"))
                        );
                        var id = result.getString("group_id");
                    };
        }
        return groups;
    }

    public void checkConsistency() {
        var props = manager.properties;
        try (var conn = DriverManager.getConnection(props.getProperty("db.url"),
                props.getProperty("db.username"),
                props.getProperty("db.password"));
             var statement = conn.createStatement();
             var result = statement.executeQuery(
                     "SELECT * from address_in_groups ag left join addressbook ab on ab.id = ag.id where ab.id is null"
                     ))
        {
            if (result.next()){
                throw new IllegalStateException("DB is corrupted");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
