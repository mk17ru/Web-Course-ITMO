<#import "commons.ftlh" as c>
<#import "/spring.ftl" as spring/>

<@c.page>
        <@c.post post=post/>
    <#if user??>
        <div class="comment-form" style="margin-bottom: 1rem">
            <div class="header">Add comment</div>
            <form method="post" action="">
                <div class="field">
                    <div class="value">
                        <textarea rows="5" cols="40" id="text" name="text">${comment.text!}</textarea>
                    </div>
                    <@c.error "comment.text"/>
                </div>
                <div class="button-field">
                    <input type="submit" value="Add">
                </div>
            </form>
        </div>
    </#if>
    <div style="margin-top: 1rem">
        <#list comments![]?reverse as comment>
            <div>${comment.user.login}: ${comment.text}</div>
        </#list>
    </div>
</@c.page>