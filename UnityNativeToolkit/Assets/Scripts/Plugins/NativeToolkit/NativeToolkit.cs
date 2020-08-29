using System.Collections;
using System.IO;
using System.Text;
using TMPro;
using UnityEngine;
using UnityEngine.UI;
using static NativeToolkitPlugin;

public class NativeToolkit : MonoBehaviour, INativeToolkitPlugin
{
    private NativeToolkitPlugin plugin = null;

    #region Variables
    [Header("Result")]
    public TextMeshProUGUI resultText = null;

    [Header("Camera/Media")]
    public Button takeShot_btn = null;
    public Button visualizeShot_btn = null;
    public RawImage shotRawImg = null;
    [Space()]
    public Button pickPhotoFromGallery_btn = null;
    public Button visualizeGalleryPhoto_btn = null;
    public RawImage galleryPhotoImg = null;
    [Space()]
    public Toggle saveShotsOnGallery = null;
    public Toggle saveShotsOnPrivateDirectory = null;
    private string shotPath = "";
    private string galleryPhotoPath = "";

    [Header("Dialogs")]
    public Button showAlertDialog_btn = null;
    public TMP_InputField dialogMessage = null;
    public TMP_InputField dialogTitle = null;
    public Toggle hasPositiveButton = null;
    public Toggle hasNegativeButton = null;
    public Toggle hasNeutralButton = null;
    [Space()]
    public Button showDatePickerDialog_btn = null;
    public Button showTimePickerDialog_btn = null;
    public Button showRateAppDialog_btn = null;

    [Header("Toast")]
    public Button showToast_btn = null;
    public TMP_InputField toastMessage = null;

    [Header("Contacts")]
    public Button pickContact_btn = null;

    [Header("Sharing")]
    public Button shareText_btn = null;
    public Button shareShot_btn = null;
    public TMP_InputField sharingTitle = null;
    public TMP_InputField textToShare = null;

    [Header("SpeechRecognizer")]
    public Button startListening_btn = null;
    public Toggle setContinousListening = null;

    [Header("TextToSpeech")]
    public Button speak_btn = null;
    public TMP_InputField textToSpeak = null;
    public TMP_InputField language = null;
    public TMP_InputField country = null;
    #endregion

    void Start()
    {
        plugin = NativeToolkitPlugin.GetPlatformPluginVersion(this.gameObject.name);
        //Camera/Media
        takeShot_btn.onClick.AddListener(() => { plugin.TakeShot(); });
        visualizeShot_btn.onClick.AddListener(() => { shotRawImg.texture = LoadPNG(shotPath); });
        visualizeGalleryPhoto_btn.onClick.AddListener(() => { galleryPhotoImg.texture = LoadPNG(galleryPhotoPath); });
        saveShotsOnGallery.onValueChanged.AddListener((isOn) => { plugin.SaveShotsOnGallery(isOn); });
        saveShotsOnPrivateDirectory.onValueChanged.AddListener((isOn) => { plugin.SaveShotsOnGallery(isOn); });
        pickPhotoFromGallery_btn.onClick.AddListener(() => { plugin.PickPhotoFromGallery(); });
        //Dialogs
        showAlertDialog_btn.onClick.AddListener(() => { plugin.ShowAlertDialog(dialogMessage.text, dialogTitle.text, hasPositiveButton.isOn, hasNegativeButton.isOn, hasNeutralButton.isOn); });
        showDatePickerDialog_btn.onClick.AddListener(() => { plugin.ShowDatePickerDialog(); });
        showTimePickerDialog_btn.onClick.AddListener(() => { plugin.ShowTimePickerDialog(); });
        showRateAppDialog_btn.onClick.AddListener(() => { plugin.ShowRateAppDialog(dialogMessage.text, dialogTitle.text); });
        //Toast
        showToast_btn.onClick.AddListener(() => { plugin.ShowToast(toastMessage.text); });
        //Contacts
        pickContact_btn.onClick.AddListener(() => { plugin.PickContact(); });
        //Sharing
        shareText_btn.onClick.AddListener(() => { plugin.ShareText(sharingTitle.text, textToShare.text); });
        shareShot_btn.onClick.AddListener(() => { plugin.ShareImage(sharingTitle.text, shotPath); });
        //SpeechRecognizer
        startListening_btn.onClick.AddListener(() => { plugin.StartListening(); });
        setContinousListening.onValueChanged.AddListener((isOn) => { plugin.SetContinuousListening(isOn); });
    }

    public void SetResultText(string result)
    {
        resultText.text = result;
    }
    public static Texture2D LoadPNG(string filePath)
    {
        Texture2D tex = null;
        byte[] fileData;

        if (File.Exists(filePath))
        {
            fileData = File.ReadAllBytes(filePath);
            tex = new Texture2D(Screen.width, Screen.height);
            tex.LoadImage(fileData); //..this will auto-resize the texture dimensions.
        }
        else
        {
            Debug.Log("FilePath " + filePath + "do not exist");
        }
        return tex;
    }

    public void OnResult(string recognizedResult)
    {
        Debug.Log(recognizedResult);
        //rawImage.texture = LoadPNG(recognizedResult);
    }

    #region FeaturesCallbacks
    #region Camera/Media
    public void OnShotTaken(string shotPath)
    {
        SetResultText("Shot taken and saved in: " + shotPath);
        this.shotPath = shotPath;
    }

    public void OnGalleryPhotoPicked(string galleryPhotoPath)
    {
        SetResultText("Photo picked from: " + galleryPhotoPath);
        this.galleryPhotoPath = galleryPhotoPath;
    }
    #endregion
    
    #region Dialogs
    public void OnDialogPositive(string result)
    {
        SetResultText("On Dialog:" + result);
    }

    public void OnDialogNegative(string result)
    {
        SetResultText("On Dialog:" + result);
    }

    public void OnDialogNeutral(string result)
    {
        SetResultText("On Dialog:" + result);
    }

    public void OnDatePicked(string year_month_day)
    {
        SetResultText("Year~Month~Day:" + year_month_day);
    }

    public void OnTimePicked(string hour_minute)
    {
        SetResultText("Hour~Minute:" + hour_minute);
    }

    public void OnRatedApp(string result)
    {
        SetResultText("On Rated App:" + result);
    }

    public void OnRatedAppPositive(string result)
    {
        SetResultText("On Rated App:" + result);
    }

    public void OnRatedAppNegative(string result)
    {
        SetResultText("On Rated App:" + result);
    }

    public void OnRatedAppNeutral(string result)
    {
        SetResultText("On Rated App:" + result);
    }
    #endregion

    #region Contacts
    public void OnContactPicked(string name_number_id)
    {
        SetResultText("Name~Number~Id:" + name_number_id);
    }
    #endregion

    #region SpeechRecognizer
    public void OnSpeechReconized(string recognizedResults)
    {
        SetResultText(recognizedResults);
    }
    #endregion

    #region SpeechToText
    public void OnSpeechStart(string result)
    {
        SetResultText(result);
    }

    public void OnSpeechDone(string result)
    {
        SetResultText(result);
    }
    #endregion
    #endregion
}