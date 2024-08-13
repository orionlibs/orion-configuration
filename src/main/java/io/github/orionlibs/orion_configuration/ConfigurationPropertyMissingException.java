package io.github.orionlibs.orion_configuration;

import io.github.orionlibs.orion_assert.OrionCheckedException;

public class ConfigurationPropertyMissingException extends OrionCheckedException
{
    private static final String DefaultErrorMessage = "the required property does not exist in the registry.";


    public ConfigurationPropertyMissingException(String message)
    {
        super(message);
    }


    public ConfigurationPropertyMissingException(String errorMessage, Object... arguments)
    {
        super(String.format(errorMessage, arguments));
    }


    public ConfigurationPropertyMissingException(Throwable cause, String errorMessage, Object... arguments)
    {
        super(String.format(errorMessage, arguments), cause);
    }


    public ConfigurationPropertyMissingException(Throwable cause)
    {
        super(cause, DefaultErrorMessage);
    }
}