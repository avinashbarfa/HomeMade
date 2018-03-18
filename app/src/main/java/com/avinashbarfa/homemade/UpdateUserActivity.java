package com.avinashbarfa.homemade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateUserActivity extends AppCompatActivity {

    EditText edtUserName , edtEmailId;
    Button btnUpdate;

    DatabaseReference databaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);


        databaseUser = FirebaseDatabase.getInstance().getReference("UserData");

        edtEmailId = (EditText)findViewById(R.id.editEmailId);
        btnUpdate = (Button)findViewById(R.id.btnUpdate);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateUserInfo();
            }
        });
    }

    private void UpdateUserInfo() {
        String fullName = edtUserName.getText().toString();
        String emailId = edtEmailId.getText().toString();

        if(!TextUtils.isEmpty(fullName)) {

            String ID = databaseUser.push().getKey();

            UserInfoBean userInfoBean = new UserInfoBean(ID, fullName , emailId );
            databaseUser.child(ID).setValue(userInfoBean);

            Toast.makeText(this, "Data Updated " , Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this , "Enter Full Name ", Toast.LENGTH_SHORT).show();
        }
    }
}
