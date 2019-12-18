package com.lang.protobufdemo;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lang.protobufdemo.entity.Login;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private TextView username;
    private TextView pwd;
    private String TAG="debug";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=findViewById(R.id.username);
        pwd=findViewById(R.id.pwd);
    }

    public void login(View view){

        String username_txt =username.getText().toString();
        String pwd_txt=pwd.getText().toString();

        if (TextUtils.isEmpty(username_txt) || TextUtils.isEmpty(pwd_txt)) {
            Toast.makeText(this, "please fill username and password", Toast.LENGTH_SHORT).show();
            return;
        }

        //序列化封装
        Login.LoginRequest loginBuild = Login.LoginRequest.newBuilder().setUsername(username_txt).setPassword(pwd_txt).build();

        RequestBody requestBody = FormBody.create(MediaType.get("application/octet-stream"), loginBuild.toByteArray());
        Request request = new Request.Builder().url("http://172.17.2.36:8080/login").post(requestBody).build();

//        Request request = new Request.Builder().url("https://time.geekbang.org/column/article/120928").build();
        Call call = new OkHttpClient().newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //解析数据
                Login.LoginResponse loginResult = Login.LoginResponse.parseFrom(response.body().bytes());

                Log.i(TAG, "login result:" + loginResult.getCode() + "--");
                Log.i(TAG, "login result :" + loginResult.getMsg());
            }
        });
    }
}
