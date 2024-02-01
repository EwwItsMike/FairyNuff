package data;

import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;

import com.google.gson.Gson;

public class DbConnection {
    private static DbConnection instance;
    private DataManager dataManager;
    private final String datafileLoc = "datafile.json";

    public static DbConnection getInstance() {
        if (instance == null) {
            instance = new DbConnection();
        }
        return instance;
    }

    private DbConnection() {
        validateStorageFile();
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public void save() {
        try (FileWriter write = new FileWriter(datafileLoc)) {
            String json = "";
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            json += gson.toJson(dataManager);
            write.write(json);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private boolean validateStorageFile() {
        try (FileReader read = new FileReader(datafileLoc)) {
            DataManager m = new Gson().fromJson(read, DataManager.class);
            if (m != null)
                dataManager = m;
            return true;
        } catch (FileNotFoundException e) {
            File nw = new File(datafileLoc);
            try {
                if (nw.createNewFile()) {
                    dataManager = new DataManager();
                    save();
                }
                return validateStorageFile();
            } catch (IOException ioe) {
                e.printStackTrace();
                return false;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return false;
        }
    }
}
