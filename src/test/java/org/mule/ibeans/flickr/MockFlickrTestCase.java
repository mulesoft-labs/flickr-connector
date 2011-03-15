/*
 * $Id: MockFlickrTestCase.java 214 2010-09-08 23:56:43Z ross $
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.ibeans.flickr;

import org.ibeans.annotation.MockIntegrationBean;
import org.junit.Before;
import org.junit.Ignore;

import static org.mockito.Mockito.when;

@Ignore
public class MockFlickrTestCase extends FlickrTestCase
{
    @MockIntegrationBean
    private FlickrIBean flickr;

    @Before
    public void initMockData() throws Exception
    {
        when(getFlickr().search(SEARCH_TERM, 1, 10)).thenAnswer(withXmlData("FlickrTestCase-testFlickr-response1.xml", getFlickr()));
        when(getFlickr().search(BAD_SEARCH_TERM, 1, 10)).thenAnswer(withXmlData("FlickrTestCase-testFlickrError-response1.xml", getFlickr()));
        when(getFlickr().getFrob()).thenAnswer(withXmlData("FlickrTestCase-testAuth-response1.xml", getFlickr()));
        when(getFlickr().checkAuthToken((String)iBeansContext.getConfig().get("flickr.auth.token"))).thenAnswer(withXmlData("FlickrTestCase-testAuth-response2.xml", getFlickr()));
    }

    protected FlickrIBean getFlickr()
    {
        return flickr;
    }
}