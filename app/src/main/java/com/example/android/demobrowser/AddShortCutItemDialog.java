package com.example.android.demobrowser;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class AddShortCutItemDialog extends AppCompatDialogFragment {
    private EditText editTextTitle,editTextWebURL;
    private AddShortCutDialogListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (AddShortCutDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+"Must implement AddShortCutDialogListener");
        }

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog_add_item,null);

        builder.setView(view).setTitle("Add New Short-cut").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String title = editTextTitle.getText().toString();
                String WebURL = editTextWebURL.getText().toString();
                listener.applyTexts(title,WebURL);

            }
        });
        editTextTitle = view.findViewById(R.id.et_dialog_add_title);
        editTextWebURL = view.findViewById(R.id.et_dialog_add_web_address);


    return builder.create();
    }
    public interface AddShortCutDialogListener{
    void applyTexts(String title, String webURL);

    }
}
