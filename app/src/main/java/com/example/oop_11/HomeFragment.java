package com.example.oop_11;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class HomeFragment extends Fragment {
    private EditText editText;
    private HomeViewModel homeViewModel;
    private SettingsViewModel settingsViewModel;
    private TextView displayText, homeText;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        displayText = root.findViewById(R.id.displayText);
        homeText = root.findViewById(R.id.homeText);
        editText = root.findViewById(R.id.editText);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                homeViewModel.setEditText(s.toString());
            }
        });
        settingsViewModel = new ViewModelProvider(requireActivity()).get(SettingsViewModel.class);
        settingsViewModel.getEditEnabled().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                String text;

                if (aBoolean) {
                    editText.setEnabled(true);
                } else {
                    editText.setEnabled(false);

                    // never setting text to empty also protects first automatic onChanged firing
                    // from removing the first placeholder text

                    if ((text = homeViewModel.getEditText().getValue()) != null && !text.equals("")) {
                        homeText.setText(text);
                        homeViewModel.setHomeText(text);
                        editText.setText("");
                    }
                }
            }
        });
        settingsViewModel.getDisplayText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                displayText.setText(s);
            }
        });
        settingsViewModel.getColor().observe(getViewLifecycleOwner(), new Observer<Integer[]>() {
            @Override
            public void onChanged(Integer[] integers) {
                for (Integer i : integers) if (i == null) return;

                homeText.setTextColor(Color.rgb(integers[0],integers[1],integers[2]));
            }
        });
        settingsViewModel.getFontSize().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                homeText.setTextSize(integer);
            }
        });
        settingsViewModel.getWidth().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                ViewGroup.LayoutParams params = homeText.getLayoutParams();
                params.width = integer;
                homeText.setLayoutParams(params);
            }
        });
        settingsViewModel.getLineSpacing().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                homeText.setLineSpacing(integer, (float) 0.85);
                // with multiplier 0.85 and add 0 there is no line spacing (taking into account letters like j and h)
            }
        });

        // get possible previous values from ViewModel when revisiting

        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);

        if (homeViewModel.getEditText().getValue() != null) {
            editText.setText(homeViewModel.getEditText().getValue());
        }

        if (homeViewModel.getHomeText().getValue() != null) {
            homeText.setText(homeViewModel.getHomeText().getValue());
        }

        return root;
    }
}
