package com.example.yulia.lab21;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.database.sqlite.SQLiteStatement;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteCursor;

import static android.content.Context.MODE_PRIVATE;


public class MainFragment extends Fragment {

    Button okButton, cancelButton;
    ListView listView;
    RadioGroup radioGroup;
    public String message = "Користувач обрав: \n",message1="",message2="";

    private OnFragmentInteractionListener mListener;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        okButton = view.findViewById(R.id.btnOk);
        cancelButton = view.findViewById(R.id.btnCancel);
        listView = view.findViewById(R.id.listView);
        radioGroup = view.findViewById(R.id.radioGroup);


        final String[] types = getResources().getStringArray(R.array.types);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, types);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                int pos = position;
                message1=types[pos].toString()+"\n";
                message+="Тип телефону: ";
                //message+=messege1;


            }
        });

        RadioGroup radioGroup = view.findViewById(R.id.radioGroup);
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

        okButton.setOnClickListener(v ->
                mListener.OnClickButtonOK(message)
        );

        cancelButton.setOnClickListener(v ->
                mListener.OnClickButtonCancel()
        );

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void hideChange(){
        radioGroup.clearCheck();
        for ( int i=0; i< 4; i++ ) {
            listView.setItemChecked(i, false);
        }
        message="Користувач обрав: \n";
    }

    public interface OnFragmentInteractionListener {
        void OnClickButtonOK(String message);
        void OnClickButtonCancel();
    }
}
