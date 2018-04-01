package android.elviera.com.elviera_1202152335_modul6;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment {

    private RecyclerView recyclerView;
    //private List<PhotoModel> datas;

    private FirebaseAuth auth;

    public MyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        auth=FirebaseAuth.getInstance();
        loadData();
        return inflater.inflate(R.layout.fragment_my, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton fab = (FloatingActionButton) getView().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), UploadActivity.class));
            }
        });


        recyclerView=(RecyclerView)view.findViewById(R.id.listMyRecycler);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
    }

    private void loadData(){
        final ProgressDialog loading=new ProgressDialog(getContext());
        loading.setMessage("Getting Data ...");
        loading.setCancelable(false);
        loading.show();
        //deklarasi database
        FirebaseDatabase mFirebaseDatabase;
        DatabaseReference mDatabase;
        mFirebaseDatabase=FirebaseDatabase.getInstance();
        mDatabase=mFirebaseDatabase.getReference("photos");

        //mengambil nilai dari database
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<PhotoModel> list = new ArrayList<>();
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    PhotoModel item = postSnapshot.getValue(PhotoModel.class);
                    if(item.getUser().equals(auth.getCurrentUser().getEmail())) {
                        list.add(item);
                    }
                }

                Log.d("FIREBASE::MDATA","num:"+list.size());

               PhotoAdapter dataAdapter = new PhotoAdapter(list);
                recyclerView.setAdapter(dataAdapter);

                loading.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                loading.dismiss();
            }
        });

    }
}
