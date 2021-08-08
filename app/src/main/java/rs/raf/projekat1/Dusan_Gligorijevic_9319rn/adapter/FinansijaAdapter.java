package rs.raf.projekat1.Dusan_Gligorijevic_9319rn.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import rs.raf.projekat1.Dusan_Gligorijevic_9319rn.EditPrihodActivity;
import rs.raf.projekat1.Dusan_Gligorijevic_9319rn.R;
import rs.raf.projekat1.Dusan_Gligorijevic_9319rn.ShowRashodActivty;
import rs.raf.projekat1.Dusan_Gligorijevic_9319rn.enums.Tip;
import rs.raf.projekat1.Dusan_Gligorijevic_9319rn.models.Finansija;
import rs.raf.projekat1.Dusan_Gligorijevic_9319rn.viewmodels.RecyclerViewModel;
import timber.log.Timber;

public class FinansijaAdapter extends ListAdapter<Finansija, FinansijaAdapter.ViewHolder> {
    private RecyclerView recyclerView;

    private final Function<Finansija, Void> onFinansijaClicked;
    public FinansijaAdapter(@NonNull DiffUtil.ItemCallback<Finansija> diffCallback, Function<Finansija, Void> onFinansijaClicked,
                            RecyclerView recyclerViewModel) {
        super(diffCallback);
        this.recyclerView=recyclerViewModel;
        this.onFinansijaClicked = onFinansijaClicked;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.finansija_list_item, parent, false);
        return new ViewHolder(view, parent.getContext(), position -> {
            Finansija finansija = getItem(position);
            onFinansijaClicked.apply(finansija);
            return null;
        });

    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Finansija finansija = getItem(position);
        holder.bind(finansija);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final Context context;
        private Button delete, edit;
        public ViewHolder(@NonNull View itemView, Context context, Function<Integer, Void> onItemClicked) {
            super(itemView);
            this.context = context;
            itemView.setOnClickListener(v -> {
                if(getAdapterPosition() != RecyclerView.NO_POSITION) {
                    onItemClicked.apply(getAdapterPosition());
                }
                itemView.findViewById(getAdapterPosition());
                Intent intent = new Intent(itemView.getContext(), ShowRashodActivty.class);
                Bundle bundle = new Bundle();
                bundle.putString("naslov",((TextView)itemView.findViewById(R.id.finansija_naslov)).getText().toString());
                bundle.putString("kolicina",((TextView)itemView.findViewById(R.id.finansija_kolicina)).getText().toString());
                intent.putExtras(bundle);
                itemView.getContext().startActivity(intent);
            });
        }

        public void bind(Finansija finansija) {
            ImageView imageView = itemView.findViewById(R.id.finansija_slika);
            ((TextView)itemView.findViewById(R.id.finansija_naslov)).setText(finansija.getNaslov());
            ((TextView)itemView.findViewById(R.id.finansija_kolicina)).setText(finansija.getKolicina());
            delete = itemView.findViewById(R.id.finansija_delete);
            edit = itemView.findViewById(R.id.finansija_izmeni);
            initListeners(finansija, itemView);

        }
        public void initListeners(Finansija finansija, View itemView){
            delete.setOnClickListener(v->{
            });
            edit.setOnClickListener(v->{
                Intent intent = new Intent(itemView.getContext(), EditPrihodActivity.class);
                itemView.getContext().startActivity(intent);
            });
        }
    }
}
