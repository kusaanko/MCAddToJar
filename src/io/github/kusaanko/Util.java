package io.github.kusaanko;

import com.google.gson.internal.LinkedTreeMap;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Util {

    public static void outputAll(Path profileFolder) {
        try {
            for(Path f : Files.list(profileFolder).collect(Collectors.toList())) {
                if(f.getFileName().toString().endsWith(".profile")) {
                    Profile profile = Profile.load(f);
                    final boolean[] ended = {false};
                    String profileName = f.getFileName().toString().substring(0, f.getFileName().toString().lastIndexOf("."));
                    if(Files.exists(Util.getPath(MCAddToJar.mcDir, "versions/"+profileName))) {
                        AddToJar addToJar = new AddToJar(Util.getPath(MCAddToJar.mcDir, "versions/" + profileName),
                                profileName, profile, true) {
                            @Override
                            public void outputEnd() {
                                ended[0] = true;
                                this.dispose();
                            }
                        };
                        addToJar.output();
                        while (!ended[0]) {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static OS getPlatform() {
        String var0 = System.getProperty("os.name").toLowerCase();
        if (var0.contains("win")) {
            return OS.WINDOWS;
        } else if (var0.contains("mac")) {
            return OS.MACOS;
        } else if (var0.contains("solaris")) {
            return OS.SOLARIS;
        } else if (var0.contains("sunos")) {
            return OS.SOLARIS;
        } else if (var0.contains("linux")) {
            return OS.LINUX;
        } else {
            return var0.contains("unix") ? OS.LINUX : OS.UNKNOWN;
        }
    }

    public static Path getWorkingDirectory(String folname) {
        String homeDir = System.getProperty("user.home", ".");
        Path path;
        switch(getPlatform()) {
            case LINUX:
            case SOLARIS:
                path = Util.getPath(homeDir, '.' + folname + '/');
                break;
            case WINDOWS:
                String var3 = System.getenv("APPDATA");
                String var4 = var3 != null ? var3 : homeDir;
                path = Util.getPath(var4, '.' + folname + '/');
                break;
            case MACOS:
                path = Util.getPath(homeDir, "Library/Application Support/" + folname);
                break;
            default:
                path = Util.getPath(homeDir, folname + '/');
        }
        return path;
    }

    public static Path getPath(String base, String... others) {
        return Paths.get(base, others);
    }

    public static Path getPath(Path base, String... others) {
        return getPath(base.toString(), others);
    }

    public static String executeGet(String url) {
        HttpURLConnection connection = null;

        try {
            URL URL = new URL(url);
            connection = (HttpURLConnection)URL.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            System.out.println("connect to "+url);
            connection.connect();
            System.out.println("connected to "+url);

            InputStream inputStream = connection.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream w = new DataOutputStream(baos);

            int len;
            byte[] bytes = new byte[4096];
            while((len = inputStream.read(bytes, 0, bytes.length)) > 0) {
                w.write(bytes, 0, len);
            }

            w.close();
            baos.close();
            return new String(baos.toByteArray(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        return null;
    }


    public static void downloadFile(String URL, String output) {
        try {
            if(Util.getPath(output).getParent()!=null) Files.createDirectories(Util.getPath(output).getParent());
            URL var1 = new URL(URL);
            byte[] var5 = new byte[4096];
            DataInputStream inputStream = new DataInputStream(var1.openStream());
            DataOutputStream outputStream = new DataOutputStream(Files.newOutputStream(Util.getPath(output)));

            int len;
            while((len = inputStream.read(var5, 0, var5.length)) > 0) {
                outputStream.write(var5, 0, len);
            }
            inputStream.close();
            outputStream.close();

        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteFolder(Path file) throws IOException {
        for(Path f : Files.list(file).collect(Collectors.toList())) {
            if(Files.isDirectory(f)) {
                deleteFolder(f);
            }else {
                Files.delete(f);
            }
        }
        Files.delete(file);
    }

    public static void copy(Path input, Path output) throws IOException{
        Files.createDirectories(output.getParent());
        InputStream fileIn = Files.newInputStream(input);
        OutputStream fileOut = Files.newOutputStream(output);

        byte[] buf = new byte[8192];
        int len;

        while((len = fileIn.read(buf))>0){
            fileOut.write(buf, 0, len);
        }

        fileOut.flush();

        fileOut.close();
        fileIn.close();
    }

    public static void copy(File input, File output) throws IOException{
        copy(input.toPath(), output.toPath());
    }

    public static void copyFolder(Path input, Path output) throws IOException{
        List<Path> pathList = Files.list(input).collect(Collectors.toList());
        for(Path path : pathList) {
            Path out = Util.getPath(output, path.toAbsolutePath().toString().replace(input.toAbsolutePath().toString(), ""));
            if(Files.isRegularFile(path)) {
                copy(path, out);
            }
            else copyFolder(path, out);
        }
    }

    public static void zipCopy(Path inZip, Path outZip) throws IOException {
        Path outTemp = Util.getPath(outZip.getParent(), "temp" + System.currentTimeMillis() + ".tmp");
        ZipInputStream zipInputStream = new ZipInputStream(Files.newInputStream(inZip));
        ZipInputStream zipInputStreamOut = new ZipInputStream(Files.newInputStream(outZip));
        ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(outTemp));

        byte[] buff = new byte[8192];
        int len;
        ZipEntry entry;
        while((entry = zipInputStream.getNextEntry()) != null) {
            zipOutputStream.putNextEntry(new ZipEntry(entry.getName()));

            while((len = zipInputStream.read(buff)) != -1) {
                zipOutputStream.write(buff, 0, len);
            }
        }
        while ((entry = zipInputStreamOut.getNextEntry()) != null) {
            try {
                zipOutputStream.putNextEntry(new ZipEntry(entry.getName()));

                while ((len = zipInputStreamOut.read(buff)) != -1) {
                    zipOutputStream.write(buff, 0, len);
                }
            }catch (ZipException ignore) {}
        }

        zipInputStreamOut.close();
        zipInputStream.close();
        zipOutputStream.close();

        Files.delete(outZip);
        Files.move(outTemp, outZip);
    }

    public static void compress(Path input, Path output) throws IOException {
        ZipOutputStream append = new ZipOutputStream(Files.newOutputStream(output));
        try {
            append.putNextEntry(new ZipEntry(input.getFileName().toString()));
            InputStream stream = Files.newInputStream(input);
            byte[] bytes = new byte[8192];
            int len;
            while ((len = stream.read(bytes, 0, bytes.length)) > 0) {
                append.write(bytes, 0, len);
            }
            append.closeEntry();
        } catch (ZipException ignore) {
        }

        append.close();
    }

    public enum OS {
        LINUX,
        SOLARIS,
        WINDOWS,
        MACOS,
        UNKNOWN,
    }

    public static String sha1sum(Path path) throws NoSuchAlgorithmException, IOException {
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        try (InputStream input = new DigestInputStream(Files.newInputStream(path), digest)) {
            byte[] buff = new byte[8192];
            while (input.read(buff) != -1) {
                // PASS;
            }

            StringBuilder hash = new StringBuilder();
            for (byte b : digest.digest()) {
                hash.append(String.format("%02x", b));
            }
            return hash.toString();
        }
    }

    public static String sha256sum(Path path) throws NoSuchAlgorithmException, IOException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        try (InputStream input = new DigestInputStream(Files.newInputStream(path), digest)) {
            byte[] buff = new byte[8192];
            while (input.read(buff) != -1) {
                // PASS;
            }

            StringBuilder hash = new StringBuilder();
            for (byte b : digest.digest()) {
                hash.append(String.format("%02x", b));
            }
            return hash.toString();
        }
    }

    @SuppressWarnings("unchecked")
    public static LinkedTreeMap<String, Object> toMap(Object src) {
        return (LinkedTreeMap<String, Object>) src;
    }
}
