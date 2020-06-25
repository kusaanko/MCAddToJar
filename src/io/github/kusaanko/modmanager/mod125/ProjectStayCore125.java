package io.github.kusaanko.modmanager.mod125;

public class ProjectStayCore125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "Project-Stay-1.2.5-core-");
    }

    @Override
    public String getDownloadURL() {
        return "https://web.archive.org/web/20190715143154if_/https://forum.minecraftuser.jp/download/file.php?id=18584";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://web.archive.org/web/20190715115539/https://forum.minecraftuser.jp/viewtopic.php?f=13&t=8454&start=0";
    }

    @Override
    public String getName() {
        return "Project Stay 1.2.5 Core";
    }

    @Override
    public String getShortName() {
        return "PS125Core";
    }

    @Override
    public String getAuthor() {
        return "さよ";
    }

    @Override
    public String getVersion() {
        return "1.1.3";
    }

    @Override
    public String getDownloadFileName() {
        return "Project-Stay-1.2.5-core-1.1.3.zip";
    }
}
