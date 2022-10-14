package com.anf.core.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Sumanth
 */
@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class NewsFeedModel {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    /*
     * created global constants  instead of getting from the configuration
     */
    public static final String PATH = "/var/commerce/products/anf-code-challenge/newsData";
    public static final String DATE_FORMAT = "MM.dd.yyyy";
    public static final String AUTHOR = "author";
    public static final String CONTENT = "content";
    public static final String DESCRIPTION = "description";
    public static final String TITLE = "title";
    public static final String URL = "url";
    public static final String URLIMAGE = "urlImage";

    /*
     *  Injecting or fetching the path from the component
     */
    @ValueMapValue
    private String newsFeedPath;

    /*
     *  Injecting the resourceResolver
     */
    @SlingObject
    ResourceResolver resourceResolver;

    private List<NewsFeed> newsFeed = new ArrayList<NewsFeed>();

    /**
     * Business logic to read the child nodes and adding their paths to the List
     */
    @PostConstruct
    public void getBusiness() {

        Resource resource = resourceResolver.getResource(newsFeedPath);

        String date = new SimpleDateFormat(DATE_FORMAT).format(new Date());

        if (resource != null) {
            final Iterator<Resource> itr = resource.listChildren();
            while (itr.hasNext()) {
                final Resource news = itr.next();
                NewsFeed newsDetails = new NewsFeed();
                newsDetails.setAuthor(news.getValueMap().get(AUTHOR, StringUtils.EMPTY));
                newsDetails.setContent(news.getValueMap().get(CONTENT, StringUtils.EMPTY));
                newsDetails.setDescription(news.getValueMap().get(DESCRIPTION, StringUtils.EMPTY));
                newsDetails.setTitle(news.getValueMap().get(TITLE, StringUtils.EMPTY));
                newsDetails.setUrl(news.getValueMap().get(URL, StringUtils.EMPTY));
                newsDetails.setUrlImage(news.getValueMap().get(URLIMAGE, StringUtils.EMPTY));
                newsDetails.setCurrentdate(date);
                newsFeed.add(newsDetails);
            }

            logger.debug("news feed list size {} ", newsFeed.size());
        }
    }

    /**
     * @return List of the NewsFeeds
     */
    public List<NewsFeed> getNewsFeed() {
        return newsFeed;
    }


}
