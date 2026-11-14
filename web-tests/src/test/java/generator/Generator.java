package generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import common.CommonFunctions;
import model.GroupData;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.json.JsonMapper;

import java.io.File;
import java.util.ArrayList;

public class Generator {

    @Parameter(names={"--type", "-t"})
    String type;

    @Parameter(names={"--format", "-f"})
    String format;

    @Parameter(names={"--output", "-o"})
    String output;

    @Parameter(names={"--count", "-c"})
    int count;

    public static void main(String[] args) {
        var generator = new Generator();
        JCommander.newBuilder()
                .addObject(generator)
                .build()
                .parse(args);
        generator.run();
    }

    private void run() {
        var data = generate();
        save(data);
    }

    private Object generate() {
        if ("groups".equals(type)) {
            return generateGroups();
        }
        else if ("contacts".equals(type)) {
            return generateContacts();
        }
        else {
            throw new IllegalArgumentException("Неизвестный тип данных" + type);
        }
    }

    private Object generateGroups() {
        var result = new ArrayList<GroupData>();
        for (int i = 0; i <= count; i++) {
            result.add(new GroupData()
                    .withName(CommonFunctions.randomString(i))
                    .withHeader(CommonFunctions.randomString(i))
                    .withFooter(CommonFunctions.randomString(i)));
        }
        return result;
    }

    private Object generateContacts() {
        var result = new ArrayList<GroupData>();
        for (int i = 0; i <= count; i++) {
            result.add(new GroupData()
                    .withName(CommonFunctions.randomString(i))
                    .withHeader(CommonFunctions.randomString(i))
                    .withFooter(CommonFunctions.randomString(i)));
        }
        return result;
    }

    private void save(Object data) {
        if ("json".equals(format)) {
            ObjectMapper mapper = JsonMapper.builder()
            .enable(SerializationFeature.INDENT_OUTPUT)
            .build();
            mapper.writeValue(new File(output), data);
        } else {
            throw new IllegalArgumentException("Неизвестный формат данных" + format);
        }
    }
}

