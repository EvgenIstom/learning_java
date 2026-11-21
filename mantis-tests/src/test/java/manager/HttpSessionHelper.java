package manager;

import okhttp3.*;
import okhttp3.java.net.cookiejar.JavaNetCookieJar;

import java.io.IOException;
import java.net.CookieManager;
import okhttp3.OkHttpClient;

public class HttpSessionHelper extends HelperBase {


    OkHttpClient client = new OkHttpClient.Builder()
            .cookieJar(new JavaNetCookieJar(new CookieManager()))
            .build();

    public HttpSessionHelper(ApplicationManager manager) {
        super(manager);
    }

    public void login(String username, String password) {
        RequestBody formBody = new FormBody.Builder()
                .add("username", username)
                .add("password", password)
                .build();
        Request request = new Request.Builder()
                .url(String.format("%s/login.php", manager.property("web.baseurl")))
                .post(formBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean isLoggedIn() {
        Request request = new Request.Builder()
                .url(manager.property("web.baseurl"))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
            String body = response.body().string();
            return body.contains("<span class=\"user-info\">");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
