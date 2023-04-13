package proyecto.AsoDesUnidos.Controladores.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import proyecto.AsoDesUnidos.Controladores.LoginActivity;
import proyecto.AsoDesUnidos.R;
import proyecto.AsoDesUnidos.databinding.ActivityAdminBinding;

public class AdminActivity extends AppCompatActivity {

    ActivityAdminBinding binding;
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminBinding.inflate(getLayoutInflater());
        Intent intent = getIntent();
        String nombreUsuario = intent.getStringExtra(LoginActivity.IDUSUARIO);
        Bundle bundle = new Bundle();
        bundle.putString(LoginActivity.IDUSUARIO, nombreUsuario);
        setContentView(binding.getRoot());
        AdminInicioFragment fragment = new AdminInicioFragment();
        fragment.setArguments(bundle);
        replaceFragment(fragment);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.btnAgregarCliente:
                    replaceFragment(new AgregarClienteFragment());
                    break;
                case R.id.btnAgregarPrestamo:
                    replaceFragment(new AgregarPrestamoFragment());
                    break;
                case R.id.btnCerrarSesion:
                    finish();
                    break;
            }

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