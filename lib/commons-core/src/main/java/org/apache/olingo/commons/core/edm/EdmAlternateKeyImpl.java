package org.apache.olingo.commons.core.edm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.olingo.commons.api.edm.EdmAlternateKey;
import org.apache.olingo.commons.api.edm.EdmAlternateKeyPropertyPath;

public class EdmAlternateKeyImpl implements EdmAlternateKey {
  private final List<EdmAlternateKeyPropertyPath> keyPropertyPaths;
  private final Map<String, EdmAlternateKeyPropertyPath> keyPropertyPathsByName;

  public EdmAlternateKeyImpl(List<EdmAlternateKeyPropertyPath> keyPropertyPaths) {
    this.keyPropertyPaths = Collections.unmodifiableList(keyPropertyPaths);
    this.keyPropertyPathsByName = keyPropertyPaths.stream()
        .collect(Collectors.toMap(
            keyPartPath -> Optional.ofNullable(keyPartPath.getAlias()).orElse(keyPartPath.getName()),
            Function.identity()));
  }

  @Override
  public List<EdmAlternateKeyPropertyPath> getAlternateKeyPropertyPaths() {
    return keyPropertyPaths;
  }

  @Override
  public EdmAlternateKeyPropertyPath getAlternateKeyPropertyPath(String keyPredicateName) {
    return keyPropertyPathsByName.get(keyPredicateName);
  }

  @Override
  public List<String> getKeyPredicateNames() {
    return new ArrayList<>(keyPropertyPathsByName.keySet());
  }
}
