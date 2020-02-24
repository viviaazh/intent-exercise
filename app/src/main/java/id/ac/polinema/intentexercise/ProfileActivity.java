package id.ac.polinema.intentexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import static id.ac.polinema.intentexercise.RegisterActivity.ABOUT_KEY;
import static id.ac.polinema.intentexercise.RegisterActivity.CPASSWORD_KEY;
import static id.ac.polinema.intentexercise.RegisterActivity.EMAIL_KEY;
import static id.ac.polinema.intentexercise.RegisterActivity.FULLNAME_KEY;
import static id.ac.polinema.intentexercise.RegisterActivity.HOMEPAGE_KEY;
import static id.ac.polinema.intentexercise.RegisterActivity.IMAGE_KEY;
import static id.ac.polinema.intentexercise.RegisterActivity.PASSWORD_KEY;

public class ProfileActivity extends AppCompatActivity {
    private TextView fullnameText;
    private TextView emailText;
    private TextView homepageText;
    private TextView aboutText;
    private Uri uri;
    private String url;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        fullnameText = findViewById(R.id.text_fullname);
        emailText = findViewById(R.id.text_email);
        homepageText = findViewById(R.id.text_homepage);
        aboutText = findViewById(R.id.text_about);
        imageView = findViewById(R.id.image_profile);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            fullnameText.setText(extras.getString(FULLNAME_KEY));
            emailText.setText(extras.getString(EMAIL_KEY));
            homepageText.setText(extras.getString(HOMEPAGE_KEY));
            aboutText.setText(extras.getString(ABOUT_KEY));
            url = extras.getString(HOMEPAGE_KEY);
            uri = Uri.parse(extras.getString(IMAGE_KEY));
            Bitmap bitmap = null;
            try{
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
            }catch (IOException e){
                e.printStackTrace();
            }
            imageView.setImageBitmap(bitmap);
        }

    }

    public void handleVisit(View view) {
        Intent homepage = new Intent(Intent.ACTION_VIEW, Uri.parse("https://"+url));
        startActivity(homepage);
    }
}
