package com.example.duckhunt;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.duckhunt.common.Constantes;

public class LoginActivity extends AppCompatActivity {
    EditText etNick;
    Button btnStart;
    String nick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etNick = findViewById(R.id.editTextNick);
        btnStart = findViewById(R.id.buttonStart);


        //Cambiar tipo de fuente
        Typeface typeface = Typeface.createFromAsset(getAssets(), "pixel.ttf");
        etNick.setTypeface(typeface);
        btnStart.setTypeface(typeface);




        //Eventos: evento click
        //Rescatamos el nickName y comprobarlo
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Validacion de usuario vacio
                nick = etNick.getText().toString();
                if (nick.isEmpty()) {
                    etNick.setError("El nombre de usuario es obligatorio");
                }else if(nick.length()<3){
                    etNick.setError("Debe tener al menos 3 caracteres");
                }else{
                    //Limpiamos la cadena vacia para que se borre el nick cuando regresemos
                    //reutilizanmos el activity de login
                    etNick.setText("");
                    //pasampos a la siguiente actividad con un intent
                    //del login activity al GameActivity
                    Intent i = new Intent(LoginActivity.this, GameActivity.class);
                    i.putExtra(Constantes.EXTRA_NICK, nick);
                    startActivity(i);
                    //se ejecuta con el start activity
                    //pasamos el nickname con el metodo putExtra antes con la variable nick
                    //devemos de tener cuidado cuando pasamos los parametros con el mismo
                    //mismo de la variable por lo cual creamos un package
                }
            }
        });

    }
}
