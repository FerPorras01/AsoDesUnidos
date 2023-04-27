package com.asoDesUnidos.controladores.admin;


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

import java.util.Calendar;
import java.util.Date;

import com.asoDesUnidos.bd.ConexionBaseDatos;
import com.asoDesUnidos.dataAccessObjects.AhorroDAO;
import com.asoDesUnidos.dataAccessObjects.ClienteDAO;
import com.asoDesUnidos.dataAccessObjects.UsuarioDAO;
import com.asoDesUnidos.modelos.Ahorro;
import com.asoDesUnidos.modelos.Cliente;
import com.asoDesUnidos.modelos.Usuario;
import com.asoDesUnidos.R;
import com.asoDesUnidos.utiles.Utiles;


public class AgregarClienteFragment extends Fragment {

    EditText txtAgrNomUsuario, txtAgrClave, txtAgrConfirmarClave, txtAgrCedula, txtAgrNombre, txtAgrSalario, txtAgrTelefono, txtAgrFecNac, txtMtlAgrDireccion;
    final String[] estadosCiviles = {"Soltero(a)", "Casado(a)", "Divorciado(a)", "Viudo(a)", "Union Libre"};
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

            int annio = c.get(Calendar.YEAR);
            int mes = c.get(Calendar.MONTH);
            int dia = c.get(Calendar.DAY_OF_MONTH);

             DatePickerDialog datePickerDialog = new DatePickerDialog(
                    view.getContext(),
                    (view1, annio1, mesDelAnnio, diaDelMes) -> txtAgrFecNac.setText(getString(R.string.fechaNacimientoFlex, diaDelMes, mesDelAnnio+1, annio1)),
                     annio, mes, dia);
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
        return verificarNombreDeUsuario() & verificarCedula() &
                Utiles.verificarCampo(txtAgrNombre) & Utiles.mayorA(txtAgrSalario, 0)
                & Utiles.igualA(txtAgrTelefono, 8) & Utiles.verificarCampo(txtAgrFecNac) &
                Utiles.verificarCampo(txtMtlAgrDireccion) & verificarClaves();
    }

    private boolean verificarCedula(){
        ConexionBaseDatos db = Room.databaseBuilder(requireActivity().getApplicationContext(),
                ConexionBaseDatos.class, "database-name").allowMainThreadQueries().build();
        ClienteDAO clienteDAO = db.clienteDAO();
        if(Utiles.igualA(txtAgrCedula, 9)) {
            Cliente cliente = clienteDAO.getClienteByCedula(txtAgrCedula.getText().toString());
            if (cliente != null) {
                txtAgrCedula.setError("Ya existe un cliente con este número de cédula.");
                return false;
            }
            txtAgrCedula.setError(null);
            return true;
        }
        return false;
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

    private boolean verificarNombreDeUsuario(){
        ConexionBaseDatos db = Room.databaseBuilder(requireActivity().getApplicationContext(),
                ConexionBaseDatos.class, "database-name").allowMainThreadQueries().build();
        UsuarioDAO usuarioDAO = db.usuarioDAO();
        if(Utiles.verificarCampo(txtAgrNomUsuario)) {
            Usuario usuario = usuarioDAO.findByUsername(txtAgrNomUsuario.getText().toString());
            if(usuario != null){
                txtAgrNomUsuario.setError("Ya existe un usuario con este nombre de usuario.");
                return false;
            }
            txtAgrNomUsuario.setError(null);
            return true;
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
        idUsuario = crearUsuario();
        ConexionBaseDatos db = Room.databaseBuilder(requireActivity().getApplicationContext(),
                ConexionBaseDatos.class, "database-name").allowMainThreadQueries().build();
        clienteDAO = db.clienteDAO();

        nuevoCliente = new Cliente((int) idUsuario, txtAgrCedula.getText().toString(), txtAgrNombre.getText().toString(), Double.parseDouble(txtAgrSalario.getText().toString()),
                txtAgrTelefono.getText().toString(), Utiles.parsearFecha(txtAgrFecNac.getText().toString()), spnAgrEstCivil.getSelectedItem().toString(), txtMtlAgrDireccion.getText().toString());
        return clienteDAO.insertarCliente(nuevoCliente);
    }

    private void crearAhorros(){
        ConexionBaseDatos db = Room.databaseBuilder(requireActivity().getApplicationContext(),
                ConexionBaseDatos.class, "database-name").allowMainThreadQueries().build();
        AhorroDAO ahorroDAO = db.ahorroDAO();
        long idCliente = crearCliente();
        Ahorro a1, a2, a3, a4;
        if(idCliente != -1){
            a1 = new Ahorro((int) idCliente, 0.0, "Navideño", 0.0, false);
            a2 = new Ahorro((int) idCliente, 0.0, "Escolar", 0.0, false);
            a3 = new Ahorro((int) idCliente, 0.0, "Marchamo", 0.0, false);
            a4 = new Ahorro((int) idCliente, 0.0, "Extraordinario", 0.0, false);
            ahorroDAO.insertAll(a1, a2, a3, a4);
            Toast.makeText(requireActivity(), "Cliente agregado.", Toast.LENGTH_SHORT).show();
        }
        else{
            Log.d("Crear Ahorros:", "Ocurrió un error inesperado");
        }
    }
}