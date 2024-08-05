        package com.example.generar_curp;

        import android.content.Intent;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Spinner;
        import android.content.res.Configuration;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.appcompat.app.AppCompatDelegate;
        import androidx.core.content.ContextCompat;

        public class MainActivity extends AppCompatActivity {
            EditText name, ape_uno, ape_dos, year_n;
            Spinner spinner1, spinner2, spinner3;
            Spinner spinner4;
            private View toggleThemeButton;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                name = findViewById(R.id.name_TextField);
                ape_uno = findViewById(R.id.ape_unoTextField);
                ape_dos = findViewById(R.id.ape_dosTextField);
                year_n = findViewById(R.id.year_nTextField);


                /*Spinners dias*/
                spinner1 = findViewById(R.id.dia_nSelection);
                String[] diaN = new String[32];
                diaN[0] = getResources().getString(R.string.select_day);

                for (int i = 1; i <= 31; i++) {
                    if (i <= 9) {
                        diaN[i] = "0" + i;
                    } else {
                        diaN[i] = String.valueOf(i);
                    }
                }

                ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, diaN);
                spinner1.setAdapter(adapter1);


                /*Spinners meses*/
                spinner2 = findViewById(R.id.mes_nSelection);
                String[] mesN = new String[13];
                mesN[0] = getResources().getString(R.string.select_month);

                for (int i = 1; i <= 12; i++) {
                    if (i <= 9) {
                        mesN[i] = "0" + i;
                    } else {
                    mesN[i] = String.valueOf(i);
                }
                }
                ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mesN);
                spinner2.setAdapter(adapter2);

                /*Spinner Estados*/
                spinner3 = findViewById(R.id.estadoSelection);

                String[] estado = {"Selecciona un estado", "Aguascalientes", "Baja California", "Baja California Sur", "Campeche", "Chiapas", "Chihuahua",
                        "Coahuila", "Colima", "Durango", "Guanajuato", "Guerrero", "Hidalgo", "Jalisco", "Estado de México", "Michoacán", "Morelos", "Nayarit",
                        "Nuevo León", "Oaxaca", "Puebla", "Querétaro", "Quintana Roo", "San Luis Potosí", "Sinaloa", "Sonora", "Tabasco",
                        "Tamaulipas", "Tlaxcala", "Veracruz", "Yucatán", "Zacatecas", "Ciudad de México"};

                ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, estado);
                spinner3.setAdapter(adapter3);

                /*Spinner Generos*/
                spinner4 = findViewById(R.id.genderSelection);

                String[] genero = new String[3];
                genero[0] = getResources().getString(R.string.select_gender);
                genero[1] = getResources().getString(R.string.male);
                genero[2] = getResources().getString(R.string.female);

                ArrayAdapter<String> adapter4 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, genero);
                spinner4.setAdapter(adapter4);

                toggleThemeButton = findViewById(R.id.mode);

                toggleThemeButton.setOnClickListener(v -> {
                    int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
                    int newNightMode = (currentNightMode == Configuration.UI_MODE_NIGHT_YES) ?
                            AppCompatDelegate.MODE_NIGHT_NO : AppCompatDelegate.MODE_NIGHT_YES;
                    AppCompatDelegate.setDefaultNightMode(newNightMode);
                    recreate();

                    if (newNightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                        toggleThemeButton.setBackgroundColor(ContextCompat.getColor(this, R.color.button_background_dark));
                    } else {
                        toggleThemeButton.setBackgroundColor(ContextCompat.getColor(this, R.color.button_background_light));
                    }
                });


                Button view = findViewById(R.id.Buscar);
                view.setOnClickListener(v -> {
                    if (validateFields()) {
                        GlobalVariable.name = name.getText().toString();
                        GlobalVariable.ape_uno = ape_uno.getText().toString();
                        GlobalVariable.ape_dos = ape_dos.getText().toString();
                        GlobalVariable.year_n = year_n.getText().toString();
                        GlobalVariable.dia_n = spinner1.getSelectedItem().toString();
                        GlobalVariable.mes_n = spinner2.getSelectedItem().toString();
                        GlobalVariable.estado = spinner3.getSelectedItem().toString();
                        GlobalVariable.genero = spinner4.getSelectedItem().toString();

                        // Agrega mensajes de registro para depuración
                        Log.d("MainActivity", "Name: " + GlobalVariable.name);
                        Log.d("MainActivity", "Ape_uno: " + GlobalVariable.ape_uno);
                        Log.d("MainActivity", "Ape_dos: " + GlobalVariable.ape_dos);
                        Log.d("MainActivity", "Year_n: " + GlobalVariable.year_n);
                        Log.d("MainActivity", "Dia_n: " + GlobalVariable.dia_n);
                        Log.d("MainActivity", "Mes_n: " + GlobalVariable.mes_n);
                        Log.d("MainActivity", "Estado: " + GlobalVariable.estado);
                        Log.d("MainActivity", "Genero: " + GlobalVariable.genero);

                        Intent gotoInformation = new Intent(MainActivity.this, information.class);
                        startActivity(gotoInformation);
                    }
                });

                Button exitBtn = findViewById(R.id.exit);
                exitBtn.setOnClickListener(v -> finish());
            }

            private boolean validateFields() {
                boolean isValid = true;

                if (name.getText().toString().isEmpty()) {
                    name.setError("Campo obligatorio");
                    isValid = false;
                } else {
                    name.setError(null);
                }

                if (ape_uno.getText().toString().isEmpty()) {
                    ape_uno.setError("Campo obligatorio");
                    isValid = false;
                } else {
                    ape_uno.setError(null);
                }

                if (ape_dos.getText().toString().isEmpty()) {
                    ape_dos.setError("Campo obligatorio");
                    isValid = false;
                } else {
                    ape_dos.setError(null);
                }

                if (year_n.getText().toString().isEmpty()) {
                    year_n.setError("Campo obligatorio");
                    isValid = false;
                } else {
                    year_n.setError(null);
                }

                if (spinner1.getSelectedItemPosition() == 0) {
                    isValid = false;
                }

                if (spinner2.getSelectedItemPosition() == 0) {
                    isValid = false;
                }

                if (spinner3.getSelectedItemPosition() == 0) {
                    isValid = false;
                }

                if (spinner4.getSelectedItemPosition() == 0) {
                    isValid = false;
                }
                return isValid;
            }
        }

