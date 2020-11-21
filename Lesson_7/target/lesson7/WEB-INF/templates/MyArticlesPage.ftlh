<#-- @ftlvariable name="error" type="java.lang.String" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="article datatable">
        <div class="caption">User</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Title</th>
                <th>Hide/Show</th>

            </tr>
            </thead>
            <tbody>
                <#list myArticles as article>
                    <tr>
                        <td class="article_id">${article.id}</td>
                        <td class="article_title">${article.title}</td>
                        <td>
                            <#assign x = "HIDE">
                            <#if article.hidden == "HIDE">
                                <#assign x = "SHOW">
                            </#if>
                            <input type="button" name="${article.id}" class="article_hidden" value="${x}"/>
                        </td>
                    </tr>
                </#list>
            </tbody>
        </table>
        <div class="error">
            ${error!}
        </div>
    </div>
    <script>
        $(function () {
            const $error =  $(".article").find(".error");
            $(".article .article_hidden").click(function() {
                var $cur = $(this);
                function success(response) {
                    if (response["error"]) {
                        $error.text(response["error"]);
                    } else {
                        if ($cur.val() === "SHOW") {
                            $cur.val("HIDE");
                        } else {
                            $cur.val("SHOW");
                        }
                    }
                    notify("Success");
                }
                const data = {
                    "action": "swapVision",
                    "articleId": $(this).attr("name"),
                    "status" : $cur.val()
                };
                commonAjax(data, success);
            });
        });
    </script>
</@c.page>
