package io.github.orionlibs.orion_configuration;

import io.github.orionlibs.orion_assert.Assert;
import io.github.orionlibs.orion_assert.ResourceException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryConfigurationService
{
    private static final ConfigurationRegistry configurationRegistry = new ConfigurationRegistry();


    public static void registerClassStaticVariable(String key, Object value)
    {
        Assert.notEmpty(key, "The given key input cannot be null/empty.");
        configurationRegistry.registerObjectProp(key, value);
    }


    public static void registerClassStaticVariables(Map<String, Object> constantsMapper)
    {
        Assert.notNull(constantsMapper, "The given constantsMapper input cannot be null.");
        constantsMapper.entrySet()
                        .stream()
                        .filter(entry -> entry.getKey() != null && !entry.getKey().isEmpty())
                        .forEach(entry -> configurationRegistry.registerObjectProp(entry.getKey(), entry.getValue()));
    }


    public static boolean doesValueExist(String value)
    {
        return configurationRegistry.containsValue(value);
    }


    public static boolean doesObjectValueExist(Object value)
    {
        return configurationRegistry.doesObjectValueExist(value);
    }


    public static String getKeyFromValue(String value)
    {
        return configurationRegistry.getKeyFromValue(value);
    }


    public static List<String> getKeysFromValue(String value)
    {
        return configurationRegistry.getKeysFromValue(value);
    }


    public static String getKeyFromObjectValue(Object value)
    {
        return configurationRegistry.getKeyFromObjectValue(value);
    }


    public static List<String> getKeysFromObjectValue(Object value)
    {
        return configurationRegistry.getKeysFromObjectValue(value);
    }


    public static void loadProps(Class<?> classToUseForClasspath, String configurationFilePath) throws ResourceException
    {
        InputStream administratorStream = classToUseForClasspath.getResourceAsStream(configurationFilePath);
        configurationRegistry.loadProps(administratorStream);
    }


    public static void loadProps(InputStream propertiesFileInput) throws ResourceException
    {
        configurationRegistry.loadProps(propertiesFileInput);
    }


    public static void loadProps(ConfigurationRegistry configurationRegistry)
    {
        configurationRegistry.loadProps(configurationRegistry);
    }


    public static void registerProp(String key, String value)
    {
        configurationRegistry.registerProp(key, value);
    }


    public static void registerObjectProp(String key, Object value)
    {
        configurationRegistry.registerObjectProp(key, value);
    }


    public static void registerListProp(String key, List<?> value)
    {
        configurationRegistry.registerListProp(key, value);
    }


    public static void updateProp(String key, String value)
    {
        configurationRegistry.updateProp(key, value);
    }


    public static void updateObjectProp(String key, Object value)
    {
        configurationRegistry.updateObjectProp(key, value);
    }


    public static void updateListProp(String key, List<?> value)
    {
        configurationRegistry.updateListProp(key, value);
    }


    public static void deleteProp(String key)
    {
        configurationRegistry.deleteProp(key);
    }


    public static int getNumberOfConfigurationProperties()
    {
        return configurationRegistry.size();
    }


    public static ConfigurationRegistry getProps()
    {
        return configurationRegistry;
    }


    public static Map<String, String> getPropsAsMap()
    {
        Map<String, String> entries = new HashMap<>();
        configurationRegistry.entrySet().forEach(entry -> entries.put(entry.getKey().toString(), entry.getValue().toString()));
        return entries;
    }


    public static boolean containsPropKey(String key)
    {
        return configurationRegistry.doesPropExist(key);
    }


    public static boolean containsPropValue(String value)
    {
        return configurationRegistry.contains(value);
    }


    public static boolean containsPropObjectValue(Object value)
    {
        return configurationRegistry.contains(value);
    }


    public static String getProp(String key)
    {
        return configurationRegistry.getProp(key);
    }


    public static String getProp(String key, String defaultValue)
    {
        return configurationRegistry.getProp(key, defaultValue);
    }


    public static List<?> getListProp(String key)
    {
        return configurationRegistry.getListProp(key);
    }


    public static List<?> getListProp(String key, List<?> defaultValue)
    {
        return configurationRegistry.getListProp(key, defaultValue);
    }


    public static Object getObjectProp(String key)
    {
        return configurationRegistry.getObjectProp(key);
    }


    public static Object getObjectProp(String key, Object defaultValue)
    {
        return configurationRegistry.getObjectProp(key, defaultValue);
    }


    public static String getPropOrThrowException(String key) throws ConfigurationPropertyMissingException
    {
        String prop = configurationRegistry.getProp(key);
        if(prop == null)
        {
            throw new ConfigurationPropertyMissingException("Configuration property {} is missing.", key);
        }
        else
        {
            return prop;
        }
    }


    public static Object getObjectPropOrThrowException(String key) throws ConfigurationPropertyMissingException
    {
        Object prop = configurationRegistry.getObjectProp(key);
        if(prop == null)
        {
            throw new ConfigurationPropertyMissingException("Configuration property {} is missing.", key);
        }
        else
        {
            return prop;
        }
    }


    public static List<?> getListPropOrThrowException(String key) throws ConfigurationPropertyMissingException
    {
        List<?> prop = configurationRegistry.getListProp(key);
        if(prop == null)
        {
            throw new ConfigurationPropertyMissingException("Configuration property {} is missing.", key);
        }
        else
        {
            return prop;
        }
    }


    public static String getPropWithPlaceholders(String key, List<String> propertyPlaceholders)
    {
        return configurationRegistry.getPropWithPlaceholders(key, propertyPlaceholders);
    }


    public static String getPropWithPlaceholders(String key, String defaultValue, List<String> propertyPlaceholders)
    {
        return configurationRegistry.getPropWithPlaceholders(key, defaultValue, propertyPlaceholders);
    }


    public static Byte getByteProp(String key)
    {
        return configurationRegistry.getByteProp(key);
    }


    public static Byte getByteProp(String key, byte defaultValue)
    {
        return configurationRegistry.getByteProp(key, defaultValue);
    }


    public static Short getShortProp(String key)
    {
        return configurationRegistry.getShortProp(key);
    }


    public static Short getShortProp(String key, short defaultValue)
    {
        return configurationRegistry.getShortProp(key, defaultValue);
    }


    public static Integer getIntegerProp(String key)
    {
        return configurationRegistry.getIntegerProp(key);
    }


    public static Integer getIntegerProp(String key, int defaultValue)
    {
        return configurationRegistry.getIntegerProp(key, defaultValue);
    }


    public static Long getLongProp(String key)
    {
        return configurationRegistry.getLongProp(key);
    }


    public static Long getLongProp(String key, long defaultValue)
    {
        return configurationRegistry.getLongProp(key, defaultValue);
    }


    public static Float getFloatProp(String key)
    {
        return configurationRegistry.getFloatProp(key);
    }


    public static Float getFloatProp(String key, float defaultValue)
    {
        return configurationRegistry.getFloatProp(key, defaultValue);
    }


    public static Double getDoubleProp(String key)
    {
        return configurationRegistry.getDoubleProp(key);
    }


    public static Double getDoubleProp(String key, double defaultValue)
    {
        return configurationRegistry.getDoubleProp(key, defaultValue);
    }


    public static BigDecimal getBigDecimalProp(String key)
    {
        return configurationRegistry.getBigDecimalProp(key);
    }


    public static BigDecimal getBigDecimalProp(String key, BigDecimal defaultValue)
    {
        return configurationRegistry.getBigDecimalProp(key, defaultValue);
    }


    public static Boolean getBooleanProp(String key)
    {
        return configurationRegistry.getBooleanProp(key);
    }


    public static Boolean getBooleanProp(String key, boolean defaultValue)
    {
        return configurationRegistry.getBooleanProp(key, defaultValue);
    }


    public static Character getCharacterProp(String key) throws InvalidConfigurationPropertyException
    {
        return configurationRegistry.getCharacterProp(key);
    }


    public static Character getCharacterProp(String key, char defaultValue) throws InvalidConfigurationPropertyException
    {
        return configurationRegistry.getCharacterProp(key, defaultValue);
    }
}