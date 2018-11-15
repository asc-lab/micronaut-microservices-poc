<#include "../../include/imports.ftl">

<#-- @ftlvariable name="facets" type="org.hippoecm.hst.content.beans.standard.HippoFacetNavigationBean" -->
<#-- @ftlvariable name="facetLimit" type="java.lang.Integer" -->
<#-- @ftlvariable name="query" type="java.lang.String" -->
<@hst.setBundle basename="essentials.facets"/>
<div>
  <form action="<@hst.link />" method="get">
    <div class="row form-group">
      <div class="col-xs-8">
      <@fmt.message key='facets.placeholder' var="placeholder"/>
      <#if query??>
        <input type="search" value="${query?html}" name="query" class="form-control" placeholder="${placeholder?html}">
      <#else>
        <input type="search" value="" name="query" class="form-control" placeholder="${placeholder?html}">
      </#if>
      </div>
      <div class="col-xs-4">
        <button type="submit" class="btn btn-primary pull-right"><@fmt.message key='facets.searchbutton' var="button"/>${button?html}</button>
      </div>
    </div>
  </form>
  <#if facets??>
    <#assign facetLimit = 50>

    <ul class="nav">
      <#list facets.folders as facetvalue>
        <#if facetvalue.folders?? && (facetvalue.folders?size > 0)>
          <li>
            <label class="nav-header">
              ${facetvalue.name?html}
              <#if facetvalue.name == "Authors"><@hst.manageContent templateQuery="new-blogauthor-document" rootPath="blog/authors"/></#if>
            </label>
            <ul class="nav">
              <#list facetvalue.folders as item>
                <#if (item.leaf?? && item.leaf && (item.count > 0))>
                  <@hst.facetnavigationlink  current=facets remove=item var="removeLink"/>
                  <li class="open">
                    <a href="${removeLink}">${item.name?html}&nbsp;&nbsp;
                      <@fmt.message key='facets.remove' var="remove"/>
                      <span class="alert-danger" title="${remove?html}"><strong>&nbsp;X&nbsp;</strong></span>
                    </a>
                  </li>
                <#else>
                  <@hst.link var="link" hippobean=item navigationStateful=true/>
                  <li <#if (item_index >= facetLimit)>class="extra"</#if>>
                    <a href="${link}">${item.name?html}&nbsp;<span>(${item.count})</span></a>
                  </li>
                </#if>
              </#list>
            </ul>
          </li>
        </#if>
      </#list>
    </ul>
  <#-- @ftlvariable name="editMode" type="java.lang.Boolean"-->
  <#elseif editMode>
    <img src="<@hst.link path='/images/essentials/catalog-component-icons/facets.png'/>"> Click to edit Facets
  </#if>
</div>