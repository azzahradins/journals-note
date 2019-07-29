package com.example.journals;

import android.content.Intent;
import android.os.Bundle;

import com.example.journals.adapter.NotesRecyclerAdapter;
import com.example.journals.data.Notes;
import com.example.journals.viewmodel.MainViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //ViewModel
    private MainViewModel viewModel;
    private static final String KEY_REQUEST_UPDATE = "1";
    // Data
    private NotesRecyclerAdapter rvNotesAdapter;
    private ArrayList<Notes> notesList;

    // Components
    private RecyclerView rvNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        this.initData();
        this.initComponent();
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewModel.deleteNotes(rvNotesAdapter.getPositionAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Note is deleted", Toast.LENGTH_SHORT).show();
            }
        });
        helper.attachToRecyclerView(rvNotes);

        rvNotesAdapter.setOnItemClickListener(new NotesRecyclerAdapter.onItemClickListener() {
            @Override
            public void onItemListener(Notes notes) {
                Intent i = new Intent(MainActivity.this, ComposeNotes.class);
                i.putExtra(KEY_REQUEST_UPDATE, KEY_REQUEST_UPDATE);
                int id = notes.getId();
                i.putExtra("kode", id);
                i.putExtra("title", notes.getTitle());
                i.putExtra("body", notes.getBody());
                startActivity(i);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent i = new Intent(MainActivity.this, ComposeNotes.class);
                startActivity(i);
            }
        });
    }

    private void initComponent() {
        this.rvNotes = this.findViewById(R.id.rv_notes);
        this.rvNotes.setLayoutManager(new LinearLayoutManager(this));
        this.rvNotes.setAdapter(this.rvNotesAdapter);

        // Load data ke recycler View
        this.rvNotesAdapter.setNotesList(this.notesList);

        //untuk offline
        this.viewModel.getNotesList().observe(this,new Observer<List<Notes>>() {
            @Override
            public void onChanged(List<Notes> notes) {
                rvNotesAdapter.setNotesList(notes);
            }
        });
    }

    private void initData() {
        this.notesList = new ArrayList<>();
        this.rvNotesAdapter = new NotesRecyclerAdapter(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(MainActivity.this, ManageActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
