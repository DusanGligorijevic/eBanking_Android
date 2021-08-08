package rs.raf.projekat1.Dusan_Gligorijevic_9319rn.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import rs.raf.projekat1.Dusan_Gligorijevic_9319rn.models.Finansija;

public class ShowViewModel extends ViewModel {

    private final MutableLiveData<String> titleLiveData = new MutableLiveData<>();

    public ShowViewModel() {
        titleLiveData.setValue("null");
    }

    public LiveData<String> getTitle() {
        return titleLiveData;
    }

}
