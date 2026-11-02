package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreateGroupTests extends TestBase {

    @Test
    public void canCreateGroup() {
        int groupCount = app.groups().getCount();
        app.groups().createGroup(new GroupData("GroupCreatedByCreateGroupTest", "header", "footer"));
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount + 1, newGroupCount);
    }

    @Test
    public void canCreateGroups() {
        int groupCount = app.groups().getCount();
        int count = Integer.parseInt(System.getProperty("count", "20"));
        for (int i = 1; i <= count; i++) {
            app.groups().createGroup(new GroupData(
                    "Name" + i, "Header" + i, "Footer" +i));
        }
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount + count, newGroupCount);
    }


    @Test
    public void canCreateGroupWithEmptyName() {
        app.groups().createGroup(new GroupData());
    }

    @Test
    public void canCreateGroupWithNameOnly() {
        app.groups().createGroup(new GroupData().withName("only name"));
    }



}
