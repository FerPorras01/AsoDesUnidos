package proyecto.AsoDesUnidos.Controladores.Admin;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import proyecto.AsoDesUnidos.BD.ConexionBaseDatos;
import proyecto.AsoDesUnidos.DataAccessObjects.ClienteDAO;
import proyecto.AsoDesUnidos.Modelos.Cliente;
import proyecto.AsoDesUnidos.R;
import proyecto.AsoDesUnidos.Utiles.Utiles;


public class AgregarPrestamoFragment extends Fragment {

    EditText txtBuscCedula, txtMontoPrestamo;
    TextView tvMontoMaximo, tvCuota;
    Button btnbuscar, btnAgrPrestamo;
    RadioGroup rdPlazoPrestamo, rdTipoPrestamo;
    Cliente cliente;

    double montoMaximo;

    public AgregarPrestamoFragment() {
        // Required empty public constructor
    }
    public static AgregarPrestamoFragment newInstance() {
        return new AgregarPrestamoFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_agregar_prestamo, container, false);

        txtBuscCedula = view.findViewById(R.id.txtBuscCedula);
        txtMontoPrestamo = view.findViewById(R.id.txtMontoPrestamo);
        tvMontoMaximo = view.findViewById(R.id.tvMontoMaximo);
        tvCuota = view.findViewById(R.id.tvCuota);
        btnbuscar = view.findViewById(R.id.btnBuscar);
        btnAgrPrestamo = view.findViewById(R.id.btnAgrPrestamo);
        rdPlazoPrestamo = view.findViewById(R.id.rdPlazoPrestamo);
        rdTipoPrestamo = view.findViewById(R.id.rdTipoPrestamo);
        cliente = null;

        return view;
    }

    @SuppressLint("SetTextI18n")
    private void habilitarFormulario(){
        montoMaximo = cliente.salario * .45;
        txtMontoPrestamo.setEnabled(true);
        tvMontoMaximo.setText("Monto máximo: " + montoMaximo);
        rdPlazoPrestamo.getChildAt(0).setSelected(true);
        rdTipoPrestamo.getChildAt(0).setSelected(true);
    }

    private void buscarCliente(){
        ConexionBaseDatos db = Room.databaseBuilder(requireActivity().getApplicationContext(),
                ConexionBaseDatos.class, "database-name").allowMainThreadQueries().build();
        ClienteDAO clienteDAO = db.clienteDAO();
        if(Utiles.verificarCampo(txtBuscCedula)){
            cliente = clienteDAO.getClienteByCedula(txtBuscCedula.getText().toString());
            if(cliente != null)
                habilitarFormulario();
            else
                Toast.makeText(requireContext(), "Cliente no encontrado.", Toast.LENGTH_SHORT).show();
        }
    }

    private void calcularCouta(){
        double resultado = Double.parseDouble(txtMontoPrestamo.getText().toString());
         switch (rdPlazoPrestamo.getCheckedRadioButtonId()){
             case R.id.rdBtnTres:

             break;
             case R.id.rdBtnCinco:
                 break;
         }

    }
}