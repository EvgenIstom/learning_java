package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CreateGroupTests extends TestBase {

    public static List<GroupData> groupProvider() {
        var result = new ArrayList<GroupData>(List.of(
                new GroupData("hello group'", "", "")));
        for (var name : List.of("", "group name")) {
            for (var header : List.of("", "group header")) {
                for (var footer : List.of("", "group footer"))
                    result.add(new GroupData(name, header, footer));
            }
        }
        for (int i = 1; i <= 6; i++) {
            result.add(new GroupData(randomString(i), randomString(i), randomString(i)));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("groupProvider")
    public void canCreateGroups(GroupData group) {
        int groupCount = app.groups().getCount();
        app.groups().createGroup(group);
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount + 1, newGroupCount);
    }

}
