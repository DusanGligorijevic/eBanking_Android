package rs.raf.projekat1.Dusan_Gligorijevic_9319rn.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import rs.raf.projekat1.Dusan_Gligorijevic_9319rn.models.Finansija;

public class SharedViewModel extends ViewModel {

    private final MutableLiveData<String> userInputLiveData = new MutableLiveData<>();

    public LiveData<String> getUserInput() {
        return userInputLiveData;
    }

    public void storeUserInput(String userInput) {
        userInputLiveData.setValue(userInput);
    }

}
