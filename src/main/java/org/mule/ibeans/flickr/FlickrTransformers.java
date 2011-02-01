/*
 * $Id: FlickrTransformers.java 214 2010-09-08 23:56:43Z ross $
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.ibeans.flickr;

import org.mule.api.annotations.Transformer;
import org.mule.ibeans.flickr.model.AuthToken;
import org.mule.util.IOUtils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.ibeans.impl.IBeansSupport;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

/**
 * Transformers used by the Flickr iBean
 */
public class FlickrTransformers
{
    @Transformer
    public BufferedImage streamToBufferedImage(InputStream is) throws IOException
    {
        try
        {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            IOUtils.copyLarge(is, baos);

            BufferedImage image = ImageIO.read(new ByteArrayInputStream(baos.toByteArray()));
            if (image == null)
            {
                throw new IOException("could not load images from stream: " + baos.toString());
            }
            return image;
        }
        finally
        {
            is.close();
        }
    }

    /**
     * Convert Flickr response Xml to an {@link org.mule.ibeans.flickr.model.AuthToken} object using JAXB
     *
     * @param doc     the resposne Xml document, note that other source types are supported  (See @Transformer.sourceTypes)
     * @param context the JAXBContext created by iBeans for this transformer.  Note it is also possible to define your own
     *                for the whole application or create one yourself in the transformer itself.
     * @return an AuthToken object that contains authentication information for working with Flickr
     * @throws javax.xml.bind.JAXBException if the Xml cannot be processed by JAXB
     */
    @Transformer(sourceTypes = {String.class, InputStream.class, InputSource.class})
    public AuthToken xmlToAuthToken(Document doc, JAXBContext context) throws JAXBException
    {
        //Remove the <rsp> wrapper element
        return (AuthToken) context.createUnmarshaller().unmarshal(IBeansSupport.selectOne("/rsp/auth", doc));
    }

    @Transformer
    public URL transformStringToURL(String string) throws MalformedURLException
    {
        try
        {
            return new URL(string);
        }
        catch (MalformedURLException e)
        {
            //provide a more descriptive error message
            throw new MalformedURLException(e.getMessage() + " " + string);
        }
    }
}
