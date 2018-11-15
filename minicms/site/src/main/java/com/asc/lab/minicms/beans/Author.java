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

import java.util.List;

import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoDocument;
import org.onehippo.cms7.essentials.components.model.AuthorEntry;
import org.hippoecm.hst.content.beans.standard.HippoGalleryImage;
import org.hippoecm.hst.content.beans.standard.HippoHtml;

import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;

@HippoEssentialsGenerated(internalName = "minicms:author")
@Node(jcrType = "minicms:author")
public class Author extends HippoDocument implements AuthorEntry {

    public static final String ROLE = "minicms:role";
    public static final String ACCOUNTS = "minicms:accounts";
    public static final String FULL_NAME = "minicms:fullname";
    public static final String IMAGE = "minicms:image";
    public static final String CONTENT = "minicms:content";

    @HippoEssentialsGenerated(internalName = "minicms:fullname")
    public String getFullName() {
        return  getProperty(FULL_NAME);
    }

    @HippoEssentialsGenerated(internalName = "minicms:content")
    public HippoHtml getContent() {
        return getHippoHtml(CONTENT);
    }

    @HippoEssentialsGenerated(internalName = "minicms:role")
    public String getRole() {
        return getProperty(ROLE);
    }

    @HippoEssentialsGenerated(internalName = "minicms:image")
    public HippoGalleryImage getImage() {
        return getLinkedBean(IMAGE, HippoGalleryImage.class);
    }

  	@HippoEssentialsGenerated(internalName = "minicms:accounts")
	  public List<Account> getAccounts() {
		    return getChildBeansByName(ACCOUNTS, Account.class);
	  }
}
