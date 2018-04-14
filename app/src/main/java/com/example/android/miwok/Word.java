package com.example.android.miwok;

/**
 * Created by Mohamed Ramadan on 06/02/2018.
 */

public class Word {
    // intialilze two vaiable
    private String miwokWord;
    private String defulteWord;
    private int imageResourceId = NO_IMAGE_PROVIDER;
    private static final int NO_IMAGE_PROVIDER = -1;
    private int soundResourse;


    /**
     * constractor
     *
     * @param defulte_Word
     * @param miwok_Word
     * @param sound_Resourse
     */
    public Word(String defulte_Word, String miwok_Word, int sound_Resourse) {
        this.defulteWord = defulte_Word;
        this.miwokWord = miwok_Word;
        this.soundResourse = sound_Resourse;
    }

    /* constractor
      *@param  miwokWord is for miwok word
      *@param defulteWord is for miwok defultelanguage
      *@param imageresourse is for image resourse
       */

    /**
     * constractor
     *
     * @param defulte_Word
     * @param miwok_Word
     * @param image_Resource_Id
     * @param sound_Resourse
     */
    public Word(String defulte_Word, String miwok_Word, int image_Resource_Id, int sound_Resourse) {
        this.defulteWord = defulte_Word;
        this.miwokWord = miwok_Word;
        this.imageResourceId = image_Resource_Id;
        this.soundResourse = sound_Resourse;
    }

    //

    /**
     * get Miwok Word
     *
     * @return
     */
    public String getMiwokWord() {
        return this.miwokWord;
    }


    /**
     * get defulte Word
     *
     * @return
     */
    public String getDefulteWord() {
        return this.defulteWord;
    }


    /**
     * get the image resouce id
     *
     * @return
     */
    public int getImageResourceId() {
        return this.imageResourceId;
    }

    /**
     * retun there are image or no
     *
     * @return
     */
    public boolean hasImage() {
        return imageResourceId != NO_IMAGE_PROVIDER;
    }
    public int getsoundResourse ()
    {
        return this.soundResourse;
    }
}
