package org.afonu.utility.client;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.afonu.utility.client.option.Option;
import org.afonu.utility.client.option.OptionInit;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigSaver {
    private static final String PATH = "config/afonu.utility/";
    private static final String FILE_NAME = "config.json";

    public static void DefaultConfig() {
        File directory = new File(PATH);
        if (! directory.exists()){
            directory.mkdir();
        }

        File config = new File(PATH + FILE_NAME);
        if(!config.exists()) {
            Option first = new Option();

            SaveConfig(first);
        }
    }

    public static Option ReadConfig() {
        Option option = new Option();
        Gson jsonDeserializer = new Gson();
        try {
            JsonReader reader = new JsonReader(new FileReader(PATH + FILE_NAME));

            option = jsonDeserializer.fromJson(reader, Option.class);
        }  catch (IOException e) {
            e.printStackTrace();
        }

        return option;
    }

    public static void SaveConfig(Option _option) {
        SaveConfigToFile(_option);
        OptionInit.Init(_option);
    }

    private static void SaveConfigToFile(Option _option) {
        try (FileWriter writer = new FileWriter(PATH + FILE_NAME)) {
            Gson jsonSerializer = new Gson();

            String configJson = jsonSerializer.toJson(_option);

            writer.write(configJson);
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }
}