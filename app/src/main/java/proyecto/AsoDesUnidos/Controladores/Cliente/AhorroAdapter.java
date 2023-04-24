package proyecto.AsoDesUnidos.Controladores.Cliente;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import java.util.List;
import proyecto.AsoDesUnidos.BD.ConexionBaseDatos;
import proyecto.AsoDesUnidos.Modelos.Ahorro;
import proyecto.AsoDesUnidos.R;

public class AhorroAdapter extends RecyclerView.Adapter<AhorroAdapter.AhorroViewHolder> {
    private List<Ahorro> ahorros;
    private Context context;
    //private AlertDialog alertDialog;

    public AhorroAdapter(Context context, List<Ahorro> ahorros) {
        this.context = context;
        this.ahorros = ahorros;
    }
    @NonNull
    @Override
    public AhorroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ahorro, parent, false);
        return new AhorroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AhorroViewHolder holder, int position) {
        Ahorro ahorro = ahorros.get(position);
        holder.txtCuota.setText(String.valueOf(ahorro.getCuota()));
        holder.txtTipoA.setText(String.valueOf(ahorro.getTipo()));
        holder.txtTotalAhorradoA.setText(String.valueOf(ahorro.getTotalAhorrado()));
        holder.switchEstado.setChecked(ahorro.isActivo());

        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.cuota_ahorro, null);
        EditText editTextCuota = dialogView.findViewById(R.id.editTextCuota);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(dialogView);
        alertDialogBuilder.setTitle("Cuota mensual del ahorro");
        alertDialogBuilder.setMessage("¿Desea digitar el monto de la cuota mensual para el ahorro" + ahorro.getId() + "?");
        alertDialogBuilder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String cuotaString = editTextCuota.getText().toString();
                double cuota = Double.parseDouble(cuotaString);
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
            }
        });
        alertDialogBuilder.setNegativeButton("Cancelar", null);
        AlertDialog alertDialog = alertDialogBuilder.create();

        holder.switchEstado.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    alertDialog.show();
                } else {
                    ahorro.setEstado(false);
                    ahorro.setCuota(0.0);
                    ConexionBaseDatos db = Room.databaseBuilder(context.getApplicationContext(),
                            ConexionBaseDatos.class, "database-name").allowMainThreadQueries().build();
                    db.ahorroDAO().updateAhorros(ahorro);
                    notifyDataSetChanged();
                }
            }
        });
    }




    @Override
    public int getItemCount() {

        return ahorros.size();
    }
    public static class AhorroViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTotalAhorradoA;
        public TextView txtCuota;
        public Switch switchEstado;
        public TextView txtTipoA;



        public AhorroViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTotalAhorradoA = itemView.findViewById(R.id.txtTotalAhorradoA);
            txtCuota= itemView.findViewById(R.id.txtCuota);
            txtTipoA = itemView.findViewById(R.id.txtTipoA);
            switchEstado= itemView.findViewById(R.id.switchEstado);

        }
    }
}
