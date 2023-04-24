package proyecto.AsoDesUnidos.Controladores.Cliente;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import proyecto.AsoDesUnidos.Modelos.Cliente;
import proyecto.AsoDesUnidos.R;
import proyecto.AsoDesUnidos.Utiles.Utiles;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalculoCuotaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalculoCuotaFragment extends Fragment {
    private EditText txtMontoAfiliado;
    private TextView tvCuotaResultante;
    RadioGroup rGroupTipoCredito;
    RadioGroup rGroupPlazo;
    Button btnCalcular;

    private double tasaInteres;
    private int plazoMeses;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CalculoCuotaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalculoCuotaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalculoCuotaFragment newInstance(String param1, String param2) {
        CalculoCuotaFragment fragment = new CalculoCuotaFragment();
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculo_cuota, container, false);
        txtMontoAfiliado = view.findViewById(R.id.txtMontoAfiliado);
        tvCuotaResultante = view.findViewById(R.id.tvCuotaResultante);
        rGroupTipoCredito = view.findViewById(R.id.rGroupTipoCredito);
        rGroupPlazo=view.findViewById(R.id.rGroupPlazo);
        btnCalcular = view.findViewById(R.id.btnCalcular);
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                    Double montoAfiliado = Double.parseDouble(txtMontoAfiliado.getText().toString());
                    double cuota = (montoAfiliado * tasaInteres) / plazoMeses;
                    tvCuotaResultante.setText(String.format("₡" + "%.2f", cuota));
                }
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