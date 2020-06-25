package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class ProjectStayEditableBook125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "PS125-EditableBook-");
    }

    @Override
    public String getDownloadURL() {
        return "https://web.archive.org/web/20190715143156if_/https://forum.minecraftuser.jp/download/file.php?id=17464";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://web.archive.org/web/20190715115539/https://forum.minecraftuser.jp/viewtopic.php?f=13&t=8454&start=0";
    }

    @Override
    public Class<? extends Mod>[] getRequireMods() {
        return genRequireMods(ProjectStayCore125.class);
    }

    @Override
    public String getName() {
        return "Project Stay 1.2.5 Editable Book";
    }

    @Override
    public String getShortName() {
        return "PS125EditableBook";
    }

    @Override
    public String getAuthor() {
        return "さよ";
    }

    @Override
    public String getVersion() {
        return "1.1.1";
    }

    @Override
    public String getDownloadFileName() {
        return "PS125-EditableBook-1.1.1.zip";
    }
}
