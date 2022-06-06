package com.mebryco.genderpredict;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.mebryco.genderpredict.databinding.ActivityCalculateBinding;
import java.util.Calendar;

public class CalculateActivity extends AppCompatActivity {
    ActivityCalculateBinding binding;
    DatePickerDialog datePickerDialogFather;
    DatePickerDialog datePickerDialogMother;
    DatePickerDialog datePickerDialogChild;
    String selectedDateFather;
    String selectedDateMother;
    String selectedDateChild;
    int fatherDateDays;
    int motherDateDays;
    int childDateDays;
    String method;
    int dateIntervalCM;
    int dateIntervalCF;
    Intent intentToPrediction;
    Intent intentToWantGender;
    int motherIntervalMod;
    int fatherIntervalMod;
    int motherInterval;
    int fatherInterval;
    int girlStartDay;
    String startDay;
    String endDay;
    String currentDate;
    int currentDateDays;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCalculateBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        setContentView(view);
        initFatherDatePicker();
        initMotherDatePicker();
        initChildDatePicker();

        Intent intent = getIntent();
        method = intent.getStringExtra("method");



        if (method.matches("withFertilization")) {
            binding.methodText.setText("Gebelik Planlanan Günü Seçiniz");
        } else if (method.matches("withBirthDate")) {
            binding.methodText.setText("Beklenen Doğum Tarihini Seçiniz");
        } else if (method.matches("withSat")) {
            binding.methodText.setText("Son Adet Tarihini Giriniz");
        } else if (method.matches("withArabic")) {
            binding.methodText.setText("Gebelik Planlanan Günü Giriniz");
        } else if (method.matches("wantBoy") || method.matches("wantGirl")) {
            binding.methodText.setText("Bugünün Tarihini kontrol ediniz");
            if (method.matches("wantBoy") || method.matches("wantGirl")) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                month++;
                currentDate = (String) makeDateString(day, month, year);
                currentDateDays = day + (month * 30) + (year * 365);
                binding.childDateText.setText(currentDate);

                binding.childDateButton.setVisibility(View.INVISIBLE);

            }
        } else {
            binding.methodText.setText("Sistemsel HATA");
        }


        intentToPrediction = new Intent(CalculateActivity.this, PredictionActivity.class);
        intentToWantGender = new Intent(CalculateActivity.this, WantGenderActivity.class);


    }


    public String makeDateString(int dayOfMonth, int month, int year) {
        return dayOfMonth + " " + month + " " + year;
    }

    public String makeDateFromInt(int dateDays) {
        int year = dateDays / 365;
        int remainder = dateDays % 365;
        int month = remainder / 30;
        int day = remainder % 30;
        return day + "/" + month + "/" + year;
    }


    public void initFatherDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                selectedDateFather = makeDateString(day, month, year);
                fatherDateDays = day + (month * 30) + (year * 365);

                binding.fatherDateText.setText(selectedDateFather);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        datePickerDialogFather = new DatePickerDialog(CalculateActivity.this, android.R.style.Theme_Holo_Light_Dialog, dateSetListener, year, month, day);

    }

    public void initMotherDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                selectedDateMother = makeDateString(day, month, year);
                motherDateDays = day + (month * 30) + (year * 365);
                binding.motherDateText.setText(selectedDateMother);

            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        datePickerDialogMother = new DatePickerDialog(CalculateActivity.this, android.R.style.Theme_Holo_Light_Dialog, dateSetListener, year, month, day);

    }


    public void initChildDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                selectedDateChild = makeDateString(day, month, year);
                childDateDays = day + (month * 30) + (year * 365);
                binding.childDateText.setText(selectedDateChild);


            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        datePickerDialogChild = new DatePickerDialog(CalculateActivity.this, android.R.style.Theme_Holo_Light_Dialog, dateSetListener, year, month, day);

    }

    public void openDatePickerFather(View view) {
        datePickerDialogFather.show();
    }

    public void openDatePickerMother(View view) {
        datePickerDialogMother.show();
    }

    public void openDatePickerChild(View view) {
        datePickerDialogChild.show();
    }

    public void calculate(View view) {

        if (method.matches("withFertilization")) {
            dateIntervalCM = (childDateDays - motherDateDays) % 333;
            dateIntervalCF = (childDateDays - fatherDateDays) % 333;
            if (childDateDays <= motherDateDays || childDateDays <= fatherDateDays || fatherDateDays == 0 || motherDateDays == 0 || childDateDays == 0) {
                Toast.makeText(this, "Girilen Tarihleri Kontrol Ediniz", Toast.LENGTH_LONG).show();
            } else if (dateIntervalCF > dateIntervalCM) {

                intentToPrediction.putExtra("gender", "girl");
                startActivity(intentToPrediction);

            } else if (dateIntervalCF < dateIntervalCM) {
                intentToPrediction.putExtra("gender", "boy");
                startActivity(intentToPrediction);

            } else {

                intentToPrediction.putExtra("gender", "equal");
                startActivity(intentToPrediction);
            }
        } else if (method.matches("withBirthDate")) {
            childDateDays = childDateDays - 266;
            dateIntervalCM = (childDateDays - motherDateDays) % 333;
            dateIntervalCF = (childDateDays - fatherDateDays) % 333;
            if (childDateDays <= motherDateDays || childDateDays <= fatherDateDays || fatherDateDays == 0 || motherDateDays == 0 || childDateDays == 0) {
                Toast.makeText(this, "Girilen Tarihleri Kontrol Ediniz", Toast.LENGTH_LONG).show();
            } else if (dateIntervalCF > dateIntervalCM) {

                intentToPrediction.putExtra("gender", "girl");
                startActivity(intentToPrediction);

            } else if (dateIntervalCF < dateIntervalCM) {

                intentToPrediction.putExtra("gender", "boy");
                startActivity(intentToPrediction);

            } else {

                intentToPrediction.putExtra("gender", "equal");
                startActivity(intentToPrediction);
            }
        } else if (method.matches("withSat")) {
            childDateDays = childDateDays + 14;
            dateIntervalCM = (childDateDays - motherDateDays) % 333;
            dateIntervalCF = (childDateDays - fatherDateDays) % 333;
            if (childDateDays <= motherDateDays || childDateDays <= fatherDateDays || fatherDateDays == 0 || motherDateDays == 0 || childDateDays == 0) {
                Toast.makeText(this, "Girilen Tarihleri Kontrol Ediniz", Toast.LENGTH_LONG).show();
            } else if (dateIntervalCF > dateIntervalCM) {

                intentToPrediction.putExtra("gender", "girl");
                startActivity(intentToPrediction);

            } else if (dateIntervalCF < dateIntervalCM) {

                intentToPrediction.putExtra("gender", "boy");
                startActivity(intentToPrediction);

            } else {

                intentToPrediction.putExtra("gender", "equal");
                startActivity(intentToPrediction);
            }
        } else if (method.matches("withArabic")) {

            dateIntervalCM = (childDateDays - motherDateDays) % 325;
            dateIntervalCF = (childDateDays - fatherDateDays) % 325;
            if (childDateDays <= motherDateDays || childDateDays <= fatherDateDays || fatherDateDays == 0 || motherDateDays == 0 || childDateDays == 0) {
                Toast.makeText(this, "Girilen Tarihleri Kontrol Ediniz", Toast.LENGTH_LONG).show();
            } else if (dateIntervalCF > dateIntervalCM) {

                intentToPrediction.putExtra("gender", "girl");
                startActivity(intentToPrediction);

            } else if (dateIntervalCF < dateIntervalCM) {

                intentToPrediction.putExtra("gender", "boy");
                startActivity(intentToPrediction);

            } else {

                intentToPrediction.putExtra("gender", "equal");
                startActivity(intentToPrediction);
            }
        } else if (method.matches("wantBoy")) {
            motherIntervalMod = (currentDateDays - motherDateDays) % 333;
            fatherIntervalMod = (currentDateDays - fatherDateDays) % 333;
            motherInterval = (currentDateDays - motherDateDays);
            fatherInterval = (currentDateDays - fatherDateDays);

            if (motherIntervalMod > fatherIntervalMod) {
                startDay = currentDate;
                girlStartDay = currentDateDays + (333 - motherIntervalMod);
                endDay = makeDateFromInt(girlStartDay);
                intentToWantGender.putExtra("gender", "male");
                intentToWantGender.putExtra("startDay", startDay);
                intentToWantGender.putExtra("endDay", endDay);
                startActivity(intentToWantGender);

            } else if (fatherIntervalMod > motherIntervalMod) {

                int startDayInt = currentDateDays + (333 - fatherIntervalMod);
                startDay = makeDateFromInt(startDayInt);
                int endDayInt =currentDateDays + (333 - motherIntervalMod);
                endDay = makeDateFromInt(endDayInt);

                intentToWantGender.putExtra("gender", "male");
                intentToWantGender.putExtra("startDay", startDay);
                intentToWantGender.putExtra("endDay", endDay);
                startActivity(intentToWantGender);


            } else {
                intentToWantGender.putExtra("gender", "equal");
            }


        } else if (method.matches("wantGirl")) {
            motherIntervalMod = (currentDateDays - motherDateDays) % 333;
            fatherIntervalMod = (currentDateDays - fatherDateDays) % 333;
            motherInterval = (currentDateDays - motherDateDays);
            fatherInterval = (currentDateDays - fatherDateDays);

            if (motherIntervalMod > fatherIntervalMod) {
                int startDayInt = currentDateDays + (333 - motherIntervalMod);
                startDay = makeDateFromInt(startDayInt);
                int endDayInt = currentDateDays + (333 - fatherIntervalMod);
                endDay = makeDateFromInt(endDayInt);

                intentToWantGender.putExtra("gender", "female");
                intentToWantGender.putExtra("startDay", startDay);
                intentToWantGender.putExtra("endDay", endDay);
                startActivity(intentToWantGender);

            } else if (fatherIntervalMod > motherIntervalMod) {

                startDay = makeDateFromInt(currentDateDays);
                int endDayInt = currentDateDays + (333 - fatherIntervalMod);
                endDay = makeDateFromInt(endDayInt);

                intentToWantGender.putExtra("gender", "female");
                intentToWantGender.putExtra("startDay", startDay);
                intentToWantGender.putExtra("endDay", endDay);
                startActivity(intentToWantGender);


            } else {
                intentToWantGender.putExtra("gender", "equal");
            }

        }
    }
}
