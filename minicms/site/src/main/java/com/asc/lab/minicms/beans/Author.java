package com.asc.lab.minicms.beans;

import java.util.List;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoDocument;
import org.onehippo.cms7.essentials.components.model.AuthorEntry;
import org.hippoecm.hst.content.beans.standard.HippoGalleryImage;
import org.hippoecm.hst.content.beans.standard.HippoHtml;
import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.hippoecm.hst.content.beans.standard.HippoGalleryImageSet;

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
        return getProperty(FULL_NAME);
    }

    @HippoEssentialsGenerated(internalName = "minicms:content")
    public HippoHtml getContent() {
        return getHippoHtml(CONTENT);
    }

    @HippoEssentialsGenerated(internalName = "minicms:role")
    public String getRole() {
        return getProperty(ROLE);
    }

    @HippoEssentialsGenerated(internalName = "minicms:accounts")
    public List<Account> getAccounts() {
        return getChildBeansByName(ACCOUNTS, Account.class);
    }

    @HippoEssentialsGenerated(internalName = "minicms:photo")
    public HippoGalleryImageSet getPhoto() {
        return getLinkedBean("minicms:photo", HippoGalleryImageSet.class);
    }
}
