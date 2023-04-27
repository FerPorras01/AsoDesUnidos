package com.asoDesUnidos.controladores.cliente;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import com.asoDesUnidos.bd.ConexionBaseDatos;
import com.asoDesUnidos.dataAccessObjects.AhorroDAO;
import com.asoDesUnidos.dataAccessObjects.ClienteDAO;
import com.asoDesUnidos.modelos.Ahorro;
import com.asoDesUnidos.R;

public class  MisAhorrosFragment extends Fragment {

    private List<Ahorro> listAhorros;

    public MisAhorrosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=  inflater.inflate(R.layout.fragment_mis_ahorros, container, false);
        RecyclerView recyclerViewAhorros = view.findViewById(R.id.recyclerViewAhorros);
        recyclerViewAhorros.setLayoutManager(new LinearLayoutManager(getActivity()));
        listAhorros= obtenerListaAhorros();
        AhorroAdapter adapter = new AhorroAdapter(getActivity(), listAhorros, getSalarioCliente());
        recyclerViewAhorros.setAdapter(adapter);
        return view;
    }
    private List<Ahorro> obtenerListaAhorros() {
       int idCliente;
       idCliente = getArguments() != null ? getArguments().getInt(ClientActivity.idCliente) : -1;
       ConexionBaseDatos db = Room.databaseBuilder(requireContext(),
                ConexionBaseDatos.class, "database-name").allowMainThreadQueries().build();
       AhorroDAO ahorroDAO = db.ahorroDAO();
        return listAhorros= ahorroDAO.findByClienteId(idCliente);
    }

    private double getSalarioCliente(){
        int idCliente;
        idCliente = getArguments() != null ? getArguments().getInt(ClientActivity.idCliente) : -1;
        ConexionBaseDatos db = Room.databaseBuilder(requireContext(),
                ConexionBaseDatos.class, "database-name").allowMainThreadQueries().build();
        ClienteDAO clienteDAO = db.clienteDAO();
        return clienteDAO.getClienteById(idCliente).getSalario();
    }
}