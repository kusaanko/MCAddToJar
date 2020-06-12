package io.github.kusaanko.modmanager;

public abstract class Mod implements Cloneable {
    private String filePath;
    private String fileName;
    private String fileVersion;

    public static enum TYPE{
        REQUIREMENTS,
        MOD,
        ADDON,
        CONFIG,
    }

    public static enum INSTALLATION_TYPE{
        MODS_FOLDER,
        IN_JAR,
        OTHER_FOLDER,
    }

    /**
     * Whether the filename passed is this mod.
     * @return null is not this mod.Returns the version if the target file is this mod.
     */
    public abstract String is(String fileName);

    public String startsWith(String base, String search) {
        if(base.startsWith(search)) {
            return base.substring(search.length());
        }
        return null;
    }

    public TYPE getType() {
        return TYPE.MOD;
    }

    public INSTALLATION_TYPE getInstallationType() {
        return INSTALLATION_TYPE.MODS_FOLDER;
    }

    //direct link
    public abstract String getDownloadURL();

    public abstract String getDownloadPageURL();

    //true is need to update
    public boolean compareVersion() {
        return !this.getFileVersion().equals(this.getVersion());
    }

    public abstract String getName();

    public abstract String getShortName();

    public abstract String getAuthor();

    public abstract String getVersion();

    public abstract String getDownloadFileName();

    public Class<?extends Mod>[] getRequireModsInJar() {
        return null;
    }

    public Class<?extends Mod>[] getRequireMods() {
        return null;
    }

    @SafeVarargs
    public final Class<?extends Mod>[] genRequireMods(Class<? extends Mod>... classes) {
        return classes;
    }

    public String getDownloadFolder() {
        return "mods/1.2.5/";
    }

    public void setFileName(String fileName) {
        this.fileName = fileName.replace("<", "");
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileVersion() {
        if(fileVersion == null) return "";
        return fileVersion;
    }

    public void setFileVersion(String fileVersion) {
        this.fileVersion = fileVersion;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Mod clone() {
        try {
            return (Mod) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
