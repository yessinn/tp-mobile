package com.hriz.yessine.glycemie;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Méthode liée au bouton CONSULTER par l'attribut android:onClick="calculer".
    // La logique sera implémentée dans le TP3.
    public void calculer(View view) {
    }
}
