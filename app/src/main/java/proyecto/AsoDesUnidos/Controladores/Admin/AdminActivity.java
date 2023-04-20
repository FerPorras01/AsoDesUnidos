package proyecto.AsoDesUnidos.Controladores.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import proyecto.AsoDesUnidos.Controladores.LoginActivity;
import proyecto.AsoDesUnidos.Controladores.MainActivity;
import proyecto.AsoDesUnidos.Modelos.Usuario;
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
        Usuario nombreUsuario = (Usuario) intent.getSerializableExtra(LoginActivity.IDUSUARIO);
        Bundle bundle = new Bundle();
        bundle.putSerializable(LoginActivity.IDUSUARIO, nombreUsuario);
        setContentView(binding.getRoot());
        AdminInicioFragment fragment = new AdminInicioFragment();
        fragment.setArguments(bundle);
        replaceFragment(fragment);
        MainActivity.deseleccionarMenu(binding.bottomNavigationView.getMenu());
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.btnAgregarCliente:
                    replaceFragment(new AgregarClienteFragment());
                    break;
                case R.id.btnAgregarPrestamo:
                    replaceFragment(new AgregarPrestamoFragment());
                    break;
                case R.id.btnCerrarSesion:
                    MainActivity.cerrarSesion(this);
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