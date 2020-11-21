<#import "./common.ftlh" as c>

<@c.page>
     <#if post_id??>
        <#attempt>
            <#assign p = c.findBy(posts, "id", post_id)!/>
            <#if p?has_content>
                <@c.table p true/>
            <#else>
                No such user
            </#if>
        <#recover>
            No such post
        </#attempt>
     <#elseif user_id??>
         <#attempt>
             <#assign p = c.findBy(posts, "user_id", user_id)!/>
             <#if p?has_content>
                 <@c.table p true/>
             <#else>
                 No such user
             </#if>
             <#recover>
                 No such post
         </#attempt>
     <#else>
         No such post
     </#if>
</@c.page>