package com.example.duckhunt;

import android.content.DialogInterface;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duckhunt.common.Constantes;

import java.util.Random;
import java.util.ResourceBundle;

public class GameActivity extends AppCompatActivity {
    TextView tvCounterDucks, tvTimer, tvNick;
    ImageView ivDuck;
    int counter = 0;
    int anchoPantalla;
    int altoPantalla;
    Random aleatorio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initViewComponents();
        eventos();
        initPantalla();

    }

    private void initPantalla() {
        //1. Conocer el tamaño de la pantalla del dispositivo
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        anchoPantalla = size.x;
        altoPantalla = size.y;

        //2. Inicializamos objeto para generar numeros aleatorios
        aleatorio = new Random();


    }

    private void initViewComponents(){
            tvCounterDucks = findViewById(R.id.textViewCounter);
            tvTimer = findViewById(R.id.textViewTimer);
            tvNick = findViewById(R.id.texViewNick);
            ivDuck = findViewById(R.id.imageViewDuck);

            //Cambiar tipo de fuente
            Typeface typeface = Typeface.createFromAsset(getAssets(), "pixel.ttf");
            tvCounterDucks.setTypeface(typeface);
            tvTimer.setTypeface(typeface);
            tvNick.setTypeface(typeface);

            //Extras: obtener nick y setear TextView
            Bundle extras = getIntent().getExtras();
            String nick = extras.getString(Constantes.EXTRA_NICK);
            tvNick.setText(nick);

        }

        private void eventos(){
                ivDuck.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Que queremos hacer al dar click en el pato
                        //Incrementamos el contador con el numero de patos
                        counter++;
                        tvCounterDucks.setText(String.valueOf(counter));
                        //cambiar la imagen del pato
                        ivDuck.setImageResource(R.drawable.duck_clicked);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                ivDuck.setImageResource(R.drawable.duck);
                                moveDuck();
                            }
                        }, 500);

                    }
                });

        }

    private void moveDuck() {
        int min = 0;
        int maximoX = anchoPantalla - ivDuck.getWidth();
        int maximoY = altoPantalla -ivDuck.getHeight();

        //Generamos 2 numeros aleatorios, uno para coordenada
        //x y otro para y
        int randomX = aleatorio.nextInt((maximoX - min) + 1);
        int randomY = aleatorio.nextInt((maximoY - min) + 1);

        //Utilizamos los numeros aleatorios para mover el pato
        //a esa posicion
        ivDuck.setX(randomX);
        ivDuck.setY(randomY);



    }


}



