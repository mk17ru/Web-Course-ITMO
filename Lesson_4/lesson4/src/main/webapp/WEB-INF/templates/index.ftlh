<#import "./common.ftlh" as c>

<@c.page>
    <#list posts as p>
        <@c.table p false/>
    </#list>

</@c.page>

