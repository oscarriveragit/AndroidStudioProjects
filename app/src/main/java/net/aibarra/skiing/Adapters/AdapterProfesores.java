package net.aibarra.skiing.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.aibarra.skiing.Objetos.Profesores;
import net.aibarra.skiing.Objetos.Usuarios;
import net.aibarra.skiing.ProfesoresActivity;
import net.aibarra.skiing.R;

import java.util.List;

/**
 * Created by cice on 4/12/17.
 */
public class AdapterProfesores extends RecyclerView.Adapter<AdapterProfesores.ProfesoresViewHolder> implements View.OnClickListener {


    private List<Usuarios> clientes;
    private View.OnClickListener listener;
    ImageView imagenProfesor;
    TextView nombreProfesor, precioClase, estacionProfesor;

    public class ProfesoresViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item


        public ProfesoresViewHolder(View v) {
            super(v);
            nombreProfesor = (TextView) v.findViewById(R.id.nombreProfesor);
            precioClase = (TextView) v.findViewById(R.id.precioClase);
            estacionProfesor = (TextView) v.findViewById(R.id.estacionProfesor);
            imagenProfesor = (ImageView) v.findViewById(R.id.imagenProfesor);

        }
    }

    public AdapterProfesores(List<Usuarios> items) {
        this.clientes = items;
    }

    @Override
    public ProfesoresViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewprofesores, parent, false);
        v.setOnClickListener(this);
        return new ProfesoresViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ProfesoresViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return clientes.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null);

    }

}
