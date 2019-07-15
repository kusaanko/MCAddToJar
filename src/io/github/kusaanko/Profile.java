package io.github.kusaanko;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
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
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(profileFile),"UTF-8"));
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            bw.write(gson.toJson(this));
            bw.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String ver;
    public static Profile load(File profileFile) {
        if(profileFile.exists()) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(profileFile), "UTF-8"));
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