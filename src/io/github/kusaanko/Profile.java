package io.github.kusaanko;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import com.google.gson.annotations.Expose;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Profile {
    public Path profileFile;
    @Expose
    public HashMap<String, ArrayList<String>> mcAddToJar;
    @Expose
    public ArrayList<String> mcAddToJarTurn;
    @Expose
    public String version;
    @Expose
    public int profile_version;

    public Profile(HashMap<String, ArrayList<String>> addToJar, ArrayList<String> turn, Path profileFile, String version) {
        this.mcAddToJar = addToJar;
        this.mcAddToJarTurn = turn;
        this.profileFile = profileFile;
        this.version = version;
        this.profile_version = 2;
    }

    public void save() {
        try{
            profile_version = 2;
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(profileFile), StandardCharsets.UTF_8));
            Gson gson = new GsonBuilder()
                    .excludeFieldsWithoutExposeAnnotation()
                    .setPrettyPrinting()
                    .create();
            bw.write(gson.toJson(this));
            bw.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void add(String path) {
        this.mcAddToJar.put(path, new ArrayList<>());
        this.mcAddToJarTurn.add(path);
    }

    public void remove(String path) {
        this.mcAddToJar.remove(path);
        this.mcAddToJarTurn.remove(path);
    }

    public String getVersionName() {
        return this.profileFile.getFileName().toString().substring(0, this.profileFile.getFileName().toString().lastIndexOf("."));
    }

    private static String ver;
    public static Profile load(Path profileFile) {
        if(Files.exists(profileFile)) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(Files.newInputStream(profileFile), StandardCharsets.UTF_8));
                Gson gson = new GsonBuilder()
                        .registerTypeAdapter(Path.class, new PathInstanceCreator())
                        .create();
                Profile profile = gson.fromJson(br, Profile.class);
                profile.profileFile = profileFile;
                br.close();
                return profile;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
