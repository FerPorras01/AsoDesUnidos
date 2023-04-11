package proyecto.AsoDesUnidos.Controladores.Cliente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import proyecto.AsoDesUnidos.R;
import proyecto.AsoDesUnidos.databinding.ActivityClientBinding;

public class ClientActivity extends AppCompatActivity {

    ActivityClientBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClientBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new ClienteInicioFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.btnMisPrestamos:
                    replaceFragment(new MisPrestamosFragment());
                    break;
                case R.id.btnMisAhorros:
                    replaceFragment(new MisAhorrosFragment());
                    break;
                case R.id.btnMisCalcCouta:
                    replaceFragment(new CalculoCuotaFragment());
                    break;
                case R.id.btnCerrarSesion:
                    finish();
                    break;
            }



            return true;
        });
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();

    }
}