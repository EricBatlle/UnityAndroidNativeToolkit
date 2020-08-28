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
        //
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
    public void OnDialogPositive(string result)
    {
        Debug.Log(result);
    }

    public void OnDialogNegative(string result)
    {
        Debug.Log(result);
    }

    public void OnDialogNeutral(string result)
    {
        Debug.Log(result);
    }

    public void OnDatePicked(string result)
    {
        Debug.Log(result);
    }

    public void OnTimePicked(string result)
    {
        Debug.Log(result);
    }
    
    public void OnRatedApp(string result)
    {
        Debug.Log(result);
    }

    public void OnRatedAppPositive(string result)
    {
        Debug.Log(result);
    }

    public void OnRatedAppNegative(string result)
    {
        Debug.Log(result);
    }

    public void OnRatedAppNeutral(string result)
    {
        Debug.Log(result);
    }

    public void OnContactPicked(string result)
    {
        Debug.Log(result);
    }

    public void OnSpeechReconized(string recognizedResults)
    {
        Debug.Log(recognizedResults);
    }

    public void OnSpeechStart(string result)
    {
        Debug.Log(result);
    }

    public void OnSpeechDone(string result)
    {
        Debug.Log(result);
    }
    #endregion
}