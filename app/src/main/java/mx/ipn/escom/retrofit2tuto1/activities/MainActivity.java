package mx.ipn.escom.retrofit2tuto1.activities;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import mx.ipn.escom.retrofit2tuto1.R;
import mx.ipn.escom.retrofit2tuto1.data.RetrofitPOJO;
import mx.ipn.escom.retrofit2tuto1.interfaces.RetrofitInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String url= "https://androidtutorials.herokuapp.com/";
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitInterface servicio= retrofit.create(RetrofitInterface.class);
        Call<List<RetrofitPOJO>> call= servicio.getUsersGet();

        call.enqueue(new Callback<List<RetrofitPOJO>>() {
            @Override
            public void onResponse(Call<List<RetrofitPOJO>> call, Response<List<RetrofitPOJO>> response) {
                for (RetrofitPOJO usuario: response.body()){
                    Log.d("RespuestaSincrono", "-> " + usuario.getName() );
                }
            }

            @Override
            public void onFailure(Call<List<RetrofitPOJO>> call, Throwable t) {

            }
        });

        //Retrofit Asincrono:
        new Peticion().execute();
    }

    public static class Peticion extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            final String url= "https://androidtutorials.herokuapp.com/";
            Retrofit retrofit= new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            RetrofitInterface servicio= retrofit.create(RetrofitInterface.class);
            Call<List<RetrofitPOJO>> respuesta= servicio.getUsersGet();

            try{
                for (RetrofitPOJO usuario: respuesta.execute().body()) {
                    Log.d("RespuestaAsincrono", "-> " + usuario.getName() );
                }
            }catch (IOException e){
                Log.e("Error", e.getMessage());
            }





            return null;
        }
    }



}
