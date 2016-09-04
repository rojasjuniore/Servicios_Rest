package com.cruzalta.rojasjuniore.consumiendoserviciosrestconretrofit;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    ListView data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = (ListView) findViewById(R.id.datos);
        RestAdapter restAdapter = new RestAdapter.Builder().
                setEndpoint("http://servicio-monkydevs.rhcloud.com")
                .build();

        ClienteService clienteService = restAdapter.create(ClienteService.class);
        clienteService.getCliente(new Callback<List<Cliente>>() {
            @Override
            public void success(List<Cliente> clientes, Response response) {
                //textView.setText(clientes.toString());
                AdapterData adapterData = new AdapterData(MainActivity.this, clientes);
                data.setAdapter(adapterData);
            }

            @Override
            public void failure(RetrofitError error) {
                textView.setText(error.getMessage());
            }
        });

    }

    private class AdapterData extends ArrayAdapter<Cliente> {
        private List<Cliente> listaCLientes;

        public AdapterData(Context context, List<Cliente> clientes) {
            super(context, R.layout.list_item, clientes);
            listaCLientes = clientes;
        }

        public View getView(int pos, View convertView, ViewGroup viewGroup) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.list_item, null);

            TextView name = (TextView) item.findViewById(R.id.name);
            TextView username = (TextView) item.findViewById(R.id.username);

            name.setText(listaCLientes.get(pos).getName());
            username.setText(listaCLientes.get(pos).getEmail());

            return item;
        }
    }
}
