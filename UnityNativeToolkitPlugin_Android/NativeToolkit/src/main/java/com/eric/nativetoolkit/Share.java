package com.eric.nativetoolkit;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;

public class Share
{
    private static final String TAG = "EricDialog";  //Tag to follow logcats
    private Fragment fragment;
    //CALLBACK NAMES
    static final String CALLBACK_SHARE = "OnContentShared";

    public Share(Fragment fragment)
    {
        this.fragment = fragment;
    }

    public void ShareText(String title, String content)
    {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, content);
        sendIntent.setType("text/plain");
        Intent shareIntent = Intent.createChooser(sendIntent, title);
        fragment.startActivity(shareIntent);
    }

    public void ShareImage(String title, Uri content)
    {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_STREAM, content);
        sendIntent.setType("image/jpeg");
        sendIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        Intent shareIntent = Intent.createChooser(sendIntent, title);
        fragment.startActivity(shareIntent);
    }
    public void ShareImage(String title, String content)
    {
        this.ShareImage(title, Uri.parse(content));
    }
}
