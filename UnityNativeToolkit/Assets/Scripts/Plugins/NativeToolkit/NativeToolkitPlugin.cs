using System.Collections.Generic;
using UnityEngine;

public abstract class NativeToolkitPlugin
{
    protected string gameObjectName = "NativeToolkit";

    protected NativeToolkitPlugin(string gameObjectName = null)
    {
        this.gameObjectName = gameObjectName;
        this.SetUp();
    }
    public static NativeToolkitPlugin GetPlatformPluginVersion(string gameObjectName = null)
    {
        if (Application.isEditor)
            return new NativeToolkitPlugin_Editor(gameObjectName);
        else
        {
            #if UNITY_ANDROID
                return new NativeToolkitPlugin_Android(gameObjectName);
            #endif

#pragma warning disable CS0162 // Unreachable code detected
            Debug.LogWarning("Remember to set project build to mobile device");
#pragma warning restore CS0162 // Unreachable code detected
            return null;
        }
    }

    public interface INativeToolkitPlugin : INativeCamera, INativeDialog, INativeContacts, INativeSpeechRecognizer, INativeTextToSpeech
    {
        void OnResult(string result);        
    }    
    protected abstract void SetUp();

    //Features
    #region Camera/Media
    public interface INativeCamera
    {
        void OnShotTaken(string shotPath);
        void OnGalleryPhotoPicked(string galleryPhotoPath);
    }
    public abstract void TakeShot();
    public abstract void SaveShotsOnGallery(bool saveShotsOnGallery);
    public abstract void SaveShotsOnPrivateDirectory(bool saveShotsOnPrivateDirectory);
    public abstract void PickPhotoFromGallery();
    #endregion
    #region Dialogs
    public interface INativeDialog
    {
        //AlertDialog
        void OnDialogPositive(string result);
        void OnDialogNegative(string result);
        void OnDialogNeutral(string result);
        //DatePicker
        void OnDatePicked(string result);
        //TimePicker
        void OnTimePicked(string result);
        //RateAppDialog
        void OnRatedAppPositive(string result);
        void OnRatedAppNegative(string result);
        void OnRatedAppNeutral(string result);
    }
    public abstract void ShowAlertDialog(string message, string title);
    public abstract void ShowAlertDialog(string message, string title, bool hasPositiveButton, bool hasNegativeButton, bool hasNeutralButton);
    public abstract void ShowDatePickerDialog();
    public abstract void ShowTimePickerDialog();
    public abstract void ShowRateAppDialog(string message, string title);
    #endregion
    #region Toast
    public enum AndroidGravityValues { CENTER, TOP, BOTTOM, LEFT, RIGHT }
    public static Dictionary<AndroidGravityValues, int> GravityDict = new Dictionary<AndroidGravityValues, int>()
    {
        //So It can be used with byte operators: CENTER|TOP = CenterTop position
        { AndroidGravityValues.CENTER, 17 },
        { AndroidGravityValues.TOP, 48 },
        { AndroidGravityValues.BOTTOM, 80 },
        { AndroidGravityValues.LEFT, 3 },
        { AndroidGravityValues.RIGHT, 5 }
    };
    public abstract void ShowToast(string message);
    public abstract void ShowToast(string message, int duration);
    public abstract void ShowToast(string message, int duration, int gravity, int xOffset, int yOffset);
    #endregion
    #region Contacts
    public interface INativeContacts
    {
        void OnContactPicked(string result);
    }
    public abstract void PickContact();
    #endregion
    #region Sharing
    public abstract void ShareText(string title, string content);
    public abstract void ShareImage(string title, string uriContentString);
    #endregion
    #region SpeechRecognizer
    public interface INativeSpeechRecognizer
    {
        void OnSpeechReconized(string recognizedResults);
    }
    public abstract void StartListening();
    public abstract void SetContinuousListening(bool isContinuous);
    #endregion
    #region TextToSpeech
    public interface INativeTextToSpeech
    {
        void OnSpeechStart(string result);
        void OnSpeechDone(string result);
    }
    public abstract void Speak(string textToSpeak);
    public abstract void Speak(string textToSpeak, string language);
    public abstract void Speak(string textToSpeak, string language, string country);
    public abstract void SetLanguage(string language);
    public abstract void SetLanguage(string language, string country);
    #endregion
}