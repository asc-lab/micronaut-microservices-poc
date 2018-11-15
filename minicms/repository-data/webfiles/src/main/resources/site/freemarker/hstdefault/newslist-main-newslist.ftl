<#include "../include/imports.ftl">

<#-- @ftlvariable name="item" type="com.asc.lab.minicms.beans.NewsDocument" -->
<#-- @ftlvariable name="pageable" type="org.onehippo.cms7.essentials.components.paging.Pageable" -->
<#if pageable?? && pageable.items?has_content>
<div>
  <#list pageable.items as item>
    <@hst.link var="link" hippobean=item />
    <article class="has-edit-button">
      <@hst.manageContent hippobean=item/>
      <h3><a href="${link}">${item.title?html}</a></h3>
      <#if item.date?? && item.date.time??>
        <p><@fmt.formatDate value=item.date.time type="both" dateStyle="medium" timeStyle="short"/></p>
      </#if>
      <p>${item.location?html}</p>
      <p>${item.introduction?html}</p>
    </article>
  </#list>
  <div class="has-new-content-button">
    <@hst.manageContent templateQuery="new-news-document" rootPath="news" defaultPath="${currentYear}/${currentMonth}"/>
  </div>
  <#if cparam.showPagination>
    <#include "../include/pagination.ftl">
  </#if>
</div>
<#-- @ftlvariable name="editMode" type="java.lang.Boolean"-->
<#elseif editMode>
<div>
  <img src="<@hst.link path='/images/essentials/catalog-component-icons/news-list.png'/>"> Click to edit News List
  <div class="has-new-content-button">
    <@hst.manageContent templateQuery="new-news-document" rootPath="news" defaultPath="${currentYear}/${currentMonth}"/>
  </div>
</div>
</#if>