<#include "../../include/imports.ftl">

<#-- @ftlvariable name="document" type="com.asc.lab.minicms.beans.Blogpost" -->
<@hst.setBundle basename="essentials.blog"/>
<#if document??>
  <div class="has-edit-button">
    <@hst.manageContent hippobean=document/>
    <h1>${document.title?html}</h1>
    <h2>by: ${document.author?html}</h2>
    <strong>
      <#if document.publicationDate??>
        <@fmt.formatDate type="date" pattern="yyyy-MM-dd" value=document.publicationDate.time/>
      </#if>
    </strong>
    <p>${document.introduction?html}</p>
    <div>
      <@hst.html hippohtml=document.content />
    </div>
    <@hst.link var="link" siteMapItemRefId="blog-list"/>
    <a href="${link}"><@fmt.message key="blog.back.to.list" var="msg"/>${msg?html}</a>
  </div>
</#if>