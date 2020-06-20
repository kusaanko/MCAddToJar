package io.github.kusaanko;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Util {

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

    public static File getWorkingDirectory(String folname) {
        String var1 = System.getProperty("user.home", ".");
        File var2;
        switch(getPlatform()) {
            case LINUX:
            case SOLARIS:
                var2 = new File(var1, '.' + folname + '/');
                break;
            case WINDOWS:
                String var3 = System.getenv("APPDATA");
                String var4 = var3 != null ? var3 : var1;
                var2 = new File(var4, '.' + folname + '/');
                break;
            case MACOS:
                var2 = new File(var1, "Library/Application Support/" + folname);
                break;
            default:
                var2 = new File(var1, folname + '/');
        }
        return var2;
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
            if(new File(output).getParentFile()!=null) new File(output).getParentFile().mkdirs();
            URL var1 = new URL(URL);
            byte[] var5 = new byte[4096];
            DataInputStream inputStream = new DataInputStream(var1.openStream());
            DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(new File(output)));

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

    public static boolean deleteFolder(File file) {
        for(File f : file.listFiles()) {
            if(f.isDirectory()) {
                deleteFolder(f);
            }else {
                f.delete();
            }
        }
        return file.delete();
    }

    public static void copy(File input, File output) throws IOException{
        output.getParentFile().mkdirs();
        FileInputStream fileIn = new FileInputStream(input);
        FileOutputStream fileOut = new FileOutputStream(output);

        byte[] buf = new byte[8192];
        int len;

        while((len = fileIn.read(buf))>0){
            fileOut.write(buf, 0, len);
        }

        fileOut.flush();

        fileOut.close();
        fileIn.close();
    }

    public static void copyFolder(File input, File output) throws IOException{
        for(File file : input.listFiles()) {
            File out = new File(output, file.getAbsolutePath().replace(input.getAbsolutePath(), ""));
            if(file.isFile()) {
                copy(file, out);
            }
            else copyFolder(file, out);
        }
    }

    public static void zipCopy(File inZip, File outZip) throws IOException {
        File outTemp = new File(outZip.getParentFile(), "temp" + System.currentTimeMillis() + ".tmp");
        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(inZip));
        ZipInputStream zipInputStreamOut = new ZipInputStream(new FileInputStream(outZip));
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(outTemp));

        System.out.println(outZip.getName());
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

        outZip.delete();
        outTemp.renameTo(outZip);
    }

    public static void compress(File input, File output) throws IOException {
        ZipOutputStream append = new ZipOutputStream(new FileOutputStream(output));
        try {
            append.putNextEntry(new ZipEntry(input.getName()));
            InputStream stream = new FileInputStream(input);
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
}
