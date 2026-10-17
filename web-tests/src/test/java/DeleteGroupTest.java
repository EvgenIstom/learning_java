import model.GroupData;
import org.junit.jupiter.api.Test;

public class DeleteGroupTest extends TestBase {

    @Test
    public void CanDeleteGroup() {
        openGroupsPage();
        if (!isGroupPresent()) {
            createGroup(new GroupData("GroupCreatedByDeleteGroupTest", "header", "footer"));
        }
        deleteGroup();
    }

}
