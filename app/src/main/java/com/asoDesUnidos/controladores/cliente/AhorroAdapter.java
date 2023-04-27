package com.asoDesUnidos.controladores.cliente;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import java.util.List;
import com.asoDesUnidos.bd.ConexionBaseDatos;
import com.asoDesUnidos.modelos.Ahorro;
import com.asoDesUnidos.R;
import com.asoDesUnidos.utiles.Utiles;

public class AhorroAdapter extends RecyclerView.Adapter<AhorroAdapter.AhorroViewHolder> {
    private final List<Ahorro> ahorros;
    private final Context context;
    private double totalCuotas = 0;
    private final double salario;


    public AhorroAdapter(Context context, List<Ahorro> ahorros, double salario) {
        this.context = context;
        this.ahorros = ahorros;
        for(Ahorro ahorro: ahorros)
            this.totalCuotas+=ahorro.getCuota();
        this.salario = salario;
    }
    @NonNull
    @Override
    public AhorroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ahorro, parent, false);
        return new AhorroViewHolder(view);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull AhorroViewHolder holder, int position) {
        Ahorro ahorro = ahorros.get(position);
        holder.txtCuota.setText(context.getString(R.string.cuotaFlex, ahorro.getCuota()));
        holder.txtTipoA.setText(context.getString(R.string.tipoFlex, ahorro.getTipo()));
        holder.txtTotalAhorradoA.setText(context.getString(R.string.montoTotalFlex,ahorro.getTotalAhorrado()));
        holder.switchEstado.setChecked(ahorro.getEstado());
        if(!holder.switchEstado.isChecked() && (salario - totalCuotas) == 0){
            holder.switchEstado.setClickable(false);
            Toast.makeText(context, "No se pueden activar más ahorros. Las cuotas no deben superar su salario.", Toast.LENGTH_SHORT).show();
        }
        else
            holder.switchEstado.setClickable(true);

        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.cuota_ahorro, null);
        EditText editTextCuota = dialogView.findViewById(R.id.editTextCuota);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(dialogView);
        alertDialogBuilder.setTitle("Cuota mensual del ahorro");
        alertDialogBuilder.setMessage("¿Desea digitar el monto de la cuota mensual para el ahorro " + ahorro.getTipo() + "?");
        alertDialogBuilder.setPositiveButton("Aceptar", null);
        alertDialogBuilder.setNegativeButton("Cancelar", (dialog, which) -> holder.switchEstado.setChecked(ahorro.getEstado()));
        AlertDialog alertDialog = alertDialogBuilder.create();

        holder.switchEstado.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                alertDialog.show();
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {
                    boolean cerrar = Utiles.menorA(editTextCuota, salario - totalCuotas) & Utiles.mayorOIgualA(editTextCuota, 5000);
                    if(cerrar) {
                        String cuotaString = editTextCuota.getText().toString();
                        double cuota = Double.parseDouble(cuotaString);
                        totalCuotas += cuota;
                        ahorro.setCuota(cuota);
                        ahorro.setEstado(true);
                        ConexionBaseDatos db = Room.databaseBuilder(context.getApplicationContext(),
                                ConexionBaseDatos.class, "database-name").allowMainThreadQueries().build();
                        double totalAhorrado = db.ahorroDAO().getAhorroById(ahorro.getId()).getTotalAhorrado();
                        totalAhorrado += cuota;
                        ahorro.setTotalAhorrado(totalAhorrado);
                        db.ahorroDAO().updateAhorroSaldo(totalAhorrado, ahorro.getId());
                        db.ahorroDAO().updateAhorros(ahorro);
                        notifyDataSetChanged();
                        alertDialog.dismiss();
                    }
                });
            } else {
                totalCuotas -= ahorro.getCuota();
                ahorro.setEstado(false);
                ahorro.setCuota(0.0);
                ConexionBaseDatos db = Room.databaseBuilder(context.getApplicationContext(),
                        ConexionBaseDatos.class, "database-name").allowMainThreadQueries().build();
                db.ahorroDAO().updateAhorros(ahorro);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {

        return ahorros.size();
    }

    private void actualizarAhorro(){

    }

    public static class AhorroViewHolder extends RecyclerView.ViewHolder {
        public final TextView txtTotalAhorradoA;
        public final TextView txtCuota;
        @SuppressLint("UseSwitchCompatOrMaterialCode")
        public final Switch switchEstado;
        public final TextView txtTipoA;

        public AhorroViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTotalAhorradoA = itemView.findViewById(R.id.txtTotalAhorradoA);
            txtCuota= itemView.findViewById(R.id.txtCuota);
            txtTipoA = itemView.findViewById(R.id.txtTipoA);
            switchEstado= itemView.findViewById(R.id.switchEstado);

        }
    }
}
