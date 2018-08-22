package com.andresnet.agendamoderna;

import android.app.DatePickerDialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private EditText eFecha,eNombre, ePass, eRPass, eEmail,eBus, eBorr;
    private TextView lugarNa, tInfo;
    private Spinner ciudades;
    private Button bGuardar, bVer, bEliminar, bBuscar;
    private CheckBox cBaile, cFootball, cCocina, cLectura;
    private String sexo = "Masculino";
    private int dia, mes, año;
    private int con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eBus = (EditText) findViewById(R.id.eBoSear);
        eBorr =(EditText) findViewById(R.id.eBoSear);
        eFecha = (EditText) findViewById(R.id.eFecha);
        cBaile = (CheckBox) findViewById(R.id.cBailar);
        cCocina = (CheckBox) findViewById(R.id.cCocinar);
        cLectura = (CheckBox) findViewById(R.id.cLeer);
        bGuardar = (Button) findViewById(R.id.bGuardar);
        eNombre = (EditText) findViewById(R.id.editText2);
        ePass = (EditText) findViewById(R.id.editText3);
        eRPass = (EditText) findViewById(R.id.editText4);
        eEmail = (EditText) findViewById(R.id.editText5);
        cFootball = (CheckBox) findViewById(R.id.cFutbol);
        lugarNa = (TextView) findViewById(R.id.tlugar);
        ciudades = (Spinner) findViewById(R.id.sp1);
        tInfo = (TextView) findViewById(R.id.tInformacion);
        bVer = (Button) findViewById(R.id.bVer);
        bBuscar = (Button) findViewById(R.id.bBuscar);
        bEliminar = (Button) findViewById(R.id.bEliminar);
        con=0;


        eFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(),"date picker");
            }
        });
        final String nom[] = new String[100];
        final String co[] = new String[100];
        final String ho [] = new String[100];
        final String ciu[] = new String[100];
        final String se [] = new String[100];
        final String fen [] = new String[100];





        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.ciudades, android.R.layout.simple_spinner_item);
        ciudades.setAdapter(adapter);
        bGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre, pass,date,place, rPass, email, hobbies=" ";
                nombre = eNombre.getText().toString();
                pass = ePass.getText().toString();
                rPass = eRPass.getText().toString();
                email = eEmail.getText().toString();
                date = eFecha.getText().toString();
                place = ciudades.getSelectedItem().toString();
                if (nombre.equals("") || email.equals("")|| date.equals("")|| place.equals("") || pass.equals("") || rPass.equals("")) {
                    Toast.makeText(getApplicationContext(), "Digite todos los datos",
                            Toast.LENGTH_SHORT).show();


                }else{
                    if(pass.equals(rPass)){
                        if(cBaile.isChecked()) hobbies = hobbies + "Bailar/ ";
                        if (cCocina.isChecked()) hobbies = hobbies + "Cocinar/ ";
                        if (cFootball.isChecked()) hobbies = hobbies + "Futbol/ ";
                        if (cLectura.isChecked()) hobbies = hobbies + "Leer/ ";
                        nom[con]=nombre;
                        co[con]=email;
                        ho[con]=hobbies;
                        ciu[con]=place;
                        se[con]=sexo;
                        fen[con]=date;
                        con+=1;
                        tInfo.setText("Name: "+nombre+ "\nEmail: "+email+"\nSexo: "+sexo + "\nhobbies: "+hobbies+ "\nDate: "+date+ "\nPlace of Birth: "+place);
                    }else {
                        Toast.makeText(getApplicationContext(), "Las contraseñas no son iguales",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });
        bBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameB;
                int no =0;
                nameB = eBus.getText().toString();
                if (nameB.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Digite el nombre", Toast.LENGTH_SHORT).show();
                }else if(con==0){
                    Toast.makeText(getApplicationContext(), "No hay Contactos todavia", Toast.LENGTH_SHORT).show();
                }
                else {

                    for(int i=0; i<con; i++){
                        if(nom[i].equals(nameB)){
                            no = 1;
                            tInfo.setText("Name: "+nom[i]+ "\nEmail: "+co[i]+"\nSexo: "+se[i] + "\nhobbies: "+ho[i]+ "\nDate: "+fen[i]+ "\nPlace of Birth: "+ciu[i]);
                            break;

                    }




                    }if (no!=1) {

                        Toast.makeText(getApplicationContext(), "No se encuentra el contacto", Toast.LENGTH_SHORT).show();
                         no = 0;
                    }

                }

            }
        });
        bEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String naBO;
                naBO = eBorr.getText().toString();
                if (naBO.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Digite el nombre", Toast.LENGTH_SHORT).show();
                }else if(con==0){
                    Toast.makeText(getApplicationContext(), "No hay Contactos todavia", Toast.LENGTH_SHORT).show();
                }
                else {
                    for (int l=0; l<con; l++){
                         if(nom[l].equals(naBO)){
                            nom[l]="";
                            co[l]="";
                            ho[l]="";
                            ciu[l]="";
                            se[l]="";
                            fen[l]="";



                        }
                        Toast.makeText(getApplicationContext(), "El contacto se elimino", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        bVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inf="";
                if(con==0){
                    Toast.makeText(getApplicationContext(), "No hay Contactos todavia", Toast.LENGTH_SHORT).show();
                }
                else {
                    for (int b=0; b<con; b++){
                        inf=inf+"\nName: "+nom[b]+ "\nEmail: "+co[b]+"\nSexo: "+se[b] + "\nhobbies: "+ho[b]+ "\nDate: "+fen[b]+ "\nPlace of Birth: "+ciu[b];
                    }
                    tInfo.setText(inf);


                }

            }
        });


    }

    @Override
    public void onDateSet(DatePicker datePicker, int dyear, int dmonth, int dday) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,dyear);
        c.set(Calendar.MONTH,dmonth);
        c.set(Calendar.DAY_OF_MONTH,dday);
        String currentDateString = DateFormat.getDateInstance().format(c.getTime());
        eFecha.setText(currentDateString);


    }
    public void radioButtonClicked(View view){
        int id = view.getId();

        if(id == R.id.rMasculino){
            sexo = "Masculino";
        }else {
            sexo = "Femenino";
        }
    }
}