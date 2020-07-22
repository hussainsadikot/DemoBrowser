package com.example.android.demobrowser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AddShortCutItemDialog.AddShortCutDialogListener {
    private RecyclerView recyclerView;
    private ItemsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView textViewTitle, textViewWebAddress;
    private Button button;
    private ArrayList<Item> items;
    private String stringURL="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewTitle = findViewById(R.id.tv_title_from_User);
        textViewWebAddress = findViewById(R.id.tv_web_address_from_User);

        button = findViewById(R.id.button_in_main);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
        items = new ArrayList<>();
        items.add(new Item(R.drawable.ic_android, "https://www.google.com/", "Google"));

        items.add(new Item(R.drawable.ic_android, "https://www.facebook.com/", "FaceBook"));

        items.add(new Item(R.drawable.ic_android, "https://www.instagram.com/", "Instagram"));
        items.add(new Item(R.drawable.ic_android, "https://www.youtube.com/", "YouTube"));

        items.add(new Item(R.drawable.ic_android, "https://www.google.com/", "Google"));

        items.add(new Item(R.drawable.ic_android, "https://www.facebook.com/", "FaceBook"));

        items.add(new Item(R.drawable.ic_android, "https://www.instagram.com/", "Instagram"));

        createItemList();
        buildMyRecyclerView();
    }

    private void buildMyRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        mLayoutManager = new GridLayoutManager(this, 5);
        recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ItemsAdapter(this, items);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new ItemsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
//                items.get(position).changetext1("Clicked");

//                mAdapter.notifyItemChanged(position);
            stringURL=items.get(position).getmLinkResource();
            openURLActivity(stringURL);



            }
        });
        mAdapter.setOnLongClickListener(new ItemsAdapter.OnLongClickListener() {
            @Override
            public void onLongClick(int position) {



            }
        });
    }

    private void openURLActivity(String stringURL) {
        Intent i = new Intent(MainActivity.this, WebActivity.class);
        i.putExtra("URL",stringURL);
        startActivity(i);

    }

    private void createItemList() {

    }

    public void openDialog() {
        AddShortCutItemDialog addShortCutItemDialog = new AddShortCutItemDialog();
        addShortCutItemDialog.show(getSupportFragmentManager(), "add shortcut dialog");


    }

    @Override
    public void applyTexts(String title, String webURL) {
        textViewTitle.setText(title);
        textViewWebAddress.setText(webURL);
        int position =items.size()-1;
        items.add(position,new Item(R.drawable.ic_android,webURL,title));
        mAdapter.notifyItemChanged(position);
    }

    public void inserItem() {
        int position = items.size()-1;
        items.add(position,new Item(R.drawable.ic_android,"LinkedIn URL","LinkedIn"));
        mAdapter.notifyItemChanged(position);
    }

    public void removeItem(int position) {
        items.remove(position);
        mAdapter.notifyItemRemoved(position);


    }

}
