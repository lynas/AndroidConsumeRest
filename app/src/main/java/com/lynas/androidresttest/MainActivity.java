package com.lynas.androidresttest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.lynas.androidresttest.domain.GitUser;
import com.lynas.androidresttest.domain.json.request.AuthenticationRequest;
import com.lynas.androidresttest.domain.json.response.AuthenticationResponse;
import com.lynas.androidresttest.service.GitHubService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                        git_id.setText(response.body().getToken());
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
