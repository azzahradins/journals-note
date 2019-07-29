package com.example.journals;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.journals.data.Notes;
import com.example.journals.viewmodel.MainViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static java.lang.Integer.parseInt;

public class ComposeNotes extends AppCompatActivity {

    public MainViewModel viewModel;
    public EditText title, body;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_notes);
        this.title = findViewById(R.id.et_title);
        this.body = findViewById(R.id.et_body);
        this.viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        Intent intent = getIntent();

        if(intent.hasExtra("1")){
            setTitle("Edit Note");
            title.setText(intent.getStringExtra("title"));
            body.setText(intent.getStringExtra("body"));
        }
    }

    public void saveNotes(View view) {
        Intent intent = getIntent();
        int id = intent.getIntExtra("kode", -1);
        String txtTitle = title.getText().toString();
        String txtBody = body.getText().toString();
        String date= intent.getStringExtra("date");
        Log.d("OuTPUT id", "ini "+ id);
        if(intent.hasExtra("1")){
            Notes n = new Notes(id, txtTitle,txtBody, date);
            this.viewModel.updateNotes(n);
            Toast.makeText(this, "UPDATED", Toast.LENGTH_SHORT).show();
        } else {
            Notes newNotes = this.makeNotes();
            this.viewModel.saveNotes(newNotes);
        }
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    private Notes makeNotes() {
        String textTitle = title.getText().toString();
        String textBody = body.getText().toString();
        Notes n = new Notes( 0, textTitle, textBody, "today");
        return n;
    }

    public void delete(View view) {
        Intent intent = getIntent();
        int id = intent.getIntExtra("kode", -1);
        this.viewModel.deleteOne(id);
    }
}
