package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class ChickenChunks125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "ChickenChunks-Client ");
    }

    @Override
    public String getDownloadURL() {
        return "http://chickenbones.net/Files/Old_Versions/1.2.5/ChickenChunks-Client%201.0.1.zip";
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
        return "ChickenChunks";
    }

    @Override
    public String getShortName() {
        return "ChickenChunks";
    }

    @Override
    public String getAuthor() {
        return "ChickenBones";
    }

    @Override
    public String getVersion() {
        return "1.0.1";
    }

    @Override
    public String getDownloadFileName() {
        return "ChickenChunks-Client 1.0.1.zip";
    }
}
