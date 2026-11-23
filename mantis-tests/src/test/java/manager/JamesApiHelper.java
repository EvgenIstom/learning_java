package manager;

import okhttp3.*;
import okhttp3.java.net.cookiejar.JavaNetCookieJar;

import java.io.IOException;
import java.net.CookieManager;

public class JamesApiHelper extends HelperBase {

    public static final MediaType JSON = MediaType.parse("application/json");

    OkHttpClient client = new OkHttpClient.Builder()
            .cookieJar(new JavaNetCookieJar(new CookieManager()))
            .build();

    public JamesApiHelper(ApplicationManager manager) {
        super(manager);
        }

    public void addUser(String email, String password) {
        RequestBody body = RequestBody.create(
                String.format("{\"password\": \"%s\"}", password), JSON);

        Request request = new Request.Builder()
                .url(String.format("%s/users/%s", manager.property("james.api.url"), email))
                .header("Password", manager.property("james.api.password"))
                .put(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}