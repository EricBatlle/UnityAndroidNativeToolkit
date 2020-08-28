package com.eric.nativetoolkit;

import android.app.Activity;
import android.app.Fragment;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;

import java.util.HashMap;
import java.util.Locale;

import static com.eric.nativetoolkit.NativeToolkitFragment.SendUnityResults;

public class NativeTextToSpeech
{
    private static final String TAG = "EricTTS";  //Tag to follow logcats
    private Fragment fragment;
    //CALLBACK NAMES
    static final String CALLBACK_TTS_START = "OnSpeechStart";
    static final String CALLBACK_TTS_DONE = "OnSpeechDone";
    //TTS
    TextToSpeech tts;
    String currentTextToSpeak = "defaultText";
    Locale currentLanguage = null;
    HashMap<String, String> hashAlarm = new HashMap<String, String>();
    UtteranceProgressListener utteranceProgressListener = new UtteranceProgressListener()
    {
        @Override
        public void onDone(String utteranceId)
        {
            SendUnityResults(CALLBACK_TTS_DONE,"TTS_DONE");
        }
        @Override
        public void onError(String utteranceId){}
        @Override
        public void onStart(String utteranceId)
        {
            SendUnityResults(CALLBACK_TTS_DONE,"TTS_START");
        }
    };

    public NativeTextToSpeech(Fragment fragment)
    {
        this.fragment = fragment;
        hashAlarm.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "UTTERANCE_ID");
    }

    private Activity getActivity()
    {
        return this.fragment.getActivity();
    }

    public void Speak(String textToSpeak)
    {
        this.currentTextToSpeak = textToSpeak;
        tts = new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener()
        {
            @Override
            public void onInit(int status)
            {
                if (status == TextToSpeech.SUCCESS)
                {
                    //Set Language / Voice
                    if(currentLanguage != null)
                    {
                        if(tts.isLanguageAvailable(currentLanguage) != TextToSpeech.LANG_NOT_SUPPORTED || tts.isLanguageAvailable(currentLanguage) != TextToSpeech.LANG_MISSING_DATA )
                            tts.setLanguage(currentLanguage);
                        else
                            Log.d(TAG, "TextToSpeech#ERROR#LANGUAGE_OR_COUNTRY_NOT_AVAILABLE#"+tts.isLanguageAvailable(currentLanguage));
                    }
                    else
                    {
                        tts.setLanguage(tts.getDefaultLanguage());
                        tts.setVoice(tts.getDefaultVoice());
                    }
                    //Set Utterance listener to know when the speaking ends
                    tts.setOnUtteranceProgressListener(utteranceProgressListener);
                    //Speak
                    tts.speak(currentTextToSpeak, TextToSpeech.QUEUE_FLUSH, hashAlarm);
                }
                else
                {
                    Log.d(TAG, "TextToSpeech#ERROR");
                }
            }
        });
    }
    public void Speak(String textToSpeak, String language)
    {
        this.SetLanguage(language);
        this.Speak(textToSpeak);
    }
    public void Speak(String textToSpeak, String language, String country)
    {
        this.SetLanguage(language, country);
        this.Speak(textToSpeak);
    }

    public void SetLanguage(String language)
    {
        this.currentLanguage = new Locale(language);
    }
    public void SetLanguage(String language, String country)
    {
        this.currentLanguage = new Locale(language, country);
    }

}
