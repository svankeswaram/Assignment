package APITest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Configuration {

    private static final Logger LOGGER = LoggerFactory.getLogger( Configuration.class );

    private static Properties configuration;

    static {
        initConfig();
    }

    public static String get(String string) {
        if (configuration == null) {
            throw new RuntimeException( "cannot get property string configuration is null" );
        }
        String prop = configuration.getProperty( string );
        if (prop == null) {
            throw new RuntimeException( "no property found for " + string );
        }
        return prop;
    }

    public static void initConfig() {

        String propertyFile = "src/test/properties/application.properties";
        System.out.println( "propertyFile: " + propertyFile );

        File propertiesFile = new File( propertyFile );
        FileInputStream fileInput = null;
        Properties localProperties = new Properties();

        try {
            fileInput = new FileInputStream( propertiesFile );
            loadProperties( localProperties, fileInput );

        } catch (FileNotFoundException e) {
            LOGGER.error( e.getMessage() );
            throw new RuntimeException( "Properties file not found", e );
        } finally {
            configuration = localProperties;
        }

        if (configuration == null) {
            System.out.println( "configuration is null" );
            throw new RuntimeException( "configuration object was found to be null" );
        }
    }

    private static void loadProperties(Properties props, InputStream inputStream) {
        try {
            props.load( inputStream );
            inputStream.close();
        } catch (IOException ioexception) {
            LOGGER.error( ioexception.getMessage() );
        }
    }

}

