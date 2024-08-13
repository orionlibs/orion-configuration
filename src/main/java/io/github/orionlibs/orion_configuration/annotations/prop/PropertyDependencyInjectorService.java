package io.github.orionlibs.orion_configuration.annotations.prop;

import io.github.orionlibs.orion_assert.InaccessibleException;
import io.github.orionlibs.orion_configuration.InMemoryConfigurationService;
import io.github.orionlibs.orion_reflection.method.retrieval.ReflectionMethodsRetrievalService;
import io.github.orionlibs.orion_reflection.variable.access.ReflectionInstanceVariablesAccessService;
import io.github.orionlibs.orion_reflection.variable.retrieval.ReflectionInstanceVariablesRetrievalService;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class PropertyDependencyInjectorService
{
    public void injectToFields(Object object) throws InvocationTargetException
    {
        List<Field> fields = ReflectionInstanceVariablesRetrievalService.getDeclaredInstanceVariables(object);
        fields.forEach(field -> processInstanceVariableForPropertyInjection(object, field));
    }


    public void injectToFieldsAndMethods(Object object) throws InvocationTargetException
    {
        injectToFields(object);
        List<Method> methods = ReflectionMethodsRetrievalService.getDeclaredMethods(object);
        methods.forEach(method ->
        {
            try
            {
                processMethodForPropertyInjection(object, method);
            }
            catch(InvocationTargetException e)
            {
                e.printStackTrace();
            }
        });
    }


    private void processInstanceVariableForPropertyInjection(Object object, Field instanceVariable)
    {
        Prop injection = instanceVariable.getAnnotation(Prop.class);
        if(injection != null)
        {
            try
            {
                processPropertyInjectionForInstanceVariable(object, injection, instanceVariable);
            }
            catch(InaccessibleException e)
            {
                //
            }
        }
    }


    private void processPropertyInjectionForInstanceVariable(Object object, Prop propertyInjection, Field instanceVariable) throws InaccessibleException
    {
        String propertyToInjectString = propertyInjection.key();
        String propertyToInject = InMemoryConfigurationService.getProp(propertyToInjectString);
        instanceVariable.setAccessible(true);
        ReflectionInstanceVariablesAccessService.injectObjectToInstanceVariable(object, propertyToInject, instanceVariable);
    }


    private void processMethodForPropertyInjection(Object object, Method method) throws InvocationTargetException
    {
        Prop injection = method.getAnnotation(Prop.class);
        if(injection != null)
        {
            try
            {
                processPropertyInjectionForMethod(object, injection, method);
            }
            catch(InvocationTargetException e)
            {
                //
            }
            catch(InaccessibleException e)
            {
                //
            }
        }
    }


    private void processPropertyInjectionForMethod(Object object, Prop propertyInjection, Method method) throws InvocationTargetException, InaccessibleException
    {
        String propertyToInjectString = propertyInjection.key();
        String propertyToInject = InMemoryConfigurationService.getProp(propertyToInjectString);
        method.setAccessible(true);
        injectObjectToMethod(method, object, propertyToInject);
    }


    private Object injectObjectToMethod(Method method, Object objectMethodBelongsTo, String propertyToInject) throws InvocationTargetException, InaccessibleException
    {
        try
        {
            return method.invoke(objectMethodBelongsTo, propertyToInject);
        }
        catch(IllegalAccessException e)
        {
            throw new InaccessibleException("The instance method is inaccessible.");
        }
        catch(IllegalArgumentException e)
        {
            throw e;
        }
    }
}