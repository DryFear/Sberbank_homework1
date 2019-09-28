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
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private EditText sourceEditText;
    private TextView resultTextView;
    private String[] russians;
    private String[] latin;
    private MyHelper conventer;
    private View copyButton;

    private CompoundButton checkCaps;

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
        checkCaps = findViewById(R.id.check_caps);
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

        sourceEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                resultTextView.setText(conventer.convert(s));
                copyButton.setEnabled(s.length() > 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
