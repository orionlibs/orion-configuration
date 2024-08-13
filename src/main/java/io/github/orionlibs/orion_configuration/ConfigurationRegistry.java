package io.github.orionlibs.orion_configuration;

import io.github.orionlibs.orion_assert.ResourceException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * central registry of system, Orion and application-specific properties
 * @author dimitrios.efthymiou
 */
public class ConfigurationRegistry extends OrionProperties
{
    public ConfigurationRegistry()
    {
        loadProps(System.getProperties());
    }


    public static ConfigurationRegistry of()
    {
        return new ConfigurationRegistry();
    }


    public boolean doesValueExist(String value)
    {
        return containsValue(value);
    }


    public boolean doesObjectValueExist(Object value)
    {
        return containsValue(value);
    }


    public boolean doesPropExist(String prop)
    {
        return getProp(prop) != null;
    }


    private static boolean keyIsNotEmpty(String key)
    {
        return key != null && !key.isEmpty();
    }


    public String getKeyFromValue(String value)
    {
        return (String)(entrySet().stream()
                        .filter(entry -> ((String)entry.getValue()).equals(value))
                        .findFirst().orElse(null).getKey());
    }


    public List<String> getKeysFromValue(String value)
    {
        return entrySet().stream()
                        .filter(entry -> ((String)entry.getValue()).equals(value))
                        .map(entry -> (String)entry.getKey())
                        .collect(Collectors.toList());
    }


    public String getKeyFromObjectValue(Object value)
    {
        return (String)(entrySet().stream()
                        .filter(entry -> (entry.getValue()).equals(value))
                        .findFirst().orElse(null).getKey());
    }


    public List<String> getKeysFromObjectValue(Object value)
    {
        return entrySet().stream()
                        .filter(entry -> (entry.getValue()).equals(value))
                        .map(entry -> (String)entry.getKey())
                        .collect(Collectors.toList());
    }


    public void loadProps(InputStream propertiesFileInput) throws ResourceException
    {
        try
        {
            load(propertiesFileInput);
        }
        catch(IOException e)
        {
            throw new ResourceException("Cannot load properties from the given InputStream.");
        }
    }


    public void loadProps(Properties properties)
    {
        putAll(properties);
    }


    public void registerListProp(String key, List<?> value)
    {
        put(key, value);
    }


    public void registerObjectProp(String key, Object value)
    {
        put(key, value);
    }


    public void registerProp(String key, String value)
    {
        put(key, value);
    }


    public void updateProp(String key, String value)
    {
        put(key, value);
    }


    public void updateObjectProp(String key, Object value)
    {
        put(key, value);
    }


    public void updateListProp(String key, List<?> value)
    {
        put(key, value);
    }


    public void deleteProp(String key)
    {
        remove(key);
    }


    public boolean isNotEmpty()
    {
        return !isEmpty();
    }


    public String getProp(String prop)
    {
        return getProp(prop, null);
    }


    public Object getObjectProp(String prop)
    {
        return getObjectProp(prop, null);
    }


    public List<?> getListProp(String prop)
    {
        return getListProp(prop, null);
    }


    public String getProp(String prop, String defaultValue)
    {
        if(keyIsNotEmpty(prop))
        {
            return super.getProperty(prop);
        }
        else
        {
            return defaultValue;
        }
    }


    public Object getObjectProp(String prop, Object defaultValue)
    {
        if(keyIsNotEmpty(prop))
        {
            return get(prop);
        }
        else
        {
            return defaultValue;
        }
    }


    public List<?> getListProp(String prop, List<?> defaultValue)
    {
        if(keyIsNotEmpty(prop))
        {
            return (List<?>)super.get(prop);
        }
        else
        {
            return defaultValue;
        }
    }


    public String getPropWithPlaceholders(String prop, List<String> propertyPlaceholders)
    {
        return getPropWithPlaceholders(prop, null, propertyPlaceholders);
    }


    public String getPropWithPlaceholders(String prop, String defaultValue, List<String> propertyPlaceholders)
    {
        return applyPlaceholders(getProp(prop, defaultValue), propertyPlaceholders);
    }


