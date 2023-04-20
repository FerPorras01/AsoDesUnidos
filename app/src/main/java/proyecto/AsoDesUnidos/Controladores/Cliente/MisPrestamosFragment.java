package proyecto.AsoDesUnidos.Controladores.Cliente;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

import proyecto.AsoDesUnidos.BD.ConexionBaseDatos;
import proyecto.AsoDesUnidos.DataAccessObjects.PrestamoDAO;
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
    private RecyclerView recyclerViewPrestamos;
    private PrestamoAdapter adapter;
    private List<Prestamo> listPrestamos;
    private PrestamoDAO prestamoDAO;


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

        View view = inflater.inflate(R.layout.fragment_mis_prestamos, container, false);
        recyclerViewPrestamos = view.findViewById(R.id.recyclerViewPrestamos);
        recyclerViewPrestamos.setLayoutManager(new LinearLayoutManager(getActivity()));
        listPrestamos= obtenerListaPrestamos();
        adapter = new PrestamoAdapter(getActivity(), listPrestamos);
        recyclerViewPrestamos.setAdapter(adapter);
        return view;



    }

    private List<Prestamo> obtenerListaPrestamos() {
        // Código para obtener la lista de préstamos del cliente
        int idCliente = getArguments().getInt(ClientActivity.idCliente);
        ConexionBaseDatos db = Room.databaseBuilder(getActivity().getApplicationContext(),
                ConexionBaseDatos.class, "database-name").allowMainThreadQueries().build();
        prestamoDAO = db.prestamoDAO();
        return listPrestamos= prestamoDAO.findByClienteId(idCliente);

    }
}
