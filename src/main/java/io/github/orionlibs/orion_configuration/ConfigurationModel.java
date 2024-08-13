package io.github.orionlibs.orion_configuration;

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
public class ConfigurationModel
{
    private String configurationKey;
    private String configurationValue;
    private String configurationType;


    public static ConfigurationModel of()
    {
        return ConfigurationModel.builder().build();
    }


    public static ConfigurationModel of(String configurationKey)
    {
        return ConfigurationModel.builder()
                        .configurationKey(configurationKey)
                        .build();
    }
}