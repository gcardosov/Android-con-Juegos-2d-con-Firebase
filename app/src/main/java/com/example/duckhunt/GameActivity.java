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
    boolean gameOver = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initViewComponents();
        eventos();
        initPantalla();
        initCuentaAtras();

    }

    private void initCuentaAtras() {
        new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                long segundosRestantes = millisUntilFinished / 1000;
                tvTimer.setText(segundosRestantes + "s");
            }

            public void onFinish() {
                tvTimer.setText( "0s");
                gameOver = true;
                mostrarDialogoGameOver();
            }
        }.start();


    }

    private void mostrarDialogoGameOver() {
        //Cuadro de dialogo
        //salir del juego a jugar de nuevo
        //usamos el componmente dialog
        // 1. Instantiate an  with its constructor
        //nos permite configurar el cuadro de dialogo
        //recibe como parametro el contexto
        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
        // 2. Chain together various setter methods to set the dialog characteristics
        //escribimos el mensaje
        builder.setMessage("Has conseguido cazar " + counter + " patos")
                .setTitle("Game over");

        //agreagamos los botones antes de generar el builder
        builder.setPositiveButton("Reiniciar el juego" new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
            }
        });
        builder.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
                dialog.dismiss();
            }
        });

        // 3. Get the from create()
        //genera el alertDialog
        //a partir del Builder
        AlertDialog dialog = builder.create();
        //el metodo show lo muestra

        //4. Mostrar el dialogo
        dialog.show();
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
                        if (!gameOver) {
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



