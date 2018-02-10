package com.example.soumit.activitylifecycle.PetBio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.soumit.activitylifecycle.R;

public class BioActivity extends AppCompatActivity {

    private static final String TAG = "BioActivity";

    private ImageView petImageView;
    private TextView petName;
    private TextView petBio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bio);

        petImageView = (ImageView) findViewById(R.id.pet_image_id);
        petName = (TextView) findViewById(R.id.pet_name_id);
        petBio = (TextView) findViewById(R.id.pet_info_id);

        Intent petBioInfo = getIntent();
        if (petBioInfo != null) {
            String petName = petBioInfo.getStringExtra("name");
            String petDesc = petBioInfo.getStringExtra("bio");
            Log.d(TAG, "onCreate: " + petName + "--->" + petDesc);

            setUpBio(petName, petDesc);
        }

    }

    public void setUpBio(String name, String bio){
        if(name.equals("Rufus")){
            petImageView.setImageResource(R.drawable.dog);
        }else if(name.equals("Jarvis")){
            petImageView.setImageResource(R.drawable.cat);
        }

        petName.setText(name);
        petBio.setText(bio);

    }


}
















