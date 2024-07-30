package org.apache.olingo.commons.core.edm;

import java.util.Optional;

import org.apache.olingo.commons.api.edm.EdmAlternateKeyPropertyPath;
import org.apache.olingo.commons.api.edm.EdmEntityType;
import org.apache.olingo.commons.api.edm.EdmException;
import org.apache.olingo.commons.api.edm.EdmNavigationProperty;
import org.apache.olingo.commons.api.edm.EdmProperty;
import org.apache.olingo.commons.api.edm.EdmStructuredType;
import org.apache.olingo.commons.api.edm.annotation.EdmPropertyPath;

public class EdmAlternateKeyPropertyPathImpl implements EdmAlternateKeyPropertyPath {
  private final EdmEntityType edmEntityType;
  private final String name;
  private final String alias;
  private final EdmPropertyPath propertyPath;

  private EdmProperty property;

  public EdmAlternateKeyPropertyPathImpl(EdmEntityType edmEntityType, String name, String alias,
      EdmPropertyPath propertyPath) {
    this.edmEntityType = edmEntityType;
    this.name = name;
    this.alias = alias;
    this.propertyPath = propertyPath;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getAlias() {
    return alias;
  }

  @Override
  public EdmProperty getProperty() {
    if (property == null) {
      String[] splitPath = getName().split("/");

      // Check against local object structure
      EdmStructuredType localTarget = edmEntityType;
      for (int i = 0; i < splitPath.length - 1; i++) {
        EdmProperty property = localTarget.getStructuralProperty(splitPath[i]);
        if (property == null) {
          localTarget = null;
          break;
        }
        localTarget = (EdmStructuredType) property.getType();
      }

      // Get property
      property = Optional.ofNullable(localTarget)
          .map(edmStructuredType -> edmStructuredType.getStructuralProperty(splitPath[splitPath.length - 1]))
          .orElseGet(() -> {
            // Check against navigation properties
            if (splitPath.length > 0) {
              EdmNavigationProperty navigationProperty = edmEntityType.getNavigationProperty(splitPath[0]);
              EdmStructuredType externalTarget = navigationProperty.getType();
              for (int i = 1; i < splitPath.length - 1; i++) {
                EdmProperty property = externalTarget.getStructuralProperty(splitPath[i]);
                if (property == null) {
                  return null;
                }
                externalTarget = (EdmStructuredType) property.getType();
              }
              return externalTarget.getStructuralProperty(splitPath[splitPath.length - 1]);
            }
            return null;
          });

      if (property == null) {
        throw new EdmException("Invalid property ref specified. Can´t find property "
            + getName() + " from " + edmEntityType.getName());
      }
    }

    return property;
  }

  @Override
  public EdmPropertyPath getPropertyPath() {
    return propertyPath;
  }
}
