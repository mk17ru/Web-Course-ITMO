<#-- @ftlvariable name="handle" type="java.lang.String" -->
<#-- @ftlvariable name="user_id" type="java.lang.String" -->

<#import "./common.ftlh" as c>

<@c.page>
    <#if handle??>
        <#attempt>
            <#assign u = c.findBy(users, "handle", handle)!/>
            <#if u?has_content>
                <@output u/>
            <#else>
                No such user
            </#if>
        <#recover>
            No such user
        </#attempt>
    <#elseif user_id??>
        <#attempt>
            <#assign u = c.findBy(users, "id", user_id)!/>
            <#if u?has_content>
                <@output u/>
            <#else>
                 No such user
            </#if>
        <#recover>
            No such user
        </#attempt>
    <#else>
        No such user
    </#if>
</@c.page>


<#macro output u>
<#if u?has_content>
        <#assign uNext = c.findNext(users, "handle", u.handle)!/>
        <#assign uPrev = c.findPrev(users, "handle", u.handle)!/>
        <div class="userTable">
            <a href="/user?handle=${u.handle}"><@c.userlink u false/></a>
            <div class="user-img">
                <img alt="Users" title="User" src="/img/no-title.jpg" >
            </div>
            <ul>
                    <li >id: ${u.id}</li>
                    <li>handle: <@c.userlink u false/></li>
                    <li>name: ${u.name}</li>
                    <li><a href="/posts?user_id=${u.id}" style="all: initial;"> View posts </a></li>

            </ul>
            <#if uPrev?has_content>
                <a href="/user?handle=${uPrev.handle}" style="text-decoration: none">←</a>
            <#else>
                <a href="/user?handle=${u}" style="text-decoration: none; pointer-events: none; color: gray;">←</a>
            </#if>

            <#if uNext?has_content>
                <a href="/user?handle=${uNext.handle}" style="text-decoration: none">→</a>
            <#else>
                <a href="/user?handle=${u}" style="text-decoration: none; pointer-events: none; color: gray;">→</a>
            </#if>
        </div>
</#if>
</#macro>