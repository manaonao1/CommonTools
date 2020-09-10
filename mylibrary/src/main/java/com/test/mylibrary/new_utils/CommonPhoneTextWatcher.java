package com.test.mylibrary.new_utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created on 2019/3/28
 *
 * @author zyf
 */
public class CommonPhoneTextWatcher implements TextWatcher {
    private EditText etPhoneInput;

    public CommonPhoneTextWatcher(EditText etPhoneInput) {
        this.etPhoneInput = etPhoneInput;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {}

    @Override
    public void afterTextChanged(Editable s) {}
}
