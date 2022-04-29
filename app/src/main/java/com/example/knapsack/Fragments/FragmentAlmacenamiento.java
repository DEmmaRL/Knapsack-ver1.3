package com.example.knapsack.Fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.knapsack.R;
import com.example.knapsack.goku.nav_menu;
import com.google.android.material.button.MaterialButton;

public class FragmentAlmacenamiento extends Fragment {
    TextView hola;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.almacenamiento_fragment,container,false);
        MaterialButton storageBtn = view.findViewById(R.id.storage_btn);
        hola= view.findViewById(R.id.texto);
        storageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(checkPermission()){
                  //  hola.setText("Permiso permitido");
                   // hola.setText("DEBUG");
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.setReorderingAllowed(true);

                    // Replace whatever is in the fragment_container view with this fragment

                    transaction.replace(R.id.almacenamiento_vista, Filelist.newInstance("spiderman", "superman", 0));
                    //getActivity().prueba("s");
                    // Commit the transaction
                    transaction.commit();


                    //Ocultar botones
                    storageBtn.setVisibility(View.GONE);
                    hola.setVisibility(View.GONE);
                    //permission allowed
                }else{
                    hola.setText("Permiso no permitido");
                    //permission not allowed
                    requestPermission();

                }
            }
        });
        return view;
    }



    private boolean checkPermission(){ int result = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(result == PackageManager.PERMISSION_GRANTED){
            return true;
        }else
        return false;
    }

    private void requestPermission(){
       if(ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            Toast.makeText(getActivity(),"Storage permission is requires,please allow from settings",Toast.LENGTH_SHORT).show();
        }else
            ActivityCompat.requestPermissions(getActivity(),new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},111);

    }
}
