package com.asc.lab.minicms.beans;

import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoGalleryImageSet;
import org.hippoecm.hst.content.beans.standard.HippoHtml;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.onehippo.cms7.essentials.components.rest.adapters.HippoHtmlAdapter;
import org.onehippo.cms7.essentials.components.rest.adapters.HippoGalleryImageAdapter;

@XmlRootElement(name = "productheader")
@XmlAccessorType(XmlAccessType.NONE)
@HippoEssentialsGenerated(internalName = "minicms:productHeader")
@Node(jcrType = "minicms:productHeader")
public class ProductHeader extends BaseDocument {
    @XmlElement
    @HippoEssentialsGenerated(internalName = "minicms:title")
    public String getTitle() {
        return getProperty("minicms:title");
    }

    @XmlElement
    @HippoEssentialsGenerated(internalName = "minicms:code")
    public String getCode() {
        return getProperty("minicms:code");
    }

    @XmlJavaTypeAdapter(HippoGalleryImageAdapter.class)
    @XmlElement
    @HippoEssentialsGenerated(internalName = "minicms:mainPicture")
    public HippoGalleryImageSet getMainPicture() {
        return getLinkedBean("minicms:mainPicture", HippoGalleryImageSet.class);
    }

    @XmlJavaTypeAdapter(HippoHtmlAdapter.class)
    @XmlElement
    @HippoEssentialsGenerated(internalName = "minicms:shortDescription")
    public HippoHtml getShortDescription() {
        return getHippoHtml("minicms:shortDescription");
    }
}
