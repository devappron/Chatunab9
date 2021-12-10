package com.ingelecron.chatunab9.presentadores;

import android.content.ContentValues;
import android.content.Context;
import android.widget.Toast;

import com.ingelecron.chatunab9.R;
import com.ingelecron.chatunab9.data.api.IEndPointsApiRest;
import com.ingelecron.chatunab9.data.api.adaptadores.AdaptadorApiRest;
import com.ingelecron.chatunab9.data.api.modelos.ContactoResponseApiRest;
import com.ingelecron.chatunab9.data.db.modelos.Contacto;
import com.ingelecron.chatunab9.data.db.ConstantesDBRVContactos;
import com.ingelecron.chatunab9.data.db.interactor.IInteractorDBRVContactos;
import com.ingelecron.chatunab9.data.db.interactor.InteractorDBRVContactos;
import com.ingelecron.chatunab9.vistas.fragment.tab1.ITab1Fragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PreTab1Fragment implements IPreTab1Fragment{

    private Context contexto;
    private ITab1Fragment iTab1Fragment;
    private IInteractorDBRVContactos iInteractorDBRVContactos;


    public PreTab1Fragment(Context contexto, ITab1Fragment iTab1Fragment) {
        this.contexto = contexto;
        this.iTab1Fragment = iTab1Fragment;
        this.iInteractorDBRVContactos = new InteractorDBRVContactos(contexto);
        dataApi();
    }

    private void dataApi() {

        AdaptadorApiRest adaptadorApiRest= new AdaptadorApiRest();
        IEndPointsApiRest iEndPointsApiRest= adaptadorApiRest.conexionApiRest();
        Call<ContactoResponseApiRest> contactoResponseApiRestCall= iEndPointsApiRest.getMedia();
        contactoResponseApiRestCall.enqueue(new Callback<ContactoResponseApiRest>() {
            @Override
            public void onResponse(Call<ContactoResponseApiRest> call, Response<ContactoResponseApiRest> response) {

                ContactoResponseApiRest contactoResponseApiRest= response.body();
                ArrayList<Contacto> contactoArrayList= contactoResponseApiRest.getContactoArrayList();

                agregarContactosDB(contactoArrayList);
                obtenerContactosDB();
            }

            @Override
            public void onFailure(Call<ContactoResponseApiRest> call, Throwable t) {
                Toast.makeText(contexto, "Error al obtener los datos", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void eliminarContactosDB() {
        iInteractorDBRVContactos.borrarContactos();
    }

    @Override
    public void agregarContactosDB(ArrayList<Contacto> contactoArrayList) {

        eliminarContactosDB();

        ContentValues contentValues;

        for(int i=0; i < contactoArrayList.size(); i++){

            contentValues= new ContentValues();
            contentValues.put(ConstantesDBRVContactos.T_CONTACTO_ID, contactoArrayList.get(i).getId());
            contentValues.put(ConstantesDBRVContactos.T_CONTACTO_FOTO, contactoArrayList.get(i).getFoto());
            contentValues.put(ConstantesDBRVContactos.T_CONTACTO_NOMBRE, contactoArrayList.get(i).getNombre());
            contentValues.put(ConstantesDBRVContactos.T_CONTACTO_ESPECIE, contactoArrayList.get(i).getEspecie());
//            contentValues.put(ConstantesDBRVContactos.T_CONTACTO_CORREO, contactoArrayList.get(i).getCorreo());

            iInteractorDBRVContactos.agregarContacto(contentValues);
        }
    }

    @Override
    public void obtenerContactosDB() {
        ArrayList<Contacto> contactoArrayList= iInteractorDBRVContactos.obtenerContactos();
        mostrarContactosRV(contactoArrayList);
    }

    @Override
    public void mostrarContactosRV(ArrayList<Contacto> contactoArrayList) {
        iTab1Fragment.inicializadorAdaptadorRVContactos(iTab1Fragment.crearAdaptadorRVContactos(contactoArrayList));
        iTab1Fragment.generarLinearLayoutManger();
    }

//    public ArrayList<Contacto> listaContactos(){
//
//        ArrayList<Contacto> contactoArrayList= new ArrayList<>();
//
//        contactoArrayList.add(new Contacto("1", String.valueOf(R.drawable.ic_persona), "Rodolfo", "rodolfo@gmail.com", "3121234567"));
//        contactoArrayList.add(new Contacto("2", String.valueOf(R.drawable.ic_mas), "Maria", "mariao@gmail.com", "3121234567"));
//        contactoArrayList.add(new Contacto("3", String.valueOf(R.drawable.ic_persona), "Juan", "juan@gmail.com", "3121234567"));
//        contactoArrayList.add(new Contacto("4", String.valueOf(R.drawable.ic_mas), "Pepe", "pepe@gmail.com", "3121234567"));
//        contactoArrayList.add(new Contacto("5", String.valueOf(R.drawable.ic_persona), "Carlos", "carlos@gmail.com", "3121234567"));
//
//        return contactoArrayList;
//    }
}
