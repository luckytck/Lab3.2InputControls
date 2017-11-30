package my.edu.tarc.lab32inputcontrols;

import android.icu.text.NumberFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Locale;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinnerAge;
    private RadioGroup radioGroupGender;
    private RadioButton radioButtonMale, radioButtonFemale;
    private CheckBox checkBoxSmoker;
    private TextView textViewPremium;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Linking UI to program
        spinnerAge = (Spinner)findViewById(R.id.spinnerAge);
        radioGroupGender = (RadioGroup)findViewById(R.id.radioGroupGender);
        radioButtonMale = (RadioButton)findViewById(R.id.radioButtonMale);
        radioButtonFemale = (RadioButton)findViewById(R.id.radioButtonFemale);
        checkBoxSmoker = (CheckBox)findViewById(R.id.checkBoxSmoker);
        textViewPremium = (TextView)findViewById(R.id.textViewPremium);

        //Create an adapter for spinner
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(
                        this,
                        R.array.age_group,
                        android.R.layout.simple_spinner_item
                );
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerAge.setOnItemSelectedListener(this);
        spinnerAge.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(),"Position :" + position, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void calculatePremium(View view){
        int pos;
        pos = spinnerAge.getSelectedItemPosition();

        double premium;
        switch (pos){
            case 0:
                premium = 50;
                break;
            case 1:
                premium = 55;
                break;
            case 2:
                premium = 60;
                break;
            case 3:
                premium = 70;
                break;
            case 4:
                premium = 120;
                break;
            case 5:
                premium = 160;
                break;
            case 6:
                premium = 200;
                break;
            case 7:
                premium = 250;
                break;
            default:
                premium = 0;
        }

        int indexGender;
        indexGender = radioGroupGender.getCheckedRadioButtonId();
        if (indexGender == R.id.radioButtonMale){
            //TODO: calculate premium for male
            switch (pos){
                case 2:
                case 5:
                    premium += 50;
                    break;
                case 3:
                case 4:
                    premium += 100;
                    break;
            }
        }

        if (checkBoxSmoker.isChecked()){
            switch (pos){
                case 3:
                    premium += 100;
                    break;
                case 4:
                case 5:
                    premium += 150;
                    break;
                case 6:
                case 7:
                    premium += 250;
                    break;
            }
        }
        Locale locale = Locale.getDefault();
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        String currencyText = format.format(premium);
        textViewPremium.setText(getString(R.string.premium) + currencyText);
    }

    public void resetPremium(View view){
        spinnerAge.setSelection(0);
        radioButtonMale.setChecked(true);
        checkBoxSmoker.setChecked(false);
        textViewPremium.setText(getString(R.string.premium));
    }
}
