package io.github.kusaanko.modmanager.mod125;

public class modLister125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "mod_ModLister125-");
    }

    @Override
    public String getDownloadURL() {
        return "http://minecraft125user.nisfan.net/forum/download/file.php?id=1312";
    }

    @Override
    public String getDownloadPageURL() {
        return "http://minecraft125user.nisfan.net/forum/viewtopic.php?f=6&t=88";
    }

    @Override
    public String getReferer() {
        return "http://minecraft125user.nisfan.net/forum/viewtopic.php?f=6&t=88";
    }

    @Override
    public String getName() {
        return "ModLister";
    }

    @Override
    public String getShortName() {
        return "ModLister";
    }

    @Override
    public String getAuthor() {
        return "Deer05";
    }

    @Override
    public String getVersion() {
        return "02";
    }

    @Override
    public String getDownloadFileName() {
        return "mod_ModLister125-02.zip";
    }
}
