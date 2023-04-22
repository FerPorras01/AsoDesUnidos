package proyecto.AsoDesUnidos.Controladores.Admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import proyecto.AsoDesUnidos.R;

public class AgregarPrestamoFragment extends Fragment {


    public AgregarPrestamoFragment() {
        // Required empty public constructor
    }

    public static AgregarPrestamoFragment newInstance(String param1, String param2) {
        return new AgregarPrestamoFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_agregar_prestamo, container, false);
    }
}