<template>
    <div class="users datatable">
        <div class="caption">Users</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Login</th>
                <th>Name</th>
            </tr>
            </thead>
            <tbody>
                <OneUser v-for="user in users" :user="user" :key="user.id"/>
            </tbody>
        </table>
    </div>
</template>

<script>
    import OneUser from "./OneUser";
    import axios from "axios";
    export default {
        name: "Users",
        components: {OneUser},
        data: function () {
            return {
                users: []
            };
        }, beforeMount() {
            axios.get("/api/1/users").then(response => {
                this.users = response.data;
            });
        }
    }
</script>

<style scoped>

</style>