package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class WorkBenchEX125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "[1.2.5]mod_WorkBenchEX_v");
    }

    @Override
    public String getDownloadURL() {
        return "https://web.archive.org/web/20190715145127if_/https://forum.minecraftuser.jp/download/file.php?id=14265";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://web.archive.org/web/20190715145137/http://forum.minecraftuser.jp/viewtopic.php?f=13&t=1758&start=180#p51082";
    }

    @Override
    public String getName() {
        return "WorkBenchEX";
    }

    @Override
    public String getShortName() {
        return "WorkBenchEX";
    }

    @Override
    public String getAuthor() {
        return "Unyuho";
    }

    @Override
    public String getVersion() {
        return "1.0.1";
    }

    @Override
    public String getDownloadFileName() {
        return "[1.2.5]mod_WorkBenchEX_v1.0.1.zip";
    }
}
