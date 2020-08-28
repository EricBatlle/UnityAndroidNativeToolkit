package com.eric.nativetoolkit;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.core.content.FileProvider;

public class Camera
{
    private static final String TAG = "EricCamera";  //Tag to follow logcats
    private Fragment fragment;
    //CALLBACK NAMES
    static final String CALLBACK_CAMERA_TAKESHOT = "OnShotTaken";
    static final String CALLBACK_CAMERA_PICKGALLERYPHOTO = "OnGalleryPhotoPicked";
    //CAMERA
    private static String fileProviderAuthority = "com.eric.nativetoolkit.fileprovider";
    private boolean isSavingShotsOnPrivateDirectory = false;
    private boolean isSavingShotsOnGallery = false;
    public String currentPhotoPath;
    public Uri currentPhotoUri;

    public Camera(Fragment fragment)
    {
        this.fragment = fragment;
    }

    private Activity getActivity()
    {
        return this.fragment.getActivity();
    }

    public void SetSaveShotsOnGallery(boolean saveShotsOnGallery)
    {
        this.isSavingShotsOnGallery = saveShotsOnGallery;
    }
    public void SetSaveShotsOnPrivateDirectory(boolean saveShotsOnPrivateDirectory)
    {
        this.isSavingShotsOnPrivateDirectory = saveShotsOnPrivateDirectory;
    }

    public void DispatchTakeShotIntent()
    {
        PackageManager packageManager = getActivity().getPackageManager();
        if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY))
        {
            Intent takeShotIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            takeShotIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

            // Ensure that there's a camera activity to handle the intent
            if (takeShotIntent.resolveActivity(packageManager) != null)
            {
                // Create the File where the photo should go
                File photoFile = null;
                try
                {
                    photoFile = CreateImageFile();
                }
                catch (IOException ex)
                {
                    Log.d(TAG, "Error occurred while creating the File:" + ex.toString());
                }
                // Continue only if the File was successfully created
                if (photoFile != null)
                {
                    currentPhotoUri = FileProvider.getUriForFile(getActivity(), fileProviderAuthority, photoFile);
                    takeShotIntent.putExtra(MediaStore.EXTRA_OUTPUT, currentPhotoUri);  //remember EXTRA_OUTPUT == getData returns null
                    fragment.startActivityForResult(takeShotIntent, NativeToolkitFragment.REQUEST_TAKE_SHOT);
                    if(isSavingShotsOnGallery)
                        AddShotToGallery();
                }
            }
        }
    }

    public void DispatchPickPhotoFromGalleryIntent()
    {
        Intent pickPhotoIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickPhotoIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        fragment.startActivityForResult(pickPhotoIntent , NativeToolkitFragment.REQUEST_PICK_PHOTO);
    }

    private File CreateImageFile() throws IOException
    {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPG_" + timeStamp + "_";

        File storageDir;
        if(isSavingShotsOnPrivateDirectory)
            storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        else
            storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",  /* suffix */
                storageDir      /* directory */
        );
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void AddShotToGallery()
    {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(currentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        getActivity().sendBroadcast(mediaScanIntent);
    }
}
