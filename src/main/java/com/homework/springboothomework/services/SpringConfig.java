package com.homework.springboothomework.services;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class SpringConfig {

    @Bean(name = "ingredientFileService")
    public FileService ingredientFileService() {
        return new FileService() {
            @Override
            public boolean saveToFile(String json) {
                return false;
            }

            @Override
            public String readFromFile() {
                return null;
            }

            @Override
            public boolean cleanFile() {
                return false;
            }

            @Override
            public File getData() {
                return null;
            }
        };
    }

    @Bean(name = "recipeFileService")
    public FileService recipeFileService(){
        return new FileService() {
            @Override
            public boolean saveToFile(String json) {
                return false;
            }

            @Override
            public String readFromFile() {
                return null;
            }

            @Override
            public boolean cleanFile() {
                return false;
            }

            @Override
            public File getData() {
                return null;
            }
        };
    }
}
