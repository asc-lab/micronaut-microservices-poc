<#-- @ftlvariable name="pageable" type="org.onehippo.cms7.essentials.components.paging.Pageable" -->
<#include "../include/imports.ftl">
<#if pageable??>
<@hst.setBundle basename="essentials.pagination"/>
<ul class="pagination">
    <li class="disabled">
        <a href="#">${pageable.total}&nbsp;<@fmt.message key="results.indication" var="indication"/>${indication?html}</a>
    </li>
  <#if pageable.totalPages gt 1>
    <#list pageable.pageNumbersArray as pageNr>
        <@hst.renderURL var="pageUrl">
            <@hst.param name="page" value="${pageNr}"/>
            <@hst.param name="pageSize" value="${pageable.pageSize}"/>
        </@hst.renderURL>
        <#if (pageNr_index==0 && pageable.previous)>
            <@hst.renderURL var="pageUrlPrevious">
                <@hst.param name="page" value="${pageable.previousPage}"/>
                <@hst.param name="pageSize" value="${pageable.pageSize}"/>
            </@hst.renderURL>
            <li><a href="${pageUrlPrevious}"><@fmt.message key="page.previous" var="prev"/>${prev?html}</a></li>
        </#if>
        <#if pageable.currentPage == pageNr>
            <li class="active"><a href="#">${pageNr}</a></li>
        <#else >
            <li><a href="${pageUrl}">${pageNr}</a></li>
        </#if>

        <#if !pageNr_has_next && pageable.next>
            <@hst.renderURL var="pageUrlNext">
                <@hst.param name="page" value="${pageable.nextPage}"/>
                <@hst.param name="pageSize" value="${pageable.pageSize}"/>
            </@hst.renderURL>
            <li><a href="${pageUrlNext}"><@fmt.message key="page.next" var="next"/>${next?html}</a></li>
        </#if>
    </#list>
  </#if>
</ul>
</#if>