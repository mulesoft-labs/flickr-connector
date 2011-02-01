/*
 * $Id: FlickrTestCase.java 214 2010-09-08 23:56:43Z ross $
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.ibeans.flickr;

import org.mule.ibeans.test.IBeansRITestSupport;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.ibeans.annotation.IntegrationBean;
import org.ibeans.api.CallException;
import org.ibeans.api.IBeansException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mule.ibeans.IBeansSupport.prettyPrintXml;
import static org.mule.ibeans.IBeansSupport.select;

@Ignore
public class FlickrTestCase extends IBeansRITestSupport
{
    public static final String SEARCH_TERM = "food";
    public static final String BAD_SEARCH_TERM = "bad";

    @IntegrationBean
    private FlickrIBean flickr;

    @Before
    public void init() throws IBeansException
    {
        getFlickr().init("${flickr.api.key}","${flickr.secret.key}", FlickrIBean.FORMAT.XML, Document.class);
        registerBeans(new FlickrTransformers());
    }

    protected FlickrIBean getFlickr()
    {
        return flickr;
    }

    /*

    @Test
    public void flickrSearch() throws Exception
    {
        Document doc = getFlickr().search(SEARCH_TERM);
        assertNotNull(doc);
        List<URL> photoUrls = new ArrayList<URL>();

        for (Node n : select("//photo", doc))
        {
            photoUrls.add(getFlickr().getPhotoURL(n));
        }

        assertEquals(10, photoUrls.size());
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

    @Test
    public void testSizeEnum() throws Exception
    {
        assertEquals("o", FlickrIBean.IMAGE_SIZE.Original.toString());
        assertEquals("m", FlickrIBean.DEFAULT_IMAGE_SIZE.toString());
        assertEquals(FlickrIBean.IMAGE_SIZE.Original, Enum.valueOf(FlickrIBean.IMAGE_SIZE.class, "Original"));

        Document doc = getFlickr().search(SEARCH_TERM);

        assertNotNull(doc);
        List<URL> photoUrls = new ArrayList<URL>();

        System.out.println(prettyPrintXml(doc));
        for (Node n : select("//photo", doc))
        {
            photoUrls.add(getFlickr().getPhotoURL(n, FlickrIBean.IMAGE_SIZE.SmallSquare, FlickrIBean.IMAGE_TYPE.Jpeg));
        }
        assertEquals(10, photoUrls.size());
        assertTrue(photoUrls.get(0).toString().endsWith("_s.jpg"));
    }
    */

    //TODO
//    @Test
//    public void testAuth() throws Exception
//    {
//        String frob = getFlickr().getFrob();
//        assertNotNull(frob);
//        URL url = getFlickr().buildAuthenticationURL(frob, "delete");
//        //Just make sure it works
//        assertNotNull(url);
//        //We can't generate a an authToken automatically as it requries the account owner to give iBeans access
//        //but we can check the validity of an existing auth token
//        //TODO URGENT can't use a placeholder value here for some reason
//        AuthToken auth = getFlickr().checkAuthToken((String)iBeansContext.getConfig().get("flickr.auth.token"));
//        assertNotNull(auth);
//        assertEquals("delete", auth.getPerms());
//        assertEquals("rossmason", auth.getUser().getUsername());
//    }

    //TODO upload not working yet, suspect it has something to do with the way form-data is handled
//    public void testUpload() throws Exception
//    {
//        URL url = new URL("file:///projects/ibeans-contrib/twitter/src/test/resources/profile.png");
//        String result = getFlickr().upload(url, get("flickr.auth.token"), "Test 1", null, null, null, null, null);
//        System.out.println(result);
//    }
}