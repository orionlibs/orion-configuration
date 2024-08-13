package io.github.orionlibs.orion_configuration.tasks;

public class ProcessStaticVariableNameWithUnderscoresTask
{
    public static void run(String variableName, StringBuilder key)
    {
        String[] tokens = variableName.split("_");
        for(int i = 0; i < tokens.length; i++)
        {
            key.append(tokens[i].toLowerCase());
            if(i < tokens.length - 1)
            {
                key.append(".");
            }
        }
    }
}