package com.mebryco.genderpredict;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


public class PredictionActivity extends AppCompatActivity {
    String gender;
    ImageView genderImage;
    TextView genderText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prediction);




        genderImage= findViewById(R.id.resultImage);
        genderText = findViewById(R.id.resultText);


        Intent intent =getIntent();
        gender = intent.getStringExtra("gender");


        ShowGender();
    }





    public void ShowGender (){
        if(gender.matches("boy")){
                genderImage.setImageResource(R.drawable.boybaby);
                genderText.setText("Bebeğinizin,  ERKEK  olma ihtimali daha fazladır.");

            } else if(gender.matches("girl")){
                genderImage.setImageResource(R.drawable.girlbaby);
                genderText.setText("Bebeğinizin,  KIZ  olma ihtimali daha fazladır ");

            } else if(gender.matches("equal")){
                genderImage.setImageResource(R.drawable.equalgender);
                genderText.setText("Bu duruma sık rastlanmaz. Devamli aynı cinsiyette çocuklar doğabilir.");

            } else {
                genderText.setText("Bilgiler Hatalıdır");



            }


    }


}