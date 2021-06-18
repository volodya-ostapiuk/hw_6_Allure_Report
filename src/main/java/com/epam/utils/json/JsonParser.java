package com.epam.utils.json;

import com.epam.model.GmailJsonEntity;
import com.epam.utils.properties.ConfigProperties;
import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Optional;

public class JsonParser {
    public static GmailJsonEntity getGmailJsonEntity() {
        Gson gson = new Gson();
        try (Reader reader = new FileReader(ConfigProperties.getDataSource())) {
            return Optional.of(gson.fromJson(reader, GmailJsonEntity.class)).orElseThrow(IOException::new);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
