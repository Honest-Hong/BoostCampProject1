package com.project.boostcamp.publiclibrary.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.project.boostcamp.publiclibrary.R;

/**
 * Created by Hong Tae Joon on 2017-07-26.
 */

public class MyAlertDialog extends DialogFragment {
    private static final String EXTRA_TITLE = "title";
    private static final String EXTRA_MESSAGE = "message";
    private DialogResultListener resultListener;

    public static MyAlertDialog newInstance(String title, String message, DialogResultListener resultListener) {
        MyAlertDialog myAlertDialog = new MyAlertDialog();
        Bundle arg = new Bundle();
        arg.putString(EXTRA_TITLE, title);
        arg.putString(EXTRA_MESSAGE, message);
        myAlertDialog.setArguments(arg);
        myAlertDialog.setResultListener(resultListener);
        return myAlertDialog;
    }

    private void setResultListener(DialogResultListener resultListener) {
        this.resultListener = resultListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = getArguments().getString(EXTRA_TITLE);
        String message = getArguments().getString(EXTRA_MESSAGE);
        return new AlertDialog.Builder(getContext())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.button_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        resultListener.onPositive();
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton(R.string.button_no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        resultListener.onNegative();
                        dialogInterface.dismiss();
                    }
                }).create();
    }
}
