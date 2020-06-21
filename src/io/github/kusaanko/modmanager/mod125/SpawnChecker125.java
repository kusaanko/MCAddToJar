package io.github.kusaanko.modmanager.mod125;

public class SpawnChecker125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "SpawnChecker_125v");
    }

    @Override
    public String getDownloadURL() {
        return "https://uc1944607c3e94b3ed45e8db5a34.dl.dropboxusercontent.com/cd/0/get/A6GFVlML7E3lwJZvgVG37lznae_yidhV71VsLcL6Gr6NKMCRa_qr5e6NaCqNea7hVOQ80EAEFwChvUUwckT8OTZcxuMk-foBLrw0VzoUSTaWPw/file?_download_id=5606614910257024279867652344208130465382611013999390410158018467&_notify_domain=www.dropbox.com&dl=1";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://web.archive.org/web/20190715204650/https://forum.minecraftuser.jp/viewtopic.php?t=3835";
    }

    @Override
    public String getName() {
        return "SpawnChecker";
    }

    @Override
    public String getShortName() {
        return "SpawnChecker";
    }

    @Override
    public String getAuthor() {
        return "ale";
    }

    @Override
    public String getVersion() {
        return "8_#93";
    }

    @Override
    public String getDownloadFileName() {
        return "SpawnChecker_125v8_#93.zip";
    }
}
