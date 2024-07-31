package org.apache.olingo.commons.api.edm;

import org.apache.olingo.commons.api.edm.annotation.EdmPropertyPath;

public interface EdmAlternateKeyPropertyPath extends EdmKeyPropertyRef {
  EdmPropertyPath getPropertyPath();
}
