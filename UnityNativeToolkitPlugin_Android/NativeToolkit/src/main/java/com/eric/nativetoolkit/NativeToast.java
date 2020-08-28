package com.eric.nativetoolkit;

import android.app.Activity;
import android.app.Fragment;
import android.widget.Toast;

public class NativeToast
{
    private static final String TAG = "EricToast";  //Tag to follow logcats
    private Fragment fragment;

    public NativeToast(Fragment fragment)
    {
        this.fragment = fragment;
    }

    private Activity getActivity()
    {
        return this.fragment.getActivity();
    }

    public void ShowToast(String message)
    {
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(getActivity(), message, duration);
        toast.show();
    }

    public void ShowToast(String message, int duration)
    {
        Toast toast = Toast.makeText(getActivity(), message, duration);
        toast.show();
    }

    public void ShowToast(String message, int duration, int gravity, int xOffset, int yOffset)
    {
        Toast toast = Toast.makeText(getActivity(), message, duration);
        toast.setGravity(gravity, xOffset, yOffset);
        toast.show();
    }}
