package com.asoDesUnidos.controladores.cliente;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.asoDesUnidos.R;

public class ClienteInicioFragment extends Fragment {

    public ClienteInicioFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Bundle args = getArguments();
        if (args != null) {
            String nombreCliente = args.getString(ClientActivity.nombreCliente);
            if (nombreCliente != null) {
                View view = inflater.inflate(R.layout.fragment_inicio, container, false);
                TextView textView2 = view.findViewById(R.id.textView2);
                textView2.setText(getString(R.string.mensajeBienvenidaFlex, nombreCliente));

                return view;
            }
        }
      return null;
    }
}