package data;

import exceptions.RsnAlreadyOccupiedException;
import exceptions.UserAlreadyExistsException;
import exceptions.UserNotFoundException;
import org.json.JSONObject;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.io.Serializable;
import java.util.HashMap;

public class DataManager implements Serializable {
    private Long serverID;

    private HashMap<Long, User> users;

    public DataManager(Long serverID){
        this.serverID = serverID;
        users = new HashMap<Long, User>();
    }

    public boolean RemoveUser(Long id){
        if (!users.containsKey(id)){
            throw new UserNotFoundException();
        }
        users.remove(id);
        DbConnection.getInstance().save();
        return true;
    }

    public boolean RemoveUser(String rsn) {
        for (Long l : users.keySet()){
            if (users.get(l).getRsn().equalsIgnoreCase(rsn)){
                users.remove(l);
                DbConnection.getInstance().save();
                return true;
            }
        }
        throw new UserNotFoundException();
    }

    public void addNewUser(long userID, String rsn) {
        try {
            User existingUser = getUserByRsn(rsn);
            if (existingUser != null) {
                throw new RsnAlreadyOccupiedException();
            }
            if (getUserByID(userID) != null) {
                throw new UserAlreadyExistsException();
            }

        } catch (UserNotFoundException e) {
            User nwUser = new User(userID, rsn);
            SaveUser(nwUser);
        }
    }

    public void SaveUser(User user){
        if (users.containsKey(user.getDiscordID())){
            users.replace(user.getDiscordID(), user);
        } else{
            users.put(user.getDiscordID(), user);
        }
        DbConnection.getInstance().save();
    }

    public User getUserByID(Long id){
        if (!users.containsKey(id)){
            throw new UserNotFoundException();
        }
        return users.get(id);
    }

    public void updateUser(long id, String rsn) {
        User user = getUserByID(id);
        user.setRsn(rsn);
        SaveUser(user);
    }

    public User getUserByRsn(String rsn){
        User user = null;

        for (User u : users.values()){
            if (rsn.equalsIgnoreCase(u.getRsn())){
                user = u;
            }
        }

        if (user == null){
            throw new UserNotFoundException();
        }

        return user;
    }

    public Long getServerID() {
        return serverID;
    }
}
