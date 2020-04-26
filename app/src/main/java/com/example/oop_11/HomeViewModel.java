package com.example.oop_11;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<String> editText = new MutableLiveData<String>();
    private MutableLiveData<String> homeText = new MutableLiveData<String>();

    public HomeViewModel() {
        editText.setValue("");
        homeText.setValue("This is some random example text. It includes even more text.");
    }

    public LiveData<String> getEditText() {
        return editText;
    }

    public LiveData<String> getHomeText() {
        return homeText;
    }

    public void setEditText(String s) {
        editText.setValue(s);
    }

    public void setHomeText(String s) {
        homeText.setValue(s);
    }
}
