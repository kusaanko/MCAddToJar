package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class BucketFilter125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "jBuildcraft-client-D-bucketFiller-");
    }

    @Override
    public String getDownloadURL() {
        return "https://github.com/downloads/Tom-V/BucketFiller/jBuildcraft-client-D-bucketFiller-3.1.5.zip";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://github.com/Tom-V/BucketFiller/downloads";
    }

    @Override
    public TYPE getType() {
        return TYPE.ADDON;
    }

    @Override
    public Class<? extends Mod>[] getRequireMods() {
        return genRequireMods(BCx125.class);
    }

    @Override
    public String getName() {
        return "BucketFilter";
    }

    @Override
    public String getShortName() {
        return "BucketFilter";
    }

    @Override
    public String getAuthor() {
        return "Tom Vervoort";
    }

    @Override
    public String getVersion() {
        return "3.1.5";
    }

    @Override
    public String getDownloadFileName() {
        return "jBuildcraft-client-D-bucketFiller-3.1.5.zip";
    }
}
