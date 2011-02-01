/*
 * $Id: AuthToken.java 72 2009-11-12 21:26:33Z ross $
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.ibeans.flickr.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Representation of the response Auth Token from Flickr
 */
@XmlRootElement(name = "auth")
@XmlAccessorType(XmlAccessType.FIELD)
public class AuthToken
{
    private String token;
    private String perms;
    private User user;

    public AuthToken()
    {
        //needed by JAXB
    }

    public AuthToken(String token, String perms, User user)
    {
        this.token = token;
        this.perms = perms;
        this.user = user;
    }

    public String getToken()
    {
        return token;
    }

    public String getPerms()
    {
        return perms;
    }

    public User getUser()
    {
        return user;
    }
}
