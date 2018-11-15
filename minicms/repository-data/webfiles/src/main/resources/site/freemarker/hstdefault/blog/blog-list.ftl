<#include "../../include/imports.ftl">

<#-- @ftlvariable name="pageable" type="org.onehippo.cms7.essentials.components.paging.Pageable" -->
<@hst.setBundle basename="essentials.blog"/>
<#if pageable??>
<div>
  <#list pageable.items as item>
    <@hst.link var="link" hippobean=item />
    <article class="has-edit-button">
      <@hst.manageContent hippobean=item/>
      <h3><a href="${link}">${item.title?html}</a></h3>
      <#if item.publicationDate?? && item.publicationDate.time??>
        <p><@fmt.formatDate value=item.publicationDate.time type="both" dateStyle="medium" timeStyle="short"/></p>
      </#if>
      <p>${item.introduction?html}</p>
      <p><a href="${link}"><@fmt.message key="blog.read.post" var="msg"/>${msg?html}</a></p>
    </article>
  </#list>
  <div class="has-new-content-button">
    <@hst.manageContent templateQuery="new-blog-document" rootPath="blog" defaultPath="${currentYear}/${currentMonth}"/>
  </div>
  <#if cparam.showPagination>
    <#include "../../include/pagination.ftl">
  </#if>
</div>
<#-- @ftlvariable name="editMode" type="java.lang.Boolean"-->
<#elseif editMode>
<div>
  <img src="<@hst.link path='/images/essentials/catalog-component-icons/blog-list.png'/>"> Click to edit Blog List
</div>
</#if>