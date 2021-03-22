<template>
    <div>
        <OnePost v-for="post in posts" :post="post" :users="users"
             :number_of_comments="number_of_comments.has(post.id) ? number_of_comments.get(post.id) : 0" :key="post.id"/>
    </div>
</template>

<script>
    import OnePost from "./OnePost";
    export default {
        name: "Posts",
        components: {OnePost},
        props: ["posts", "users", "number_of_comments"]
    }
</script>

<style scoped>

</style>