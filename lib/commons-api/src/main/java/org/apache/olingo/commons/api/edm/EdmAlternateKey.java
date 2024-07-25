package org.apache.olingo.commons.api.edm;

import java.util.List;

public interface EdmAlternateKey
{
    List<EdmAlternateKeyPropertyPath> getAlternateKeyPropertyPaths();

    EdmAlternateKeyPropertyPath getAlternateKeyPropertyPath(String keyPredicateName);

    List<String> getKeyPredicateNames();
}
