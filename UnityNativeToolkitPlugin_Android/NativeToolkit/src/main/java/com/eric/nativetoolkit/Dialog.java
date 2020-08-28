package com.eric.nativetoolkit;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.text.format.DateFormat;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;

public class Dialog
{
    private static final String TAG = "EricDialog";  //Tag to follow logcats
    private Fragment fragment;
    //CALLBACK NAMES
    static final String CALLBACK_DIALOG_POSITIVE = "OnDialogPositive";
    static final String CALLBACK_DIALOG_NEGATIVE = "OnDialogNegative";
    static final String CALLBACK_DIALOG_NEUTRAL = "OnDialogNeutral";
    static final String CALLBACK_DATEPICKER = "OnDatePicked";
    static final String CALLBACK_TIMEPICKER = "OnTimePicked";
    static final String CALLBACK_RATEAPP_POSITIVE = "OnRatedAppPositive";
    static final String CALLBACK_RATEAPP_NEGATIVE = "OnRatedAppNegative";
    static final String CALLBACK_RATEAPP_NEUTRAL = "OnRatedAppNeutral";

    public Dialog(Fragment fragment)
    {
        this.fragment = fragment;
    }

    private Activity getActivity()
    {
        return this.fragment.getActivity();
    }

    public void ShowAlertDialog(String dialogMessage, String dialogTitle, boolean hasPositiveButton, boolean hasNegativeButton, boolean hasNeutralButton)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(dialogMessage)
                .setTitle(dialogTitle);

        if(hasPositiveButton)
        {
            builder.setPositiveButton(getResourceString(R.string.DIALOG_POSITIVE), new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int id)
                {
                    NativeToolkitFragment.SendUnityResults(CALLBACK_DIALOG_POSITIVE, getResourceString(R.string.RESPONSE_DIALOG_POSITIVE));
                }
            });
        }

        if(hasNegativeButton)
        {
            builder.setNegativeButton(getResourceString(R.string.DIALOG_NEGATIVE), new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int id)
                {
                    NativeToolkitFragment.SendUnityResults(CALLBACK_DIALOG_NEGATIVE, getResourceString(R.string.RESPONSE_DIALOG_NEGATIVE));
                }
            });
        }

        if(hasNeutralButton)
        {
            builder.setNeutralButton(getResourceString(R.string.DIALOG_NEUTRAL), new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int id)
                {
                    NativeToolkitFragment.SendUnityResults(CALLBACK_DIALOG_NEUTRAL, getResourceString(R.string.RESPONSE_DIALOG_NEUTRAL));
                }
            });
        }

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void ShowAlertDialog(String dialogMessage, String dialogTitle)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(dialogMessage)
                .setTitle(dialogTitle);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void ShowDatePickerDialog()
    {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                String datePicked = year + "~" + month + "~" + day;
                NativeToolkitFragment.SendUnityResults(CALLBACK_DATEPICKER, datePicked);
            }
        }, year, month, day);

        dialog.show();
    }

    public void ShowTimePickerDialog()
    {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        TimePickerDialog dialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute)
            {
                String timePicked = hour + "~" + minute;
                NativeToolkitFragment.SendUnityResults(CALLBACK_TIMEPICKER, timePicked);
            }
        }, hour, minute, DateFormat.is24HourFormat(getActivity()));

        dialog.show();
    }

    public void ShowRateAppDialog(String dialogMessage, String dialogTitle)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(dialogMessage)
                .setTitle(dialogTitle);

        builder.setPositiveButton(getResourceString(R.string.RATEDIALOG_POSITIVE), new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                NativeToolkitFragment.SendUnityResults(CALLBACK_RATEAPP_POSITIVE, getResourceString(R.string.RESPONSE_RATEDIALOG_POSITIVE));
                fragment.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getActivity().getPackageName())));
            }
        });

        builder.setNegativeButton(getResourceString(R.string.RATEDIALOG_NEGATIVE), new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                NativeToolkitFragment.SendUnityResults(CALLBACK_RATEAPP_NEGATIVE, getResourceString(R.string.RESPONSE_RATEDIALOG_POSITIVE));
            }
        });

        builder.setNeutralButton(getResourceString(R.string.RATEDIALOG_NEUTRAL), new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                NativeToolkitFragment.SendUnityResults(CALLBACK_RATEAPP_NEUTRAL, getResourceString(R.string.RESPONSE_RATEDIALOG_NEUTRAL));
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public final String getResourceString(int resId) {
        return fragment.getResources().getString(resId);
    }
}
