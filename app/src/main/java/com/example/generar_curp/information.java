package com.example.generar_curp;

import android.annotation.SuppressLint;
import android.app.UiModeManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.util.HashMap;
import java.util.Random;

public class information extends AppCompatActivity {
    TextView resultsTxtView;
    Button backBtn;
    Button newCURP;
    Button toggleThemeButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        TextView nameTxtView = findViewById(R.id.nameTextView);
        TextView ape1TxtView = findViewById(R.id.ape_unoTextView);
        TextView ape2TxtView = findViewById(R.id.ape_dosTextView);
        TextView diaNTxtView = findViewById(R.id.dia_nTextView);
        TextView mesNTxtView = findViewById(R.id.mes_nTextView);
        TextView yearNTxtView = findViewById(R.id.year_nTextView);
        TextView estadoTxtView = findViewById(R.id.estadoTextView);
        TextView gendersTxtView = findViewById(R.id.generoTextView);
        resultsTxtView = findViewById(R.id.resultsTextView);
        backBtn = findViewById(R.id.regresar);
        newCURP = findViewById(R.id.new_curp);

        toggleThemeButton = findViewById(R.id.new_curp);
        toggleThemeButton = findViewById(R.id.regresar);

        nameTxtView.setText(GlobalVariable.name);
        ape1TxtView.setText(GlobalVariable.ape_uno);
        ape2TxtView.setText(GlobalVariable.ape_dos);
        diaNTxtView.setText(GlobalVariable.dia_n);
        mesNTxtView.setText(GlobalVariable.mes_n);
        yearNTxtView.setText(GlobalVariable.year_n);
        estadoTxtView.setText(GlobalVariable.estado);
        gendersTxtView.setText(GlobalVariable.genero);
        resultsTxtView.setText(GlobalVariable.result);

        backBtn.setOnClickListener(v -> onBackPressed());

        newCURP.setOnClickListener(v -> {
            Intent intent = new Intent(information.this, MainActivity.class);
            startActivity(intent);
        });

        toggleThemeButton.setOnClickListener(v -> {
            int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
            int newNightMode = (currentNightMode == Configuration.UI_MODE_NIGHT_YES) ?
                    AppCompatDelegate.MODE_NIGHT_NO : AppCompatDelegate.MODE_NIGHT_YES;
            AppCompatDelegate.setDefaultNightMode(newNightMode);
            recreate();
        });

        generarYMostrarCURP();
    }
            @SuppressLint("SetTextI18n")
            private void generarYMostrarCURP() {
                String apeUno = GlobalVariable.ape_uno.toUpperCase();
                String apeDos = GlobalVariable.ape_dos.toUpperCase();
                String[] fullname = GlobalVariable.name.split(" ");
                String yearN = GlobalVariable.year_n.substring(2);
                int mesN = Integer.parseInt(GlobalVariable.mes_n);
                int diaN = Integer.parseInt(GlobalVariable.dia_n);
                String genero = GlobalVariable.genero.toUpperCase();

                String state = obtenerAbreviaturaEntidadFederativa(GlobalVariable.estado.toUpperCase());

                Random random = new Random();
                String n1 = String.valueOf(random.nextInt(10));
                String n2 = String.valueOf(random.nextInt(10));

                String curp = generateCurp(apeUno, apeDos, fullname, yearN, mesN, diaN, genero, state, n1, n2);
                resultsTxtView.setText(curp);
                resultsTxtView.setTextSize(20);
            }
            private String obtenerAbreviaturaEntidadFederativa(String estado) {
                HashMap<String, String> entidadesFederativas = new HashMap<>();
                entidadesFederativas.put("Aguascalientes", "AS");
                entidadesFederativas.put("Baja California", "BC");
                entidadesFederativas.put("Baja California Sur", "BS");
                entidadesFederativas.put("Campeche", "CC");
                entidadesFederativas.put("Chiapas", "CS");
                entidadesFederativas.put("Chihuahua", "CH");
                entidadesFederativas.put("Ciudad de México", "DF");
                entidadesFederativas.put("Coahuila", "CL");
                entidadesFederativas.put("Colima", "CM");
                entidadesFederativas.put("Durango", "DG");
                entidadesFederativas.put("Guanajuato", "GT");
                entidadesFederativas.put("Guerrero", "GR");
                entidadesFederativas.put("Hidalgo", "HG");
                entidadesFederativas.put("Jalisco", "JC");
                entidadesFederativas.put("Estado de México", "MC");
                entidadesFederativas.put("Michoacán", "MN");
                entidadesFederativas.put("Morelos", "MS");
                entidadesFederativas.put("Nayarit", "NT");
                entidadesFederativas.put("Nuevo León", "NL");
                entidadesFederativas.put("Oaxaca", "OC");
                entidadesFederativas.put("Puebla", "PL");
                entidadesFederativas.put("Querétaro", "QO");
                entidadesFederativas.put("Quintana Roo", "QR");
                entidadesFederativas.put("San Luis Potosí", "SP");
                entidadesFederativas.put("Sinaloa", "SL");
                entidadesFederativas.put("Sonora", "SR");
                entidadesFederativas.put("Tabasco", "TC");
                entidadesFederativas.put("Tamaulipas", "TS");
                entidadesFederativas.put("Tlaxcala", "TL");
                entidadesFederativas.put("Veracruz", "VZ");
                entidadesFederativas.put("Yucatán", "YN");
                entidadesFederativas.put("Zacatecas", "ZS");

                return entidadesFederativas.get(GlobalVariable.estado) != null ? entidadesFederativas.get(GlobalVariable.estado) : "";
            }
            public static String obtenerPrimeraConsonanteInterna(String cadena) {
                StringBuilder consonanteBuilder = new StringBuilder();

                // Itera a través de las letras para encontrar la primera consonante interna
                for (int i = 1; i < cadena.length(); i++) {
                    char letra = cadena.charAt(i);
                    if (Character.isLetter(letra) && "aeiouAEIOU".indexOf(letra) == -1) {
                        consonanteBuilder.append(letra);
                        break; // Sale del bucle después de agregar la primera consonante interna
                    }
                }
                return consonanteBuilder.toString();
            }
            @SuppressLint("DefaultLocale")
            private String generateCurp(String apeUno, String apeDos, String[] fullname, String yearN, int mesN, int diaN, String genero, String state, String n1, String n2) {
                StringBuilder curpBuilder = new StringBuilder();

                // Paso 1 y 2
                curpBuilder.append(apeUno.substring(0, 2).toUpperCase());
                // Paso 3 y 4
                curpBuilder.append(apeDos.charAt(0));
                if (fullname.length > 1) {
                    curpBuilder.append(fullname[1].charAt(0));
                } else {
                    curpBuilder.append(fullname[0].charAt(0));
                }
                // Paso 5 y 6
                curpBuilder.append(yearN);

                // Paso 7 y 8
                curpBuilder.append(String.format("%02d", mesN));

                // Paso 9 y 10
                curpBuilder.append(String.format("%02d", diaN));

                // Paso 11
                curpBuilder.append(genero);
                // Paso 12 y 13
                curpBuilder.append(state);
                curpBuilder.append(obtenerPrimeraConsonanteInterna(apeUno));

        // Paso 15 - Primera consonante interna del segundo apellido
                curpBuilder.append(obtenerPrimeraConsonanteInterna(apeDos));

        // Paso 16 - Primera consonante interna del nombre de pila
                curpBuilder.append(obtenerPrimeraConsonanteInterna(fullname[0]));

                // Paso 17 y 18
                curpBuilder.append(n1).append(n2);

                return curpBuilder.toString().toUpperCase();
            }

        }


