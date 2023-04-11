package proyecto.AsoDesUnidos.Controladores.Cliente;

import androidx.appcompat.app.AppCompatActivity;

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

        //binding.bottomNavigationView.setOnItemSelectedListener();
    }
}