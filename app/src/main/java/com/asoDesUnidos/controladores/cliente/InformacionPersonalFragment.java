package com.asoDesUnidos.controladores.cliente;

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
import android.widget.Toast;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import com.asoDesUnidos.bd.ConexionBaseDatos;
import com.asoDesUnidos.dataAccessObjects.ClienteDAO;
import com.asoDesUnidos.modelos.Cliente;
import com.asoDesUnidos.R;


public class InformacionPersonalFragment extends Fragment {
    EditText txtNombreCliente, txtNumberSalario, txtTelefono, txtFecha, txtMlDireccion;
    final String[] estadoCivil = {"Soltero(a)", "Casado(a)", "Divorciado(a)", "Viudo(a)", "Union Libre"};
    Spinner spinnerEstadoCivil;
    Button btnEditar;
    Cliente cliente;
    ConexionBaseDatos db;
    ClienteDAO clienteDAO;

    public InformacionPersonalFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = Room.databaseBuilder(requireContext(),
                ConexionBaseDatos.class, "database-name").allowMainThreadQueries().build();
        int idCliente = getArguments() != null ? getArguments().getInt(ClientActivity.idCliente) : -1;
        clienteDAO = db.clienteDAO();
        cliente = clienteDAO.getClienteById(idCliente);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_cliente_informacion_personal, container, false);
        inicializarVistas(view);
        btnEditar.setOnClickListener(v -> {
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

            int annio = c.get(Calendar.YEAR);
            int mes = c.get(Calendar.MONTH);
            int dia = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    view.getContext(),
                    (view1, annio1, mesDelAnnio, diaDelMes) -> txtFecha.setText(getString(R.string.fechaNacimientoFlex, diaDelMes, mesDelAnnio+1, annio1)),
                    annio, mes, dia);
            datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
            datePickerDialog.show();
        });
        mostrarDatos();
    }


    @SuppressLint("DefaultLocale")
    public void mostrarDatos(){
        txtNombreCliente.setText(cliente.getNombre());
        txtNumberSalario.setText(String.format("%.2f", cliente.getSalario()));
        txtTelefono.setText(String.valueOf(cliente.getTelefono()));
        txtFecha.setText(getString(R.string.fechaNacimientoFlex, cliente.getFecNac().getDayOfMonth(), cliente.getFecNac().getMonthValue(), cliente.getFecNac().getYear()));
        txtMlDireccion.setText(String.valueOf(cliente.getDireccion()));
        int estadoCivilIndex = Arrays.asList(estadoCivil).indexOf(cliente.getEstCivil());
        spinnerEstadoCivil.setSelection(estadoCivilIndex);
    }
}



