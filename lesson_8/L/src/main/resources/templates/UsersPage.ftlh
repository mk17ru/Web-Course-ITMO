<#-- @ftlvariable name="users" type="ru.itmo.wp.domain.User[]" -->
<#-- @ftlvariable name="curUser" type="ru.itmo.wp.domain.User" -->
<#-- @ftlvariable name="user" type="ru.itmo.wp.domain.User" -->


<#import "commons.ftlh" as c>

<@c.page>
    <div class="users datatable">
        <div class="caption">User</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Login</th>
                <th>Created</th>
                <#if user??>
                    <th>Disabled</th>
                </#if>
            </tr>
            </thead>
            <tbody>
            <#if users?size==0>
                <tr class="noData">
                    <td colspan="4">
                        No data
                    </td>
                </tr>
            <#else>
                <#list users as curUser>
                    <tr>
                        <td class="id">${curUser.id}</td>
                        <td class="login">${curUser.login}</td>
                        <td class="creationTime">${curUser.creationTime}</td>
                        <#if user??>
                            <td>
                                <form method="post" name="disableForm">
                                    <input type="hidden" name="userD" value="${user.disabled?string}">
                                    <input type="hidden" name="curId" value="${curUser.id}">
                                    <#if curUser.disabled>
                                        <input type="hidden" id="disabled" name="disabled" value="false">
                                        <input type="submit" value="Enable">
                                    <#else>
                                        <input type="hidden" id="disabled" name="disabled" value="true">
                                        <input type="submit" value="Disable">
                                    </#if>
                                 </form>
                            </td>
                        </#if>
                    </tr>
                </#list>
            </#if>
            </tbody>
        </table>
    </div>
</@c.page>
