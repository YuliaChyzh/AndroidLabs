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
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    private final static String FILE_NAME = "content.txt";

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

    public void saveText(View view){

        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(message.getBytes());
            Toast.makeText(this, "Файл збережено", Toast.LENGTH_SHORT).show();
        }
        catch(IOException ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally{
            try{
                if(fos!=null)
                    fos.close();
            }
            catch(IOException ex){

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void openText(View view) {

        FileInputStream fin = null;
        TextView textView = (TextView) findViewById(R.id.textViewRes);
        try {
            fin = openFileInput(FILE_NAME);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String(bytes);
            //text+=getFileStreamPath(FILE_NAME);
            textView.setText(text);
        } catch (IOException ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {

            try {
                if (fin != null)
                    fin.close();
            } catch (IOException ex) {

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void OnClickButtonOK(View view) {
        //TextView result = (TextView)findViewById(R.id.textViewRes);
        //result.setText(message);

        saveText(view);
    }

    public void OnClickButtonCancel(View view) {
        deleteFile(FILE_NAME);
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

    public void OnClickButtonOpen(View view) {
        openText(view);
    }

}
