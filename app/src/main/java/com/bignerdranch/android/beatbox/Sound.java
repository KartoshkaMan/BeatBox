package com.bignerdranch.android.beatbox;

/**
 * Created by user on 2/21/16.
 */
public class Sound {

    private Integer mSoundId;
    private String mAssetPath;
    private String mName;

    public Sound(String assetPath) {
        String[] components = assetPath.split("/");
        String filename = components[components.length - 1];

        mAssetPath = assetPath;
        mName = filename.replace(".wav", "");
    }

    public Integer getSoundId() {
        return mSoundId;
    }
    public String getAssetPath() {
        return mAssetPath;
    }
    public String getName() {
        return mName;
    }

    public void setSoundId(Integer soundId) {
        mSoundId = soundId;
    }
}
