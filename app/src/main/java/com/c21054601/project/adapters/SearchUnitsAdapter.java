package com.c21054601.project.adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.c21054601.project.DAOs.GameUnitDAO;
import com.c21054601.project.R;
import com.c21054601.project.fragments.SearchUnits;
import com.c21054601.project.fragments.UnitViewer;
import com.c21054601.project.databinding.FragmentSearchUnitsAdapterBinding;

import java.util.ArrayList;

public class SearchUnitsAdapter extends RecyclerView.Adapter<SearchUnitsAdapter.GameUnitViewHolder>{

    private final Context context;
    private final ArrayList<GameUnitDAO> values;
    private FragmentSearchUnitsAdapterBinding searchUnitsAdapterBinding;

    public SearchUnitsAdapter(@NonNull Context context, ArrayList<GameUnitDAO> values) {
        this.context = context;
        this.values = values;
    }

    @NonNull
    @Override
    public SearchUnitsAdapter.GameUnitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fragment_search_units_adapter, parent, false);
        GameUnitViewHolder vh = new GameUnitViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchUnitsAdapter.GameUnitViewHolder holder, int position){
        try{
            GameUnitViewHolder vh = (GameUnitViewHolder) holder;
            vh.bindView(position);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount(){
        if (values == null){
            return 0;
        }else{
            return values.size();
        }
    }

    public class GameUnitViewHolder extends RecyclerView.ViewHolder {
        private TextView unitLabel;
        private ImageView icon;
        private View unitViewerAdapter;

        public GameUnitViewHolder(@NonNull View itemView) {
            super(itemView);
            unitLabel = itemView.findViewById(R.id.unitLabel);
            icon = itemView.findViewById(R.id.icon);
            unitViewerAdapter = itemView.findViewById(R.id.unitViewerAdapter);
        }

        public void bindView(int position) {
            String valtext = values.get(position).getGameUnitName();
            unitLabel.setText(valtext);
            unitViewerAdapter.setTag(values.get(position));

            Glide.with(context)
                    .load(values.get(position).getGameUnitImageReference())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(icon);

            unitViewerAdapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GameUnitDAO tag = (GameUnitDAO) v.getTag();
                    UnitViewer targetFragment = new UnitViewer(tag);
                    SearchUnits searchUnits = SearchUnits.searchUnitsContext;
                    searchUnits.changeInternalFragment(targetFragment);
                }
            });
        }
    }

}