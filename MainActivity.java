package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    static final int CURRENCY_CHOOSER_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void convert(View sender) {
        EditText input = (EditText) findViewById( R.id.edit_input );
        EditText output = (EditText) findViewById( R.id.edit_output  );
        TextView exchange_rate = (TextView) findViewById( R.id.taux );

        String val = input.getText().toString();
        String exchange_r = exchange_rate.getText().toString();
        double valFloat = Float.parseFloat(val) * Float.parseFloat(exchange_r);

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2); //arrondi à 2 chiffres apres la virgules
        valFloat = Double.parseDouble(df.format (valFloat));


        String valS = Double.toString(valFloat);

        output.setText(valS);

    }

    public void choose_currency(View sender){
        Intent intent = new Intent(this, CurrencyChooserActivity.class);
        startActivityForResult(intent, CURRENCY_CHOOSER_REQUEST);
    }

    protected void onActivityResult( int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CURRENCY_CHOOSER_REQUEST) {
            if (resultCode == RESULT_OK) {
                String currency_from = data.getStringExtra("CURRENCY_FROM");
                String currency_to = data.getStringExtra("CURRENCY_TO");

                EditText input = (EditText) findViewById( R.id.edit_input );
                TextView inputV = (TextView) findViewById( R.id.view_input );
                EditText output = (EditText) findViewById( R.id.edit_output  );
                TextView outputV = (TextView) findViewById( R.id.view_output  );
                TextView exchange_rate = (TextView) findViewById( R.id.taux );

                // FROM
                if(currency_from.equals("euro")){
                    System.out.println("From EURO");
                    input.setHint(getResources().getString(R.string.euros_label));
                    inputV.setText(getResources().getString(R.string.euros_label));

                    // Mise à jour du taux
                    if(currency_to.equals("euro")){
                        exchange_rate.setText("1");
                    }
                    if(currency_to.equals("pound")){
                        exchange_rate.setText("0.87");
                    }
                    if(currency_to.equals("dollar")){
                        exchange_rate.setText("1.20");
                    }
                }
                if(currency_from.equals("pound")){
                    System.out.println("From POUND");

                    input.setHint(getResources().getString(R.string.pounds_label));
                    inputV.setText(getResources().getString(R.string.pounds_label));

                    // Mise à jour du taux
                    if(currency_to.equals("euro")){
                        exchange_rate.setText("1.15");
                    }
                    if(currency_to.equals("pound")){
                        exchange_rate.setText("1");
                    }
                    if(currency_to.equals("dollar")){
                        exchange_rate.setText("1.38");
                    }
                }
                if(currency_from.equals("dollar")){
                    System.out.println("From DOLLAR");
                    input.setHint(getResources().getString(R.string.dollars_label));
                    inputV.setText(getResources().getString(R.string.dollars_label));

                    // Mise à jour du taux
                    if(currency_to.equals("euro")){
                        exchange_rate.setText("0.84");
                    }
                    if(currency_to.equals("pound")){
                        exchange_rate.setText("0.73");
                    }
                    if(currency_to.equals("dollar")){
                        exchange_rate.setText("1");
                    }
                }

                // TO
                if(currency_to.equals("euro")){
                    System.out.println("To EURO");
                    output.setText(getResources().getString(R.string.euros_label));
                    outputV.setText(getResources().getString(R.string.euros_label));
                }
                if(currency_to.equals("pound")){
                    System.out.println("To POUND");
                    output.setText(getResources().getString(R.string.pounds_label));
                    outputV.setText(getResources().getString(R.string.pounds_label));
                }
                if(currency_to.equals("dollar")){
                    System.out.println("To DOLLAR");
                    output.setText(getResources().getString(R.string.dollars_label));
                    outputV.setText(getResources().getString(R.string.dollars_label));
                }
            }
        }
    }

    public void inverse(View view) {
        EditText input = (EditText) findViewById(R.id.edit_input);
        TextView inputV = (TextView) findViewById(R.id.view_input);
        EditText output = (EditText) findViewById(R.id.edit_output);
        TextView outputV = (TextView) findViewById(R.id.view_output);
        TextView exchange_rate = (TextView) findViewById(R.id.taux);

        // Echange des devises
        String temp = output.getHint().toString();
        String tempV = outputV.getText().toString();

        output.setHint(input.getHint().toString());
        outputV.setText(inputV.getText().toString());
        input.setHint(temp);
        inputV.setText(tempV);

        // On inverse le taux de change
        String exchange_r = exchange_rate.getText().toString();
        double valFloat = 1/Float.parseFloat(exchange_r);

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2); //arrondi à 2 chiffres apres la virgules
        valFloat = Double.parseDouble(df.format (valFloat));

        String valS = Double.toString(valFloat);
        exchange_rate.setText(valS);
    }
}
