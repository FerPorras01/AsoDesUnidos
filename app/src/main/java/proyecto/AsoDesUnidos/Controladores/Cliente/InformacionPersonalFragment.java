package proyecto.AsoDesUnidos.Controladores.Cliente;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import proyecto.AsoDesUnidos.BD.ConexionBaseDatos;
import proyecto.AsoDesUnidos.DataAccessObjects.ClienteDAO;
import proyecto.AsoDesUnidos.Modelos.Cliente;
import proyecto.AsoDesUnidos.R;
import proyecto.AsoDesUnidos.Utiles.Utiles;


/*


public class InformacionPersonalFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText txtNombreCliente, txtNumberSalario, txtTelefono, txtFecha, txtMlDireccion;
    String[] estadoCivil = {"Soltero(a)", "Casado(a)", "Divorciado(a)", "Viudo(a)", "Union Libre"};
    Spinner spinnerEstadoCivil;
    Button btnEditar;
    Cliente cliente;
    ConexionBaseDatos db;
    ClienteDAO clienteDAO;

    public static InformacionPersonalFragment newInstance(String param1, String param2) {
        InformacionPersonalFragment fragment = new InformacionPersonalFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public InformacionPersonalFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        db = Room.databaseBuilder(getContext(),
                ConexionBaseDatos.class, "database-name").allowMainThreadQueries().build();
        int idCliente = getArguments().getInt(ClientActivity.idCliente);
        clienteDAO = db.clienteDAO();
        cliente = clienteDAO.getClienteById(idCliente);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_informacion_personal, container, false);

        txtNombreCliente = view.findViewById(R.id.txtNombreCliente);
        txtNumberSalario = view.findViewById(R.id.txtNumberSalario);
        txtTelefono = view.findViewById(R.id.txtTelefono);
        txtFecha = view.findViewById(R.id.txtFecha);
        txtMlDireccion = view.findViewById(R.id.TxtMlDireccion);
        btnEditar = view.findViewById(R.id.btnEditar);
        spinnerEstadoCivil = view.findViewById(R.id.spinnerEstadoCivil);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, estadoCivil);
        spinnerEstadoCivil.setAdapter(adapter);

        txtFecha.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);


            @SuppressLint("SetTextI18n") DatePickerDialog datePickerDialog = new DatePickerDialog(
                    view.getContext(),
                    (view1, year1, monthOfYear, dayOfMonth) -> {
                        txtFecha.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year1);
                    },
                    year, month, day);
            datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
            datePickerDialog.show();
        });
        mostrarDatos();
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreStr = txtNombreCliente.getText().toString();
                String nombre = nombreStr;
                cliente.setNombre(nombre);
                txtNombreCliente.setText(nombre);
                clienteDAO.updateClients();
                cliente.setNombre(txtNombreCliente.getText().toString());
                cliente.setSalario(Double.parseDouble(txtNumberSalario.getText().toString()));
                cliente.setTelefono(txtTelefono.getText().toString());
                cliente.setFecNac(LocalDate.parse(txtFecha.getText().toString()));
                cliente.setDireccion(txtMlDireccion.getText().toString());
                clienteDAO.updateClients(cliente);
                txtNombreCliente.setText(cliente.getNombre());
            }
        });
        return view;
    }
    public void mostrarDatos(){
        //int idCliente = getArguments().getInt(ClientActivity.idCliente);
        txtNombreCliente= txtNombreCliente.findViewById(R.id.txtNombreCliente);
        txtNombreCliente.setText(cliente.getNombre());
        txtNumberSalario =txtNumberSalario.findViewById(R.id.txtNumberSalario);
        txtNumberSalario.setText(String.valueOf(cliente.getSalario()));
        txtTelefono = txtTelefono.findViewById(R.id.txtTelefono);
        txtTelefono.setText(String.valueOf(cliente.getTelefono()));
        txtFecha= txtFecha.findViewById(R.id.txtFecha);
        txtFecha.setText(String.valueOf(cliente.getFecNac()));
        txtMlDireccion = txtMlDireccion.findViewById(R.id.TxtMlDireccion);
        txtMlDireccion.setText(String.valueOf(cliente.getDireccion()));
    }
}
*/
public class InformacionPersonalFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText txtNombreCliente, txtNumberSalario, txtTelefono, txtFecha, txtMlDireccion;
    String[] estadoCivil = {"Soltero(a)", "Casado(a)", "Divorciado(a)", "Viudo(a)", "Union Libre"};
    Spinner spinnerEstadoCivil;
    Button btnEditar;
    Cliente cliente;
    ConexionBaseDatos db;
    ClienteDAO clienteDAO;

    public static InformacionPersonalFragment newInstance(String param1, String param2) {
        InformacionPersonalFragment fragment = new InformacionPersonalFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public InformacionPersonalFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        db = Room.databaseBuilder(getContext(),
                ConexionBaseDatos.class, "database-name").allowMainThreadQueries().build();
        int idCliente = getArguments().getInt(ClientActivity.idCliente);
        clienteDAO = db.clienteDAO();
        cliente = clienteDAO.getClienteById(idCliente);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_informacion_personal, container, false);
        inicializarVistas(view);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    cliente.setNombre(txtNombreCliente.getText().toString());
                    cliente.setSalario(Double.parseDouble(txtNumberSalario.getText().toString()));
                    cliente.setTelefono(txtTelefono.getText().toString());
                    cliente.setDireccion(txtMlDireccion.getText().toString());
                    cliente.setEstCivil(spinnerEstadoCivil.getSelectedItem().toString());
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
                    cliente.setFecNac(LocalDate.parse((txtFecha.getText().toString()),formatter ));
                    clienteDAO.updateClients(cliente);
                    Toast.makeText(getActivity(), "Se actualizó correctamente", Toast.LENGTH_SHORT).show();
                    mostrarDatos();
                } catch (DateTimeParseException e) {
                    Toast.makeText(getActivity(), "Fecha inválida", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return view;
    }
    private void inicializarVistas(View view) {
        txtNombreCliente = view.findViewById(R.id.txtNombreCliente);
        txtNumberSalario = view.findViewById(R.id.txtNumberSalario);
        txtTelefono = view.findViewById(R.id.txtTelefono);
        txtFecha = view.findViewById(R.id.txtFecha);
        txtMlDireccion = view.findViewById(R.id.TxtMlDireccion);
        btnEditar = view.findViewById(R.id.btnEditar);
        spinnerEstadoCivil = view.findViewById(R.id.spinnerEstadoCivil);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, estadoCivil);
        spinnerEstadoCivil.setAdapter(adapter);
        txtFecha.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            @SuppressLint("SetTextI18n") DatePickerDialog datePickerDialog = new DatePickerDialog(
                    view.getContext(),
                    (view1, year1, monthOfYear, dayOfMonth) -> {
                        txtFecha.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year1);
                    },
                    year, month, day);
            datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
            datePickerDialog.show();
        });
        mostrarDatos();
    }

    public void mostrarDatos(){
        txtNombreCliente.setText(cliente.getNombre());
        txtNumberSalario.setText(String.valueOf(cliente.getSalario()));
        txtTelefono.setText(String.valueOf(cliente.getTelefono()));
        txtFecha.setText(cliente.getFecNac().getDayOfMonth() + "-" + cliente.getFecNac().getMonthValue()+ "-" + cliente.getFecNac().getYear());
        txtMlDireccion.setText(String.valueOf(cliente.getDireccion()));
        int estadoCivilIndex = Arrays.asList(estadoCivil).indexOf(cliente.getEstCivil());
        spinnerEstadoCivil.setSelection(estadoCivilIndex);

    }
   /* private boolean verificarFormulario(){
        return Utiles.verificarCampo(txtNombreCliente)  & Utiles.mayorA(txtSalario, 0)
                & Utiles.igualA(txtTelefono, 8) & Utiles.verificarCampo(txtFecha) &
                Utiles.verificarCampo(txtMtlDireccion) ;
    }*/
}



