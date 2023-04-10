package com.wjs.android.demo.widgetstest;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.widget.EditText;

import com.android.widget_extra.textfield.ICIInputText;
import com.android.widget_extra.textfield.ICISearchBar;
import com.wjs.android.demo.R;

public class TexfieldActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ICISearchBar searchBar = findViewById(R.id.ici_sample_search);
        searchBar.addDeleteIV();
        searchBar.addVoiceIV();

        ICIInputText iciInputText2 = findViewById(R.id.ici_input_view2);
        EditText editText2 = iciInputText2.addEditText();
        editText2.setHint("Textfield With hint");

        ICIInputText iciInputText3 = findViewById(R.id.ici_input_view3);
        EditText editText3 = iciInputText3.addEditText();
        editText3.setHint("Textfield Disable");
        iciInputText3.setEnabled(false);

        ICIInputText iciInputText4 = findViewById(R.id.ici_input_view4);
        EditText editText4 = iciInputText4.addEditText();
        editText4.setText("Textfield With Input");

        ICIInputText iciInputText5 = findViewById(R.id.ici_input_view5);
        EditText editText5 = iciInputText5.addEditText();
        iciInputText5.addDeleteView();
        editText5.setText("Textfield With Delete");

        ICIInputText iciInputText6 = findViewById(R.id.ici_input_view6);
        EditText editText6 = iciInputText6.addEditText();
        editText6.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        iciInputText6.addDeleteView();
        iciInputText6.addPasswordShowView();
        editText6.setText("Textfield With 2 button");

        ICIInputText iciInputText7 = findViewById(R.id.ici_input_view7);
        EditText editText7 = iciInputText7.addEditText();
        editText7.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        iciInputText7.addDeleteView();
        iciInputText7.addPasswordShowView();
        editText7.setText("Textfield With 2 Button");

        final ICIInputText iciInputText8 = findViewById(R.id.ici_input_view8);
        EditText editText8 = iciInputText8.addEditText();
        iciInputText8.addDeleteView();
        editText8.setHint("Textfield With BOTTOM label");
        iciInputText8.addErrorTextView();

        editText8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                iciInputText8.addSugguestTextView().setText(s.toString().length() + "/45");
            }
        });
        iciInputText8.setRightMessage("提示内容文本");

        final ICIInputText iciInputText9 = findViewById(R.id.ici_input_view9);
        EditText editText9 = iciInputText9.addEditText();
        iciInputText9.addDeleteView();
        editText9.setHint("Textfield With BOTTOM label");
        iciInputText9.addErrorTextView();
        editText9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                iciInputText9.addSugguestTextView().setText(s.toString().length() + "/45");
            }
        });
        iciInputText9.setRightMessage("提示内容文本提示内容文本提示内容文本提示内容文本提示内容文本提示内容文本提示");

        final ICIInputText iciInputText10 = findViewById(R.id.ici_input_view10);
        EditText editText10 = iciInputText10.addEditText();
        iciInputText10.addDeleteView();
        iciInputText10.addPasswordShowView();
        iciInputText10.addErrorTextView();
        editText10.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        editText10.setHint("Textfield With error");
        iciInputText10.setRightErrorMessage("提示内容文本");

        editText10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                iciInputText10.setRightMessage("");
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_textfeild;
    }
}
