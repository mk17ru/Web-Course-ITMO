<#-- @ftlvariable name="users" type="ru.itmo.wp.model.domain.User[]" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="users datatable">
        <div class="caption">User</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Login</th>
                <#if user?? && user.admin>  <th>Admin</th> </#if>
            </tr>
            </thead>
            <tbody>
            <template>
                <tr>
                    <td class="user_id"></td>
                    <td class="user_login"></td>
                    <#if user?? && user.admin>
                        <td class="user_admin"> <span class="adm"></span>
                          <a href="#" class="admin" name=${user.id}></td>
                    </#if>
                </tr>
            </template>
            <tr class="noData">
                <td colspan="3">
                    No data
                </td>
            </tr>
            </tbody>
        </table>
    </div>

    <script>
        $(function () {
            const $noData = $(".users .noData");
            $noData.find("td").text("Loading...");
            $.ajax({
                dataType: "json",
                data: {
                    action: "findAll"
                },
                success: function(response) {
                    response["users"].forEach(function(user) {
                        $noData.hide();
                        const $tr = $($(".users template").prop("content")).clone();
                        $tr.find(".user_id").text(user["id"]);
                        $tr.find(".user_login").text(user["login"]);
                        $tr.find("tr").attr("data-userId", user["id"]);
                        $tr.find(".user_admin .adm").text(user["admin"] ? "Admin " : "User");
                        $tr.find(".admin").text(user["admin"] ? "Disable" : "Enable");
                        $tr.find(".user_admin .admin").attr("name", user["id"]);
                        $(".users tbody").append($tr);
                    });
                    var $d;
                    $(".user_admin .admin").click(function() {
                        const $error =  $(".article").find(".error");
                        let $cur = $(this);
                        const $state = $(this).closest("tr").find(".user_admin .adm");
                        function clickFunc(response) {
                            if (response["error"]) {
                                $error.text(response["error"]);
                            } else {
                                if ($cur.text() === "Enable") {
                                    $cur.text("Disable");
                                    $state.text("Admin");
                                } else {
                                    $cur.text("Enable");
                                    $state.text("User");
                                }
                                notify("Success");
                            }
                        }
                        const data = {action: "changeAdmin", userId : $(this).attr("name"), status : $cur.text()};
                        commonAjax(data, clickFunc);
                        return false;
                    });
                    // $(".users tr").click(function() {
                    //     $.ajax({
                    //         dataType: "json",
                    //         data: {
                    //             action: "findUser",
                    //             userId: $(this).attr("data-userId")
                    //         },
                    //         success: function (response) {
                    //             notify(response["user"].creationTime);
                    //         },
                    //         type: "POST"
                    //     })
                    // });
                },
                type: "POST"
            });
        })
    </script>
</@c.page>
