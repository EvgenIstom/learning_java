import model.GroupData;
import org.junit.jupiter.api.Test;

public class CreateGroupTest extends TestBase {

    @Test
    public void CanCreateGroup() {
        openGroupsPage();
        createGroup(new GroupData("GroupCreatedByCreateGroupTest", "header", "footer"));
    }

    @Test
    public void CanCreateGroupWithEmptyName() {
        openGroupsPage();
        createGroup(new GroupData());
    }

    @Test
    public void CanCreateGroupWithNameOnly() {
        openGroupsPage();
        createGroup(new GroupData().withName("only name"));
    }



}
