package com.eric.nativetoolkit;

import android.Manifest;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import com.unity3d.player.UnityPlayer;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import static android.app.Activity.RESULT_OK;

public class NativeToolkitFragment extends Fragment
{
    //DEFAULT
    private static final String TAG = "EricNativeToolkit";  //Tag to follow logcats
    public static NativeToolkitFragment instance;       // Singleton instance.
    //UNITY CONTEXT
    String gameObjectName;
    public static String resultCallbackName = "OnResult";
    //COMPONENTS
    private Camera camera = null;
    private Dialog dialog = null;
    private NativeToast toast = null;
    private NativeContacts contacts = null;
    private Share share = null;
    private NativeSpeechRecognizer speechRecognizer = null;
    private NativeTextToSpeech tts = null;

    //REQUEST CODES
    static final int REQUEST_TAKE_SHOT = 1;
    static final int REQUEST_PICK_PHOTO = 2;
    static final int REQUEST_PICK_CONTACT = 3;

    public static void SendUnityResults(String results)
    {
        UnityPlayer.UnitySendMessage(instance.gameObjectName, resultCallbackName, results);
        Log.d(TAG, results);
    }
    public static void SendUnityResults(String resultCallbackName, String results)
    {
        UnityPlayer.UnitySendMessage(instance.gameObjectName, resultCallbackName, results);
        Log.d(TAG, results);
    }
    public static void SetUp(String gameObjectName)
    {
        instance = new NativeToolkitFragment();
        instance.gameObjectName = gameObjectName; // Store 'GameObject' reference
        UnityPlayer.currentActivity.getFragmentManager().beginTransaction().add(instance, NativeToolkitFragment.TAG).commit();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        RequestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        RequestPermission(Manifest.permission.RECORD_AUDIO);
        InitializeComponents();
    }

    public void InitializeComponents()
    {
        camera = new Camera(this);
        dialog = new Dialog(this);
        toast = new NativeToast(this);
        contacts = new NativeContacts(this);
        share = new Share(this);
        speechRecognizer = new NativeSpeechRecognizer(this);
        tts = new NativeTextToSpeech(this);
    }

    private void RequestPermission(String manifestPermissionName)
    {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(getActivity(), manifestPermissionName) != PackageManager.PERMISSION_GRANTED)
        {
            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), manifestPermissionName))
            {
                // Show an explanation to the user *asynchronously* -- don't block this thread waiting for the user's response!
                // After the user sees the explanation, try again to request the permission.
                Log.d(TAG, "Permission demands an explanation");
            }
            else
            {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(getActivity(), new String[]{manifestPermissionName},20);
                // Callback method gets the result of the request.
            }
        }
        else
        {
            // Permission has already been granted
            Log.d(TAG, "Permission has already been granted");
        }
    }


    //region Camera
    public void TakeShot()
    {
        camera.DispatchTakeShotIntent();
    }
    public void SaveShotsOnGallery(boolean saveShotsOnGallery)
    {
        camera.SetSaveShotsOnGallery(saveShotsOnGallery);
    }
    public void SaveShotsOnPrivateDirectory(boolean saveShotsOnPrivateDirectory)
    {
        camera.SetSaveShotsOnPrivateDirectory(saveShotsOnPrivateDirectory);
    }
    public void PickPhotoFromGallery()
    {
        camera.DispatchPickPhotoFromGalleryIntent();
    }
    //endregion

    //region Dialog
    public void ShowAlertDialog(String message, String title)
    {
        dialog.ShowAlertDialog(message, title);
    }
    public void ShowAlertDialog(String message, String title, boolean hasPositiveButton, boolean hasNegativeButton, boolean hasNeutralButton)
    {
        dialog.ShowAlertDialog(message, title, hasPositiveButton, hasNegativeButton, hasNeutralButton);
    }
    public void ShowDatePickerDialog()
    {
        dialog.ShowDatePickerDialog();
    }
    public void ShowTimePickerDialog()
    {
        dialog.ShowTimePickerDialog();
    }
    public void ShowRateAppDialog(String message, String title)
    {
        dialog.ShowRateAppDialog(message, title);
    }
    //endregion

    //region Toast
    public void ShowToast(String message)
    {
        toast.ShowToast(message);
    }
    public void ShowToast(String message, int duration)
    {
        toast.ShowToast(message, duration);
    }
    public void ShowToast(String message, int duration, int gravity, int xOffset, int yOffset)
    {
        toast.ShowToast(message, duration, gravity, xOffset, yOffset);
    }
    //endregion

    //region Contacts
    public void PickContact()
    {
        contacts.PickContact();
    }
    //endregion

    //region Share
    public void ShareText(String title, String content)
    {
        share.ShareText(title, content);
    }
    public void ShareImage(String title, String uriContentString)
    {
        share.ShareImage(title, uriContentString);
    }
    //endregion

    //region SpeechRecognizer
    public void StartListening()
    {
        speechRecognizer.StartListening();
    }
    public void SetContinuousListening(boolean isContinuous)
    {
        speechRecognizer.SetContinuousListening(isContinuous);
    }
    //endregion

    //region TextToSpeech
    public void Speak(String textToSpeak)
    {
        tts.Speak(textToSpeak);
    }
    public void Speak(String textToSpeak, String language)
    {
        tts.Speak(textToSpeak, language);
    }
    public void Speak(String textToSpeak, String language, String country)
    {
        tts.Speak(textToSpeak, language, country);
    }
    public void SetLanguage(String language)
    {
        tts.SetLanguage(language);
    }
    public void SetLanguage(String language, String country)
    {
        tts.SetLanguage(language, country);
    }
    //endregion

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        SendUnityResults(resultCallbackName, "activityResult"+requestCode+"_"+"resultCode");

        if (requestCode == REQUEST_TAKE_SHOT && resultCode == RESULT_OK)
        {
            SendUnityResults(Camera.CALLBACK_CAMERA_TAKESHOT, camera.currentPhotoPath);
        }
        else if(requestCode == REQUEST_PICK_PHOTO && resultCode == RESULT_OK)
        {
            Uri selectedImage = data.getData();
            SendUnityResults(Camera.CALLBACK_CAMERA_PICKGALLERYPHOTO, getRealPathFromURI(selectedImage));
        }
        else if(requestCode == REQUEST_PICK_CONTACT && resultCode == RESULT_OK)
        {
            Uri selectedContact = data.getData();
            SendUnityResults(NativeContacts.CALLBACK_CONTACTS_PICKCONTACT, contacts.GetContactAsStringInfoFromUri(selectedContact));
        }
    }

    //region Utils
    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getActivity().getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }
    //endregion
}
