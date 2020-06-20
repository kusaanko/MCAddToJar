package io.github.kusaanko.modmanager.mod125;

public class TrainMod125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "TrainModClient_v");
    }

    @Override
    public String getDownloadURL() {
        return "https://web.archive.org/web/20141226035520if_/https://dl.dropboxusercontent.com/u/26029386/TrainModClient_v2.1.2b.zip";
    }

    @Override
    public String getDownloadPageURL() {
        return "http://traincraft-mod.com/wordpress/downloads/";
    }

    @Override
    public String getName() {
        return "Train & Zeppelin";
    }

    @Override
    public String getShortName() {
        return "TrainMod";
    }

    @Override
    public String getAuthor() {
        return "Spitfire4466";
    }

    @Override
    public String getVersion() {
        return "2.1.2b";
    }

    @Override
    public String getDownloadFileName() {
        return "TrainModClient_v2.1.2b.zip";
    }
}
