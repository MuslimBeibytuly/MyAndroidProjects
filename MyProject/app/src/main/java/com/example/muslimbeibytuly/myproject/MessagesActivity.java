package com.example.muslimbeibytuly.myproject;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.muslimbeibytuly.myproject.entities.Message;
import com.example.muslimbeibytuly.myproject.entities.User;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.util.Date;

public class MessagesActivity extends AppCompatActivity {
    FirebaseListAdapter<Message> adapter;
    ListView messages;
    Query query;
    EditText input;
    FirebaseListOptions<Message> options;
    User interlocutor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        Intent intent = getIntent();
        interlocutor = new User(intent.getStringExtra("email"));

        messages = findViewById(R.id.messages);
        FloatingActionButton send = findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input = findViewById(R.id.input);
                FirebaseDatabase.getInstance()
                        .getReference("messages")
                        .push()
                        .setValue(new Message(input.getText().toString(),
                                interlocutor.getEmail(),
                                FirebaseAuth.getInstance()
                                        .getCurrentUser().getEmail())
                        );
                Log.wtf("token", FirebaseInstanceId.getInstance().getToken());
                input.setText("");
            }
        });

        query = FirebaseDatabase.getInstance().getReference("messages")
                .orderByChild("from").equalTo(FirebaseAuth
                        .getInstance().getCurrentUser().getEmail());
        options = new FirebaseListOptions.Builder<Message>()
                .setQuery(query, Message.class).setLayout(R.layout.message).build();
        adapter = new FirebaseListAdapter<Message>(options) {
            @Override
            protected void populateView(View v, Message model, int position) {
                TextView content = v.findViewById(R.id.content);
                TextView time = v.findViewById(R.id.time);
                time.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)", model.getTime()));
                content.setText(model.getContent());
            }
        };
        messages.setAdapter(adapter);
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
