/*
 *  Copyright 2015 Adobe Systems Incorporated
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.anf.core.servlets;

import com.anf.core.services.ContentService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Sumanth
 *
 */
@Component(service = { Servlet.class })
@SlingServletPaths( value = "/bin/saveUserDetails")
@ServiceDescription(" Servlet used to save user details in the nodes ")
public class UserServlet extends SlingSafeMethodsServlet {

    private static final long serialVersionUID = 1L;

    @Reference
    private ContentService contentService;

    private final transient Logger log=LoggerFactory.getLogger(this.getClass());

    /**
     * doGet() used to save the data and return the response to the ajax
     */
    @Override
    protected void doGet(final SlingHttpServletRequest req,
            final SlingHttpServletResponse resp) throws ServletException, IOException {
    	/*
    	 * getting the requested parameters from the ajax
    	 */

    	final String firstName=req.getParameter("firstName");
    	final String lastName=req.getParameter("lastName");
    	final int age=Integer.parseInt(req.getParameter("age"));
    	final String country=req.getParameter("country");
    	String responseString="";
    	/*
    	 * adding all the properties to the map to save the data to the user node
    	 */

    	Map<String,Object> properties = new HashMap<String,Object>();
    	properties.put("jcr:primaryType", "nt:unstructured");
    	properties.put("firstName", firstName);
		properties.put("lastName", lastName);
		properties.put("age", age);
		properties.put("country", country);

    	int maxAge = 0;
    	int minAge = 0;
    	/*
    	 * Getting the user AGE from the /etc/age assigning to the variables
    	 */
    	ResourceResolver resolver=req.getResourceResolver();
    	Resource resource=resolver.getResource("/etc/age");
    	if(null!=resource) {

    	ModifiableValueMap modifiableValueMap = resource.adaptTo(ModifiableValueMap.class);
    	  maxAge=Integer.parseInt(modifiableValueMap.get("maxAge",String.class));
    	  minAge=Integer.parseInt(modifiableValueMap.get("minAge",String.class));
    	}

    	/*
    	 * Validating the age criteria if it is true it will call the commitUserDetails and save the data
    	 */

    	if (age >= minAge && age <= maxAge) {

    		log.debug("user age is under the limit");
    		contentService.commitUserDetails(properties);
    		responseString ="user data is saved successfully !!!! ";

    	}else{
    			log.debug("user age is not under the limit");
    			responseString ="unable create the user because user age is not under the limit";
    		}

    	resp.setContentType("application/text");
        resp.getWriter().write(responseString);

    }
}
