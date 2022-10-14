package com.anf.core.services.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anf.core.services.ContentService;

/**
 * @author Sumanth
 */
@Component(immediate = true, service = ContentService.class)
public class ContentServiceImpl implements ContentService {

    public static final String ANF_SERVICE_USER = "anfServiceUser";
    public static final String USERS_PATH = "/var/anf-code-challenge";

    private static final Logger logger = LoggerFactory.getLogger(ContentServiceImpl.class);
    /*
     * injecting resolverFactory in to the service
     */
    @Reference
    private ResourceResolverFactory resolverFactory;


    /**
     * Write utility method to fetch the ResourceResolver
     */
    @Override
    public ResourceResolver getResolver() {
        ResourceResolver resolver = null;
        try {

            Map<String, Object> params = new HashMap<>();
            params.put(ResourceResolverFactory.SUBSERVICE, ANF_SERVICE_USER);
            resolver = resolverFactory.getServiceResourceResolver(params);
        } catch (LoginException e) {
            logger.error("Error processing page creation event", e);
        }

        return resolver;
    }


    /**
     * Save the user details to the node
     */
    @Override
    public void commitUserDetails(Map<String, Object> properties) {
        try {

            ResourceResolver resolver = getResolver();
            Resource resource = resolver.getResource(USERS_PATH);
            /*
             * checking the condition if the user node exist or not
             */
            final String nodeName = (String) properties.get("firstName");
            if (resource != null && resolver.getResource(USERS_PATH.concat("/").concat(nodeName)) == null) {
                resolver.create(resource, nodeName, properties);
                resolver.commit();
            }

        } catch (PersistenceException ex) {

            logger.error("Error finding data roots", ex);

        }


    }

}
