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
        PATCH,
    }

    public static enum PATCH_TYPE{
        OVERWRITE_ZIP,
        DELETE_FILES,
    }

    public static enum INSTALLATION_TYPE{
        MODS_FOLDER,
        IN_JAR,
        OTHER_FOLDER,
    }

    public static enum PROCESS_TYPE{
        PLAIN,
        UNZIP,
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

    public PROCESS_TYPE getProcessType() {
        return PROCESS_TYPE.PLAIN;
    }

    //Folder a/b/
    //File a/b/c
    public String getUnzipFile() {
        return null;
    }

    public Class<? extends Mod> getPatchMod() {
        return null;
    }

    //Folder a/b/
    //File a/b/c
    public String[] getPatchDeleteFiles() {
        return new String[]{};
    }

    public PATCH_TYPE getPatchType() {
        return PATCH_TYPE.OVERWRITE_ZIP;
    }

    public String getInstallationFolder() {
        return "mods";
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

    public Class<?extends Mod>[] getConflictMods() {
        return null;
    }

    @SafeVarargs
    public final Class<?extends Mod>[] genRequireMods(Class<? extends Mod>... classes) {
        return classes;
    }

    public boolean needUserDownload() {
        return false;
    }

    public abstract String getDownloadFolder();

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
