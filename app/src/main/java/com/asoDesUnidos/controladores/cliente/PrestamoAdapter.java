package com.asoDesUnidos.controladores.cliente;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import java.util.List;

import com.asoDesUnidos.bd.ConexionBaseDatos;
import com.asoDesUnidos.dataAccessObjects.PrestamoDAO;
import com.asoDesUnidos.modelos.Prestamo;
import com.asoDesUnidos.R;

public class PrestamoAdapter extends RecyclerView.Adapter<PrestamoAdapter.PrestamoViewHolder> {

    private final List<Prestamo> prestamos;
    private final Context context;


    public PrestamoAdapter(Context context, List<Prestamo> prestamos) {
        this.context = context;
        this.prestamos = prestamos;
    }
    @NonNull
    @Override
    public PrestamoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_prestamo, parent, false);
        return new PrestamoViewHolder(view);
    }
    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull PrestamoViewHolder holder, int position) {
        Prestamo prestamo = prestamos.get(position);
        holder.txtMonto.setText(context.getString(R.string.montoFlex, prestamo.getMonto()));
        holder.txtInteres.setText(context.getString(R.string.interesFlex, prestamo.getInteres()));
        holder.txtTipo.setText(context.getString(R.string.tipoFlex, prestamo.getTipo()));
        holder.txtMontoTotal.setText(context.getString(R.string.montoTotalFlex, prestamo.getMontoTotal()));
        holder.txtPeriodo.setText(context.getString(R.string.periodoFlex, prestamo.getPeriodo()));

        holder.btnPagar.setOnClickListener(v -> pagarPrestamo(prestamo, holder));
        if(prestamo.getMontoTotal() == 0.0)
            holder.btnPagar.setEnabled(false);
    }

    private void pagarPrestamo(Prestamo prestamo, PrestamoViewHolder holder){
        double resultado = prestamo.getMontoTotal() - prestamo.getMonto();
        ConexionBaseDatos db = Room.databaseBuilder(context,
                ConexionBaseDatos.class, "database-name").allowMainThreadQueries().build();
        PrestamoDAO prestamoDAO = db.prestamoDAO();

        if(prestamo.getMontoTotal() > 0){
            if(resultado < 0)
                resultado = 0;
            prestamo.setMontoTotal(resultado);
            prestamoDAO.updatePrestamos(prestamo);
            holder.txtMontoTotal.setText(context.getString(R.string.montoTotalFlex, prestamo.getMontoTotal()));
            if(resultado == 0){
                holder.btnPagar.setEnabled(false);
                Toast.makeText(context, "Pago ejecutado! Se ha completado el pago del préstamo.", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(context, "Pago ejecutado!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return prestamos.size();
    }

    public static class PrestamoViewHolder extends RecyclerView.ViewHolder {
        public final TextView txtMontoTotal;
        public final TextView txtMonto;
        public final TextView txtInteres;
        public final TextView txtTipo;
        public final Button btnPagar;
        public final TextView txtPeriodo;

        public PrestamoViewHolder(View itemView) {
            super(itemView);
            txtMonto = itemView.findViewById(R.id.txtMonto);
            txtMontoTotal = itemView.findViewById(R.id.txtMontoTotal);
            txtInteres = itemView.findViewById(R.id.txtInteres);
            txtTipo = itemView.findViewById(R.id.txtTipo);
            txtPeriodo= itemView.findViewById(R.id.txtPeriodo);
            btnPagar = itemView.findViewById(R.id.btnPagar);
        }
    }
}