package com.boutique.store;

import com.boutique.store.presentation.UserLoginJFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.awt.*;

/**
 * This CommandLineRunner fires off at runtime and boots up our GUI.
 */
@Component
public class BoutiqueStoreCommandLineRunner implements CommandLineRunner {
    private final UserLoginJFrame userLogin;

    @Autowired
    public BoutiqueStoreCommandLineRunner(UserLoginJFrame main) {
        main.setVisible(true);
        this.userLogin = main;
    }


    @Override
    public void run(String... args) {
        //This boots up the GUI initial Login screen.
        EventQueue.invokeLater(() -> userLogin.setVisible(true));
    }
}
