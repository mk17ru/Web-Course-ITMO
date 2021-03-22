<template>
    <div class="middle">
        <Sidebar :posts="viewPosts"/>
        <main>
            <Enter v-if="page === 'Enter'"/>
            <Register v-if="page === 'Register'"/>
            <Users v-if="page === 'Users'"/>
            <WritePost v-if="page === 'WritePost'"/>
            <Index v-if="page === 'Index'" :posts="sortPosts"/>
        </main>
    </div>
</template>

<script>
import Sidebar from "@/components/sidebar/Sidebar";
import Index from "@/components/middle/Index";
import Enter from "@/components/middle/Enter";
import Users from "@/components/middle/users/Users";
import Register from "@/components/middle/Register";
import WritePost from "@/components/middle/WritePost";


export default {
    name: "Middle",
    data: function () {
        return {
            page: "Index"
        }
    },
    components: {
        Register,
        Enter,
        Index,
        Sidebar,
        Users,
        WritePost
    },
    props: ["posts"],
    computed: {
        viewPosts: function () {
            return Object.values(this.posts).sort((a, b) => b.id - a.id).slice(0, 2);
        }, sortPosts: function () {
            return Object.values(this.posts).sort((a, b) => a.id - b.id);
        }
    }, beforeCreate() {
        this.$root.$on("onChangePage", (page) => this.page = page);

    }
}
</script>

<style scoped>

</style>