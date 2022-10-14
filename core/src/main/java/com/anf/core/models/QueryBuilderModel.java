package com.anf.core.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.jcr.Session;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.SearchResult;

@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class QueryBuilderModel {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    /*
     * Injecting Resource Resolver
     */
    @SlingObject
    ResourceResolver resourceResolver;

    private List<String> pagePaths = new ArrayList<>();

    /*
     * Init method to frame the Query and read the pages
     */
    @PostConstruct
    public void init() {

        /*
         * constructed Map query
         */
        Map<String, String> map = new HashMap<>();
        map.put("path", "/content");
        map.put("type", "cq:Page");
        map.put("1_property", "jcr:content/anfCodeChallenge");
        map.put("1_property.value", "true");
        map.put("p.limit", "10");

        final QueryBuilder queryBuilder = resourceResolver.adaptTo(QueryBuilder.class);
        final Session session = resourceResolver.adaptTo(Session.class);

        final Query query = queryBuilder.createQuery(PredicateGroup.create(map), session);
        final SearchResult result = query.getResult();
        Resource resultRes = null;

        if (null != result) {
            final Iterator<Resource> nodeItr = result.getResources();
            while (nodeItr.hasNext()) {
                resultRes = nodeItr.next();
                pagePaths.add(resultRes.getPath());
            }

            logger.debug("number of the results {} ", result.getHits().size());
        }
    }

    /**
     * @return paths of the page
     */
    public List<String> getPagePaths() {
        return pagePaths;
    }


}
