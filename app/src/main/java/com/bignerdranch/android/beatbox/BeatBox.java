package com.bignerdranch.android.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2/21/16.
 */
public class BeatBox {

    // Private static Variables
    private static final int MAX_SOUNDS = 5;

    private static final String SOUNDS_FOLDER = "sample_sounds";
    private static final String TAG = "BeatBox";

    // Private Variables
    private AssetManager mAssets;
    private List<Sound> mSounds;
    private SoundPool mSoundPool;


    // Constructors
    public BeatBox(Context context) {
        mAssets = context.getAssets();
        mSounds = new ArrayList<>();
        mSoundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0);
        loadSound();
    }


    // Private Methods
    private void load(Sound sound) throws IOException {
        AssetFileDescriptor afd = mAssets.openFd(sound.getAssetPath());
        int soundId = mSoundPool.load(afd, 1);
        sound.setSoundId(new Integer(soundId));
    }
    private void loadSound() {
        String[] soundNames;

        try {
            soundNames = mAssets.list(SOUNDS_FOLDER);
            Log.i(TAG, "Found " + soundNames.length + " sounds");

            for (String filename : soundNames) {
                String assetPath = SOUNDS_FOLDER + "/" + filename;
                Sound sound = new Sound(assetPath);
                this.load(sound);
                mSounds.add(sound);
            }
        } catch (IOException ioe) {
            Log.e(TAG, "Could not list assets", ioe);
        }
    }


    // Public Methods
    public List<Sound> getSounds() {
        return mSounds;
    }

    public void play(Sound sound) {
        Integer soundId = sound.getSoundId();
        if (soundId == null)
            return;

        mSoundPool.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f);
    }
    public void release() {
        mSoundPool.release();
    }
}
