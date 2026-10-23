package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class UpdateGroupTests extends TestBase {

    @Test
    void canUpdateGroup() {
        if (!app.groups().isGroupPresent()) {
            app.groups().createGroup(new GroupData("GroupCreatedByUpdateGroupTest", "header", "footer"));
        }
        app.groups().updateGroup(new GroupData("UpdatedGroup", "UpdatedGroupHeader", "UpdatedGroupFooter"));
    }

}