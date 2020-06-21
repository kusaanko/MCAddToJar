package io.github.kusaanko.modmanager.mod125;

public class CamouflageMaterials125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "[124]-mod_CamouflageMaterials_v");
    }

    @Override
    public String getDownloadURL() {
        return "https://blog.wolfs.jp/downloads/minecraft/124/mod_CamouflageMaterials_v110.zip";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://blog.wolfs.jp/down/minecraft/oldversion/";
    }

    @Override
    public String getReferer() {
        return "https://blog.wolfs.jp/down/minecraft/oldversion/";
    }

    @Override
    public String getName() {
        return "CamouflageMaterials";
    }

    @Override
    public String getShortName() {
        return "CamouflageMaterials";
    }

    @Override
    public String getAuthor() {
        return "REIMA";
    }

    @Override
    public String getVersion() {
        return "110";
    }

    @Override
    public String getDownloadFileName() {
        return "[124]-mod_CamouflageMaterials_v110.zip";
    }
}
