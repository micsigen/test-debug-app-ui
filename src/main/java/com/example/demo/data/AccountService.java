package com.example.demo.data;

import java.io.IOException;
import java.io.Serializable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.springframework.stereotype.Service;

@Service
public class AccountService implements Serializable {

    private static final long serialVersionUID = -933133039756646517L;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public Account query(String id) throws JsonMappingException, JsonProcessingException, IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("http://localhost:8081/account/" + id).build();
        Response response = okHttpClient.newCall(request).execute();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response.body().string(), Account.class);
    }

    public Account increment(String id, Double amount)
            throws JsonMappingException, JsonProcessingException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(new AccountHistory(id, amount)));
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("http://localhost:8081/account/increase").post(body).build();
        okHttpClient.newCall(request).execute();
        return query(id);
    }

    public Account withdraw(String id, Double amount)
            throws JsonMappingException, JsonProcessingException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(new AccountHistory(id, amount)));
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("http://localhost:8081/account/withdraw").post(body).build();
        Response response = okHttpClient.newCall(request).execute();  
        if (response.code() == 200) return query(id);
        else throw new IOException(objectMapper.readTree(response.body().string()).get("message").asText());
    }
}
