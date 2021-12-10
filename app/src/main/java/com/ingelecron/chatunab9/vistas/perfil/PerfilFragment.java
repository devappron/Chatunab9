package com.ingelecron.chatunab9.vistas.perfil;

import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.ingelecron.chatunab9.R;
import com.ingelecron.chatunab9.controladores.PerfilControlador;
import com.ingelecron.chatunab9.data.firebase.ConstantesFirebase;
import com.ingelecron.chatunab9.data.firebase.modelos.Usuario;
import com.squareup.picasso.Picasso;


public class PerfilFragment extends Fragment {

    View view;
    String imgUrl;
    private ImageView iv_foto;
    private TextInputEditText tie_nombre, tie_correo;
    private Button b_editar;
    private ListenerRegistration listenerRegistration;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view= inflater.inflate(R.layout.fragment_perfil, container, false);

        iv_foto= view.findViewById(R.id.iv_foto);
        tie_nombre= view.findViewById(R.id.tie_nombre);
        tie_correo= view.findViewById(R.id.tie_correo);
        b_editar= view.findViewById(R.id.b_editar);

        iv_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popupMenu= new PopupMenu(getActivity(), v);
                popupMenu.getMenuInflater().inflate(R.menu.popup_img_perfil, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()){
                            case R.id.galeria:
                                    obtenerImagen.launch("image/*");
                                break;
                            case R.id.camara:
                                    Toast.makeText(getActivity(), "Tomar foto", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.eliminar:
                                    if(imgUrl!=null && imgUrl.length()>0){
                                        PerfilControlador.eliminarFotoPerfil(getActivity(), imgUrl);
                                    }else {
                                        Toast.makeText(getActivity(), "No hay foto para eliminar", Toast.LENGTH_SHORT).show();
                                    }
                                break;
                        }

                        return false;
                    }
                });
                popupMenu.show();
            }
        });

        b_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(getNombre().trim().length()>0){
                    PerfilControlador.actualizarDatos(getActivity(), "nombre", getNombre());
                }else {
                    Toast.makeText(getActivity(), "Los campos no pueden estar vacios", Toast.LENGTH_SHORT).show();
                }
            }
        });

        obtenerInformacion();

        return view;
    }

    private void obtenerInformacion() {

        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();

        DocumentReference documentReference= FirebaseFirestore.getInstance()
                .collection(ConstantesFirebase.USUARIO)
                .document(firebaseUser.getUid());

        listenerRegistration= documentReference.addSnapshotListener(informacionUsuario);

    }

    private EventListener<DocumentSnapshot> informacionUsuario= new EventListener<DocumentSnapshot>() {
        @Override
        public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

            try {

                Usuario usuario= value.toObject(Usuario.class);

                if(usuario!=null){

                    imgUrl= usuario.getImagen();
                    String nombre= usuario.getNombre();
                    String correo= usuario.getCorreo();

                    if(imgUrl!=null && imgUrl.length()>0){
                        Picasso.get().load(imgUrl).placeholder(R.drawable.ic_logo).error(R.drawable.ic_logo).into(iv_foto);
                    }else {
                        iv_foto.setImageResource(R.drawable.ic_logo);
                    }

                    tie_nombre.setText(nombre);
                    tie_correo.setText(correo);
                }

            }catch (NullPointerException | IllegalStateException e){
                e.getCause();
            }
        }
    };

    @Override
    public void onDetach() {
        super.onDetach();
        if(listenerRegistration!=null){
            listenerRegistration.remove();
            listenerRegistration=null;
        }
    }

    public String getNombre(){
        return tie_nombre.getText().toString();
    }

    ActivityResultLauncher<String> obtenerImagen= registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri result) {
                    PerfilControlador.actualizarImagen(getActivity(), result);
                }
            });

}