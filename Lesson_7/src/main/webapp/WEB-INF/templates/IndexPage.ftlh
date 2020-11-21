<#import "commons.ftlh" as c>

<@c.page>
    <div class="art">
        <template>
        <article class="article">
                <div class="title">Codeforces Round #510 (Div. 2)</div>
                <div class="information"></div>
                <div class="body-t">
                </div>
                <ul class="attachment">
                    <li>Announcement of <a href="#">Codeforces Round #510 (Div. 1)</a></li>
                    <li>Announcement of <a href="#">Codeforces Round #510 (Div. 2)</a></li>
                </ul>
                <div class="footer">
                    <div class="left">
                        <img src="img/voteup.png" title="Vote Up" alt="Vote Up"/>
                        <span class="positive-score">+173</span>
                        <img src="img/votedown.png" title="Vote Down" alt="Vote Down"/>
                    </div>
                    <div class="right">
                        <img src="img/date_16x16.png" title="Publish Time" alt="Publish Time"/>
                        2 days ago
                        <img src="img/comments_16x16.png" title="Comments" alt="Comments"/>
                        <a href="#">68</a>
                    </div>
                </div>
        </article>
        </template>
        <div class="noData">
            <p>No data</p>
        </div>
    </div>
<script>
    $(function () {
        const $noData = $(".art .noData");
        $noData.find("p").text("Loading...");
        const data = {"action": "findAll"};

        function callBack(response) {
            $noData.hide();
            response["articles"].forEach(function (article) {
                const $cur = $($(".art template").prop("content")).clone();
                $cur.find(".title").text(article["title"]);
                $cur.find(".body-t").text(article["text"]);
                $(".art").append($cur);
            });
            if (Object.keys(response["articles"]).length === 0) {
                $noData.show();
                $noData.find("p").text("No data!");
            }
        }
        commonAjax(data, callBack);
    })
</script>
</@c.page>
