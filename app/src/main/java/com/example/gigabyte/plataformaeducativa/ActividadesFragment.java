package com.example.gigabyte.plataformaeducativa;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ActividadesFragment extends Fragment {
    SQLiteDatabase db;
    Cursor cursor;
    RecyclerView materiasRecycler;

    public ActividadesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        materiasRecycler = (RecyclerView) inflater.inflate(R.layout.fragment_actividades, container, false);
        new LoadMateriasTask().execute();

        return materiasRecycler;

    }

    private class LoadMateriasTask extends AsyncTask<Void, Void, Boolean> {
        String[] materiaNombre;
        int[] materiaImagenes;
        int i = 0;
        @Override
        protected Boolean doInBackground(Void... voids) {
            try{
                SQLiteOpenHelper paideiaDatabaseHelper = new PaideiaDatabaseHelper(getActivity());
                db = paideiaDatabaseHelper.getReadableDatabase();
                cursor = db.query("MATERIA", new String[] {"_ID", "NAME", "IMAGE_RESOURCE_ID"}, null, null, null, null, null);
                materiaImagenes = new int[cursor.getCount()];
                materiaNombre = new String[cursor.getCount()];
                while(cursor.moveToNext()){
                    materiaNombre[i] = cursor.getString(1);
                    materiaImagenes[i] = cursor.getInt(2);
                    i++;
                }
                return true;
            } catch(SQLiteException ex){
                return false;
            }
        }

        protected void onPostExecute(Boolean success){
            if(!success){
                Toast toast = Toast.makeText(getActivity(), "Base de datos no disponible", Toast.LENGTH_SHORT);
                toast.show();
            } else {

                CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(materiaNombre, materiaImagenes);
                materiasRecycler.setAdapter(adapter);
                GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
                materiasRecycler.setLayoutManager(layoutManager);

                adapter.setListener(new CaptionedImagesAdapter.Listener() {
                    public void onClick(int position) {
                        Intent intent;
                        switch(position){
                            case 0:
                                intent = new Intent(getActivity(), EspanolActivity.class);
                                getActivity().startActivity(intent);
                                break;
                            case 1:
                                intent = new Intent(getActivity(), MenuMateActivity.class);
                                getActivity().startActivity(intent);
                                break;
                            default:
                                break;
                        }
                        return;
                    }
                });
            }
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        cursor.close();
        db.close();
    }
}
