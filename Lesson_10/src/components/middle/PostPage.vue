<template>
    <article>
        <a href="#" style="text-decoration: none">
            <div class="title">{{post.title}}</div>
        </a>
        <div class="information">By {{users[post.userId].login}}</div>
        <div class="body">{{post.text}}</div>
        <ul class="attachment">
            <li>Announcement of <a href="#">Codeforces Round #510 (Div. 1)</a></li>
            <li>Announcement of <a href="#">Codeforces Round #510 (Div. 2)</a></li>
        </ul>
        <div class="footer">
            <div class="left">
                <img src="../../assets/img/voteup.png" title="Vote Up" alt="Vote Up"/>
                <span class="positive-score">+173</span>
                <img src="../../assets/img/votedown.png" title="Vote Down" alt="Vote Down"/>
            </div>
            <div class="right">
                <img src="../../assets/img/date_16x16.png" title="Publish Time" alt="Publish Time"/>
                <img src="../../assets/img/comments_16x16.png" title="Comments" alt="Comments"/>
                <a href="#">{{Object.keys(comments).length}}</a>
            </div>
        </div>
        <OneComment v-for="comment in comments" :comment="comment" :key="comment.id"/>
    </article>
</template>

<script>
    import OneComment from "./OneComment";
    export default {
        name: "OnePost",
        components: {OneComment},
        props: ["post", "users", "comments"]
    }
</script>

<style scoped>

</style>