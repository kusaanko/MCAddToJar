package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class AdditionalPipesFix125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "AdditionalPipesFix_");
    }

    @Override
    public String getDownloadURL() {
        return "http://download1589.mediafire.com/9gznf91ma9yg/02olx4l8y768y4i/AdditionalPipesFix_1.0.zip";
    }

    @Override
    public String getDownloadPageURL() {
        return "http://www.mediafire.com/file/02olx4l8y768y4i/AdditionalPipesFix_1.0.zip/file";
    }

    @Override
    public TYPE getType() {
        return TYPE.PATCH;
    }

    @Override
    public Class<? extends Mod>[] getRequireMods() {
        return genRequireMods(BCx125.class, AdditionalPipes125.class);
    }

    @Override
    public Class<? extends Mod> getPatchMod() {
        return AdditionalPipes125.class;
    }

    @Override
    public String getName() {
        return "Additional Pipes Fix";
    }

    @Override
    public String getShortName() {
        return "Additional Pipes Fix";
    }

    @Override
    public String getAuthor() {
        return "Unknown";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public String getDownloadFileName() {
        return "AdditionalPipesFix_1.0.zip";
    }
}
