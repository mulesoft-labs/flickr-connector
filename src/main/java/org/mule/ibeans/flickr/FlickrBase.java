/*
 * $Id: FlickrBase.java 214 2010-09-08 23:56:43Z ross $
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.ibeans.flickr;

import org.ibeans.annotation.Usage;
import org.ibeans.annotation.filter.XmlErrorFilter;
import org.ibeans.annotation.param.ReturnType;

@Usage("Provides simplified access to the Flickr API for searching and reading photos. This bean supports XML and JSON responses. XML is used by default, but JSON can be set by" +
        "setting the 'format' param to 'json' in one of the init methods. Note that 'json' or '' are the only valid values for 'format'. The ReturnType param can be set to java.lang.String (default), org.w3c.dom.Document, or org.mule.module.json.JsonData.")
@XmlErrorFilter(expr = "/rsp/@stat='fail'", errorCode = "/rsp/err/@code")
public interface FlickrBase
{
    public static enum IMAGE_SIZE
    {
        SmallSquare("s"),
        Thumbnail("t"),
        Small("m"),
        Medium("-"),
        Large("b"),
        Original("o");

        private String value;

        private IMAGE_SIZE(String value)
        {
            this.value = value;
        }

        public String toString()
        {
            return value;
        }

    };

    public static enum IMAGE_TYPE
    {
        Jpeg("jpg"),
        Gif("gif"),
        Png("png");

        private String value;

        private IMAGE_TYPE(String value)
        {
            this.value = value;
        }

        public String toString()
        {
            return value;
        }
    };

    public static enum FORMAT
    {
        JSON("json"),
        XML("");

        private String value;

        private FORMAT(String value)
        {
            this.value = value;
        }

        public String toString()
        {
            return value;
        }
    };

    public static enum SAFETY_LEVEL
    {
        Safe("1"),
        Moderate("2"),
        Restricted("3");

        private String value;

        private SAFETY_LEVEL(String value)
        {
            this.value = value;
        }

        public String toString()
        {
            return value;
        }
    };

    public static enum CONTENT_TYPE
    {
        Photo("1"),
        Screenshot("2"),
        Other("3");

        private String value;

        private CONTENT_TYPE(String value)
        {
            this.value = value;
        }

        public String toString()
        {
            return value;
        }
    };

    public static enum VISIBILITY
    {
        Visible("1"),
        Hidden("2");

        private String value;

        private VISIBILITY(String value)
        {
            this.value = value;
        }

        public String toString()
        {
            return value;
        }
    };

    @ReturnType
    public static final Class DEFAULT_RETURN_TYPE = String.class;
}
