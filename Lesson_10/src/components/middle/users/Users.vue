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
    export default {
        name: "Users",
        components: {OneUser},
        props: ["users"]
    }
</script>

<style scoped>

</style>