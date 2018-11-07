package Business;

import Acquaintance.IProfile;
import Acquaintance.IUser;
import Acquaintance.Nationality;
import java.util.List;

public class User implements IUser {

    Management management;
    ClientSystem client = ClientSystem.getInstance();

    private int userID;
    private boolean banned; // a flag for if the user is banned
    private int reports;    // the amount of reprts a user have received
    private List<Integer> chats;
    private Friends friends;
    private Profile profile;

    public User(String firstName, String lastName, Nationality nationality) {
        new Profile(firstName, lastName, nationality);
    }

    public User(int userID, boolean banned, int reports, List<Integer> chats, Friends friends, Profile profile) {
        this.userID = userID;
        this.banned = banned;
        this.reports = reports;
        this.chats = chats;
        this.friends = friends;
        this.profile = profile;
    }

    @Override
    public int getUserID() {
        return userID;
    }

    @Override
    public boolean isBanned() {
        return banned;
    }

    @Override
    public int getReports() {
        return reports;
    }

    @Override
    public List<Integer> getChats() {
        return chats;
    }
    
    @Override
    public IProfile getProfile() {
        return profile;
    }

    public int changePw(String oldPw, String newPw) {
        management = new Management(2, client.getCurrentUser().getUserID(), client.hash(oldPw), client.hash(newPw));
        return BusinessFacade.getInstance().sendChangePw(management);
    }

    public int changeMail(String pw, String newMail) {
        management = new Management(1, client.getCurrentUser().getUserID(), client.hash(pw), client.hash(newMail));
        return BusinessFacade.getInstance().sendChangeMail(management);
    }

    public boolean addFriend(int userID, String profileName) {
        if (friends.addFriend(userID, profileName)) {
            return updateFriends(friends);
        } else {
            return true;
        }
    }

    public boolean removeFriend(int userID) {
        friends.removeFriend(userID);
        return updateFriends(friends);
    }

    boolean updateFriends(Friends friends) {
        return BusinessFacade.getInstance().updateFriends(friends);
    }

}
