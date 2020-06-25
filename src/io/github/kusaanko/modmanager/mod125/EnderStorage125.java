package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class EnderStorage125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "EnderStorage-Client ");
    }

    @Override
    public String getDownloadURL() {
        return "http://chickenbones.net/Files/Old_Versions/1.2.5/EnderStorage-Client%201.1.5.zip";
    }

    @Override
    public String getDownloadPageURL() {
        return "http://chickenbones.net/Files/Old_Versions/";
    }

    @Override
    public Class<? extends Mod>[] getRequireMods() {
        return genRequireMods(CodeChickenCore125.class);
    }

    @Override
    public String getName() {
        return "EnderStorage";
    }

    @Override
    public String getShortName() {
        return "EnderStorage";
    }

    @Override
    public String getAuthor() {
        return "ChickenBones";
    }

    @Override
    public String getVersion() {
        return "1.1.5";
    }

    @Override
    public String getDownloadFileName() {
        return "EnderStorage-Client 1.1.5.zip";
    }
}
