package com.lynas.androidresttest;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.lynas.androidresttest.domain.Book;
import com.lynas.androidresttest.domain.json.request.AuthenticationRequest;
import com.lynas.androidresttest.domain.json.response.AuthenticationResponse;
import com.lynas.androidresttest.service.GitHubService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.et_user_name)
    EditText etUserName;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.bt_login)
    Button login;
    @Bind(R.id.git_id)
    TextView git_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        login.setOnClickListener(arg0 -> {
            System.out.println("it works");
            System.out.println(etUserName.getText());
            System.out.println(etPassword.getText());
            callAsync();
        });

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    @OnClick(R.id.bt_book_list)
    public void submit(View view) {
        //git_id.setText(AppConstant.TOKEN);
        System.out.println("#############################");
        System.out.println("#############################");
        System.out.println("#############################");
        SharedPreferences prefs = getSharedPreferences(AppConstant.TOKEN, MODE_PRIVATE);
        System.out.println(prefs.getString("token", null));
        GitHubService.Factory
                .getinstance()
                .getBookList(prefs.getString("token", null))
                .enqueue(new Callback<List<Book>>() {
                    @Override
                    public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                        if (!response.body().isEmpty())
                            git_id.setText(""+response.body().size());
                        else
                            git_id.setText("0");
                    }

                    @Override
                    public void onFailure(Call<List<Book>> call, Throwable t) {

                    }
                });
    }

    private void callAsync() {
        /*GitHubService.Factory.getinstance().getUser().enqueue(new Callback<GitUser>() {
            @Override
            public void onResponse(Call<GitUser> call, Response<GitUser> response) {
                System.out.println("#############################");
                System.out.println("#############################");
                System.out.println("#############################");
                System.out.println(response.body().getId());
                git_id.setText(response.body().getId());
            }

            @Override
            public void onFailure(Call<GitUser> call, Throwable t) {
                System.out.println("#############################");
                System.out.println("#############################");
                System.out.println("#############################");
                System.out.println("error");
            }
        });*/

        GitHubService.Factory
                .getinstance()
                .login(new AuthenticationRequest("sazzad", "123456"))
                .enqueue(new Callback<AuthenticationResponse>() {
                    @Override
                    public void onResponse(Call<AuthenticationResponse> call, Response<AuthenticationResponse> response) {
                        //git_id.setText(response.body().getToken());
                        AppConstant.TOKEN = response.body().getToken();
                        git_id.setText("Found");

                        SharedPreferences.Editor editor = getSharedPreferences(AppConstant.TOKEN, MODE_PRIVATE).edit();
                        editor.putString("token", response.body().getToken());
                        editor.commit();
                    }

                    @Override
                    public void onFailure(Call<AuthenticationResponse> call, Throwable t) {
                        git_id.setText("error");
                    }
                });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
