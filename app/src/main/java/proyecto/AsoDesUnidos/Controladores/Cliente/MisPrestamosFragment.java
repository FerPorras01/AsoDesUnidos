package proyecto.AsoDesUnidos.Controladores.Cliente;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.room.Room;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.List;
import proyecto.AsoDesUnidos.BD.ConexionBaseDatos;
import proyecto.AsoDesUnidos.DataAccessObjects.ClienteDAO;
import proyecto.AsoDesUnidos.DataAccessObjects.PrestamoDAO;
import proyecto.AsoDesUnidos.Modelos.Cliente;
import proyecto.AsoDesUnidos.Modelos.Prestamo;
import proyecto.AsoDesUnidos.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MisPrestamosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MisPrestamosFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MisPrestamosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MisPrestamosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MisPrestamosFragment newInstance(String param1, String param2) {
        MisPrestamosFragment fragment = new MisPrestamosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        int idCliente=getArguments().getInt(ClientActivity.idCliente);
        View view = inflater.inflate(R.layout.fragment_mis_prestamos, container, false);
        ListView lViewPrestamos = view.findViewById(R.id.lViewPrestamos);
        Prestamo[] prestamos;
        ConexionBaseDatos db = Room.databaseBuilder(getActivity().getApplicationContext(),
                ConexionBaseDatos.class, "database-name").allowMainThreadQueries().build();
        PrestamoDAO prestamoDAO = db.prestamoDAO();
        List<Prestamo> listPrestamos = prestamoDAO.findByClienteId(idCliente);
        //ArrayAdapter<Prestamo> adapter = new ArrayAdapter<Prestamo>(this,
                //android.R.layout.simple_list_item_1, android.R.id.text1, listPrestamos);
       // lViewPrestamos.setAdapter(adapter);


        //textView2 = view.findViewById(R.id.textView2);
        //textView2.setText("¡Bienvenido, "+  nombreCliente+"!");
        return view;
    }
}