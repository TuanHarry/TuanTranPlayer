package com.example.tuantran.ttplayer.ui.player;


import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tuantran.ttplayer.AlarmBroadcastReceiver;
import com.example.tuantran.ttplayer.Contanst;
import com.example.tuantran.ttplayer.R;
import com.example.tuantran.ttplayer.ui.base.fragment.BaseFragment;

import static android.content.Context.ALARM_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogAlarmFragment extends BottomSheetDialogFragment {

    EditText edtTime;

    public DialogAlarmFragment() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.fragment_dialog_alarm,null);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)
                // Add action buttons
                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // TODO load list song current.
                        String strminute = edtTime.getText().toString();
                        int minute = Integer.parseInt(strminute);
                        Intent intent = new Intent(getActivity(), AlarmBroadcastReceiver.class);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), Contanst.REQUEST_CODE_BROADCAST,intent,0);
                        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(ALARM_SERVICE);
                        alarmManager.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+ (minute *60000),pendingIntent);
                        Toast.makeText(getActivity(), "Nhac se tat sau " + minute +"phut", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DialogAlarmFragment.this.getDialog().cancel();
                    }
                });

        edtTime = view.findViewById(R.id.edt_time);
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
