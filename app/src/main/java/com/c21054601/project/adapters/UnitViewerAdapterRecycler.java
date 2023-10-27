package com.c21054601.project.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.c21054601.project.DAOs.WeaponDAO;
import com.c21054601.project.R;
import com.c21054601.project.databinding.FragmentUnitViewerAdapterBinding;

import java.util.ArrayList;
import java.util.Locale;

public class UnitViewerAdapterRecycler extends RecyclerView.Adapter<UnitViewerAdapterRecycler.WeaponViewHolder>{

    private final Context context;
    private final ArrayList<WeaponDAO> values;

    public UnitViewerAdapterRecycler(@NonNull Context context, ArrayList<WeaponDAO> values) {
        this.context = context;
        this.values = values;
    }

    @NonNull
    @Override
    public UnitViewerAdapterRecycler.WeaponViewHolder  onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fragment_unit_viewer_adapter, parent, false);
        WeaponViewHolder vh = new WeaponViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull UnitViewerAdapterRecycler.WeaponViewHolder holder, int position) {
        try{
            WeaponViewHolder vh = (WeaponViewHolder) holder;
            vh.bindView(position);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (values == null){
            return 0;
        }else{
            return values.size();
        }
    }

    public class WeaponViewHolder extends RecyclerView.ViewHolder {
        private TextView weaponName;
        private TextView weaponTrait1;
        private TextView weaponTrait2;
        private TextView weaponTrait3;
        private TextView weaponPen;
        private TextView weaponSuppress;
        private TextView weaponDamageHe;
        private TextView weaponGroundRange;
        private TextView weaponHelicopterRange;
        private TextView weaponPlaneRange;
        private TextView weaponStaticAccuracy;
        private TextView weaponMovingAccuracy;
        private TextView weaponAimingTime;
        private TextView weaponReloadTime;
        private TextView weaponSalvoLength;
        private TextView weaponSupplyCost;

        public WeaponViewHolder(@NonNull View itemView) {
            super(itemView);
            // TODO: This is probably bad, should use the binding
            weaponName = itemView.findViewById(R.id.unitViewerFragmentWeaponName);
            weaponTrait1 = itemView.findViewById(R.id.unitViewerFragmentWeaponTraits1);
            weaponTrait2 = itemView.findViewById(R.id.unitViewerFragmentWeaponTraits2);
            weaponTrait3 = itemView.findViewById(R.id.unitViewerFragmentWeaponTraits3);
            weaponPen = itemView.findViewById(R.id.unitViewerFragmentWeaponDamagePenetrationTextView);
            weaponSuppress = itemView.findViewById(R.id.unitViewerFragmentWeaponDamageSuppressTextView);
            weaponDamageHe = itemView.findViewById(R.id.unitViewerFragmentWeaponDamageHETextView);
            weaponGroundRange = itemView.findViewById(R.id.unitViewerFragmentRangeGroundTextView);
            weaponHelicopterRange = itemView.findViewById(R.id.unitViewerFragmentRangeHelicopterTextView);
            weaponPlaneRange = itemView.findViewById(R.id.unitViewerFragmentRangePlaneTextView);
            weaponStaticAccuracy = itemView.findViewById(R.id.unitViewerFragmentWeaponStaticAccuracyTextView);
            weaponMovingAccuracy = itemView.findViewById(R.id.unitViewerFragmentWeaponMovingAccuracyTextView);
            weaponAimingTime = itemView.findViewById(R.id.unitViewerFragmentWeaponAimingTimeTextView);
            weaponReloadTime = itemView.findViewById(R.id.unitViewerFragmentWeaponReloadTimeTextView);
            weaponSalvoLength = itemView.findViewById(R.id.unitViewerFragmentWeaponSalvoLengthTextView);
            weaponSupplyCost = itemView.findViewById(R.id.unitViewerFragmentWeaponSupplyCostTextView);
        }

        public void bindView(int position) {
            String weaponString = values.get(position).getWeaponName();
            weaponName.setText(weaponString);

            ArrayList<String> weaponTraits = values.get(position).getWeaponTraits();
            int weaponArraySize = weaponTraits.size();

            String weaponTraitString1 = weaponTraits.get(0);
            weaponTrait1.setText(String.format(Locale.getDefault(), "%s", weaponTraitString1));

            if (weaponArraySize >= 2){
                String weaponTraitString2 = weaponTraits.get(1);
                weaponTrait2.setText(String.format(Locale.getDefault(), "%s", weaponTraitString2));
            } else{
                weaponTrait2.setVisibility(View.INVISIBLE);
            }

            if (weaponArraySize >= 3){
                String weaponTraitString3 = weaponTraits.get(2);
                weaponTrait3.setText(String.format(Locale.getDefault(), "%s", weaponTraitString3));
            } else{
                weaponTrait3.setVisibility(View.INVISIBLE);
            }

            weaponPen.setText(String.format(Locale.getDefault(), "%d", values.get(position).getPenetration()));
            weaponSuppress.setText(String.format(Locale.getDefault(), "%d", values.get(position).getSuppress()));
            weaponDamageHe.setText(String.format(Locale.getDefault(), "%.1f", values.get(position).getHeDamage()));
            weaponGroundRange.setText(String.format(Locale.getDefault(), "%d", values.get(position).getGroundRange()));
            weaponHelicopterRange.setText(String.format(Locale.getDefault(), "%d", values.get(position).getHelicopterRange()));
            weaponPlaneRange.setText(String.format(Locale.getDefault(), "%d", values.get(position).getPlaneRange()));
            // TODO: Fix this, not good chief
            weaponStaticAccuracy.setText(String.format(Locale.getDefault(), "%d%%", values.get(position).getAccuracy()));
            weaponMovingAccuracy.setText(String.format(Locale.getDefault(), "%d%%", values.get(position).getMovingAccuracy()));
            weaponAimingTime.setText(String.format(Locale.getDefault(), "%.1f", values.get(position).getAimingTime()));
            weaponReloadTime.setText(String.format(Locale.getDefault(), "%.1f", values.get(position).getReloadTime()));
            weaponSalvoLength.setText(String.format(Locale.getDefault(), "%d", values.get(position).getSalvoLength()));
            weaponSupplyCost.setText(String.format(Locale.getDefault(), "%d", values.get(position).getSupplyCost()));
        }
    }
}
