package com.example.yulia.lab21;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements MainFragment.OnFragmentInteractionListener {

    ResultFragment resultFragment;
    MainFragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultFragment = (ResultFragment) getSupportFragmentManager().findFragmentById(R.id.result_fragment);
        mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.main_fragment);
    }

    @Override
    public void OnClickButtonOK(String message) {


        resultFragment.showResult(message);
    }

    @Override
    public void OnClickButtonCancel() {
        mainFragment.hideChange();
        resultFragment.hideResult();

    }
}
