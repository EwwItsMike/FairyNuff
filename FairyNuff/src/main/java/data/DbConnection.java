package data;

import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;

import com.google.gson.Gson;

public class DbConnection {
    private static DbConnection instance;
    private ArrayList<DataManager> dataManagers;
    private final String datafileLoc = "datafile.json";

    public static DbConnection getInstance() {
        if (instance == null) {
            instance = new DbConnection();
        }
        return instance;
    }

    private DbConnection() {
        dataManagers = new ArrayList<DataManager>();
        validateStorageFile();
    }

    public DataManager addNewDataManager(long serverID) {
        DataManager m = new DataManager(serverID);
        dataManagers.add(m);
        save();
        return m;
    }

    public DataManager getDataManager(long serverID) {
        if (!doesServerHaveDataManager(serverID)){
            return addNewDataManager(serverID);
        } else {
            for (DataManager man : dataManagers){
                if (man.getServerID().equals(serverID)){
                    return man;
                }
            }
        }
        return null;
    }

    public void removeDataManager(Long serverID) {
        for (DataManager m : dataManagers) {
            if (m.getServerID().equals(serverID)) {
                dataManagers.remove(m);
                break;
            }
        }
        save();
    }

    public boolean doesServerHaveDataManager(long id) {
        if (dataManagers.size() == 0)
            return false;

        for (DataManager m : dataManagers) {
            if (m.getServerID().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public void save() {
        try (FileWriter write = new FileWriter(datafileLoc)) {
            String json = "";
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            int counter = 0;
            for (DataManager dataManager : dataManagers) {
                if (counter > 0)
                    json += ",\n";
                json += gson.toJson(dataManager);
                counter ++;
            }
            write.write(json);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private boolean validateStorageFile() {
        try (FileReader read = new FileReader(datafileLoc)) {
            DataManager m = new Gson().fromJson(read, DataManager.class);
            if (m != null)
                dataManagers.add(m);
            return true;
        } catch (FileNotFoundException e) {
            File nw = new File(datafileLoc);
            try {
                if (nw.createNewFile()) {
                    addNewDataManager(Long.parseLong("332595657363685377"));
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
