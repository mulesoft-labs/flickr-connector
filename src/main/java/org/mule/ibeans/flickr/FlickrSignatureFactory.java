/*
 * $Id: FlickrSignatureFactory.java 214 2010-09-08 23:56:43Z ross $
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.ibeans.flickr;

import org.mule.util.StringUtils;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.Map;

import javax.activation.DataSource;

import org.apache.commons.io.IOUtils;
import org.ibeans.api.InvocationContext;
import org.ibeans.api.ParamFactory;

/**
 * A param factory used to create the Flickr MD5 signatured requeired for authentication for some method calls
 */
public class FlickrSignatureFactory implements ParamFactory
{
    public Object create(String paramName, boolean optional, InvocationContext invocationContext)
    {
        String secretKey = (String) invocationContext.getIBeanConfig().getPropertyParams().get("secret_key");
        if (secretKey == null)
        {
            throw new IllegalArgumentException("A Flickr secret key must be set using one of the init methods on this iBeans");
        }
        String sig;

        StringBuffer buf = new StringBuffer();
        buf.append(secretKey);

        try
        {
            //Need to find a cleaner way of doing this for users
            if ("GET".equals(invocationContext.getIBeanConfig().getPropertyParams().get("http.method")) || invocationContext.isTemplateMethod())
            {
                Map<String, String> params;
                if (invocationContext.isTemplateMethod())
                {
                    params = invocationContext.getTemplateSpecificUriParams();
                }
                else
                {
                    params = invocationContext.getCallSpecificUriParams();
                }
                for (Map.Entry<String, String> entry : params.entrySet())
                {
                    //Always delete the param for this factory. Also photo should be removed
                    if (entry.getKey().equals(paramName) || entry.getKey().equals("photo"))
                    {
                        continue;
                    }
                    else
                    {
                        buf.append(entry.getKey()).append(entry.getValue());
                    }
                }
            }
            else
            {
                for (DataSource ds : invocationContext.getIBeanConfig().getAttachments())
                {
                    if (ds.getName().equals("photo") || ds.getName().equals("api_sig"))
                    {
                        continue;
                    }
                    buf.append(ds.getName()).append(IOUtils.toCharArray(ds.getInputStream()));
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        sig = buf.toString();

        try
        {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(buf.toString().getBytes("UTF-8"));
            sig = StringUtils.toHexString(bytes);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return sig;
    }
}
