package com.scrooge.scrooge.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class AvatarDataInitializer implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) {
        if(checkIfInitialDataExists()) {
            insertInitialData();
        }
    }

    private boolean checkIfInitialDataExists() {
        Long count = (Long) entityManager.createQuery("SELECT COUNT(a) FROM Avatar a").getSingleResult();
        return count == 0;
    }

    private void insertInitialData() {
        String[] names = new String[101];
        for(int i=1; i<=101;i++) {
            names[i-1] = "avatar" + i;
        }

        for (int i = 0; i < names.length; i++) {
            String insertQuery = "INSERT INTO avatar (name) VALUES " +
                    "('" + names[i] +  "');";
            entityManager.createNativeQuery(insertQuery).executeUpdate();
        }
    }
}
