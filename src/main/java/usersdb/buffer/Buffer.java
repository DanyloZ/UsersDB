package usersdb.buffer;

import usersdb.entity.User;

import java.util.ArrayList;

public class Buffer {
    private static Buffer buffer;

    private ArrayList<User> newUsers = new ArrayList<>();

    public ArrayList<User> getNewUsers() {
        return newUsers;
    }

    public void refreshUserList() {
        newUsers = new ArrayList<>();
    }

    public static Buffer getBuffer() {
        if (buffer == null) {
            buffer = new Buffer();
        }
        return buffer;
    }

    private Buffer() {
    }
}
