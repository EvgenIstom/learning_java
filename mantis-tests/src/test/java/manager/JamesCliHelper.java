package manager;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import java.io.IOException;

public class JamesCliHelper extends HelperBase {

    public JamesCliHelper (ApplicationManager manager) {
        super(manager);
    }

    public void addUser (String email, String password) {
        String classpath =
               manager.property("james.workingdir") +
                "/james-server-jpa-app.lib/*";


        CommandLine cmd = new CommandLine("java");
            cmd.addArgument("-cp");
            cmd.addArgument(classpath);
            cmd.addArgument("org.apache.james.cli.ServerCmd");

            cmd.addArgument("-username");
            cmd.addArgument(manager.property("james.jmx.username"));
            cmd.addArgument("-password");
            cmd.addArgument(manager.property("james.jmx.password"));
            cmd.addArgument("AddUser");

            cmd.addArgument(email);
            cmd.addArgument(password);


            try {
                DefaultExecutor.builder().get().execute(cmd);
            } catch (IOException e) {
                throw new RuntimeException("Не удалось создать пользователя James: " + email, e);
            }
    }

}

//java -cp "james-server-jpa-app.lib/*"
//-h 127.0.0.1 -p 9999 -username james-admin -password TestAdmin123 ListUsers