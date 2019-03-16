package com.example.trabalho01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    EditText text;
    Button sendButton;
    ToggleButton toggleButton;
    Spinner spinner;

    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.editText);
        sendButton = findViewById(R.id.sendButton);
        toggleButton = findViewById(R.id.toggleButton);
        spinner = findViewById(R.id.spinner);

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast toast =Toast.makeText(getApplicationContext(), "Enable", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    Toast toast =Toast.makeText(getApplicationContext(), "Disable", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        radioGroup = findViewById(R.id.radioGroup);
        textView = findViewById(R.id.text_view_selected);

        Button buttonApply = findViewById(R.id.buttonApply);
        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = radioGroup.getCheckedRadioButtonId();

                radioButton = findViewById(radioId);

                textView.setText("Your choice: " + radioButton.getText());
            }
        });

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.times, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }

    public void onClickSendButton(View v){
        String txt = text.getText().toString();
        txt = "Msg: " + txt;
        Toast toast = Toast.makeText(getApplicationContext(), txt, Toast.LENGTH_LONG);
        toast.show();
    }

    private static final String[] TIMES = new String[] {
            "Barcelona", "Ajax", "Juventus", "Liverpool"
    };

    public void checkButton(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(radioId);

        Toast.makeText(this, "Selected Radio Button: " + radioButton.getText(),
                Toast.LENGTH_SHORT).show();
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, Tela2.class);
        startActivity(intent);
    }

}
