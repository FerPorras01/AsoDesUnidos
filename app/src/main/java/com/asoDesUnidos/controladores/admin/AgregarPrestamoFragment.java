package com.asoDesUnidos.controladores.admin;


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

import com.asoDesUnidos.bd.ConexionBaseDatos;
import com.asoDesUnidos.dataAccessObjects.ClienteDAO;
import com.asoDesUnidos.dataAccessObjects.PrestamoDAO;
import com.asoDesUnidos.modelos.Cliente;
import com.asoDesUnidos.modelos.Prestamo;
import com.asoDesUnidos.R;
import com.asoDesUnidos.utiles.Utiles;

public class AgregarPrestamoFragment extends Fragment {

    EditText txtBuscCedula, txtMontoPrestamo;
    TextView tvNombreCliente, tvMontoMaximo, tvMontoTotal, tvCuota;
    Button btnbuscar, btnAgrPrestamo;
    RadioGroup rdPlazoPrestamo, rdTipoPrestamo;
    Cliente cliente;
    int periodo = -1;
    double montoMaximo, resultado = -1, cuota = -1;
    float interes = -1;

    String tipo;

    public AgregarPrestamoFragment() {    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_agregar_prestamo, container, false);

        tvNombreCliente = view.findViewById(R.id.tvNombreCliente);
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
        tvNombreCliente.setText(getString(R.string.cliente, ""));
        tvMontoTotal.setText(getString(R.string.montoTotalFlex, 0.0));
        tvCuota.setText(getString(R.string.cuotaFlex, 0.0));

        btnbuscar.setOnClickListener(v -> buscarCliente());
        txtMontoPrestamo.setOnFocusChangeListener((v, hasFocus) -> {
            if(!hasFocus)
                calcularCouta();
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

    
    private void habilitarFormulario(){
        montoMaximo = cliente.getSalario() * .45;
        txtMontoPrestamo.setEnabled(true);

        tvNombreCliente.setText(getString(R.string.cliente,cliente.getNombre()));
        tvNombreCliente.setVisibility(View.VISIBLE);

        tvMontoMaximo.setText(getString(R.string.montoMaximo,montoMaximo));
        tvMontoMaximo.setVisibility(View.VISIBLE);

        tvMontoTotal.setVisibility(View.VISIBLE);
        tvCuota.setVisibility(View.VISIBLE);

        for(int i = 0; i<rdPlazoPrestamo.getChildCount(); i++){
            rdPlazoPrestamo.getChildAt(i).setClickable(true);
        }
        for(int i = 0; i<rdTipoPrestamo.getChildCount(); i++){
            rdTipoPrestamo.getChildAt(i).setClickable(true);
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
        btnAgrPrestamo.setOnClickListener(v -> agregarPrestamo());
    }

    private void calcularResultado() {
        resultado = Double.parseDouble(txtMontoPrestamo.getText().toString());

        if (rdTipoPrestamo.getCheckedRadioButtonId() == R.id.rdBtnHipotecario){
            resultado *= 1.075;
            tipo = "Hipotecario";
            interes = 7.5f;
        } else if(rdTipoPrestamo.getCheckedRadioButtonId() == R.id.rdBtnEducacion){
                resultado *= 1.08f;
                tipo = "Educacion";
                interes = 8;
        } else if(rdTipoPrestamo.getCheckedRadioButtonId() == R.id.rdBtnPersonal){
                resultado *= 1.1f;
                tipo = "Personal";
                interes = 10;
        } else if(rdTipoPrestamo.getCheckedRadioButtonId() ==  R.id.rdBtnViajes) {
            resultado *= 1.12f;
            tipo = "Viajes";
            interes = 12;
        } else {
            resultado = -1;
            tipo = "";
            interes = -1;
        }
    }

    private void calcularCouta() {
        if (Utiles.mayorA(txtMontoPrestamo, 0) && Utiles.menorA(txtMontoPrestamo, montoMaximo)) {
            calcularResultado();
            if(rdPlazoPrestamo.getCheckedRadioButtonId() == R.id.rdBtnTres) {
                cuota = resultado / 36;
                periodo = 3;
            } else if(rdPlazoPrestamo.getCheckedRadioButtonId() == R.id.rdBtnCinco) {
                cuota = resultado / 60;
                periodo = 5;
            } else if(rdPlazoPrestamo.getCheckedRadioButtonId() == R.id.rdBtnDiez){
                cuota = resultado / 120;
                periodo = 10;
            } else
                cuota = -1;

            tvMontoTotal.setText(getString(R.string.montoTotalFlex,resultado));
            tvCuota.setText(getString(R.string.cuotaFlex, cuota));
        }
    }

    private void limpiarFormulario(){

    }

    private void agregarPrestamo(){
        try {
            ConexionBaseDatos db = Room.databaseBuilder(requireContext(),
                    ConexionBaseDatos.class, "database-name").allowMainThreadQueries().build();
            PrestamoDAO prestamoDAO = db.prestamoDAO();
            calcularCouta();
            prestamoDAO.insertAll(new Prestamo(cliente.getId(), cuota, resultado, tipo, interes, periodo));
            Toast.makeText(requireActivity(), "Se agregó el préstamo a " + cliente.getNombre(), Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(requireActivity(), "Error al insertar", Toast.LENGTH_SHORT).show();
        }

    }
}