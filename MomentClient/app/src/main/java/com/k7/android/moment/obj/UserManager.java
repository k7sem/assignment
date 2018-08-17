package com.k7.android.moment.obj;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Kevin
 * @since 08/17/2018
 *
 *
 */

public class UserManager {
    private static UserManager instance = new UserManager();

    private Hashtable<Integer, User> userCache = new Hashtable<>();
    private Hashtable<Integer, Url> urlCache = new Hashtable<>();

    private UserManager(){}

    public static UserManager getInstance() {
        return instance;
    }

    public void addUser(User user) {
        userCache.put(user.getId(), user);
    }

    public void addUrl(Url url) {
        urlCache.put(url.getId(), url);
    }

    public List<User> getUsers() {
        List<User> result = new ArrayList<User>();
        Enumeration<User> users = userCache.elements();
        while (users.hasMoreElements()) {
            User u = users.nextElement();
            result.add(u);
        }
        return result;
    }

    public List<Url> getUrls(int userid) {
        List<Url> result = new ArrayList<>();
        Enumeration<Url> urls = urlCache.elements();
        while (urls.hasMoreElements()) {
            Url u = urls.nextElement();
            if (u.getUserid() == userid) {
                result.add(u);
            }
        }
        return result;
    }


}
