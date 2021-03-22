<template>
    <Posts :posts="posts" :users="users" :number_of_comments="number_of_comments"/>
</template>

<script>
    import Posts from "@/components/middle/posts/Posts";

    export default {
        name: "Index",
        components: {
            Posts
        },
        props: ["posts", "users", "number_of_comments"]
    }
</script>

<style scoped>

</style>