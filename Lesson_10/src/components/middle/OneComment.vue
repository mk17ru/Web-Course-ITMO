<template>
    <p>{{comment.id}} : {{comment.text}}</p>
</template>

<script>
    export default {
        name: "Comments",
        props: ["comment"]
    }
</script>

<style scoped>

</style>