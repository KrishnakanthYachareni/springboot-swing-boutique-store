package com.boutique.store;

import com.boutique.store.forms.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.awt.*;

/**
 * This CommandLineRunner fires off at runtime and boots up our GUI.
 */
@Component
public class BoutiqueStoreCommandLineRunner implements CommandLineRunner {
    private final UserLogin userLogin;

    @Autowired
    public BoutiqueStoreCommandLineRunner(UserLogin main) {
        main.setVisible(true);
        this.userLogin = main;
    }


    @Override
    public void run(String... args) {
        //This boots up the GUI initial Login screen.
        EventQueue.invokeLater(() -> userLogin.setVisible(true));
    }
}
