package proyecto.AsoDesUnidos.Controladores.Admin;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.DatePicker;
import android.widget.EditText;
import java.util.Calendar;

import proyecto.AsoDesUnidos.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AgregarClienteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AgregarClienteFragment extends Fragment {

    EditText txtAgrCedula, txtAgrNombre, txtAgrSalario, txtAgrTelefono, txtAgrFecNac, txtMtlAgrDireccion;
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

        txtAgrCedula = view.findViewById(R.id.txtAgrCedula);
        txtAgrNombre = view.findViewById(R.id.txtAgrNombre);
        txtAgrSalario = view.findViewById(R.id.txtAgrSalario);
        txtAgrTelefono = view.findViewById(R.id.txtAgrTelefono);
        txtAgrFecNac = view.findViewById(R.id.txtAgrFecNac);
        txtMtlAgrDireccion = view.findViewById(R.id.txtMtlAgrDireccion);
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
                // at last we are calling show to
                // display our date picker dialog.
                datePickerDialog.show();
            }
        });
        return view;
    }
}