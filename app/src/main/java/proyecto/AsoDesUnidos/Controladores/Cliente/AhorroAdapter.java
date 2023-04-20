package proyecto.AsoDesUnidos.Controladores.Cliente;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import proyecto.AsoDesUnidos.Modelos.Ahorro;
import proyecto.AsoDesUnidos.Modelos.Prestamo;
import proyecto.AsoDesUnidos.R;

public class AhorroAdapter extends RecyclerView.Adapter<AhorroAdapter.AhorrosViewHolder> {
    private List<Ahorro> ahorros;
    private Context context;
    private AlertDialog alertDialog;

    public AhorroAdapter(Context context, List<Ahorro> ahorros) {
        this.context = context;
        this.ahorros = ahorros;
    }
    @NonNull
    @Override
    public AhorrosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_prestamo, parent, false);
        return new AhorrosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AhorrosViewHolder holder, int position) {
        Ahorro ahorro = ahorros.get(position);
        holder.txtCuota.setText(String.valueOf(ahorro.getCuota()));
        holder.txtTipoA.setText(String.valueOf(ahorro.getTipo()));
        holder.txtTotalAhorradoA.setText(String.valueOf(ahorro.getTotalAhorrado()));
        holder.switchEstado.setText(String.valueOf(ahorro.getEstado()));

        LayoutInflater inflater = LayoutInflater.from(context);
    }


    @Override
    public int getItemCount() {
        return 0;
    }
    public static class AhorrosViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTotalAhorradoA;
        public TextView txtCuota;
        public Switch switchEstado;
        public TextView txtTipoA;



        public AhorrosViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTotalAhorradoA = itemView.findViewById(R.id.txtTotalAhorradoA);
            txtCuota= itemView.findViewById(R.id.txtCuota);
            txtTipoA = itemView.findViewById(R.id.txtTipoA);
            switchEstado= itemView.findViewById(R.id.switchEstado);

        }
    }
}
