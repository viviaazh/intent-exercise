package id.ac.polinema.intentexercise;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = RegisterActivity.class.getCanonicalName();

    public static final String IMAGE_KEY = "image";
    public static final String ABOUT_KEY = "about";
    public static final String FULLNAME_KEY = "fullname";
    public static final String EMAIL_KEY = "email";
    public static final String HOMEPAGE_KEY = "homepage";


    private EditText fullnameInput;
    private EditText emailInput;
    private EditText passwordInput;
    private EditText cpasswordInput;
    private EditText homepageInput;
    private EditText aboutInput;
    private ImageView imageView;

    private Uri imageUri = null;

    private static final int GALLERY_REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        imageView = findViewById(R.id.image_profile);
        fullnameInput = findViewById(R.id.text_fullname);
        emailInput = findViewById(R.id.text_email);
        passwordInput = findViewById(R.id.text_password);
        cpasswordInput = findViewById(R.id.text_confirm_password);
        homepageInput = findViewById(R.id.text_homepage);
        aboutInput = findViewById(R.id.text_about);
    }

    public void handleOk(View view) {
        String fullname = fullnameInput.getText().toString();
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();
        String cpassword = cpasswordInput.getText().toString();
        String homepage = homepageInput.getText().toString();
        String about = aboutInput.getText().toString();



        if(fullnameInput.length()==0){
            fullnameInput.setError("Fullname harus diisi");
        }
        else if(emailInput.length()==0){
            emailInput.setError("Email harus diisi");
        }
        else if(passwordInput.length()==0){
            passwordInput.setError("Password harus diisi");
        }
        else if(cpasswordInput.length()==0){
            cpasswordInput.setError("Konfirmasi password terlebih dahulu");
        }
        else if (!password.equals(cpassword)){
            cpasswordInput.setError("Password tidak sama, silahkan ulangi");
        }
        else if(homepageInput.length()==0){
            homepageInput.setError("Homepage harus diisi");
        }
        else if(aboutInput.length()==0){
            aboutInput.setError("About harus diisi");
        }
        else{
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra(ABOUT_KEY, about);
            intent.putExtra(FULLNAME_KEY, fullname);
            intent.putExtra(EMAIL_KEY, email);
            intent.putExtra(HOMEPAGE_KEY, homepage);

            if(imageUri!= null){
                intent.putExtra(IMAGE_KEY, imageUri.toString());
                try{
                    startActivity(intent);
                }catch(Exception e){
                    intent.putExtra(IMAGE_KEY, "");
                    Toast.makeText(this, "Foto terlalu besar", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            }
            else{
                Toast.makeText(this, "Masukkan foto terlebih dahulu", Toast.LENGTH_SHORT).show();
                handleChangePict(view);
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED){
            return;
        }
        if(requestCode == GALLERY_REQUEST_CODE){
            if(data != null){
                try{
                    imageUri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    imageView.setImageBitmap(bitmap);
                }catch (IOException e){
                    Toast.makeText(this, "Can't Load Image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }

    public void handleChangePict(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }
}
