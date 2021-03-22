<template>
    <div class="middle">
        <Sidebar :posts="viewPosts"/>
        <main>
            <Index v-if="page === 'Index'" :posts="sortPosts" :users="users" :number_of_comments="numberOfComments"/>
            <Enter v-if="page === 'Enter'"/>
            <Register v-if="page === 'Register'"/>
            <WritePost v-if="page === 'WritePost'"/>
            <EditPost v-if="page === 'EditPost'"/>
            <PostPage v-if="page === 'PostPage'" :post="post" :users="users" :comments="viewComments(post)"/>
            <Users v-if="page === 'Users'" :users="users"/>
        </main>
    </div>
</template>

<script>
import Sidebar from "@/components/sidebar/Sidebar";
import Index from "@/components/middle/Index";
import Enter from "@/components/middle/Enter";
import WritePost from "@/components/middle/WritePost";
import EditPost from "@/components/middle/EditPost";
import Users from "@/components/middle/users/Users";
import PostPage from "@/components/middle/PostPage";


import Register from "./middle/Register";

export default {
    name: "Middle",
    data: function () {
        return {
            page: "Index",
            post: 0
        }
    },
    components: {
        Register,
        WritePost,
        Enter,
        Index,
        Sidebar,
        EditPost,
        Users,
        PostPage
    },
    props: ["posts", "users", "comments"],
    methods: {
        viewComments: function (post) {
            return Object.values(this.comments).filter((comment) => comment.postId === post.id);
        }
    },
    computed: {
        viewPosts: function () {
            return Object.values(this.posts).sort((a, b) => b.id - a.id).slice(0, 2);
        },
        sortPosts: function () {
            return Object.values(this.posts).sort((a, b) => a.id - b.id);
        },
        numberOfComments: function () {
            const number_of_comments = new Map();
            Object.values(this.comments).forEach((comment) => {
                if (number_of_comments.has(comment.postId)) {
                    number_of_comments.set(comment.postId, number_of_comments.get(comment.postId) + 1);
                } else {
                    number_of_comments.set(comment.postId, 1);
                }});
            return number_of_comments;
        }
    }, beforeCreate() {
        this.$root.$on("onChangePage", (page) => this.page = page);
        this.$root.$on("onShowPost", (post) => {
            this.post = post;
            this.page = "PostPage";
        });
    }
}
</script>

<style scoped>

</style>