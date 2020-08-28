using UnityEngine;

public class NativeToolkitPlugin_Editor : NativeToolkitPlugin
{
    public NativeToolkitPlugin_Editor(string gameObjectName) : base(gameObjectName) { }

    protected override void SetUp()
    {
        Debug.LogWarning(" <b> WARNING </b>: You are running this plugin on Editor mode. Real recognition only works running on mobile device.");
    }

    #region Features
    public override void SaveShotsOnGallery(bool saveShotsOnGallery)
    {
        throw new System.NotImplementedException();
    }

    public override void SaveShotsOnPrivateDirectory(bool saveShotsOnPrivateDirectory)
    {
        throw new System.NotImplementedException();
    }

    public override void TakeShot()
    {
        throw new System.NotImplementedException();
    }

    public override void PickPhotoFromGallery()
    {
        throw new System.NotImplementedException();
    }

    public override void ShowAlertDialog(string message, string title)
    {
        throw new System.NotImplementedException();
    }

    public override void ShowAlertDialog(string message, string title, bool hasPositiveButton, bool hasNegativeButton, bool hasNeutralButton)
    {
        throw new System.NotImplementedException();
    }

    public override void ShowDatePickerDialog()
    {
        throw new System.NotImplementedException();
    }

    public override void ShowTimePickerDialog()
    {
        throw new System.NotImplementedException();
    }

    public override void ShowRateAppDialog(string message, string title)
    {
        throw new System.NotImplementedException();
    }

    public override void ShowToast(string message, int duration, int gravity, int xOffset, int yOffset)
    {
        throw new System.NotImplementedException();
    }

    public override void ShowToast(string message)
    {
        throw new System.NotImplementedException();
    }

    public override void ShowToast(string message, int duration)
    {
        throw new System.NotImplementedException();
    }

    public override void PickContact()
    {
        throw new System.NotImplementedException();
    }
    
    public override void ShareText(string title, string content)
    {
        throw new System.NotImplementedException();
    }

    public override void ShareImage(string title, string uriContentString)
    {
        throw new System.NotImplementedException();
    }

    public override void StartListening()
    {
        throw new System.NotImplementedException();
    }

    public override void SetContinuousListening(bool isContinuous)
    {
        throw new System.NotImplementedException();
    }

    public override void Speak(string textToSpeak)
    {
        throw new System.NotImplementedException();
    }

    public override void SetLanguage(string language)
    {
        throw new System.NotImplementedException();
    }

    public override void SetLanguage(string language, string country)
    {
        throw new System.NotImplementedException();
    }

    public override void Speak(string textToSpeak, string language)
    {
        throw new System.NotImplementedException();
    }

    public override void Speak(string textToSpeak, string language, string country)
    {
        throw new System.NotImplementedException();
    }
    #endregion
}