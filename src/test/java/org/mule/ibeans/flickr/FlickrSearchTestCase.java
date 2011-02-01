/*
 * $Id: FlickrSearchTestCase.java 214 2010-09-08 23:56:43Z ross $
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.ibeans.flickr;

import org.mule.ibeans.test.IBeansRITestSupport;
import org.mule.module.json.JsonData;

import org.codehaus.jackson.node.ArrayNode;
import org.ibeans.annotation.IntegrationBean;
import org.ibeans.api.CallException;
import org.ibeans.api.IBeansException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.w3c.dom.Document;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * This tests that we can use the FlickrSearch iBean without needing a 'secret_key' which is required for other
 * parts of the Flickr API
 */
@Ignore
public class FlickrSearchTestCase extends IBeansRITestSupport
{
    public static final String SEARCH_TERM = "food";
    public static final String BAD_SEARCH_TERM = "bad";

    @IntegrationBean
    private FlickrSearchIBean flickr;

    @Before
    public void init() throws IBeansException
    {
        //getFlickr().init("${flickr.api.key}", FlickrIBean.FORMAT.JSON, JsonData.class);
        registerBeans(new FlickrTransformers());
    }

    protected FlickrSearchIBean getFlickr()
    {
        return flickr;
    }

    /*

    @Test
    public void flickrSearch() throws Exception
    {
        JsonData doc = getFlickr().search(SEARCH_TERM);
        assertNotNull(doc);
        assertEquals(10, ((ArrayNode) doc.get("/photos/photo")).size());
    }

    //This will fail since "badkey" is not a recognised key
    @Test
    public void flickrError() throws Exception
    {
        getFlickr().init("badkey", FlickrIBean.FORMAT.XML, Document.class);

        try
        {
            getFlickr().search(BAD_SEARCH_TERM);
        }
        catch (CallException e)
        {
            //Flickr error code
            assertEquals("100", e.getErrorCode());
        }
    }

    */
}