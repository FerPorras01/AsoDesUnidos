package com.asoDesUnidos.controladores.cliente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;

import com.asoDesUnidos.bd.ConexionBaseDatos;
import com.asoDesUnidos.controladores.LoginActivity;
import com.asoDesUnidos.controladores.MainActivity;
import com.asoDesUnidos.dataAccessObjects.ClienteDAO;
import com.asoDesUnidos.modelos.Cliente;
import com.asoDesUnidos.modelos.Usuario;
import com.asoDesUnidos.R;
import com.asoDesUnidos.databinding.ActivityClienteBinding;

public class ClientActivity extends AppCompatActivity {

    ActivityClienteBinding binding;
    public static final String nombreCliente="Cliente.nombreCliente";
    public static final String idCliente= "Cliente.idCliente";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClienteBinding.inflate(getLayoutInflater());
        Intent intent = getIntent();
        Usuario usuario= (Usuario)intent.getSerializableExtra(LoginActivity.IDUSUARIO);
        ConexionBaseDatos db = Room.databaseBuilder(getApplicationContext(),
                ConexionBaseDatos.class, "database-name").allowMainThreadQueries().build();
        ClienteDAO clienteDAO = db.clienteDAO();
        Cliente cliente=clienteDAO.getClienteByIdUsuario(usuario.getId());
        Bundle bundle = new Bundle();
        bundle.putString(nombreCliente, cliente.getNombre());
        setContentView(binding.getRoot());
        ClienteInicioFragment fragment = new ClienteInicioFragment();
        fragment.setArguments(bundle);
        replaceFragment(fragment);




        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if(item.getItemId() == R.id.btnMisPrestamos) {
                bundle.putInt(idCliente, cliente.getId());
                MisPrestamosFragment misPrestamosFragment = new MisPrestamosFragment();
                misPrestamosFragment.setArguments(bundle);
                replaceFragment(misPrestamosFragment);
            } else if(item.getItemId() ==R.id.btnMisAhorros) {
                bundle.putInt(idCliente, cliente.getId());
                MisAhorrosFragment misAhorrosFragment = new MisAhorrosFragment();
                misAhorrosFragment.setArguments(bundle);
                replaceFragment(misAhorrosFragment);
            } else if(item.getItemId() == R.id.btnMisCalcCouta){
                bundle.putInt(idCliente, cliente.getId());
                CalculoCuotaFragment calculoCuotaFragment = new CalculoCuotaFragment();
                calculoCuotaFragment.setArguments(bundle);
                replaceFragment(calculoCuotaFragment);
            } else if(item.getItemId() == R.id.btnInfoPersonal) {
                bundle.putInt(idCliente, cliente.getId());
                InformacionPersonalFragment informacionPersonalFragment = new InformacionPersonalFragment();
                informacionPersonalFragment.setArguments(bundle);
                replaceFragment(informacionPersonalFragment);
            }
            else if(item.getItemId() == R.id.btnCerrarSesion){
                MainActivity.cerrarSesion(this);
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