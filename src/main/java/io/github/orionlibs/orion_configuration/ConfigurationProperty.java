package io.github.orionlibs.orion_configuration;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ConfigurationProperty implements Serializable
{
    private String key;
    private String value;
    private String type;


    public static ConfigurationProperty of()
    {
        return ConfigurationProperty.builder().build();
    }
}