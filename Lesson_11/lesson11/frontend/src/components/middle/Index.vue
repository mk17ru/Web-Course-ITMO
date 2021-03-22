<template>
    <Posts :posts="posts"/>
</template>

<script>
    import Posts from "@/components/middle/posts/Posts";

    export default {
    name: "Index",
        components: {
            Posts
        },
        props: ["posts"]
    }
</script>

<style scoped>

</style>