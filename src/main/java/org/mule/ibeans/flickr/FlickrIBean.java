/*
 * $Id: FlickrIBean.java 214 2010-09-08 23:56:43Z ross $
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.ibeans.flickr;

import org.ibeans.annotation.IBeanGroup;

import org.mule.tools.cloudconnect.annotations.Connector;
import org.mule.modules.flickr.FlickrFactoryBean;

@IBeanGroup
@Connector(namespacePrefix = "flickr", namespaceUri = "http://www.mulesoft.org/schema/mule/flickr", 
           factory = FlickrFactoryBean.class)
public interface FlickrIBean extends FlickrBase, FlickrSearchIBean, FlickrUploadIBean, FlickrAuthenticationIBean
{

}