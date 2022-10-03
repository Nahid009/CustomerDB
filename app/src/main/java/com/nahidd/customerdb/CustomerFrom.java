package com.nahidd.customerdb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CustomerFrom extends AppCompatActivity {

    private EditText customer_name, customer_email;
    private Button buttonAdd, buttonFetch;
    TextView textView;
    FirebaseFirestore dbRoot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_from);

        customer_name = findViewById(R.id.customer_name);
        customer_email = findViewById(R.id.customer_email);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonFetch = findViewById(R.id.fetchButton);
        textView = findViewById(R.id.textView);


        dbRoot = FirebaseFirestore.getInstance();

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }

            private void insertData() {
                Map<String, String> items = new HashMap<>();
                items.put("Name", customer_name.getText().toString().trim());
                items.put("Email", customer_email.getText().toString().trim());

                dbRoot.collection("Students").add(items)
                        .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                customer_name.setText("");
                                customer_email.setText("");
                                Toast.makeText(getApplicationContext(), "Insert Successfully",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        buttonFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchData();
            }

            private void fetchData() {

                DocumentReference document = dbRoot.collection("Nahid").document("Row_3");
                document.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if (documentSnapshot.exists()) {
                                    textView.setText(documentSnapshot.getString("Name") + " "
                                            + documentSnapshot.getString("Email"));
                                } else {
                                    Toast.makeText(getApplicationContext(), "Row Not Found", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "Failed to Fetch data", Toast.LENGTH_SHORT)
                                        .show();
                            }
                        });
            }


        });


    }
}