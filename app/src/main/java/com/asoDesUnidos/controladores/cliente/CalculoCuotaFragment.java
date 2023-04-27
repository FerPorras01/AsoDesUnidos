package com.asoDesUnidos.controladores.cliente;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.asoDesUnidos.R;

public class CalculoCuotaFragment extends Fragment {
    private EditText txtMontoAfiliado;
    private TextView tvCuotaResultante;
    RadioGroup rGroupTipoCredito;
    RadioGroup rGroupPlazo;
    Button btnCalcular;

    private double tasaInteres;
    private int plazoMeses;


    public CalculoCuotaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cliente_calculo_cuota, container, false);
        txtMontoAfiliado = view.findViewById(R.id.txtMontoAfiliado);
        tvCuotaResultante = view.findViewById(R.id.tvCuotaResultante);
        rGroupTipoCredito = view.findViewById(R.id.rGroupTipoCredito);
        rGroupPlazo=view.findViewById(R.id.rGroupPlazo);
        btnCalcular = view.findViewById(R.id.btnCalcular);

        tvCuotaResultante.setText(getString(R.string.cuotaFlex, 0.0));

        btnCalcular.setOnClickListener(v -> {
            //if (Utiles.verificarCampo(txtMontoAfiliado, getContext()) && Utiles.verificarRadioButton(rGroupTipoCredito)&&Utiles.verificarRadioButton(rGroupPlazo)) {
               if(verificarMonto()){
                int creditoSeleccionado = rGroupTipoCredito.getCheckedRadioButtonId();
                if (creditoSeleccionado == R.id.rBtnHipotecario) {
                    tasaInteres = 0.075;
                } else if (creditoSeleccionado == R.id.rBtnEducacion) {
                    tasaInteres = 0.08;
                } else if (creditoSeleccionado == R.id.rBtnPersonal) {
                    tasaInteres = 0.1;
                } else if (creditoSeleccionado == R.id.rBtnViajes) {
                    tasaInteres = 0.12;
                }

                int plazoSeleccionado = rGroupPlazo.getCheckedRadioButtonId();
                if (plazoSeleccionado == R.id.rBtnPlazo3) {
                    plazoMeses = 36;
                } else if (plazoSeleccionado == R.id.rBtnPlazo5) {
                    plazoMeses = 60;
                } else if (plazoSeleccionado == R.id.rBtnPlazo10) {
                    plazoMeses = 120;
                }
                double montoAfiliado = Double.parseDouble(txtMontoAfiliado.getText().toString());
                double cuota = (montoAfiliado * tasaInteres) / plazoMeses;
                tvCuotaResultante.setText(getString(R.string.cuotaFlex, cuota));
            }
        });
        return view;
    }



    public boolean verificarMonto() {
        String montoString = txtMontoAfiliado.getText().toString().trim();
        if (montoString.isEmpty()) {
            txtMontoAfiliado.setError("Ingrese un monto válido");
            return false;
        }
        double monto = Double.parseDouble(montoString);
        if (monto <= 0) {
            txtMontoAfiliado.setError("Ingrese un monto válido");
            return false;
        }
        if (rGroupTipoCredito.getCheckedRadioButtonId() == -1) {
            Toast.makeText(getContext(), "Seleccione el tipo de crédito", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (rGroupPlazo.getCheckedRadioButtonId() == -1) {
            Toast.makeText(getContext(), "Seleccione el plazo", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}