package com.asc.lab.minicms.beans;
/*
 * Copyright 2014 Hippo B.V. (http://www.onehippo.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import java.util.Calendar;
import java.util.List;

import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoDocument;
import org.hippoecm.hst.content.beans.standard.HippoHtml;
import org.onehippo.cms7.essentials.components.model.Authors;
import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;

@HippoEssentialsGenerated(internalName = "minicms:blogpost")
@Node(jcrType = "minicms:blogpost")
public class Blogpost extends HippoDocument implements Authors {

    public static final String TITLE = "minicms:title";
    public static final String INTRODUCTION = "minicms:introduction";
    public static final String CONTENT = "minicms:content";
    public static final String PUBLICATION_DATE = "minicms:publicationdate";
    public static final String CATEGORIES = "minicms:categories";
    public static final String AUTHOR = "minicms:author";
    public static final String AUTHOR_NAMES = "minicms:authornames";
    public static final String LINK = "minicms:link";
    public static final String AUTHORS = "minicms:authors";
    public static final String TAGS = "hippostd:tags";


   @HippoEssentialsGenerated(internalName = "minicms:publicationdate")
    public Calendar getPublicationDate() {
        return getProperty(PUBLICATION_DATE);
    }

    @HippoEssentialsGenerated(internalName = "minicms:authornames")
    public String[] getAuthorNames() {
        return getProperty(AUTHOR_NAMES);
    }

    public String getAuthor() {
        final String[] authorNames = getAuthorNames();
        if(authorNames !=null && authorNames.length > 0){
            return authorNames[0];
        }
        return null;
    }


    @HippoEssentialsGenerated(internalName = "minicms:title")
    public String getTitle() {
        return getProperty(TITLE);
    }

    @HippoEssentialsGenerated(internalName = "minicms:content")
    public HippoHtml getContent() {
        return getHippoHtml(CONTENT);
    }

    @HippoEssentialsGenerated(internalName = "minicms:introduction")
    public String getIntroduction() {
        return getProperty(INTRODUCTION);
    }

    @HippoEssentialsGenerated(internalName = "minicms:categories")
    public String[] getCategories() {
        return getProperty(CATEGORIES);
    }

    @Override
    @HippoEssentialsGenerated(internalName = "minicms:authors")
    public List<Author> getAuthors() {
        return getLinkedBeans(AUTHORS, Author.class);
    }
}