import org.junit.jupiter.api.Test;

public class DeleteGroupTest extends TestBase {

    @Test
    public void CanDeleteGroup() {
        openGroupsPage();
        if (!isGroupPresent()) {
            createGroup("GroupCreatedByDeleteGroupTest", "header", "footer");
        }
        deleteGroup();
    }

}
