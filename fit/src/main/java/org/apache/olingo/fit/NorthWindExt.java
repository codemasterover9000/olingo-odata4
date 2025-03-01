/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.olingo.fit;

import java.io.IOException;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import org.apache.cxf.interceptor.InInterceptors;
import org.apache.olingo.fit.rest.ResolvingReferencesInterceptor;
import org.apache.olingo.fit.rest.XHTTPMethodInterceptor;
import org.apache.olingo.fit.utils.ConstantKey;
import org.apache.olingo.fit.utils.Constants;
import org.springframework.stereotype.Service;

@Service
@Path("/V40/NorthWindExt.svc")
@InInterceptors(classes = { XHTTPMethodInterceptor.class, ResolvingReferencesInterceptor.class })
public class NorthWindExt extends Services {

  public NorthWindExt() throws IOException {
    super();
  }

  @Override
  public Response getMetadata() {
    return getMetadata("northwindExt-" + Constants.get(ConstantKey.METADATA));
  }
}
