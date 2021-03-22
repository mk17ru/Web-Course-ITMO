<#import "commons.ftlh" as c>

<@c.page>
    <#if user??>
        <div class="notice form-box" style="float: left; width:70%">
            <div class="header">Notice</div>
            <div class="body" >
                <form method="post" action="">
                <p>
                    <textarea name="content" rows="10" style="width: 95%">${noticeForm.content!}</textarea>
                </p>
                <@c.error "noticeForm.content"/>
                    <div class="button-field">
                        <input type="submit" value="Add">
                    </div>
                </form>
            </div>
        </div>
    </#if>
</@c.page>
