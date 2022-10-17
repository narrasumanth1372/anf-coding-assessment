package com.anf.core.models;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(AemContextExtension.class)
class NewsFeedModelTest {

    public final AemContext aemContext = new AemContext();

    private NewsFeedModel newsFeedModel;

    @BeforeEach
    void setup() {
        aemContext.addModelsForClasses(NewsFeedModel.class);
    }

    @Test
    void testNewsFeedListWithValue() {
        aemContext.load(true).json("/com/anf/core/models/NewsFeedModel/NewsFeedModel.json",
                "/content/anf/newsFeed");
        Resource newsFeed = aemContext.resourceResolver().getResource("/content/anf/newsFeed/jcr:content/newsFeed_with_value");
        aemContext.request().setResource(newsFeed);
        aemContext.load(true).json("/com/anf/core/models/NewsFeedModel/NewsFeedCommerceData.json",
                "/var/commerce/products/anf-code-challenge/newsData");
        newsFeedModel = aemContext.request().adaptTo(NewsFeedModel.class);
        assertEquals(2, newsFeedModel.getNewsFeedList().size());
        assertEquals("Caroline Fox", newsFeedModel.getNewsFeedList().get(0).getAuthor());
        assertEquals("News Feed Content", newsFeedModel.getNewsFeedList().get(0).getContent());
        assertEquals("News feed sample content for test!", newsFeedModel.getNewsFeedList().get(0).getDescription());
        assertEquals("News Feed 0", newsFeedModel.getNewsFeedList().get(0).getTitle());
        assertEquals("https://bbc.co.uk/news_feed_0", newsFeedModel.getNewsFeedList().get(0).getUrl());
        assertEquals("https://bbc.co.uk/news_feed_0/news.png", newsFeedModel.getNewsFeedList().get(0).getUrlImage());
        assertNotNull(newsFeedModel.getNewsFeedList().get(0).getCurrentDate());
    }


    @Test
    void testNewsFeedListWithoutValue() {
        aemContext.load(true).json("/com/anf/core/models/NewsFeedModel/NewsFeedModel.json",
                "/content/anf/newsFeed");
        Resource newsFeed = aemContext.resourceResolver().getResource("/content/anf/newsFeed/jcr:content/newsFeed_without_value");
        aemContext.request().setResource(newsFeed);
        newsFeedModel = aemContext.request().adaptTo(NewsFeedModel.class);
        assertTrue(newsFeedModel.getNewsFeedList().isEmpty());
    }

}
