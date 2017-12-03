package com.example.muslimbeibytuly.myproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import com.example.muslimbeibytuly.myproject.entities.User;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 123;
    FirebaseListAdapter<User> adapter;
    ListView users;
    Query query;
    FirebaseListOptions<User> options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
//            ,
//            new AuthUI.IdpConfig.Builder(AuthUI.PHONE_VERIFICATION_PROVIDER).build(),
//                    new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build(),
//                    new AuthUI.IdpConfig.Builder(AuthUI.FACEBOOK_PROVIDER).build(),
//                    new AuthUI.IdpConfig.Builder(AuthUI.TWITTER_PROVIDER).build()
            List<AuthUI.IdpConfig> providers = Arrays.asList(
                    new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build());
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder()
                    .setAvailableProviders(providers).build(), RC_SIGN_IN);
        }
        users = findViewById(R.id.users);
        query = FirebaseDatabase.getInstance().getReference("users");
        options = new FirebaseListOptions.Builder<User>()
                .setQuery(query, User.class).setLayout(R.layout.user).build();
        adapter = new FirebaseListAdapter<User>(options) {
            @Override
            protected void populateView(View v, User model, int position) {
                TextView email = v.findViewById(R.id.email);
                email.setText(model.getEmail());
                email.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView email = v.findViewById(R.id.email);
                        Intent intent = new Intent(getApplicationContext(), MessagesActivity.class);
                        intent.putExtra("email", email.getText());
                        startActivity(intent);
                    }
                });
            }
        };
        users.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }


    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
