<template>
    <div>
        <OnePost v-for="post in posts" :post="post" :key="post.id"/>
    </div>
</template>

<script>
    import OnePost from "./OnePost";
    export default {
        name: "Posts",
        components: {OnePost},
        props: ["posts", "users"]
    }
</script>

<style scoped>

</style>