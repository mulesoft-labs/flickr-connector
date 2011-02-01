/*
 * $Id: User.java 72 2009-11-12 21:26:33Z ross $
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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represnetation of a flickr User as defined by the Auth Token response
 */
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class User
{
    @XmlAttribute
    private String nsid;

    @XmlAttribute
    private String username;

    @XmlAttribute
    private String fullname;

    public User()
    {
        //needed by JAXB
    }

    public User(String nsid, String username, String fullname)
    {
        this.nsid = nsid;
        this.username = username;
        this.fullname = fullname;
    }

    public String getNsid()
    {
        return nsid;
    }

    public String getUsername()
    {
        return username;
    }

    public String getFullname()
    {
        return fullname;
    }
}
