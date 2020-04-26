package com.example.oop_11;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SettingsViewModel extends ViewModel {
    private MutableLiveData<Boolean> editEnabled = new MutableLiveData<Boolean>();
    private MutableLiveData<String> displayText = new MutableLiveData<String>();
    private MutableLiveData<String> locale = new MutableLiveData<String>();
    private MutableLiveData<Integer> fontSize = new MutableLiveData<Integer>();
    private MutableLiveData<Integer> width = new MutableLiveData<Integer>();
    private MutableLiveData<Integer> lineSpacing = new MutableLiveData<Integer>();
    private MutableLiveData<Integer[]> color = new MutableLiveData<Integer[]>();

    public SettingsViewModel() {
        editEnabled.setValue(false);
        displayText.setValue("");
        color.setValue(new Integer[3]);
    }

    public LiveData<Boolean> getEditEnabled() {
        return editEnabled;
    }

    public LiveData<String> getDisplayText() {
        return displayText;
    }

    public LiveData<String> getLocale() {
        return locale;
    }

    public LiveData<Integer> getFontSize() {
        return fontSize;
    }

    public LiveData<Integer> getWidth() {
        return width;
    }

    public LiveData<Integer> getLineSpacing() {
        return lineSpacing;
    }

    public LiveData<Integer[]> getColor() {
        return color;
    }

    public void setEditEnabled(boolean value) {
        editEnabled.setValue(value);
    }

    public void setDisplayText(String s) {
        displayText.setValue(s);
    }

    public void setLocale(String s) {
        locale.setValue(s);
    }

    public void setFontSize(Integer size) {
        fontSize.setValue(size);
    }

    public void setWidth(Integer w) {
        width.setValue(w);
    }

    public void setLineSpacing(Integer space) {
        lineSpacing.setValue(space);
    }

    public void setColor(Integer[] c) {
        color.setValue(c);
    }
}
