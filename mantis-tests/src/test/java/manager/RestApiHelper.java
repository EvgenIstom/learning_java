package manager;

import mantis.api.IssuesApi;
import mantis.api.UserApi;
import mantis.invoker.ApiClient;
import mantis.invoker.ApiException;
import mantis.invoker.Configuration;
import mantis.invoker.auth.ApiKeyAuth;
import mantis.model.Identifier;
import mantis.model.Issue;
import mantis.model.User;
import mantis.model.UserAddResponse;
import model.IssueData;
import model.UserData;

public class RestApiHelper extends HelperBase {

    public RestApiHelper(ApplicationManager manager) {
        super(manager);
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath(manager.property("api.baseurl"));
        ApiKeyAuth Authorization = (ApiKeyAuth) defaultClient.getAuthentication("Authorization");
        Authorization.setApiKey(manager.property("api.key"));

    }

    public void createIssue(IssueData issueData) {
        Issue issue = new Issue();
        issue.setSummary(issueData.summary());
        issue.setDescription(issueData.description());
        var projectId = new Identifier();
        projectId.setId(issueData.project());
        issue.setProject(projectId);
        var categoryId = new Identifier();
        categoryId.setId(issueData.category());
        issue.setCategory(categoryId);

        IssuesApi apiInstance = new IssuesApi();
        try {
            apiInstance.issueAdd(issue);
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void registerUser(UserData user) {
        User body = new User();
        body.setUsername(user.username());
        body.setEmail(user.email());
        body.setPassword(user.password());
        UserApi apiInstance = new UserApi();
        try {
            UserAddResponse result = apiInstance.userAdd(body);
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }
}

