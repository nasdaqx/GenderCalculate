package com.mebryco.genderpredict;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class WantGenderActivity extends AppCompatActivity {

    TextView startDay;
    TextView endDay;
    TextView resultText;
    ImageView genderImage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_want_gender);





        startDay = findViewById(R.id.genderStartDate);
        endDay = findViewById(R.id.genderEndDate);
        resultText = findViewById(R.id.genderWantResultText);
        genderImage = findViewById(R.id.genderWantImage);

        Intent intent = getIntent();
        String gender = intent.getStringExtra("gender");
        String start= intent.getStringExtra("startDay");
        String end = intent.getStringExtra("endDay");

        startDay.setText(start);
        endDay.setText(end);
        if(gender.matches("male")) {
            resultText.setText("Yukarıdaki tarihler arasında,\n ERKEK\n çocuğu olma ihtimali fazladır");
            genderImage.setImageResource(R.drawable.boybaby);

        } else if (gender.matches("female")) {
            resultText.setText("Yukarıdaki tarihler arasında, \n KIZ\n  çocuğu olma ihtimali fazladır");
            genderImage.setImageResource(R.drawable.girlbaby);
        } else if(gender.matches("equal")){
            resultText.setText("Bütün çocukların aynı cinsiyette olması beklenir");
            genderImage.setImageResource(R.drawable.equalgender);
        }



    }
}