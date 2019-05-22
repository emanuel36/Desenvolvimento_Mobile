package com.example.firebase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    DatabaseReference myRef;
    EditText nome;
    EditText numero;
    Contato contato;
    ListView listaContatos;
    List<Contato> lista = new ArrayList<Contato>();
    ArrayAdapter<Contato> arrayAdapter;
    Contato contatoSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contato = new Contato();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        nome = (EditText) findViewById(R.id.editTextNome);
        numero = (EditText) findViewById(R.id.editTextNumero);
        listaContatos = (ListView) findViewById(R.id.listaView);
        atualizaLista();

        listaContatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                contatoSelecionado = (Contato)parent.getItemAtPosition(position);
                nome.setText(contatoSelecionado.getNome());
                numero.setText(contatoSelecionado.getNumero());
            }
        });
    }

    public void limpaCampos(){
        nome.setText("");
        numero.setText("");
    }

    public void cadastrar(View v) {
        contato.setId(UUID.randomUUID().toString());
        contato.setNome(nome.getText().toString());
        contato.setNumero(numero.getText().toString());
        myRef.child("Contato").child(contato.getId()).setValue(contato);
        limpaCampos();
        Toast.makeText(this, "Contato cadastrado!", Toast.LENGTH_SHORT).show();
    }

    public void deletar(View v){
        Contato c =  new Contato();
        c.setId(contatoSelecionado.getId());
        myRef.child("Contato").child(c.getId()).removeValue();
        limpaCampos();
        Toast.makeText(this, "Contato deletado", Toast.LENGTH_SHORT).show();
    }

    public void editar(View v){
        Contato c =  new Contato();
        c.setId(contatoSelecionado.getId());
        c.setNome(nome.getText().toString());
        c.setNumero(numero.getText().toString());
        myRef.child("Contato").child(c.getId()).setValue(c);
        limpaCampos();
        Toast.makeText(this, "Contato editado!", Toast.LENGTH_SHORT).show();
    }

    public void atualizaLista(){
        myRef.child("Contato").addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                lista.clear();
                for(DataSnapshot objSnap:dataSnapshot.getChildren()){
                    Contato c = objSnap.getValue(Contato.class);
                    lista.add(c);
                }
                arrayAdapter = new ArrayAdapter<Contato>(MainActivity.this, android.R.layout.simple_list_item_1, lista);
                listaContatos.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}