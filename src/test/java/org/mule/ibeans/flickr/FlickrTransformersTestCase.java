/*
 * $Id: FlickrTransformersTestCase.java 214 2010-09-08 23:56:43Z ross $
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.ibeans.flickr;

import org.mule.api.transformer.DiscoverableTransformer;
import org.mule.api.transformer.Transformer;
import org.mule.ibeans.flickr.model.AuthToken;
import org.mule.ibeans.test.IBeansRITestSupport;

import java.io.ByteArrayInputStream;
import java.net.URL;

import org.ibeans.api.IBeansException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@Ignore
public class FlickrTransformersTestCase extends IBeansRITestSupport
{
    @Before
    public void init() throws IBeansException
    {
        registerBeans(new FlickrTransformers());
    }

    @Test
    public void flickrTransformers() throws Exception
    {

        //Test auto transform
        URL url = iBeansContext.transform("http://foo.com", URL.class);
        assertNotNull(url);
        assertEquals("http://foo.com", url.toString());

        Object t = iBeansContext.getConfig().get("FlickrTransformers.streamToBufferedImage");
        assertNotNull(t);
        assertTrue(t instanceof Transformer);
        assertTrue(t instanceof DiscoverableTransformer);
    }

    @Test
    public void jaxBTransformers() throws Exception
    {
        String data = "<rsp stat='ok'><auth>\n" +
                "\t<token>976598454353455</token>\n" +
                "\t<perms>write</perms>\n" +
                "\t<user nsid=\"12037949754@N01\" username=\"Bees\" fullname=\"Cal H\" />\n" +
                "</auth></rsp>";
        AuthToken token = iBeansContext.transform(new ByteArrayInputStream(data.getBytes()), AuthToken.class);
        assertNotNull(token);
        assertEquals("976598454353455", token.getToken());
        assertEquals("write", token.getPerms());
        assertEquals("12037949754@N01", token.getUser().getNsid());
        assertEquals("Bees", token.getUser().getUsername());
        assertEquals("Cal H", token.getUser().getFullname());

    }


}
