package proyecto.AsoDesUnidos.Controladores.Admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import proyecto.AsoDesUnidos.Controladores.LoginActivity;
import proyecto.AsoDesUnidos.Controladores.MainActivity;
import proyecto.AsoDesUnidos.Modelos.Usuario;
import proyecto.AsoDesUnidos.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminInicioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminInicioFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Usuario nombreUsuario;
    private TextView textView2;
    public AdminInicioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdminInicioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdminInicioFragment newInstance(String param1, String param2) {
        AdminInicioFragment fragment = new AdminInicioFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_inicio, container, false);
        nombreUsuario = (Usuario) getArguments().getSerializable(LoginActivity.IDUSUARIO);
        textView2 = view.findViewById(R.id.textView2);
        textView2.setText("¡Bienvenido, "+ nombreUsuario.nombre +"!");
        return view;
    }
}