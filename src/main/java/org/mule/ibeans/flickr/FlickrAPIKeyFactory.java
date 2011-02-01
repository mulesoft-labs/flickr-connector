/*
 * $Id: FlickrAPIKeyFactory.java 214 2010-09-08 23:56:43Z ross $
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.ibeans.flickr;

import org.ibeans.api.InvocationContext;
import org.ibeans.api.ParamFactory;

/**
 * TODO
 */
public class FlickrAPIKeyFactory implements ParamFactory
{
    public Object create(String paramName, boolean optional, InvocationContext invocationContext)
    {
        String apiKey = (String) invocationContext.getIBeanConfig().getUriParams().get("api_key");
        if (apiKey == null)
        {
            throw new IllegalArgumentException("Flickr API Key has not been set yet");
        }
        return apiKey;
    }
}
