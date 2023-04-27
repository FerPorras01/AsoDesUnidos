package com.asoDesUnidos.controladores.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.content.Intent;
import android.os.Bundle;

import com.asoDesUnidos.controladores.LoginActivity;
import com.asoDesUnidos.controladores.MainActivity;
import com.asoDesUnidos.modelos.Usuario;
import com.asoDesUnidos.R;
import com.asoDesUnidos.databinding.ActivityAdminBinding;

public class AdminActivity extends AppCompatActivity {

    ActivityAdminBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAdminBinding.inflate(getLayoutInflater());
        Intent intent = getIntent();
        Usuario nombreUsuario = (Usuario) intent.getSerializableExtra(LoginActivity.IDUSUARIO);
        Bundle bundle = new Bundle();
        bundle.putSerializable(LoginActivity.IDUSUARIO, nombreUsuario);
        setContentView(binding.getRoot());
        AdminInicioFragment fragment = new AdminInicioFragment();
        fragment.setArguments(bundle);
        replaceFragment(fragment);
        MainActivity.deseleccionarMenu(binding.bottomNavigationView.getMenu());
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if(item.getItemId() == R.id.btnAgregarCliente )
                replaceFragment(new AgregarClienteFragment());
            else if(item.getItemId() == R.id.btnAgregarPrestamo)
                replaceFragment(new AgregarPrestamoFragment());
            else if(item.getItemId() ==  R.id.btnCerrarSesion)
                MainActivity.cerrarSesion(this);

            return true;
        });
    }


    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }
}