package com.anf.core.services;

import java.util.Map;

import org.apache.sling.api.resource.ResourceResolver;

/**
 * @author Sumanth
 */
public interface ContentService {

    /**
     * @return ResorceResolver object
     */
    ResourceResolver getResolver();

    /**
     * @param properties , passing the node properties to save the user details
     */
    void commitUserDetails(Map<String, Object> properties);

}
