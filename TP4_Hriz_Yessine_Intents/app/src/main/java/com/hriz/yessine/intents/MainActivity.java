package com.hriz.yessine.intents;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText edtnom;
    private EditText edtprenom;
    private EditText edtmail;
    private Button envoyer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1) Initialisation des objets en les associant à leurs équivalents dans activity_main.xml
        edtnom = (EditText) findViewById(R.id.nom);
        edtprenom = (EditText) findViewById(R.id.prenom);
        edtmail = (EditText) findViewById(R.id.mail);
        envoyer = (Button) findViewById(R.id.btnsend);

        envoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 2) Récupération des valeurs saisies dans les trois EditText
                String getnom = edtnom.getText().toString();
                String getprenom = edtprenom.getText().toString();
                String getmail = edtmail.getText().toString();

                // 3) Intent explicite : contexte + classe Java de l'activité cible
                Intent myintent = new Intent(MainActivity.this, WelcomeActivity.class);

                // 4) Ajout des données dans le champ "extra" de l'intent
                myintent.putExtra("name", getnom);
                myintent.putExtra("lastname", getprenom);
                myintent.putExtra("mymail", getmail);

                // 5) Lancement de la deuxième activité
                startActivity(myintent);
            }
        });
    }
}
