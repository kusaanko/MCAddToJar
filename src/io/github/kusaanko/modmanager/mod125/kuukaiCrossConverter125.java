package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class kuukaiCrossConverter125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "kuukaiCrossConverter-Client-1.2.5.v");
    }

    @Override
    public String getDownloadURL() {
        return "https://web.archive.org/web/20151019141616if_/http://kuukai.6.ql.bz:80/CGI/Download/Main.cgi?kuukaiCrossConverter-Client-1.2.5.v4_dev0.zip";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://web.archive.org/web/20160325230007/http://kuukai.6.ql.bz/minecraft/CrossConverter.html";
    }

    @Override
    public Class<? extends Mod>[] getRequireMods() {
        return genRequireMods(BCx125.class);
    }

    @Override
    public String getName() {
        return "kuukai's Cross Converter";
    }

    @Override
    public String getShortName() {
        return "kuukai's Cross Converter";
    }

    @Override
    public String getAuthor() {
        return "kuukai";
    }

    @Override
    public String getVersion() {
        return "4_dev0";
    }

    @Override
    public String getDownloadFileName() {
        return "kuukaiCrossConverter-Client-1.2.5.v4_dev0.zip";
    }
}
