package org.apache.olingo.commons.core.edm;

import org.apache.olingo.commons.api.edm.EdmAlternateKeyPropertyPath;
import org.apache.olingo.commons.api.edm.annotation.EdmPropertyPath;

public class EdmAlternateKeyPropertyPathImpl implements EdmAlternateKeyPropertyPath
{
    private final String name;
    private final String alias;
    private final EdmPropertyPath propertyPath;

    public EdmAlternateKeyPropertyPathImpl(String name, String alias, EdmPropertyPath propertyPath)
    {
        this.name = name;
        this.alias = alias;
        this.propertyPath = propertyPath;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public String getAlias()
    {
        return alias;
    }

    @Override
    public EdmPropertyPath getPropertyPath()
    {
        return propertyPath;
    }
}
