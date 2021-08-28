package utils;

import okhttp3.*;

import java.io.IOException;
import java.util.Objects;

public class NetUtil {

    static final String url = "";
    static final String body = "";
    static final String cookie = "";

    public static void main(String[] args) {

    }

    public static void postForJson(String url, String body, String cookie) {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), body);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .header("Cookie", cookie)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response)throws IOException {
                if (response.isSuccessful()) {
                    // 回调的方法执行在子线程。
                    System.out.println(Objects.requireNonNull(response.body()).string());
                }
            }
        });
    }

}
