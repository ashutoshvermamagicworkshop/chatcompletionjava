package chatCompletion;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.util.ArrayList;

public class Client {
    private String key;
    private String url;
    private RequestBodyImpl myRequestBody;
    private OkHttpClient client;
    private ObjectMapper mapper;

    public Client() {
        this.key = Config.key;
        this.url = Config.url;
        myRequestBody = new RequestBodyImpl(Config.model, new ArrayList<>());
        client = new OkHttpClient();
        mapper = new ObjectMapper();
    }

    public void chat(String input) throws Exception {
        myRequestBody.addMessage(new Message("user", input));

        var reqbod = RequestBody.create(mapper.writeValueAsString(myRequestBody), MediaType.get("application/json"));

        var req = new Request.Builder().url(url).addHeader("Authorization", "Bearer " + key).post(reqbod).build();

        var res = client.newCall(req).execute();
        if (!res.isSuccessful()) System.err.println(res.body().string());
        else {
            var root = mapper.readTree(res.body().string());
            var resbody = root.path("choices").get(0).path("message").path("content").asText();

            System.out.println(resbody);

            myRequestBody.addMessage(new Message("assistant", resbody));
        }
    }
}
