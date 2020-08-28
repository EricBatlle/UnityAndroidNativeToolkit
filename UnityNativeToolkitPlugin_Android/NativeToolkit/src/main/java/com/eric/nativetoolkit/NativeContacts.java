package com.eric.nativetoolkit;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;

public class NativeContacts
{
    private static final String TAG = "EricContacts";  //Tag to follow logcats
    private Fragment fragment;
    //CALLBACK NAMES
    static final String CALLBACK_CONTACTS_PICKCONTACT = "OnContactPicked";

    public NativeContacts(Fragment fragment)
    {
        this.fragment = fragment;
    }

    private Activity getActivity()
    {
        return this.fragment.getActivity();
    }

    public void PickContact()
    {
        Intent pickContactIntent = new Intent(Intent.ACTION_PICK);
        pickContactIntent.setDataAndType(Contacts.CONTENT_URI, Phone.CONTENT_TYPE);
        fragment.startActivityForResult(pickContactIntent, NativeToolkitFragment.REQUEST_PICK_CONTACT);
    }

    public String GetContactAsStringInfoFromUri(Uri dataUri)
    {
        String[] projection = new String[]{Phone.CONTACT_ID, Phone.DISPLAY_NAME, Phone.NUMBER};

        // get display name and phone number
        Cursor cursor = getActivity().getContentResolver().query(dataUri, projection, null, null, null);
        String name = "";
        String number = "";
        if (cursor != null && cursor.moveToFirst())
        {
            name = cursor.getString(cursor.getColumnIndex(Phone.DISPLAY_NAME));
            number = cursor.getString(cursor.getColumnIndex(Phone.NUMBER));
        }

        String id = cursor.getString(cursor.getColumnIndex(Phone.CONTACT_ID));
        cursor.close();

        String contactInfo = name + "~" + number + "~" + id;
        return contactInfo;
    }
}
