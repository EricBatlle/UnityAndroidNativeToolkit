using System.Collections;
using System.IO;
using System.Text;
using UnityEngine;
using UnityEngine.UI;
using static NativeToolkitPlugin;

public class NativeToolkit : MonoBehaviour, INativeToolkitPlugin
{
    private NativeToolkitPlugin plugin = null;
    public Button btn = null;
    public Button btn_bot = null;
    public Button btn_bot2 = null;
    public Texture2D target = null;
    public RawImage rawImage = null;

    void Start()
    {
        plugin = NativeToolkitPlugin.GetPlatformPluginVersion(this.gameObject.name);
        
        btn.onClick.AddListener(() => plugin.ShareText("Title of sharing", "https://github.com/EricBatlle"));
        btn_bot.onClick.AddListener(() => {
            plugin.Speak("Hello Eric, either you got a missile? Can you please say something longer to check if this is working? Thanks!", "en", "US");
        });
        btn_bot2.onClick.AddListener(() => {
            plugin.Speak("Hello Eric, either you got a missile? Can you please say something longer to check if this is working? Thanks!", "en", "IE");
        });
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

    public void OnShotTaken(string result)
    {
        Debug.Log(result);
    }

    public void OnGalleryPhotoPicked(string result)
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