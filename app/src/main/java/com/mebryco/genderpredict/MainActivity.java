package com.mebryco.genderpredict;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void withFertilization(View view) {
        Intent intent = new Intent(MainActivity.this,CalculateActivity.class);
        intent.putExtra("method","withFertilization");
        startActivity(intent);

    }

    public void withBirthDate(View view) {
        Intent intent = new Intent(MainActivity.this,CalculateActivity.class);
        intent.putExtra("method","withBirthDate");
        startActivity(intent);
    }

    public void wantGirl(View view) {
        Intent intent = new Intent(MainActivity.this,CalculateActivity.class);
        intent.putExtra("method","wantGirl");
        startActivity(intent);
    }

    public void wantBoy(View view) {
        Intent intent = new Intent(MainActivity.this,CalculateActivity.class);
        intent.putExtra("method","wantBoy");
        startActivity(intent);
    }

    public void withSat(View view) {
        Intent intent = new Intent(MainActivity.this,CalculateActivity.class);
        intent.putExtra("method","withSat");
        startActivity(intent);
    }

    public void withArabic(View view) {
        Intent intent = new Intent(MainActivity.this,CalculateActivity.class);
        intent.putExtra("method","withArabic");
        startActivity(intent);
    }

    public void infoMenu(View view) {
        Intent intent = new Intent(MainActivity.this, InfoActivity.class);
        startActivity(intent);
    }
}