package org.mule.ibeans.flickr.config;

import org.mule.api.MuleEvent;
import org.mule.construct.SimpleFlowConstruct;
import org.mule.tck.FunctionalTestCase;

public class FlickrSearchTagsTest extends FunctionalTestCase
{

    @Override
    protected String getConfigResources()
    {
        return "config/flickr-search-tags.xml";
    }

    public void testSearchTags() throws Exception
    {
        String payload = "<flickr-search-tags/>";
        SimpleFlowConstruct flow = lookupFlowConstruct("searchTagsFlow");
        MuleEvent event = getTestEvent(payload);
        MuleEvent responseEvent = flow.process(event);
    }

    private SimpleFlowConstruct lookupFlowConstruct(String name)
    {
        return (SimpleFlowConstruct) muleContext.getRegistry().lookupFlowConstruct(name);
    }

}