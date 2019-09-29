package ru.unfortunately.school.russianpassword;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private EditText sourceEditText;
    private TextView resultTextView;
    private TextView generateTextView;
    private String[] russians;
    private String[] latin;
    private MyHelper conventer;
    private View copyButton;
    private View generateButton;
    private View copyGenerateButton;
    private TextView weaknessText;
    private ImageView weaknessBar;
    private CompoundButton checkCaps;
    private CompoundButton checkNums;
    private CompoundButton checkSymbs;
    private SeekBar generateSeekBar;
    private TextView changedLengthText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sourceEditText = findViewById(R.id.edit_src);
        resultTextView = findViewById(R.id.text_result);
        russians = getResources().getStringArray(R.array.russians);
        latin = getResources().getStringArray(R.array.latin);
        conventer = new MyHelper(russians, latin);
        copyButton = findViewById(R.id.copyResultButton);
        copyGenerateButton = findViewById(R.id.copyGeneratedButton);
        generateButton = findViewById(R.id.generateButton);
        checkCaps = findViewById(R.id.check_caps);
        checkNums = findViewById(R.id.check_number);
        checkSymbs = findViewById(R.id.check_symbols);
        weaknessBar = findViewById(R.id.weakness_bar);
        weaknessText = findViewById(R.id.weakness_text);
        generateTextView = findViewById(R.id.text_generated);
        generateSeekBar = findViewById(R.id.generate_seek_bar);
        changedLengthText = findViewById(R.id.changed_length_text);

        copyGenerateButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager manager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                if(manager != null){
                    manager.setPrimaryClip(
                            ClipData.newPlainText(
                                    getString(R.string.clipboard_title), generateTextView.getText()
                            )
                    );
                    Toast.makeText(MainActivity.this, getString(R.string.toast_copied), Toast.LENGTH_SHORT).show();
                }
            }
        });

        copyButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager manager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                if(manager != null){
                    manager.setPrimaryClip(
                        ClipData.newPlainText(
                                getString(R.string.clipboard_title), resultTextView.getText()
                        )
                    );
                    Toast.makeText(MainActivity.this, getString(R.string.toast_copied), Toast.LENGTH_SHORT).show();
                }
            }
        });

        generateButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                generateTextView.setText(PasswordGenerator.generate(
                        checkCaps.isChecked(),
                        checkNums.isChecked(),
                        checkSymbs.isChecked(),
                        generateSeekBar.getProgress()
                ));
            }
        });

        sourceEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                resultTextView.setText(conventer.convert(s));
                copyButton.setEnabled(s.length() > 0);
                checkWeakness(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        generateSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                changedLengthText.setText(getString(R.string.text_under_seek_bar) + progress +
                        " " + getResources().getQuantityString(R.plurals.symbols_count, progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void checkWeakness(CharSequence s){
        String[] conditions = getResources().getStringArray(R.array.weakness_conditions);
        int value = (s.length() < conditions.length ? s.length() : conditions.length-1);
        weaknessText.setText(conditions[value]);
        weaknessBar.setImageLevel(value*10000/conditions.length);
    }

}
