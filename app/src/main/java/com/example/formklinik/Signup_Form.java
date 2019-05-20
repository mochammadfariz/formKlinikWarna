package com.example.formklinik;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Signup_Form extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String TAG = "MainActivity";

    private TextView mDisplaydate,mDisplaydate2,mDisplaydate3;
    private DatePickerDialog.OnDateSetListener lahir,tgl_kunjungan;
    EditText bpjs;
    Switch swbpjs;

    Context mContext = this;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__form);

        bpjs = (EditText) findViewById(R.id.inputbpjs);
        swbpjs = (Switch) findViewById(R.id.switch1);
        swbpjs.setChecked(false);

        mDisplaydate2 = (TextView) findViewById(R.id.jamKunj);
        Calendar calendar1 = Calendar.getInstance();

        final int hour = calendar1.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar1.get(Calendar.MINUTE);

       mDisplaydate2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {
                   @Override
                   public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                       String jamHadir = hourOfDay + ":" + minute ;
                       mDisplaydate2.setText(jamHadir);
                   }
               },hour,minute,android.text.format.DateFormat.is24HourFormat(mContext));
               timePickerDialog.show();
           }
       });

        swbpjs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    bpjs.setVisibility(View.VISIBLE);
                    bpjs.setHint("Input nomor BPJS");
                    bpjs.setHintTextColor(Color.rgb(24,188,156));
                }
                else
                {
                    bpjs.setVisibility(View.INVISIBLE);
                    bpjs.setHint("");
                }
            }
        });

        mDisplaydate = (TextView) findViewById(R.id.tvdate);

        mDisplaydate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(Signup_Form.this,
                        android.R.style.Theme_Material_Dialog,
                        lahir,
                        year,month,day);

                dialog.show();
            }
        });

      lahir = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1 ;
                String date = dayOfMonth + "/" + month + "/" + year ;
                mDisplaydate.setText(date);
            }
        };

        mDisplaydate3 = (TextView) findViewById(R.id.tvdate2);

        mDisplaydate3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(Signup_Form.this,
                        android.R.style.Theme_Material_Dialog,
                        tgl_kunjungan,
                        year,month,day);

                dialog.show();
            }
        });

        tgl_kunjungan = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1 ;
                String date = dayOfMonth + "/" + month + "/" + year ;
                mDisplaydate3.setText(date);
            }
        };


        Spinner spinner = findViewById(R.id.pilihPoli);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.poli,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        Spinner spinner1 = findViewById(R.id.pilihKlinik);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.klinik,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(this);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
