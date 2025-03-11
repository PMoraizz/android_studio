package com.example.barbearia;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class MaskEditUtil {
    public static TextWatcher mask(final EditText editText, final String mask) {
        return new TextWatcher() {
            boolean isUpdating;
            String oldString = "";

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = MaskEditUtil.unmask(s.toString());
                String masked = "";
                if (isUpdating) {
                    oldString = str;
                    isUpdating = false;
                    return;
                }
                int i = 0;
                for (char m : mask.toCharArray()) {
                    if (m != '#' && str.length() > oldString.length()) {
                        masked += m;
                        continue;
                    }
                    try {
                        masked += str.charAt(i);
                    } catch (Exception e) {
                        break;
                    }
                    i++;
                }
                isUpdating = true;
                editText.setText(masked);
                editText.setSelection(masked.length());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable s) {}
        };
    }

    public static String unmask(String s) {
        return s.replaceAll("[^\\d]", "");
    }
}

