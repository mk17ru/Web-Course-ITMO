<template>
    <header>
        <a href="#" @click.prevent="changePage('Index')">
            <img src="../assets/img/logo.png" alt="Codeforces" title="Codeforces"/>
        </a>
        <div class="languages">
            <a href="#"><img src="../assets/img/gb.png" alt="In English" title="In English"/></a>
            <a href="#"><img src="../assets/img/ru.png" alt="In Russian" title="In Russian"/></a>
        </div>
        <p><label for="sldr">Is codeforces cool?</label></p>
        <input type="range" min="10" max="100" value="10" id="sldr" @click.prevent="doThing()">
        <div class="enter-or-register-box">
            <template v-if="userId">
                Name: {{ users[userId].name }}
                Login: {{ users[userId].login }}
                |
                <a href="#" @click.prevent="onLogout">Logout</a>
            </template>
            <template v-else>
                <a href="#" @click.prevent="changePage('Enter')">Enter</a>
                |
                <a href="#" @click.prevent="changePage('Register')">Register</a>
            </template>
        </div>
        <nav>
            <ul>
                <li><a href="#" @click.prevent="changePage('Index')">Home</a></li>
                <li><a href="#" @click.prevent="changePage('Users')">Users</a></li>
                <li v-if="userId"><a href="#" @click.prevent="changePage('WritePost')">Write Post</a></li>
                <li v-if="userId"><a href="#" @click.prevent="changePage('EditPost')">Edit Post</a></li>
            </ul>
        </nav>
    </header>

</template>

<script>
export default {
    name: "Header",
    props: ["userId", "users"],
    methods: {
        changePage: function (page) {
            this.$root.$emit("onChangePage", page);
        },
        onLogout: function () {
            this.$root.$emit("onLogout");
            this.changePage("Index");
        },
        doThing: function () {
            var sliderinput = document.getElementById("sldr");
            var size = sliderinput.value    ;
            alert(size);
        }
    }
}
</script>

<style scoped>

</style>