package com.eric.nativetoolkit;

import android.app.Activity;
import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;

import java.util.ArrayList;
import java.util.Locale;

import static com.eric.nativetoolkit.NativeToolkitFragment.SendUnityResults;

public class NativeSpeechRecognizer
{
    private static final String TAG = "EricSpeechRecognizer";  //Tag to follow logcats
    private Fragment fragment;
    //CALLBACK NAMES
    static final String CALLBACK_SPEECHRECOGNIZER_STARTLISTENING = "OnSpeechReconized";
    //SPEECH RECOGNIZER
    public SpeechRecognizer sr;
    public SpeechRecognitionListener speechListener = new SpeechRecognitionListener();
    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private static String gQuestion = "Hello, How can I help you?";
    private static boolean languageNotSet = true;
    private static String glanguage = "en-US";
    private static int gMaxResults = 10;

    public NativeSpeechRecognizer(Fragment fragment)
    {
        this.fragment = fragment;
    }

    private Activity getActivity()
    {
        return this.fragment.getActivity();
    }

    public void StartListening()
    {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "StartListeningCalled");
                sr = SpeechRecognizer.createSpeechRecognizer(getActivity());
                sr.setRecognitionListener(speechListener);

                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                if (languageNotSet)
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                else
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, glanguage);
                intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, gMaxResults);
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, gQuestion);

                try
                {
                    sr.startListening(intent);
                }
                catch (ActivityNotFoundException a)
                {
                    Log.d(TAG, a.toString());
                }
            }
        });
    }
    private void StopListening()
    {
        sr.destroy();
        sr = null;
    }
    private void RestartListening()
    {
        StopListening();
        StartListening();
    }
    public void SetContinuousListening(boolean isContinuous)
    {
        speechListener.continuousListening = isContinuous;
    }

    //SPEECH RECOGNIZER_LISTENER
    private class SpeechRecognitionListener implements RecognitionListener
    {
        private ArrayList<String> resultData = new ArrayList<>();
        public boolean continuousListening = false;

        public void onReadyForSpeech(Bundle params) {
            Log.d(TAG, "onReadyForSpeech");
        }

        public void onBeginningOfSpeech() {
            Log.d(TAG, "onBeginningOfSpeech");
        }

        public void onRmsChanged(float rmsdB) {
            /*Log.d(TAG, "onRmsChanged");*/
        }

        public void onBufferReceived(byte[] buffer) {
            Log.d(TAG, "onBufferReceived");
        }

        public void onEndOfSpeech() {
            Log.d(TAG, "onEndofSpeech");
        }

        public void onError(int error)
        {
            if(continuousListening)
                RestartListening();
        }

        public void onResults(Bundle results)
        {
            StringBuilder str = new StringBuilder();
            resultData = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
            if (resultData != null)
            {
                str.append(resultData.get(0));
                for (int i = 1; i < resultData.size(); i++)
                {
                    str.append("~").append(resultData.get(i));
                }
            }

            SendUnityResults(CALLBACK_SPEECHRECOGNIZER_STARTLISTENING, str.toString());
            if(continuousListening)
                RestartListening();
        }

        public void onPartialResults(Bundle partialResults) {
            Log.d(TAG, "onPartialResults");
        }

        public void onEvent(int eventType, Bundle params) {
            Log.d(TAG, "onEvent " + eventType);
        }
    }
}
