package com.example.yulia.lab1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.RadioGroup;
import android.widget.RadioButton;


public class MainActivity extends AppCompatActivity {

    public String message = "Користувач обрав: \n";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.listView);
        final String[] types = getResources().getStringArray(R.array.types);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, types);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                int pos = position;
                String messege1=types[pos].toString()+"\n";
                message+="Тип телефону: ";
                message+=messege1;
            }
        });

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                message+="Фірма: ";
                String message1;
                switch (checkedId) {
                    case -1:
                        message1="не обрано \n";
                        message+=message1;
                        break;
                    case R.id.radioButtonSamsung:
                        message1="Samsung \n";
                        message+=message1;
                        break;
                    case R.id.radioButtonXiaomi:
                        message1="Xiaomi \n";
                        message+=message1;
                        break;
                    case R.id.radioButtonNokia:
                        message1="Nokia \n";
                        message+=message1;
                        break;
                    case R.id.radioButtonApple:
                        message1="Apple \n";
                        message+=message1;
                        break;
                    case R.id.radioButtonLG:
                        message1="LG \n";
                        message+=message1;
                        break;
                    default:
                        break;
                }
            }
        });

    }

    public void OnClickButtonOK(View view) {
        TextView result = (TextView)findViewById(R.id.textViewRes);
        result.setText(message);
    }

    public void OnClickButtonCancel(View view) {
        TextView result = (TextView)findViewById(R.id.textViewRes);
        result.setText("");
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.clearCheck();
        ListView listView = (ListView) findViewById(R.id.listView);
        for ( int i=0; i< 4; i++ ) {
            listView.setItemChecked(i, false);
        }
        message="Користувач обрав: \n";
    }
}
