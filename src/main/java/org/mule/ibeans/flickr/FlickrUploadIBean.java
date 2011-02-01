/*
 * $Id: FlickrUploadIBean.java 214 2010-09-08 23:56:43Z ross $
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.ibeans.flickr;

import java.net.URL;

import org.ibeans.annotation.Call;
import org.ibeans.annotation.param.Attachment;
import org.ibeans.annotation.param.Optional;
import org.ibeans.api.CallException;
import org.ibeans.api.channel.HTTP;

/**
 * Flickr Methods for uploading photos
 */
public interface FlickrUploadIBean extends FlickrAuthenticationIBean
{
    @Call(uri = "http://api.flickr.com/services/upload/", properties = HTTP.POST)
    public String upload(@Attachment("photo") URL photo,
                         @Attachment("auth_token") String authToken,
                         @Optional @Attachment("title") String title,
                         @Optional @Attachment("description") String description,
                         @Optional @Attachment("tags") String tags,
                         @Optional @Attachment("safety_level") FlickrBase.SAFETY_LEVEL safetyLevel,
                         @Optional @Attachment("content_type") FlickrBase.CONTENT_TYPE contentType,
                         @Optional @Attachment("hidden") FlickrBase.VISIBILITY visibility) throws CallException;
}
