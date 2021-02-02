package com.example.leerficherotexto;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    TextView txtdatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtdatos = findViewById(R.id.txtdatos);
    }

    public void grabarFichero(View v) {
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("datos.txt", Activity.MODE_PRIVATE));
            String contenido = this.txtdatos.getText().toString();

            archivo.write(contenido);
            archivo.flush();
            archivo.close();
        } catch (IOException ex) {
            Toast.makeText(this, "Error: " + ex.toString(), Toast.LENGTH_LONG).show();
        }
        Toast.makeText(this, "Datos almacenados", Toast.LENGTH_LONG).show();
    }

    private boolean existe(String[] archivos, String nombrefichero) {
        for (String fic : archivos) {
            if (nombrefichero.equals(fic)) {
                return true;
            }
        }
        return false;
    }

    public void leerFichero(View v) {
        String[] archivos = fileList();

        if (existe(archivos, "datos.txt")) {
            try {
                InputStreamReader archivo = new InputStreamReader(openFileInput("datos.txt"));
                BufferedReader lector = new BufferedReader(archivo);
                String linea = lector.readLine();
                String contenido = "";
                while (linea != null) {
                    contenido = contenido + linea + "\n";
                    linea = lector.readLine();

                }
                lector.close();
                archivo.close();
                this.txtdatos.setText(contenido);

            } catch (IOException ex) {
            Toast.makeText(this, "Error: " + ex.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }
}