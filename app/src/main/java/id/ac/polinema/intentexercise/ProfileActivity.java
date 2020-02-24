package id.ac.polinema.intentexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import static id.ac.polinema.intentexercise.RegisterActivity.ABOUT_KEY;
import static id.ac.polinema.intentexercise.RegisterActivity.CPASSWORD_KEY;
import static id.ac.polinema.intentexercise.RegisterActivity.EMAIL_KEY;
import static id.ac.polinema.intentexercise.RegisterActivity.FULLNAME_KEY;
import static id.ac.polinema.intentexercise.RegisterActivity.HOMEPAGE_KEY;
import static id.ac.polinema.intentexercise.RegisterActivity.PASSWORD_KEY;

public class ProfileActivity extends AppCompatActivity {
    private TextView fullnameText;
    private TextView emailText;
    private TextView passwordText;
    private TextView cpasswordText;
    private TextView homepageText;
    private TextView aboutText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        fullnameText = findViewById(R.id.text_fullname);
        emailText = findViewById(R.id.text_email);
        passwordText = findViewById(R.id.text_password);
        cpasswordText = findViewById(R.id.text_confirm_password);
        homepageText = findViewById(R.id.text_homepage);
        aboutText = findViewById(R.id.text_about);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            fullnameText.setText(extras.getString(FULLNAME_KEY));
            emailText.setText(extras.getString(EMAIL_KEY));
            passwordText.setText(extras.getString(PASSWORD_KEY));
            cpasswordText.setText(extras.getString(CPASSWORD_KEY));
            homepageText.setText(extras.getString(HOMEPAGE_KEY));
            aboutText.setText(extras.getString(ABOUT_KEY));
        }

    }
}
