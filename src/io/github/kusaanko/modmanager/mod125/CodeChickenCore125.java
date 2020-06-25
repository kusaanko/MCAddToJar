package io.github.kusaanko.modmanager.mod125;

public class CodeChickenCore125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "CodeChickenCore-Client ");
    }

    @Override
    public String getDownloadURL() {
        return "http://chickenbones.net/Files/Old_Versions/1.2.5/CodeChickenCore-Client%200.5.5.zip";
    }

    @Override
    public String getDownloadPageURL() {
        return "http://chickenbones.net/Files/Old_Versions/";
    }

    @Override
    public TYPE getType() {
        return TYPE.REQUIREMENTS;
    }

    @Override
    public String getName() {
        return "CodeChickenCore";
    }

    @Override
    public String getShortName() {
        return "CodeChickenCore";
    }

    @Override
    public String getAuthor() {
        return "ChickenBones";
    }

    @Override
    public String getVersion() {
        return "0.5.5";
    }

    @Override
    public String getDownloadFileName() {
        return "CodeChickenCore-Client 0.5.5.zip";
    }
}
