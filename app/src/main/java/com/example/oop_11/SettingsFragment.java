package com.example.oop_11;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class SettingsFragment extends Fragment {
    private EditText displayInput, fontInput, widthInput, lineSpacingInput, colorInputR, colorInputG, colorInputB;
    private SettingsViewModel model;
    private Spinner langSpinner;
    private String[] langArray = {"Change language", "English", "Suomi", "Svenska"};
    private Switch editSwitch;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        model = new ViewModelProvider(requireActivity()).get(SettingsViewModel.class);
        colorInputR = root.findViewById(R.id.colorInputR);
        colorInputR.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                Integer[] modelColors = model.getColor().getValue();

                if (modelColors != null) {
                    try {
                        Integer val = Integer.valueOf(s.toString());
                        if (val >= 0 && val <= 255) modelColors[0] = val;
                    } catch (Exception ignore) {}
                }
            }
        });
        colorInputG = root.findViewById(R.id.colorInputG);
        colorInputG.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                Integer[] modelColors = model.getColor().getValue();

                if (modelColors != null) {
                    try {
                        Integer val = Integer.valueOf(s.toString());
                        if (val >= 0 && val <= 255) modelColors[1] = val;
                    } catch (Exception ignore) {}
                }
            }
        });
        colorInputB = root.findViewById(R.id.colorInputB);
        colorInputB.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                Integer[] modelColors = model.getColor().getValue();

                if (modelColors != null) {
                    try {
                        Integer val = Integer.valueOf(s.toString());
                        if (val >= 0 && val <= 255) modelColors[2] = val;
                    } catch (Exception ignore) {}
                }
            }
        });
        editSwitch = root.findViewById(R.id.editSwitch);
        editSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                model.setEditEnabled(isChecked);
            }
        });
        fontInput = root.findViewById(R.id.fontInput);
        fontInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    Integer val = Integer.valueOf(s.toString());
                    model.setFontSize(val);
                } catch (Exception ignore) {}
            }
        });
        lineSpacingInput = root.findViewById(R.id.lineSpacingInput);
        lineSpacingInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    Integer val = Integer.valueOf(s.toString());
                    model.setLineSpacing(val);
                } catch (Exception ignore) {}
            }
        });
        widthInput = root.findViewById(R.id.widthInput);
        widthInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    Integer val = Integer.valueOf(s.toString());
                    model.setWidth(val);
                } catch (Exception ignore) {}
            }
        });
        displayInput = root.findViewById(R.id.displayInput);
        displayInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                model.setDisplayText(s.toString());
            }
        });
        langSpinner = root.findViewById(R.id.langSpinner);
        langArray[0] = getResources().getString(R.string.spinner_hint);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item) {
            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }
        };
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.addAll(langArray);
        langSpinner.setAdapter(adapter);
        langSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position > 0) { // the first item, which is hint, is automatically selected when creating
                    if (langArray[position].equals("Suomi")) {
                        model.setLocale("fi");
                    } else if (langArray[position].equals("English")) {
                        model.setLocale("en");
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {}
        });

        if (model.getDisplayText().getValue() != null) {
            displayInput.setText(model.getDisplayText().getValue());
        }

        if (model.getEditEnabled().getValue() != null) {
            editSwitch.setChecked(model.getEditEnabled().getValue());
        } else {
            editSwitch.setChecked(false);
        }

        Integer[] colorList;

        if ((colorList = model.getColor().getValue()) != null) {
            if (colorList[0] != null) colorInputR.setText(colorList[0].toString());
            if (colorList[1] != null) colorInputG.setText(colorList[1].toString());
            if (colorList[2] != null) colorInputB.setText(colorList[2].toString());
        }

        if (model.getFontSize().getValue() != null) {
            fontInput.setText(model.getFontSize().getValue().toString());
        }

        if (model.getWidth().getValue() != null) {
            widthInput.setText(model.getWidth().getValue().toString());
        }

        if (model.getLineSpacing().getValue() != null) {
            lineSpacingInput.setText(model.getLineSpacing().getValue().toString());
        }

        return root;
    }
}
