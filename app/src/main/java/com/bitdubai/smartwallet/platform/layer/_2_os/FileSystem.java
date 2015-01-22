package com.bitdubai.smartwallet.platform.layer._2_os;

/**
 * Created by ciencias on 20.01.15.
 */
public interface FileSystem {

    public File getFile (String fileName);

    public File createFile (String fileName, FilePrivacy privacyLevel, FileLifeSpan lifeSpan );

}
