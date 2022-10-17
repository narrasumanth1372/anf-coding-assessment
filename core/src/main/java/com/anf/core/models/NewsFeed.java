package com.anf.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Sumanth
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class NewsFeed {

    private static final String DATE_FORMAT = "MM.dd.yyyy";

    @Inject
    private String title;
    @Inject
    private String author;

    private String currentDate;

    @Inject
    private String description;

    @Inject
    private String content;

    @Inject
    private String url;

    @Inject
    private String urlImage;

    @PostConstruct
    public void init() {
        this.currentDate = new SimpleDateFormat(DATE_FORMAT).format(new Date());
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public String getDescription() {
        return description;
    }

    public String getContent() {
        return content;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlImage() {
        return urlImage;
    }
}
