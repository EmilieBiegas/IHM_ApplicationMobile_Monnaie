package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import static com.example.helloworld.MainActivity.CURRENCY_CHOOSER_REQUEST;

public class CurrencyChooserActivity extends AppCompatActivity {
    public static final String BUNDLE_EXTRA_CURRENCY_FROM = "CURRENCY_FROM";
    public static final String BUNDLE_EXTRA_CURRENCY_TO = "CURRENCY_TO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_chooser);
    }

    public void convert(View sender){
        Intent res_intent = new Intent();

        // Pour le groupe de bouton "From"
        RadioGroup radio_grpe = (RadioGroup) findViewById( R.id.currency_from );
        int idIn = radio_grpe.getCheckedRadioButtonId();

        if( idIn == R.id.currency_euros_from) {
            // System.out.println("from EUROS");
            res_intent.putExtra(BUNDLE_EXTRA_CURRENCY_FROM, "euro");
        }

        if( idIn == R.id.currency_dollars_from) {
            // System.out.println("from DOLLARS");
            res_intent.putExtra(BUNDLE_EXTRA_CURRENCY_FROM, "dollar");
        }

        if( idIn == R.id.currency_pounds_from) {
            // System.out.println("from POUNDS");
            res_intent.putExtra(BUNDLE_EXTRA_CURRENCY_FROM, "pound");
        }

        // Pour le groupe de bouton "To"
        RadioGroup radio_grpeO = (RadioGroup) findViewById( R.id.currency_to );
        int idOut = radio_grpeO.getCheckedRadioButtonId();

        if( idOut == R.id.currency_euros_to) {
            // System.out.println("to EUROS");
            res_intent.putExtra(BUNDLE_EXTRA_CURRENCY_TO, "euro");
        }

        if( idOut == R.id.currency_dollars_to) {
            // System.out.println("to DOLLARS");
            res_intent.putExtra(BUNDLE_EXTRA_CURRENCY_TO, "dollar");
        }

        if( idOut == R.id.currency_pounds_to) {
            // System.out.println("to POUNDS");
            res_intent.putExtra(BUNDLE_EXTRA_CURRENCY_TO, "pound");
        }

        setResult(RESULT_OK, res_intent);

        onActivityResult(CURRENCY_CHOOSER_REQUEST, RESULT_OK, res_intent);

        finish();
    }
}