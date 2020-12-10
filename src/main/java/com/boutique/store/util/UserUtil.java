package com.boutique.store.util;

import com.boutique.store.entities.User;
import com.boutique.store.enums.Role;

public class UserUtil {
    private UserUtil() {
        //Prevent instantiation.
    }

    public static boolean isAdmin(User user) {
        return Role.ADMIN == user.getRole();
    }

    public static boolean isSaleUser(User user) {
        return Role.USER == user.getRole();
    }
}
