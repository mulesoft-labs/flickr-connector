/*
 * $Id: FlickrAPIKeyFactory.java 214 2010-09-08 23:56:43Z ross $
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.modules.flickr;

import org.mule.cloudconnect.ibeans.IBeanFactoryBean;
import org.mule.ibeans.flickr.FlickrBase;
import org.mule.ibeans.flickr.FlickrIBean;

public class FlickrFactoryBean extends IBeanFactoryBean<FlickrIBean>
{

    private String apiKey;
    private String secretKey;
    private FlickrIBean.FORMAT format;

    @Override
    public void init(FlickrIBean flickrIBean)
    {
        flickrIBean.init(apiKey, secretKey, format);
    }

    public String getApiKey()
    {
        return apiKey;
    }

    public void setApiKey(String apiKey)
    {
        this.apiKey = apiKey;
    }

    public String getSecretKey()
    {
        return secretKey;
    }

    public void setSecretKey(String secretKey)
    {
        this.secretKey = secretKey;
    }

    public FlickrBase.FORMAT getFormat()
    {
        return format;
    }

    public void setFormat(FlickrBase.FORMAT format)
    {
        this.format = format;
    }
}
