package proyecto.AsoDesUnidos.Controladores.Cliente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;

import java.io.Serializable;

import proyecto.AsoDesUnidos.BD.ConexionBaseDatos;
import proyecto.AsoDesUnidos.Controladores.LoginActivity;
import proyecto.AsoDesUnidos.Controladores.MainActivity;
import proyecto.AsoDesUnidos.DataAccessObjects.ClienteDAO;
import proyecto.AsoDesUnidos.DataAccessObjects.UsuarioDAO;
import proyecto.AsoDesUnidos.Modelos.Cliente;
import proyecto.AsoDesUnidos.Modelos.Usuario;
import proyecto.AsoDesUnidos.R;
import proyecto.AsoDesUnidos.databinding.ActivityClientBinding;

public class ClientActivity extends AppCompatActivity {

    ActivityClientBinding binding;
    public static final String nombreCliente="Cliente.nombreCliente";
    public static final String idCliente= "Cliente.idCliente";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClientBinding.inflate(getLayoutInflater());
        Intent intent = getIntent();
        Usuario usuario= (Usuario)intent.getSerializableExtra(LoginActivity.IDUSUARIO);
        ConexionBaseDatos db = Room.databaseBuilder(getApplicationContext(),
                ConexionBaseDatos.class, "database-name").allowMainThreadQueries().build();
        ClienteDAO clienteDAO = db.clienteDAO();
        Cliente cliente=clienteDAO.getClienteByIdUsuario(usuario.id);
        Bundle bundle = new Bundle();
        bundle.putString(nombreCliente, cliente.nombre);
        setContentView(binding.getRoot());
        ClienteInicioFragment fragment = new ClienteInicioFragment();
        fragment.setArguments(bundle);
        replaceFragment(fragment);




        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.btnMisPrestamos:
                    bundle.putInt(idCliente, cliente.id);
                    MisPrestamosFragment misPrestamosFragment = new MisPrestamosFragment();
                    misPrestamosFragment.setArguments(bundle);
                    replaceFragment(misPrestamosFragment);
                    break;
                case R.id.btnMisAhorros:
                    bundle.putInt(idCliente, cliente.id);
                    MisAhorrosFragment misAhorrosFragment= new MisAhorrosFragment();
                    misAhorrosFragment.setArguments(bundle);
                    replaceFragment(misAhorrosFragment);
                    break;
                case R.id.btnMisCalcCouta:
                    replaceFragment(new CalculoCuotaFragment());
                    break;
                case R.id.btnCerrarSesion:
                    MainActivity.cerrarSesion(this);
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