package com.example.inclassassignment08_angelchangwei;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        keyField = (EditText) findViewById(R.id.key_edit_text_field);
        valueField = (EditText) findViewById(R.id.value_edit_text_field);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("The Messages");
        }
public void writeToCloud(View view) {
        myRef.child(keyField.getText().toString()).setValue(valueField.getText().toString());
        }

public void readFromCloud(View view) {
        DatabaseReference childRef = myRef.child(keyField.getText().toString());
        childRef.addValueEventListener(new ValueEventListener() {
@Override
public void onDataChange(DataSnapshot dataSnapshot) {
        String loadedData = dataSnapshot.getValue(String.class);
        valueField.setText(loadedData);
        }
@Override
public void onCancelled(DatabaseError error) {
        Toast.makeText(MainActivity.this, "This is an error for loading Firebase", Toast.LENGTH_SHORT).show();
        }
        });
        }
        }