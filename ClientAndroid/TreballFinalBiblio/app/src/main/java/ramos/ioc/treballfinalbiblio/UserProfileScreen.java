package ramos.ioc.treballfinalbiblio;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import model.User;
import ramos.ioc.treballfinalbiblio.databinding.ActivityUserProfileScreenBinding;

public class UserProfileScreen extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityUserProfileScreenBinding binding;

    private TextView userName;
    private TextView userMail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityUserProfileScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        userName = findViewById(R.id.userName_textView);
        userMail = findViewById(R.id.email_textView);

        Intent intent = getIntent();
        User user = (User)intent.getSerializableExtra("userProfile");
        userName.setText(user.getUsuari());
        userMail.setText(user.getId());

    }

}