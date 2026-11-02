package manager;

import model.GroupData;
import org.openqa.selenium.By;

public class GroupHelper extends HelperBase{

    public GroupHelper(ApplicationManager manager) {
        super(manager);
    }

    public void openGroupsPage() {
        if (!manager.isElementPresent(By.name("new"))) {
            click(By.linkText("groups"));
        }
    }

    public void createGroup(GroupData group) {
        openGroupsPage();
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupsPage();
    }


    private void initGroupCreation() {
        click(By.name("new"));
    }

    public void deleteGroup() {
        openGroupsPage();
        selectGroup();
        deleteSelectedGroups();
        returnToGroupsPage();
    }

    private void deleteSelectedGroups() {
        click(By.name("delete"));
    }

    public void updateGroup(GroupData updatedGroup) {
        openGroupsPage();
        selectGroup();
        initUpdatingGroup();
        fillGroupForm(updatedGroup);
        submitGroupUpdate();
        returnToGroupsPage();
     }

    private void returnToGroupsPage() {
        click(By.linkText("group page"));
    }

    private void fillGroupForm(GroupData group) {
        type(By.name("group_name"), group.name());
        type(By.name("group_header"), group.header());
        type(By.name("group_footer"), group.footer());
    }

    private void initUpdatingGroup() {
        click(By.name("edit"));
    }

    private void selectGroup() {
        click(By.name("selected[]"));
    }

    private void submitGroupCreation() {
        click(By.name("submit"));
    }

    private void submitGroupUpdate() {
        click(By.name("update"));
    }

    public int getCount() {
        openGroupsPage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void deleteAllGroups() {
        openGroupsPage();
        selectAllGroups();
        deleteSelectedGroups();
    }

    private void selectAllGroups() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }
}
