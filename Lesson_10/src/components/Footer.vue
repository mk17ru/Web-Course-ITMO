<template>
    <footer>
        <p><a href="/">Codeforces</a> &copy; 2020 by Mike Mirzayanov</p>
        <p>Number of posts: {{ number_of_posts }},
            Number of users: {{ number_of_users }}</p>
    </footer>
</template>

<script>
export default {
    name: "Footer",
    props: ["number_of_posts", "number_of_users"]
}
</script>

<style scoped>

</style>