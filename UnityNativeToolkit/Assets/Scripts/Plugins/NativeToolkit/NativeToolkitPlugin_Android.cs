using UnityEngine;

public class NativeToolkitPlugin_Android : NativeToolkitPlugin
{
    public string javaClassPackageName = "com.eric.nativetoolkit.NativeToolkitFragment";
    private AndroidJavaClass javaClass = null;
    AndroidJavaObject instance = null;

    public NativeToolkitPlugin_Android(string gameObjectName) : base(gameObjectName) { }

    protected override void SetUp()
    {
        javaClass = new AndroidJavaClass(javaClassPackageName);
        javaClass.CallStatic("SetUp", gameObjectName);
        instance = javaClass.GetStatic<AndroidJavaObject>("instance");
    }

    #region Features    
    #region Camera
    public override void TakeShot()
    {
        instance.Call("TakeShot");
    }

    public override void SaveShotsOnGallery(bool saveShotsOnGallery)
    {
        instance.Call("SaveShotsOnGallery", saveShotsOnGallery);
    }

    public override void SaveShotsOnPrivateDirectory(bool saveShotsOnPrivateDirectory)
    {
        instance.Call("SaveShotsOnPrivateDirectory", saveShotsOnPrivateDirectory);
    }

    public override void PickPhotoFromGallery()
    {
        instance.Call("PickPhotoFromGallery");
    }
    #endregion
    
    #region Dialog
    public override void ShowAlertDialog(string message, string title)
    {
        instance.Call("ShowAlertDialog", message, title);
    }
    public override void ShowAlertDialog(string message, string title, bool hasPositiveButton, bool hasNegativeButton, bool hasNeutralButton)
    {
        instance.Call("ShowAlertDialog", message, title, hasPositiveButton, hasNegativeButton, hasNeutralButton);
    }

    public override void ShowDatePickerDialog()
    {
        instance.Call("ShowDatePickerDialog");
    }

    public override void ShowTimePickerDialog()
    {
        instance.Call("ShowTimePickerDialog");
    }

    public override void ShowRateAppDialog(string message, string title)
    {
        instance.Call("ShowRateAppDialog", message, title);
    }
    #endregion
    
    #region Toast
    public override void ShowToast(string message)
    {
        instance.Call("ShowToast", message);
    }
    public override void ShowToast(string message, int duration)
    {
        instance.Call("ShowToast", message, duration);
    }
    public override void ShowToast(string message, int duration, int gravity, int xOffset, int yOffset)
    {
        instance.Call("ShowToast", message, duration, gravity, xOffset, yOffset);
    }
    #endregion
    
    #region Contacts
    public override void PickContact()
    {
        instance.Call("PickContact");
    }
    #endregion
    
    #region Share
    public override void ShareText(string title, string content)
    {
        instance.Call("ShareText", title, content);
    }

    public override void ShareImage(string title, string uriContentString)
    {
        instance.Call("ShareImage", title, uriContentString);
    }
    #endregion

    #region SpeechRecognizer
    public override void StartListening()
    {
        instance.Call("StartListening");
    }

    public override void SetContinuousListening(bool isContinuous)
    {
        instance.Call("SetContinuousListening", isContinuous);
    }
    #endregion

    #region TextToSpeech
    public override void Speak(string textToSpeak)
    {
        instance.Call("Speak", textToSpeak);
    }

    public override void SetLanguage(string language)
    {
        instance.Call("SetLanguage", language);
    }

    public override void SetLanguage(string language, string country)
    {
        instance.Call("SetLanguage", language, country);
    }

    public override void Speak(string textToSpeak, string language)
    {
        instance.Call("Speak", textToSpeak, language);
    }

    public override void Speak(string textToSpeak, string language, string country)
    {
        instance.Call("Speak", textToSpeak, language, country);
    }
    #endregion
    #endregion
}