package com.example.tuantran.ttplayer.ui.Offline.playlistoffline;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.tuantran.ttplayer.R;
import com.example.tuantran.ttplayer.ui.Offline.addplaylist.AddPlaylistFragment;
import com.example.tuantran.ttplayer.ui.base.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DialogPlaylistFragment extends BaseFragment {

    EditText editNamePlayList;
    public DialogPlaylistFragment() {
    }

    public static DialogPlaylistFragment getInstance(){
        DialogPlaylistFragment fragment = new DialogPlaylistFragment();
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_new_playlist,null);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)
                // Add action buttons
                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // TODO load list song current.
                        String namePlaylist = editNamePlayList.getText().toString();
                        AddPlaylistFragment songLocalFragment = AddPlaylistFragment.getInstance(namePlaylist);
                        getFragNav().pushFragment(songLocalFragment);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DialogPlaylistFragment.this.getDialog().cancel();
                    }
                });

        editNamePlayList = view.findViewById(R.id.edt_name_playlist);
        return builder.create();
    }



}
