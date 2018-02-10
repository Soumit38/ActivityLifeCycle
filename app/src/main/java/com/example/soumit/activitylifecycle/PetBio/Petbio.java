package com.example.soumit.activitylifecycle.PetBio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.soumit.activitylifecycle.R;

public class Petbio extends AppCompatActivity implements View.OnClickListener{

    private ImageView catView;
    private ImageView dogView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petbio_main);

        catView = (ImageView) findViewById(R.id.cat_image_id);
        dogView = (ImageView) findViewById(R.id.dog_image_id);

        catView.setOnClickListener(this);
        dogView.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cat_image_id:
                Intent catIntent = new Intent(Petbio.this, BioActivity.class);
                catIntent.putExtra("name", "Jarvis");
                catIntent.putExtra("bio", "Great cat. Loves people and meows a lot!");
                startActivity(catIntent);
                break;
            case R.id.dog_image_id:
                Intent dogIntent = new Intent(Petbio.this, BioActivity.class);
                dogIntent.putExtra("name", "Rufus");
                dogIntent.putExtra("bio" , "Loves people and eats a lot!");
                startActivity(dogIntent);
                break;
        }
    }
}
















