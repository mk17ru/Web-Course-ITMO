<#-- @ftlvariable name="users" type="ru.itmo.wp.model.domain.User[]" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="form-box" style="margin-bottom: 3rem; margin-left: 0rem; width: 100%">
        <div class="header">New message</div>
        <div class="body">
            <form method="post" action="">
                <input type="hidden" name="action" value="register"/>
                <SELECT id="user_option" name="user_option" size=1 style="margin: 0.3rem">
                    <OPTION selected value="No user">Выберите получателя:</OPTION>
                    <#list users as user>
                    <OPTION value=${user.id}> ${user.login}</OPTION>
                    </#list>
                </SELECT>
                <p>
                    <textarea rows="10" id="typing" name="typing" style="width: 90%"></textarea>
                </p>
                <#if error??>
                    <div class="error">
                        ${error!}
                    </div>
                </#if>
                <div class="button-field">
                    <input type="submit" value="Send">
                </div>
            </form>
        </div>
    </div>
    <div class="datatable">
        <div class="caption">User</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>From</th>
                <th>To</th>
                <th>Text</th>
            </tr>
            </thead>
                <tbody>
                <#list talks as talk>
                    <tr>
                        <td>${talk.id}</td>
                        <td>${talk.sourceUserLogin}</td>
                        <td>${talk.targetUserLogin}</td>
                        <td>${talk.text}</td>
                        <td>${talk.creationTime}</td>
                    </tr>
                </#list>
            </tbody>
        </table>
    </div>
</@c.page>
