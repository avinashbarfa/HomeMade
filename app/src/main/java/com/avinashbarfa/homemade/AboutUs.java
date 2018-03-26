package com.avinashbarfa.homemade;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AboutUs extends AppCompatActivity implements View.OnClickListener {

    Button btnContact,btnFeedback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        btnContact = (Button)findViewById(R.id.btnContact);
        btnFeedback = (Button)findViewById(R.id.btnFeedback);

        btnContact.setOnClickListener(this);
        btnFeedback.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnContact) {
            Intent email = new Intent(Intent.ACTION_SEND);
            email.putExtra(Intent.EXTRA_EMAIL, new String[]{"avibarfa98@gmail.com"});
            email.putExtra(Intent.EXTRA_SUBJECT, "");
            email.putExtra(Intent.EXTRA_TEXT, "");

            //need this to prompts email client only
            email.setType("message/rfc822");

            startActivity(Intent.createChooser(email, "Choose an Email client :"));
        }

        if (view == btnFeedback) {
            try {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "Snap Ticket");
                String sAux = "\nLet me recommend you this application\n\n";
                sAux = sAux + "https://play.google.com/store/apps/details?id=Orion.Soft \n\n";
                i.putExtra(Intent.EXTRA_TEXT, sAux);
                startActivity(Intent.createChooser(i, "choose one"));
            } catch (Exception e) {
                e.toString();
            }
        }
    }
}
