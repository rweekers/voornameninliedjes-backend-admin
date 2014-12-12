package org.orangeflamingo.namesandsongs.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("propertiesService")
@Scope("singleton")
public class PropertiesServiceImpl implements PropertiesService {

    private static final Logger LOGGER = Logger
            .getLogger(PropertiesServiceImpl.class);

    private static Properties props;

    public PropertiesServiceImpl() throws IOException {
        LOGGER.info("Creating propertiesService...");
        // create and load default properties
        props = new Properties();
        FileInputStream in = new FileInputStream("/config.properties");
        LOGGER.info("Properties loaded...");
        props.load(in);
        in.close();

        OutputStream out = new FileOutputStream("/test.txt");
        out.close();
    }

    public String get(String key) {
        return props.getProperty(key);
    }

}
