package rs.raf.projekat1.Dusan_Gligorijevic_9319rn.differ;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import rs.raf.projekat1.Dusan_Gligorijevic_9319rn.models.Finansija;

public class Differ extends DiffUtil.ItemCallback<Finansija>{
    @Override
    public boolean areItemsTheSame(@NonNull Finansija oldItem, @NonNull Finansija newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Finansija oldItem, @NonNull Finansija newItem) {
        return oldItem.getId()==(newItem.getId())
                && oldItem.getTip().equals(newItem.getTip())
                && oldItem.getNaslov().equals(newItem.getNaslov())
                && oldItem.getKolicina() == newItem.getKolicina();
    }
}
