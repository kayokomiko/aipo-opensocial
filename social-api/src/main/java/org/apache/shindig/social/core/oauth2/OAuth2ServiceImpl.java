/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.shindig.social.core.oauth2;

import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.shindig.social.core.oauth2.OAuth2Client.ClientType;
import org.apache.shindig.social.core.oauth2.OAuth2Types.CodeType;
import org.apache.shindig.social.core.oauth2.OAuth2Types.ErrorType;
import org.apache.shindig.social.core.oauth2.validators.AccessTokenRequestValidator;
import org.apache.shindig.social.core.oauth2.validators.AuthorizationCodeRequestValidator;
import org.apache.shindig.social.core.oauth2.validators.DefaultResourceRequestValidator;
import org.apache.shindig.social.core.oauth2.validators.OAuth2ProtectedResourceValidator;
import org.apache.shindig.social.core.oauth2.validators.OAuth2RequestValidator;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

/**
 * A simple in-memory implementation of the OAuth 2 services.
 */
@Singleton
public class OAuth2ServiceImpl implements OAuth2Service {

  private final OAuth2DataService store; // underlying OAuth data store

  private final long authCodeExpires;

  private final long accessTokenExpires;

  // validators
  private final OAuth2RequestValidator accessTokenValidator;

  private final OAuth2RequestValidator authCodeValidator;

  private final OAuth2ProtectedResourceValidator resourceReqValidator;

  @Inject
  public OAuth2ServiceImpl(OAuth2DataService store,
      @Named("shindig.oauth2.authCodeExpiration") long authCodeExpires,
      @Named("shindig.oauth2.accessTokenExpiration") long accessTokenExpires) {
    this.store = store;

    this.authCodeExpires = authCodeExpires;
    this.accessTokenExpires = accessTokenExpires;

    // TODO (Matt): validators should be injected
    authCodeValidator = new AuthorizationCodeRequestValidator(store);
    accessTokenValidator = new AccessTokenRequestValidator(store);
    resourceReqValidator = new DefaultResourceRequestValidator(store);
  }

  @Override
  public OAuth2DataService getDataService() {
    return store;
  }

  protected long getAuthCodeExpires() {
    return authCodeExpires;
  }

  protected long getAccessTokenExpires() {
    return accessTokenExpires;
  }

  @Override
  public void authenticateClient(OAuth2NormalizedRequest req)
      throws OAuth2Exception {
    OAuth2Client client = store.getClient(req.getClientId());
    if (client == null) {
      OAuth2NormalizedResponse resp = new OAuth2NormalizedResponse();
      resp.setError(ErrorType.INVALID_CLIENT.toString());
      resp.setErrorDescription("The client ID is invalid or not registered");
      resp.setBodyReturned(true);
      resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      throw new OAuth2Exception(resp);
    }
    String realSecret = client.getSecret();
    String reqSecret = req.getClientSecret();
    if (realSecret != null
      || reqSecret != null
      || client.getType() == ClientType.CONFIDENTIAL) {
      if (realSecret == null
        || reqSecret == null
        || !realSecret.equals(reqSecret)) {
        OAuth2NormalizedResponse resp = new OAuth2NormalizedResponse();
        resp.setError(ErrorType.UNAUTHORIZED_CLIENT.toString());
        resp.setErrorDescription("The client failed to authorize");
        resp.setBodyReturned(true);
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        throw new OAuth2Exception(resp);
      }
    }
  }

  @Override
  public void validateRequestForAuthCode(OAuth2NormalizedRequest req)
      throws OAuth2Exception {
    authCodeValidator.validateRequest(req);
  }

  @Override
  public void validateRequestForAccessToken(OAuth2NormalizedRequest req)
      throws OAuth2Exception {
    accessTokenValidator.validateRequest(req);
  }

  @Override
  public void validateRequestForResource(OAuth2NormalizedRequest req,
      Object resourceRequest) throws OAuth2Exception {
    resourceReqValidator.validateRequestForResource(req, resourceRequest);
  }

  @Override
  public OAuth2Code grantAuthorizationCode(OAuth2NormalizedRequest req) {
    OAuth2Code authCode = generateAuthorizationCode(req);
    store.registerAuthorizationCode(req.getClientId(), authCode);
    return authCode;
  }

  @Override
  public OAuth2Code grantAccessToken(OAuth2NormalizedRequest req) {
    OAuth2Code accessToken = generateAccessToken(req);
    OAuth2Code authCode =
      store.getAuthorizationCode(req.getClientId(), req.getAuthorizationCode());
    if (authCode != null) {
      authCode.setRelatedAccessToken(accessToken);
    }
    store.registerAccessToken(req.getClientId(), accessToken);
    return accessToken;
  }

  @Override
  public OAuth2Code grantRefreshToken(OAuth2NormalizedRequest req) {
    OAuth2Code refreshToken = generateRefreshToken(req);
    store.registerRefreshToken(req.getClientId(), refreshToken);
    return refreshToken;
  }

  @Override
  public OAuth2Code generateAuthorizationCode(OAuth2NormalizedRequest req) {
    OAuth2Code authCode = new OAuth2Code();
    authCode.setValue(UUID.randomUUID().toString());
    authCode.setExpiration(System.currentTimeMillis() + authCodeExpires);
    OAuth2Client client = store.getClient(req.getClientId());
    authCode.setClient(client);
    if (req.getRedirectURI() != null) {
      authCode.setRedirectURI(req.getRedirectURI());
    } else {
      authCode.setRedirectURI(client.getRedirectURI());
    }
    return authCode;
  }

  @Override
  public OAuth2Code generateAccessToken(OAuth2NormalizedRequest req) {
    // generate token value
    OAuth2Code accessToken = new OAuth2Code();
    accessToken.setType(CodeType.ACCESS_TOKEN);
    accessToken.setValue(UUID.randomUUID().toString());
    accessToken.setExpiration(System.currentTimeMillis() + accessTokenExpires);
    if (req.getRedirectURI() != null) {
      accessToken.setRedirectURI(req.getRedirectURI());
    } else {
      accessToken.setRedirectURI(store
        .getClient(req.getClientId())
        .getRedirectURI());
    }

    // associate with existing authorization code, if an auth code exists.
    if (req.getAuthorizationCode() != null) {
      OAuth2Code authCode =
        store.getAuthorizationCode(req.getClientId(), req
          .getAuthorizationCode());
      accessToken.setRelatedAuthCode(authCode);
      accessToken.setClient(authCode.getClient());
      if (authCode.getScope() != null) {
        accessToken.setScope(new ArrayList<String>(authCode.getScope()));
      }
    }

    return accessToken;
  }

  // TODO (Eric): Refresh tokens are not yet supported.
  @Override
  public OAuth2Code generateRefreshToken(OAuth2NormalizedRequest req) {
    throw new RuntimeException("not yet implemented");
  }
}
