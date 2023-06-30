package com.example.lab9;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {
    Spinner sp;
    EditText et;
    Button btnAdd, btnUpdate, btnDelete, btnClear;
    ArrayList<String> batches = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = (Spinner) findViewById(R.id.spinner);
        et = (EditText) findViewById(R.id.editText);
        btnAdd = (Button) findViewById(R.id.button);
        btnUpdate = (Button) findViewById(R.id.button2);
        btnDelete = (Button) findViewById(R.id.button3);
        btnClear = (Button) findViewById(R.id.button4);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, batches);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {

                et.setText(batches.get(pos));

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add();
                et.setText("");
            }



        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
                et.setText("");
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
                et.setText("");
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear();
                et.setText("");
            }
        });

    }

    private void add() {
        String batch = et.getText().toString();
        if (!batch.isEmpty() && batch.length() > 0) {
            adapter.add(batch);
            adapter.notifyDataSetChanged();
            et.setText("");
            Toast.makeText(getApplicationContext(), "added: " + batch, Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "nothing to add", Toast.LENGTH_LONG).show();
        }

    }


    private void update() {
        String batch = et.getText().toString();
        int pos = sp.getSelectedItemPosition();
        if (!batch.isEmpty() && batch.length() > 0) {
            adapter.remove(batches.get(pos));
            adapter.insert(batch, pos);
            adapter.notifyDataSetChanged(); et.setText("");
            Toast.makeText(getApplicationContext(), "updated: " + batch, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "not updated", Toast.LENGTH_LONG).show();
        }
    }

    private void delete() {
        String batch = et.getText().toString();
        int pos = sp.getSelectedItemPosition();

        if (pos > -1) {
            adapter.remove(batches.get(pos));

            adapter.notifyDataSetChanged(); et.setText("");
            Toast.makeText(getApplicationContext(), "deleted: " + batch, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "not deleted", Toast.LENGTH_LONG).show();
        }


    }

    private void clear() {

        adapter.clear(); Toast.makeText(getApplicationContext(), " cleared ",
                Toast.LENGTH_LONG).show();
    }

}
