package com.hriz.yessine.intents;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    private TextView welcome;
    private String welcomenom;
    private String welcomeprenom;
    private String welcomemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);

        // 1) Initialisation du TextView qui affiche les informations
        welcome = (TextView) findViewById(R.id.welcome);

        // 2) Récupération des extras de l'Intent qui a démarré l'activité
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            welcomenom = extras.getString("name");
            welcomeprenom = extras.getString("lastname");
            welcomemail = extras.getString("mymail");
        }

        // 3) Affichage des informations saisies
        welcome.setText("Bienvenue " + welcomenom + " " + welcomeprenom + ",\n"
                + "c'est la deuxième activité.\n"
                + "Vous serez bientôt contacté sur : " + welcomemail);
    }
}
