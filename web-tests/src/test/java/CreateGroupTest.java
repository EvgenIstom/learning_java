import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class CreateGroupTest extends TestBase {

    @Test
    public void CanCreateGroup() {
        openGroupsPage();
        createGroup("GroupCreatedByCreateGroupTest", "header", "footer");
    }
}
