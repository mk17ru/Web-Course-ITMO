<#import "./common.ftlh" as c>

<@c.page>
    <#list posts as p>
        <#attempt>
            <#if p.user_id == user_id>
                <@c.table p false/>
            </#if>
        <#recover>
            Wrong link
            <#break>
        </#attempt>
    </#list>
</@c.page>