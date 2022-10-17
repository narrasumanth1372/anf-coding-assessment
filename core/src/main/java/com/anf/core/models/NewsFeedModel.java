package com.anf.core.models;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * @author Sumanth
 */
@Model(adaptables = SlingHttpServletRequest.class)
public class NewsFeedModel {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ValueMapValue @Optional
    private String newsFeedPath;

    @Self
    private SlingHttpServletRequest request;

    private List<NewsFeed> newsFeedList;

    /**
     * Business logic to read the child nodes and adding their paths to the List
     */
    @PostConstruct
    public void getBusiness() {
        if (StringUtils.isNotBlank(newsFeedPath)) {
            Resource resource = request.getResourceResolver().getResource(newsFeedPath);
            if (Objects.nonNull(resource)) {
                newsFeedList = new ArrayList<>();
                Iterator<Resource> iterator = resource.listChildren();
                while (iterator.hasNext()) {
                    Resource newsRes = iterator.next();
                    NewsFeed newsFeed = newsRes.adaptTo(NewsFeed.class);
                    newsFeedList.add(newsFeed);
                }
            }
            logger.debug("News Feed list size {} ", newsFeedList.size());
        }
    }

    /**
     * @return List of the NewsFeeds
     */
    public List<NewsFeed> getNewsFeedList() {
        if (newsFeedList != null && !newsFeedList.isEmpty()) {
            return new ArrayList<>(newsFeedList);
        }
        return new ArrayList<>();
    }
}
