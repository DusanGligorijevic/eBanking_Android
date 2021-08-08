package rs.raf.projekat1.Dusan_Gligorijevic_9319rn.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import rs.raf.projekat1.Dusan_Gligorijevic_9319rn.enums.Tip;
import rs.raf.projekat1.Dusan_Gligorijevic_9319rn.models.Finansija;


public class RecyclerViewModel extends ViewModel {

    public static int counter = 101;

    private final MutableLiveData<List<Finansija>> finansije = new MutableLiveData<>();
    private final ArrayList<Finansija> listaFinansije = new ArrayList<>();

    public RecyclerViewModel() {
      //  for(int i=0; i<=100; i++) {
        //    Finansija finansija = new Finansija(i, Tip.PRIHOD, "prihod " + i, "1","ovo je prihod");
       //     listaFinansije.add(finansija);
       // }
        // ovo radimo zato sto cars.setValue u pozadini prvo proverava da li je pokazivac na objekat isti i ako jeste nece uraditi notifyAll
        // kreiranjem nove liste dobijamo novi pokazivac svaki put
        ArrayList<Finansija> finansijeToSubmit = new ArrayList<>(listaFinansije);
        finansije.setValue(finansijeToSubmit);
    }

    public MutableLiveData<List<Finansija>> getFinansije() {
        return finansije;
    }

    public void filterfinansije(String filter) {
        List<Finansija> filteredList = listaFinansije.stream().filter(finansija -> finansija.getNaslov().toLowerCase().startsWith(filter.toLowerCase())).collect(Collectors.toList());
        finansije.setValue(filteredList);
    }

    public void addFinansija( Tip tip, String naslov, String kolicina, String opis) {
        Finansija finansija = new Finansija(counter++, tip, naslov, kolicina, opis);
        listaFinansije.add(finansija);
        ArrayList<Finansija> listToSubmit = new ArrayList<>(listaFinansije);
        finansije.setValue(listToSubmit);
    }

    public ArrayList<Finansija> getListaFinansije() {
        return listaFinansije;
    }

}
