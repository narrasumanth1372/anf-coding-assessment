package com.anf.core.listeners;

import java.util.Iterator;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anf.core.services.ContentService;
import com.day.cq.wcm.api.PageEvent;
import com.day.cq.wcm.api.PageModification;
import com.day.cq.wcm.api.PageModification.ModificationType;

/**
 * @author Sumanth
 */
@Component(service = EventHandler.class, immediate = true, configurationPolicy = ConfigurationPolicy.OPTIONAL, property = {
        EventConstants.EVENT_TOPIC + "=" + PageEvent.EVENT_TOPIC})
public class PageCreateListener implements EventHandler {

    public static final String PATH = "/content/anf-code-challenge/us/en";
    public static final String PROPERTY = "pageCreated";

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Reference
    ContentService contentService;


    /**
     * handle the event when page is created under the path
     */
    public void handleEvent(final Event event) {

        Iterator<PageModification> pageIter = PageEvent.fromEvent(event).getModifications();

        while (pageIter.hasNext()) {

            PageModification modification = pageIter.next();

            logger.debug("event Type: {}, event Page Path: {}", modification.getType(), modification.getPath());

            if (modification.getType().equals(ModificationType.CREATED) && modification.getPath().startsWith(PATH)) {
                Session session = contentService.getResolver().adaptTo(Session.class);
                try {

                    Node contentNode = session.getNode(modification.getPath() + "/jcr:content");
                    contentNode.setProperty(PROPERTY, Boolean.TRUE);
                    session.save();

                    logger.debug("updated the page property after page creation");

                } catch (RepositoryException e) {
                    logger.error("Error processing page creation event", e);
                } finally {

                    if (session.isLive()) {
                        session.logout();
                    }
                }

            }
        }

    }
}
