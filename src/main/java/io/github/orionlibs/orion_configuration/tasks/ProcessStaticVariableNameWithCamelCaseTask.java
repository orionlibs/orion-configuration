package io.github.orionlibs.orion_configuration.tasks;

public class ProcessStaticVariableNameWithCamelCaseTask
{
    public static void run(String variableName, StringBuilder key)
    {
        char[] characters = variableName.toCharArray();
        key.append(Character.toLowerCase(characters[0]));
        for(int i = 1; i < characters.length; i++)
        {
            if(Character.isUpperCase(characters[i]))
            {
                key.append(".");
                key.append(Character.toLowerCase(characters[i]));
            }
            else
            {
                key.append(characters[i]);
            }
        }
    }
}