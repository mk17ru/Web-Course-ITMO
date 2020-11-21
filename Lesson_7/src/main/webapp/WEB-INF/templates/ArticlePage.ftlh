<#-- @ftlvariable name="error" type="java.lang.String" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="article form-box" style="float: left; width:70%">
        <div class="header">Article</div>
        <div class="body" >
            <form method="post" action="">
                <input type="hidden"/>
                <div class="field" style="">
                    <div class="name">
                        <label for="title">Title</label>
                    </div>
                    <div class="value">
                        <input id="title" name="title" value="${title!}"/>
                    </div>
                </div>
                <p>
                    <textarea rows="10" id="typing" name="typing" style="width: 95%"></textarea>
                </p>
                <div class="error">
                    ${error!}
                </div>
                <div class="button-field">
                    <input type="submit" value="Create">
                </div>
            </form>
        </div>
    </div>
<script>
    $(function () {
        $(".body form").submit(function () {
            const title = $(this).find("input[name='title']").val();
            const text = $(this).find("textarea[name='typing']").val();
            const $error = $(this).find(".error");
            function errorFunc(response) {
                if (response["error"]) {
                    $error.text(response["error"]);
                }
            }
            const data = {action: "add", title: title, text: text};
            commonAjax(data, errorFunc);
            return false;
        });
    })
</script>
</@c.page>
