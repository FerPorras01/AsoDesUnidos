package proyecto.AsoDesUnidos.Controladores.Admin;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import proyecto.AsoDesUnidos.BD.ConexionBaseDatos;
import proyecto.AsoDesUnidos.DataAccessObjects.AhorroDAO;
import proyecto.AsoDesUnidos.DataAccessObjects.ClienteDAO;
import proyecto.AsoDesUnidos.DataAccessObjects.UsuarioDAO;
import proyecto.AsoDesUnidos.Modelos.Ahorro;
import proyecto.AsoDesUnidos.Modelos.Cliente;
import proyecto.AsoDesUnidos.Modelos.Usuario;
import proyecto.AsoDesUnidos.R;
import proyecto.AsoDesUnidos.Utiles.Utiles;


public class AgregarClienteFragment extends Fragment {

    EditText txtAgrNomUsuario, txtAgrClave, txtAgrConfirmarClave, txtAgrCedula, txtAgrNombre, txtAgrSalario, txtAgrTelefono, txtAgrFecNac, txtMtlAgrDireccion;
    String[] estadosCiviles = {"Soltero(a)", "Casado(a)", "Divorciado(a)", "Viudo(a)", "Union Libre"};
    Spinner spnAgrEstCivil;
    Button btnAgrCliente;

    public AgregarClienteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_admin_agregar_cliente, container, false);

        txtAgrNomUsuario = view.findViewById(R.id.txtBuscCedula);
        txtAgrClave = view.findViewById(R.id.txtAgrClave);
        txtAgrConfirmarClave = view.findViewById(R.id.txtAgrConfirmarClave);
        txtAgrCedula = view.findViewById(R.id.txtAgrCedula);
        txtAgrNombre = view.findViewById(R.id.txtAgrNombre);
        txtAgrSalario = view.findViewById(R.id.txtAgrSalario);
        txtAgrTelefono = view.findViewById(R.id.txtAgrTelefono);
        txtAgrFecNac = view.findViewById(R.id.txtAgrFecNac);
        txtMtlAgrDireccion = view.findViewById(R.id.txtMtlAgrDireccion);
        btnAgrCliente = view.findViewById(R.id.btnAgrCliente);
        spnAgrEstCivil = view.findViewById(R.id.spnAgrEstCivil);
        ArrayAdapter <String> adapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, estadosCiviles);
        spnAgrEstCivil.setAdapter(adapter);

        txtAgrFecNac.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();

            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            @SuppressLint("SetTextI18n") DatePickerDialog datePickerDialog = new DatePickerDialog(
                    view.getContext(),
                    (view1, year1, monthOfYear, dayOfMonth) -> {
                        txtAgrFecNac.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year1);

                    },
                    year, month, day);
            datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
            datePickerDialog.show();
        });

        btnAgrCliente.setOnClickListener(v -> agregarCliente());

        return view;
    }

    private void agregarCliente(){
        if(verificarFormulario()){
            crearAhorros();
        }
    }
    private boolean verificarFormulario(){
        return Utiles.verificarCampo(txtAgrNomUsuario) & Utiles.verificarCampo(txtAgrCedula) &
                Utiles.verificarCampo(txtAgrNombre) & Utiles.mayorA(txtAgrSalario, 0)
                & Utiles.igualA(txtAgrTelefono, 8) & Utiles.verificarCampo(txtAgrFecNac) &
                Utiles.verificarCampo(txtMtlAgrDireccion) & verificarClaves();
    }

    private boolean verificarClaves(){
        if(Utiles.verificarCampo(txtAgrClave) & Utiles.verificarCampo(txtAgrConfirmarClave)) {
            if (!txtAgrClave.getText().toString().equals(txtAgrConfirmarClave.getText().toString())) {
                txtAgrConfirmarClave.setError("Las contraseñas deben coincidir.");
                return false;
            }
            else {
                txtAgrConfirmarClave.setError(null);
                return true;
            }
        }
        return false;
    }

    private long crearUsuario() throws android.database.sqlite.SQLiteConstraintException{
        ConexionBaseDatos db = Room.databaseBuilder(requireActivity().getApplicationContext(),
            ConexionBaseDatos.class, "database-name").allowMainThreadQueries().build();
        Usuario nuevoUsuario = new Usuario(txtAgrNomUsuario.getText().toString(), txtAgrClave.getText().toString(), "cliente");
        UsuarioDAO usuarioDAO = db.usuarioDAO();
        return usuarioDAO.insertUser(nuevoUsuario);
    }
    private long crearCliente(){
        Cliente nuevoCliente;
        long idUsuario;
        ClienteDAO clienteDAO;
        try{
            idUsuario = crearUsuario();
            ConexionBaseDatos db = Room.databaseBuilder(requireActivity().getApplicationContext(),
                    ConexionBaseDatos.class, "database-name").allowMainThreadQueries().build();
            clienteDAO = db.clienteDAO();

            nuevoCliente = new Cliente((int) idUsuario, txtAgrCedula.getText().toString(), txtAgrNombre.getText().toString(), Double.parseDouble(txtAgrSalario.getText().toString()),
                    txtAgrTelefono.getText().toString(), Utiles.parsearFecha(txtAgrFecNac.getText().toString()), spnAgrEstCivil.getSelectedItem().toString(), txtMtlAgrDireccion.getText().toString());
            return clienteDAO.insertarCliente(nuevoCliente);
        }
        catch (android.database.sqlite.SQLiteConstraintException e){
            txtAgrNomUsuario.setError("Ya existe un usuario con este nombre de usuario.");
            return -1;
        }
    }

    private void crearAhorros(){
        ConexionBaseDatos db = Room.databaseBuilder(requireActivity().getApplicationContext(),
                ConexionBaseDatos.class, "database-name").allowMainThreadQueries().build();
        AhorroDAO ahorroDAO = db.ahorroDAO();
        long idCliente = crearCliente();
        Ahorro a1, a2, a3, a4;
        if(idCliente != -1){
            a1 = new Ahorro((int) idCliente, 0.0, "Navideño", 0.0);
            a2 = new Ahorro((int) idCliente, 0.0, "Escolar", 0.0);
            a3 = new Ahorro((int) idCliente, 0.0, "Marchamo", 0.0);
            a4 = new Ahorro((int) idCliente, 0.0, "Extraordinario", 0.0);
            ahorroDAO.insertAll(a1, a2, a3, a4);
            Toast.makeText(requireActivity(), "Cliente agregado.", Toast.LENGTH_SHORT).show();
        }
        else{
            Log.d("Crear Ahorros:", "Ocurrió un error inesperado");
        }
    }
}