package com.asc.lab.minicms.beans;

import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.hippoecm.hst.content.beans.Node;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.NONE)
@HippoEssentialsGenerated(internalName = "minicms:product")
@Node(jcrType = "minicms:product")
public class Product extends BaseDocument {
    @XmlElement
    @HippoEssentialsGenerated(internalName = "minicms:name")
    public String getName() {
        return getProperty("minicms:name");
    }

    @XmlElement
    @HippoEssentialsGenerated(internalName = "minicms:description")
    public String getDescription() {
        return getProperty("minicms:description");
    }
}
