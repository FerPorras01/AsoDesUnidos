package proyecto.AsoDesUnidos.Controladores.Cliente;

import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import proyecto.AsoDesUnidos.Modelos.Prestamo;
import proyecto.AsoDesUnidos.R;

public class PrestamoAdapter extends RecyclerView.Adapter<PrestamoAdapter.PrestamoViewHolder> {

    private List<Prestamo> prestamos;
    private Context context;
    private AlertDialog alertDialog;

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

    @Override
    public void onBindViewHolder(@NonNull PrestamoViewHolder holder, int position) {
        Prestamo prestamo = prestamos.get(position);
        holder.txtMonto.setText(String.valueOf(prestamo.getMonto()));
        holder.txtInteres.setText(String.valueOf(prestamo.getInteres()));
        holder.txtTipo.setText(prestamo.getTipo());
        holder.txtMontoTotal.setText(String.valueOf(prestamo.getMonto() + prestamo.getInteres()));

        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.pagar_prestamo, null);
        EditText editTextNMonto = dialogView.findViewById(R.id.editTextNMonto);
        Button btnPagoPrestamo = dialogView.findViewById(R.id.btnPagoPrestamo);
        holder.btnPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setView(dialogView);
                alertDialogBuilder.setTitle("Pagar préstamo");
                alertDialogBuilder.setMessage("¿Desea pagar el préstamo " + prestamo.getId() + " ?");
                alertDialogBuilder.setPositiveButton("Pagar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Aquí puedes implementar la lógica para pagar el préstamo
                        String montoStr = editTextNMonto.getText().toString();
                        double monto = Double.parseDouble(montoStr);
                        // ...
                    }
                });
                alertDialogBuilder.setNegativeButton("Cancelar", null);
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return prestamos.size();
    }

    public static class PrestamoViewHolder extends RecyclerView.ViewHolder {
        public TextView txtMontoTotal;
        public TextView txtMonto;
        public TextView txtInteres;
        public TextView txtTipo;
        public Button btnPagar;


        public PrestamoViewHolder(View itemView) {
            super(itemView);
            txtMonto = itemView.findViewById(R.id.txtMonto);
            txtMontoTotal = itemView.findViewById(R.id.txtMontoTotal);
            txtInteres = itemView.findViewById(R.id.txtInteres);
            txtTipo = itemView.findViewById(R.id.txtTipo);
            btnPagar = itemView.findViewById(R.id.btnPagar);
        }
    }
}