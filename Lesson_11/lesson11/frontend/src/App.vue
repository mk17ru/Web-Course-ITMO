<template>
    <div id="app">
        <Header :user="user"/>
        <Middle :posts="posts"/>
        <Footer/>
    </div>
</template>

<script>
import Header from "./components/Header";
import Middle from "./components/Middle";
import Footer from "./components/Footer";
import axios from "axios"

export default {
    name: 'App',
    components: {
        Footer,
        Middle,
        Header
    },
    data: function () {
        return {
            user: null,
            posts: []
        }
    },
    beforeMount() {
        if (localStorage.getItem("jwt") && !this.user) {
            this.$root.$emit("onJwt", localStorage.getItem("jwt"));
        }

        axios.get("/api/1/posts").then(response => {
            this.posts = response.data;
        });

    },
    beforeCreate() {
        this.$root.$on("onEnter", (login, password) => {
            if (password === "") {
                this.$root.$emit("onEnterValidationError", "Password is required");
                return;
            }
            axios.post("/api/1/jwt", {
                    login, password
            }).then(response => {
                localStorage.setItem("jwt", response.data);
                this.$root.$emit("onJwt", response.data);
            }).catch(error => {
                this.$root.$emit("onEnterValidationError", error.response.data);
            });
        });

        this.$root.$on("onJwt", (jwt) => {
            localStorage.setItem("jwt", jwt);

            axios.get("/api/1/users/auth", {
                params: {
                    jwt
                }
            }).then(response => {
                this.user = response.data;
                this.$root.$emit("onChangePage", "Index");
            }).catch(() => this.$root.$emit("onLogout"))
        });

        this.$root.$on("onLogout", () => {
            localStorage.removeItem("jwt");
            this.user = null;
        });

        this.$root.$on("onRegister", (login, name, password) => {
            if (password === "") {
                this.$root.$emit("onRegisterValidationError", "Password is required");
                return;
            }
            axios.post("/api/1/users/register", {login, name, password}).then(() => {
                this.$root.$emit("onEnter", login, password);
                this.$root.$emit("onChangePage", "Index");
            }).catch(error => {
                this.$root.$emit("onRegisterValidationError", error.response.data);
            });
        });

        this.$root.$on("onWritePost", (title, text) => {
            if (text === "") {
                this.$root.$emit("onPostValidationError", "Text is required");
                return;
            }
            // eslint-disable-next-line no-undef
            axios.post("/api/1/posts", {title, text, this.user.login}).then(() => {
                this.$root.$emit("onChangePage", "Index");
                axios.get("/api/1/posts").then(response => {
                    this.posts = response.data;
                });
            }).catch(error => {
                this.$root.$emit("onPostValidationError", error.response.data);
            });
        });

    }
}
</script>

<style>
#app {

}
</style>
