
package com.anf.core.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.Workspace;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;
import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anf.core.services.ContentService;

/**
 * @author Sumanth
 */
@Component(service = {Servlet.class})
@SlingServletPaths(value = "/bin/fetchPageResults")
public class SQL2Servlet extends SlingSafeMethodsServlet {

    private static final long serialVersionUID = 1L;

    @Reference
    private ContentService contentService;

    private final transient Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * doGet() to fetch the list of the child pages
     */
    @Override
    protected void doGet(final SlingHttpServletRequest req,
                         final SlingHttpServletResponse resp) {

        String query = "SELECT * FROM [cq:PageContent] as nodes WHERE ISDESCENDANTNODE ([/content/anf-code-challenge/us/en]) \n" +
                "AND nodes.[anfCodeChallenge] = CAST(\"true\" AS BOOLEAN) ";
        ResourceResolver resolver = req.getResourceResolver();
        try {

            List<String> allResources = new ArrayList<>();
            Session session = resolver.adaptTo(Session.class);
            Workspace workspace = session.getWorkspace();
            QueryManager qm = workspace.getQueryManager();
            Query jcrQuery = qm.createQuery(query, "JCR-SQL2");
            jcrQuery.setLimit(10);

            QueryResult result = jcrQuery.execute();

            log.debug("size of the results query {} ", result.getNodes().getSize());

            NodeIterator nodes = result.getNodes();

            while (nodes.hasNext()) {
                Node node = nodes.nextNode();
                if (node != null) {
                    allResources.add(node.getPath());
                }
            }

            log.debug("size of the list {} ", allResources.size());

            resp.setContentType("application/json");
            resp.getWriter().write(allResources.toString());

        } catch (RepositoryException | IOException exception) {

            log.error("Error finding data roots", exception);
        }


    }
}
