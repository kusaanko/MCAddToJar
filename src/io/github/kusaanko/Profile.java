package io.github.kusaanko;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Profile {
    public File profileFile;
    public HashMap<String, ArrayList<String>> mcAddToJar;
    public ArrayList<String> mcAddToJarTurn;
    public String version;
    public int profile_version;

    public Profile(HashMap<String, ArrayList<String>> addToJar, ArrayList<String> turn, File profileFile, String version) {
        this.mcAddToJar = addToJar;
        this.mcAddToJarTurn = turn;
        this.profileFile = profileFile;
        this.version = version;
        this.profile_version = 2;
    }

    public void save() {
        try{
            profile_version = 2;
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(profileFile), StandardCharsets.UTF_8));
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
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
        return this.profileFile.getName().substring(0, this.profileFile.getName().lastIndexOf("."));
    }

    private static String ver;
    public static Profile load(File profileFile) {
        if(profileFile.exists()) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(profileFile), StandardCharsets.UTF_8));
                Gson gson = new Gson();
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