    private String applyPlaceholders(String aString, List<String> placeholders)
    {
        if(aString != null && !aString.isEmpty() && placeholders != null && !placeholders.isEmpty())
        {
            Object[] placeholders1 = placeholders.toArray(new Object[0]);
            return MessageFormat.format(aString, placeholders1);
        }
        else
        {
            return aString;
        }
    }


    public Byte getByteProp(String key)
    {
        return getByteProp(key, Byte.MIN_VALUE);
    }


    public Byte getByteProp(String key, byte defaultValue)
    {
        if(keyIsNotEmpty(key))
        {
            try
            {
                return Byte.parseByte(getProperty(key));
            }
            catch(NumberFormatException e)
            {
                return defaultValue;
            }
        }
        return defaultValue;
    }


    public Short getShortProp(String key)
    {
        return getShortProp(key, Short.MIN_VALUE);
    }


    public Short getShortProp(String key, short defaultValue)
    {
        if(keyIsNotEmpty(key))
        {
            try
            {
                return Short.parseShort(getProperty(key));
            }
            catch(NumberFormatException e)
            {
                return defaultValue;
            }
        }
        return defaultValue;
    }


    public Integer getIntegerProp(String key)
    {
        return getIntegerProp(key, Integer.MIN_VALUE);
    }


    public Integer getIntegerProp(String key, int defaultValue)
    {
        if(keyIsNotEmpty(key))
        {
            try
            {
                return Integer.parseInt(getProperty(key));
            }
            catch(NumberFormatException e)
            {
                return defaultValue;
            }
        }
        return defaultValue;
    }


    public Long getLongProp(String key)
    {
        return getLongProp(key, Long.MIN_VALUE);
    }


    public Long getLongProp(String key, long defaultValue)
    {
        if(keyIsNotEmpty(key))
        {
            try
            {
                return Long.parseLong(getProperty(key));
            }
            catch(NumberFormatException e)
            {
                return defaultValue;
            }
        }
        return defaultValue;
    }


    public Float getFloatProp(String key)
    {
        return getFloatProp(key, Float.MIN_VALUE);
    }


    public Float getFloatProp(String key, float defaultValue)
    {
        if(keyIsNotEmpty(key))
        {
            try
            {
                return Float.parseFloat(getProperty(key));
            }
            catch(NumberFormatException e)
            {
                return defaultValue;
            }
        }
        return defaultValue;
    }


    public Double getDoubleProp(String key)
    {
        return getDoubleProp(key, Double.MIN_VALUE);
    }


    public Double getDoubleProp(String key, double defaultValue)
    {
        if(keyIsNotEmpty(key))
        {
            try
            {
                return Double.parseDouble(getProperty(key));
            }
            catch(NumberFormatException e)
            {
                return defaultValue;
            }
        }
        return defaultValue;
    }


    public BigDecimal getBigDecimalProp(String key)
    {
        return getBigDecimalProp(key, BigDecimal.ZERO);
    }


    public BigDecimal getBigDecimalProp(String key, BigDecimal defaultValue)
    {
        if(keyIsNotEmpty(key))
        {
            try
            {
                return new BigDecimal(getProperty(key));
            }
            catch(NumberFormatException e)
            {
                return defaultValue;
            }
        }
        return defaultValue;
    }


    public Boolean getBooleanProp(String key)
    {
        return getBooleanProp(key, Boolean.FALSE);
    }


    public Boolean getBooleanProp(String key, boolean defaultValue)
    {
        if(keyIsNotEmpty(key))
        {
            return Boolean.parseBoolean(getProperty(key));
        }
        return defaultValue;
    }


    public Character getCharacterProp(String key) throws InvalidConfigurationPropertyException
    {
        return getCharacterProp(key, Character.MIN_VALUE);
    }


    public Character getCharacterProp(String key, char defaultValue) throws InvalidConfigurationPropertyException
    {
        if(keyIsNotEmpty(key))
        {
            String value = getProperty(key);
            if(value.length() == 1)
            {
                return Character.valueOf(value.toCharArray()[0]);
            }
            else
            {
                throw new InvalidConfigurationPropertyException(String.format("The property value '{}', cannot be converted to a character object.", value));
            }
        }
        return defaultValue;
    }
}