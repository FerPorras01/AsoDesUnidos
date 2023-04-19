package proyecto.AsoDesUnidos.Controladores.Admin;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import proyecto.AsoDesUnidos.BD.ConexionBaseDatos;
import proyecto.AsoDesUnidos.DataAccessObjects.AhorroDAO;
import proyecto.AsoDesUnidos.DataAccessObjects.ClienteDAO;
import proyecto.AsoDesUnidos.DataAccessObjects.UsuarioDAO;
import proyecto.AsoDesUnidos.Modelos.Ahorro;
import proyecto.AsoDesUnidos.Modelos.Cliente;
import proyecto.AsoDesUnidos.Modelos.Usuario;
import proyecto.AsoDesUnidos.R;
import proyecto.AsoDesUnidos.Utiles.Utiles;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AgregarClienteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AgregarClienteFragment extends Fragment {

    EditText txtAgrNomUsuario, txtAgrClave, txtAgrConfirmarClave, txtAgrCedula, txtAgrNombre, txtAgrSalario, txtAgrTelefono, txtAgrFecNac, txtMtlAgrDireccion;
    String[] estadosCiviles = {"Soltero(a)", "Casado(a)", "Divorciado(a)", "Viudo(a)", "Union Libre"};
    Spinner spnAgrEstCivil;
    Button btnAgrCliente;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public AgregarClienteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AgregarClienteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AgregarClienteFragment newInstance(String param1, String param2) {
        AgregarClienteFragment fragment = new AgregarClienteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // TODO: Rename and change types of parameters
            String mParam1 = getArguments().getString(ARG_PARAM1);
            String mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_admin_agregar_cliente, container, false);

        txtAgrNomUsuario = view.findViewById(R.id.txtAgrNomUsuario);
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
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, estadosCiviles);
        spnAgrEstCivil.setAdapter(adapter);

        txtAgrFecNac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are getting
                // the instance of our calendar.
                final Calendar c = Calendar.getInstance();

                // on below line we are getting
                // our day, month and year.
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                // on below line we are creating a variable for date picker dialog.
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        // on below line we are passing context.
                        view.getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // on below line we are setting date to our edit text.
                                txtAgrFecNac.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        },
                        // on below line we are passing year,
                        // month and day for selected date in our date picker.
                        year, month, day);
                datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
                // at last we are calling show to
                // display our date picker dialog.
                datePickerDialog.show();
            }
        });

        btnAgrCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarCliente();
            }
        });

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
        try{
            ConexionBaseDatos db = Room.databaseBuilder(requireActivity().getApplicationContext(),
                ConexionBaseDatos.class, "database-name").allowMainThreadQueries().build();
            Usuario nuevoUsuario = new Usuario(txtAgrNomUsuario.getText().toString(), txtAgrClave.getText().toString(), "cliente");
            UsuarioDAO usuarioDAO = db.usuarioDAO();
            return usuarioDAO.insertUser(nuevoUsuario);
        } catch (android.database.sqlite.SQLiteConstraintException e){
            throw e;
        }
    }
    private long crearCliente(){
        Cliente nuevoCliente;
        Long idUsuario = null;
        String spinner = spnAgrEstCivil.getSelectedItem().toString();
        ClienteDAO clienteDAO;
        try{
            idUsuario = crearUsuario();
            ConexionBaseDatos db = Room.databaseBuilder(requireActivity().getApplicationContext(),
                    ConexionBaseDatos.class, "database-name").allowMainThreadQueries().build();
            clienteDAO = db.clienteDAO();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
            nuevoCliente = new Cliente(idUsuario.intValue(), txtAgrCedula.getText().toString(), txtAgrNombre.getText().toString(), Double.parseDouble(txtAgrSalario.getText().toString()),
                    txtAgrTelefono.getText().toString(), LocalDate.parse(txtAgrFecNac.getText().toString(), formatter), spnAgrEstCivil.getSelectedItem().toString(), txtMtlAgrDireccion.getText().toString());
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
        Long idCliente = crearCliente();
        Ahorro a1, a2, a3, a4;
        if(idCliente != -1){
            a1 = new Ahorro(idCliente.intValue(), 0.0, "Navideño", 0.0);
            a2 = new Ahorro(idCliente.intValue(), 0.0, "Escolar", 0.0);
            a3 = new Ahorro(idCliente.intValue(), 0.0, "Marchamo", 0.0);
            a4 = new Ahorro(idCliente.intValue(), 0.0, "Extraordinario", 0.0);
            ahorroDAO.insertAll(a1, a2, a3, a4);
            Toast.makeText(requireActivity(), "Cliente agregado.", Toast.LENGTH_SHORT).show();
        }
        else{
            Log.d("Crear Ahorros:", "Ocurrió un error inesperado");
        }
    }
}