package io.github.kusaanko.modmanager.mod125;

public class ForestryForMinecraft125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "forestry-client-A-");
    }

    @Override
    public String getDownloadURL() {
        return "https://web.archive.org/web/20131022232309if_/http://forestry.sengir.net/files/forestry/releases/1.4.8.7/forestry-client-A-1.4.8.7.jar";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://web.archive.org/web/20130114000158/http://forestry.sengir.net/wiki.new/doku.php?id=main:deprecatedversions";
    }

    @Override
    public String getName() {
        return "ForestryForMinecraft";
    }

    @Override
    public String getShortName() {
        return "FFM";
    }

    @Override
    public String getAuthor() {
        return "SirSengir";
    }

    @Override
    public String getVersion() {
        return "1.4.8.7";
    }

    @Override
    public String getDownloadFileName() {
        return "forestry-client-A-1.4.8.7.jar";
    }
}
