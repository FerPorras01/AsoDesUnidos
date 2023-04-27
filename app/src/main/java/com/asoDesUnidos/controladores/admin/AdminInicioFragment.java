package com.asoDesUnidos.controladores.admin;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.asoDesUnidos.controladores.LoginActivity;
import com.asoDesUnidos.modelos.Usuario;
import com.asoDesUnidos.R;

public class AdminInicioFragment extends Fragment {
    public AdminInicioFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);
        Usuario usuario = (Usuario) (getArguments() != null ? getArguments().getSerializable(LoginActivity.IDUSUARIO) : null);
        TextView textView2 = view.findViewById(R.id.textView2);
        String nombreUsuario="Desconocido";
        if (usuario != null)
            nombreUsuario = usuario.getNombre();
        textView2.setText(getString(R.string.mensajeBienvenidaFlex, nombreUsuario));
        return view;
    }
}