/*
 * $Id: FlickrAuthenticationIBean.java 214 2010-09-08 23:56:43Z ross $
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.ibeans.flickr;

import org.mule.ibeans.flickr.model.AuthToken;

import java.net.URL;

import org.ibeans.annotation.Call;
import org.ibeans.annotation.Return;
import org.ibeans.annotation.State;
import org.ibeans.annotation.Template;
import org.ibeans.annotation.param.PropertyParam;
import org.ibeans.annotation.param.ReturnType;
import org.ibeans.annotation.param.UriParam;
import org.ibeans.api.CallException;
import org.ibeans.api.ParamFactory;

/**
 * TODO
 */

public interface FlickrAuthenticationIBean extends FlickrBase
{
    @UriParam("api_sig")
    static ParamFactory signatureFactory = new FlickrSignatureFactory();

    @State
    public void init(@UriParam("api_key") String apikey, @PropertyParam("secret_key") String secretKey);

    @State
    public void init(@UriParam("api_key") String apikey, @PropertyParam("secret_key") String secretKey, @UriParam("format") FlickrBase.FORMAT format);

    @State
    public void init(@UriParam("api_key") String apikey, @PropertyParam("secret_key") String secretKey, @UriParam("format") FlickrBase.FORMAT format, @ReturnType() Class<?> returnType);


    @Call(uri = "http://api.flickr.com/services/rest/?method=flickr.auth.getFrob&api_key={api_key}&api_sig={api_sig}")
    @Return("xpath2:/rsp/frob")
    public String getFrob() throws CallException;

    @Call(uri = "http://api.flickr.com/services/rest/?method=flickr.auth.getToken&api_key={api_key}&frob={frob}&api_sig={api_sig}")
    public AuthToken getAuthToken(@UriParam("frob") String frob) throws CallException;

    @Call(uri = "http://api.flickr.com/services/rest/?method=flickr.auth.checkToken&api_key={api_key}&auth_token={auth_token}&api_sig={api_sig}")
    public AuthToken checkAuthToken(@UriParam("auth_token") String token) throws CallException;

    @Call(uri = "http://api.flickr.com/services/rest/?method=flickr.auth.getFullToken&api_key={api_key}&mini_token={mini_token}&api_sig={api_sig}")
    public AuthToken getFullAuthToken(@UriParam("mini_token") String miniToken) throws CallException;

    @Template("http://api.flickr.com/services/auth/?api_key={api_key}&perms={perms}&frob={frob}&api_sig={api_sig}")
    public URL buildAuthenticationURL(@UriParam("frob") String frob, @UriParam("perms") String permission) throws CallException;

    @Call(uri = "{url}")
    public String redirect(@UriParam("url") URL url) throws CallException;
}
