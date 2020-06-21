package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class ComputerCraft125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "ComputerCraft");
    }

    @Override
    public String getDownloadURL() {
        return "http://download848.mediafire.com/cy0ax7rrjnbg/hdngqjluuy3a98c/ComputerCraft1.41.zip";
    }

    @Override
    public String getDownloadPageURL() {
        return "http://www.computercraft.info/download/";
    }

    @Override
    public String getName() {
        return "ComputerCraft";
    }

    @Override
    public String getShortName() {
        return "ComputerCraft";
    }

    @Override
    public String getAuthor() {
        return "Daniel Ratcliffe";
    }

    @Override
    public String getVersion() {
        return "1.41";
    }

    @Override
    public String getDownloadFileName() {
        return "ComputerCraft1.41.zip";
    }
}
