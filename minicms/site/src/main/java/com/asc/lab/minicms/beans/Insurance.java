package com.asc.lab.minicms.beans;

import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoHtml;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.onehippo.cms7.essentials.components.rest.adapters.HippoHtmlAdapter;

@XmlRootElement(name = "insurance")
@XmlAccessorType(XmlAccessType.NONE)
@HippoEssentialsGenerated(internalName = "minicms:insurance")
@Node(jcrType = "minicms:insurance")
public class Insurance extends BaseDocument {
    @XmlElement
    @HippoEssentialsGenerated(internalName = "minicms:productName")
    public String getProductName() {
        return getProperty("minicms:productName");
    }

    @XmlElement
    @HippoEssentialsGenerated(internalName = "minicms:html")
    public String getHtml() {
        return getProperty("minicms:html");
    }

    @XmlJavaTypeAdapter(HippoHtmlAdapter.class)
    @XmlElement
    @HippoEssentialsGenerated(internalName = "minicms:moreDesc")
    public HippoHtml getMoreDesc() {
        return getHippoHtml("minicms:moreDesc");
    }
}
