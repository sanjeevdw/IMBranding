package com.example.android.imbranding;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    private static final String LOG_TAG = LoginActivity.class.getSimpleName();
    private EditText emailText;
    private EditText passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailText = (EditText) findViewById(R.id.email_address_edit);
        passwordText = (EditText) findViewById(R.id.password_edit);

        Button loginButton = (Button) findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginNetworkRequest();

                }
        });
    }
    private void loginNetworkRequest() {

       final String userEmail = emailText.getText().toString().trim();
        final String userPassword = passwordText.getText().toString().trim();

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://139.59.33.52/imakeprofile/public/api/company/login";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject baseJSONResponse = new JSONObject(response);
                            JSONObject userObject = baseJSONResponse.getJSONObject("user");
                            String userName = userObject.getString("name");
                            if (userName != null) {
                                Toast.makeText(getApplicationContext(), "Logging In.", Toast.LENGTH_SHORT).show();
                                Intent toHomepage = new Intent(getApplicationContext(), HomepageActivity.class);
                                toHomepage.putExtra("userName", userName);
                                startActivity(toHomepage);
                            }
                            } catch (JSONException e)  {

                            Log.e(LOG_TAG, e.getMessage());
                            Toast.makeText(getApplicationContext(), "Please check your internet connection.", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error Occurred", Toast.LENGTH_SHORT).show();
                Intent toMain = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(toMain);
                finish();
            }

            }) { @Override
                    protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                    params.put("email", userEmail);
                    params.put("password", userPassword);
                    return params;
                }
                };
        queue.add(stringRequest);
    }
}