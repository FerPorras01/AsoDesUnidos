package com.asoDesUnidos.controladores.cliente;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

import com.asoDesUnidos.bd.ConexionBaseDatos;
import com.asoDesUnidos.dataAccessObjects.PrestamoDAO;
import com.asoDesUnidos.modelos.Prestamo;
import com.asoDesUnidos.R;

public class MisPrestamosFragment extends Fragment {
    private List<Prestamo> listPrestamos;


    public MisPrestamosFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mis_prestamos, container, false);
        RecyclerView recyclerViewPrestamos = view.findViewById(R.id.recyclerViewPrestamos);
        recyclerViewPrestamos.setLayoutManager(new LinearLayoutManager(getActivity()));
        listPrestamos= obtenerListaPrestamos();
        PrestamoAdapter adapter = new PrestamoAdapter(getActivity(), listPrestamos);
        recyclerViewPrestamos.setAdapter(adapter);
        return view;
    }

    private List<Prestamo> obtenerListaPrestamos() {
        // Código para obtener la lista de préstamos del cliente
        int idCliente = getArguments() != null ? getArguments().getInt(ClientActivity.idCliente) : -1;
        ConexionBaseDatos db = Room.databaseBuilder(requireContext(),
                ConexionBaseDatos.class, "database-name").allowMainThreadQueries().build();
        PrestamoDAO prestamoDAO = db.prestamoDAO();
        return listPrestamos= prestamoDAO.findByClienteId(idCliente);
    }
}
