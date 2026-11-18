package com.hriz.yessine.glycemie;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // 1) Attributs : les vues nécessaires à la logique de l'application
    private EditText etValeur;      // l'EditText qui comporte la valeur mesurée
    private TextView tvAge;         // affiche "Votre âge : x"
    private SeekBar sbAge;          // choix de l'âge (0..120)
    private RadioButton rbtOui;     // le patient jeûne
    private RadioButton rbtNon;     // le patient ne jeûne pas
    private Button btnConsulter;    // bouton CONSULTER
    private TextView tvResultat;    // affiche le résultat calculé

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        // 3) Listener du SeekBar : mise à jour du TextView tvAge à chaque changement
        sbAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // affichage dans le Log de Android Studio pour voir les changements détectés sur le SeekBar
                Log.i("Information", "onProgressChanged " + progress);
                // mise à jour du TextView par la valeur : progress à chaque changement
                tvAge.setText("Votre âge : " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // 5) Listener explicite du bouton CONSULTER (remplace android:onClick="calculer")
        btnConsulter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculer(v);
            }
        });
    }

    // 2) Récupération des références des vues depuis le layout activity_main.xml
    private void init() {
        etValeur = findViewById(R.id.etValeur);
        tvAge = findViewById(R.id.tvAge);
        sbAge = findViewById(R.id.sbAge);
        rbtOui = findViewById(R.id.rbtOui);
        rbtNon = findViewById(R.id.rbtNon);
        btnConsulter = findViewById(R.id.btnConsulter);
        tvResultat = findViewById(R.id.tvResultat);
    }

    // 4) Calcul du niveau de glycémie
    public void calculer(View view) {
        int age = sbAge.getProgress();
        String saisie = etValeur.getText().toString().trim().replace(',', '.');

        if (age == 0) {
            Toast.makeText(this, "Veuillez choisir votre âge", Toast.LENGTH_SHORT).show();
            return;
        }
        if (saisie.isEmpty()) {
            Toast.makeText(this, "Veuillez saisir la valeur mesurée", Toast.LENGTH_SHORT).show();
            return;
        }

        float valeur;
        try {
            valeur = Float.parseFloat(saisie);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Valeur mesurée invalide", Toast.LENGTH_SHORT).show();
            return;
        }

        String resultat;
        if (rbtOui.isChecked()) {
            // Avant les repas (ou s'il jeûne)
            float min, max;
            if (age >= 13) {          // adultes et enfants de plus de 13 ans
                min = 5.0f;
                max = 7.2f;
            } else if (age >= 6) {    // enfants de 6 à 12 ans
                min = 5.0f;
                max = 10.0f;
            } else {                  // enfants de moins de 6 ans
                min = 5.5f;
                max = 10.0f;
            }

            if (valeur < min) {
                resultat = "Niveau de glycémie est trop bas";
            } else if (valeur > max) {
                resultat = "Niveau de glycémie est trop élevée";
            } else {
                resultat = "Niveau de glycémie est normale";
            }
        } else {
            // Après les repas : moins de 10.5 mmol/L pour les adultes et les enfants
            if (valeur < 10.5f) {
                resultat = "Niveau de glycémie est normale";
            } else {
                resultat = "Niveau de glycémie est trop élevée";
            }
        }

        tvResultat.setText(resultat);
    }
}
