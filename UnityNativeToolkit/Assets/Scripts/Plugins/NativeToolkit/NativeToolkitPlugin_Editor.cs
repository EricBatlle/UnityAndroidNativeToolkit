using UnityEngine;

public class NativeToolkitPlugin_Editor : NativeToolkitPlugin
{
    public NativeToolkitPlugin_Editor(string gameObjectName) : base(gameObjectName) { }

    protected override void SetUp()
    {
        Debug.LogWarning(" <b> WARNING </b>: You are running this plugin on Editor mode. Real plugin only works running on mobile device.");
    }

    #region Features
    public override void SaveShotsOnGallery(bool saveShotsOnGallery)
    {
        Debug.LogWarning(" <b> WARNING </b>: You are trying one feature of the plugin on Editor mode. Real features only work running on mobile device.");
    }

    public override void SaveShotsOnPrivateDirectory(bool saveShotsOnPrivateDirectory)
    {
        Debug.LogWarning(" <b> WARNING </b>: You are trying one feature of the plugin on Editor mode. Real features only work running on mobile device.");
    }

    public override void TakeShot()
    {
        Debug.LogWarning(" <b> WARNING </b>: You are trying one feature of the plugin on Editor mode. Real features only work running on mobile device.");
    }

    public override void PickPhotoFromGallery()
    {
        Debug.LogWarning(" <b> WARNING </b>: You are trying one feature of the plugin on Editor mode. Real features only work running on mobile device.");
    }

    public override void ShowAlertDialog(string message, string title)
    {
        Debug.LogWarning(" <b> WARNING </b>: You are trying one feature of the plugin on Editor mode. Real features only work running on mobile device.");
    }

    public override void ShowAlertDialog(string message, string title, bool hasPositiveButton, bool hasNegativeButton, bool hasNeutralButton)
    {
        Debug.LogWarning(" <b> WARNING </b>: You are trying one feature of the plugin on Editor mode. Real features only work running on mobile device.");
    }

    public override void ShowDatePickerDialog()
    {
        Debug.LogWarning(" <b> WARNING </b>: You are trying one feature of the plugin on Editor mode. Real features only work running on mobile device.");
    }

    public override void ShowTimePickerDialog()
    {
        Debug.LogWarning(" <b> WARNING </b>: You are trying one feature of the plugin on Editor mode. Real features only work running on mobile device.");
    }

    public override void ShowRateAppDialog(string message, string title)
    {
        Debug.LogWarning(" <b> WARNING </b>: You are trying one feature of the plugin on Editor mode. Real features only work running on mobile device.");
    }

    public override void ShowToast(string message, int duration, int gravity, int xOffset, int yOffset)
    {
        Debug.LogWarning(" <b> WARNING </b>: You are trying one feature of the plugin on Editor mode. Real features only work running on mobile device.");
    }

    public override void ShowToast(string message)
    {
        Debug.LogWarning(" <b> WARNING </b>: You are trying one feature of the plugin on Editor mode. Real features only work running on mobile device.");
    }

    public override void ShowToast(string message, int duration)
    {
        Debug.LogWarning(" <b> WARNING </b>: You are trying one feature of the plugin on Editor mode. Real features only work running on mobile device.");
    }

    public override void PickContact()
    {
        Debug.LogWarning(" <b> WARNING </b>: You are trying one feature of the plugin on Editor mode. Real features only work running on mobile device.");
    }

    public override void ShareText(string title, string content)
    {
        Debug.LogWarning(" <b> WARNING </b>: You are trying one feature of the plugin on Editor mode. Real features only work running on mobile device.");
    }

    public override void ShareImage(string title, string uriContentString)
    {
        Debug.LogWarning(" <b> WARNING </b>: You are trying one feature of the plugin on Editor mode. Real features only work running on mobile device.");
    }

    public override void StartListening()
    {
        Debug.LogWarning(" <b> WARNING </b>: You are trying one feature of the plugin on Editor mode. Real features only work running on mobile device.");
    }

    public override void SetContinuousListening(bool isContinuous)
    {
        Debug.LogWarning(" <b> WARNING </b>: You are trying one feature of the plugin on Editor mode. Real features only work running on mobile device.");
    }

    public override void Speak(string textToSpeak)
    {
        Debug.LogWarning(" <b> WARNING </b>: You are trying one feature of the plugin on Editor mode. Real features only work running on mobile device.");
    }

    public override void SetLanguage(string language)
    {
        Debug.LogWarning(" <b> WARNING </b>: You are trying one feature of the plugin on Editor mode. Real features only work running on mobile device.");
    }

    public override void SetLanguage(string language, string country)
    {
        Debug.LogWarning(" <b> WARNING </b>: You are trying one feature of the plugin on Editor mode. Real features only work running on mobile device.");
    }

    public override void Speak(string textToSpeak, string language)
    {
        Debug.LogWarning(" <b> WARNING </b>: You are trying one feature of the plugin on Editor mode. Real features only work running on mobile device.");
    }

    public override void Speak(string textToSpeak, string language, string country)
    {
        Debug.LogWarning(" <b> WARNING </b>: You are trying one feature of the plugin on Editor mode. Real features only work running on mobile device.");
    }
    #endregion
}