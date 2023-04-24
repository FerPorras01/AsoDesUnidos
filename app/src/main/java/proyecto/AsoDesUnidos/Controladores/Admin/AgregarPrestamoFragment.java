package proyecto.AsoDesUnidos.Controladores.Admin;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
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
    TextView tvMontoMaximo, tvMontoTotal, tvCuota;
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
        tvMontoTotal = view.findViewById(R.id.tvMontoTotal);
        tvCuota = view.findViewById(R.id.tvCuota);
        btnbuscar = view.findViewById(R.id.btnBuscar);
        btnAgrPrestamo = view.findViewById(R.id.btnAgrPrestamo);
        rdPlazoPrestamo = view.findViewById(R.id.rdPlazoPrestamo);
        rdTipoPrestamo = view.findViewById(R.id.rdTipoPrestamo);
        cliente = null;

        btnbuscar.setOnClickListener(v -> buscarCliente());
        txtMontoPrestamo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                    calcularCouta();
            }
        });

        return view;
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

    @SuppressLint("SetTextI18n")
    private void habilitarFormulario(){
        montoMaximo = cliente.salario * .45;
        txtMontoPrestamo.setEnabled(true);
        tvMontoMaximo.setText("Monto máximo: " + montoMaximo);
        tvMontoMaximo.setVisibility(View.VISIBLE);
        tvMontoTotal.setVisibility(View.VISIBLE);
        tvCuota.setVisibility(View.VISIBLE);

        for(int i = 0; i<rdPlazoPrestamo.getChildCount(); i++){
            ((RadioButton)rdPlazoPrestamo.getChildAt(i)).setClickable(true);
        }
        for(int i = 0; i<rdTipoPrestamo.getChildCount(); i++){
            ((RadioButton)rdTipoPrestamo.getChildAt(i)).setClickable(true);
        }

        ((RadioButton)rdPlazoPrestamo.getChildAt(0)).setChecked(true);
        ((RadioButton)rdTipoPrestamo.getChildAt(0)).setChecked(true);
        btnAgrPrestamo.setEnabled(true);

        agregarListeners();
    }

    private void agregarListeners(){
        txtMontoPrestamo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calcularCouta();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });

        rdPlazoPrestamo.setOnCheckedChangeListener((group, checkedId) -> calcularCouta());
        rdTipoPrestamo.setOnCheckedChangeListener((group, checkedId) -> calcularCouta());
    }

    @SuppressLint("NonConstantResourceId")
    private void calcularCouta() {
        if (Utiles.verificarCampo(txtMontoPrestamo)) {
            double resultado = Double.parseDouble(txtMontoPrestamo.getText().toString()), cuota = 0;
            switch (rdTipoPrestamo.getCheckedRadioButtonId()) {
                case R.id.rdBtnHipotecario:
                    resultado *= 1.075;
                    break;
                case R.id.rdBtnEducacion:
                    resultado *= 1.08;
                    break;
                case R.id.rdBtnPersonal:
                    resultado *= 1.1;
                    break;
                case R.id.rdBtnViajes:
                    resultado *= 1.12;
                    break;
                default:
                    resultado = 0;
            }
            switch (rdPlazoPrestamo.getCheckedRadioButtonId()) {
                case R.id.rdBtnTres:
                    cuota = resultado / 36;
                    break;
                case R.id.rdBtnCinco:
                    cuota = resultado / 60;
                    break;
                case R.id.rdBtnDiez:
                    cuota = resultado / 120;
                    break;
                default:
                    cuota = 0;
            }

            tvMontoTotal.setText(String.format("Monto Total: $%.2f", resultado));
            tvCuota.setText(String.format("Cuota: $%.2f", cuota));
        }
    }
}