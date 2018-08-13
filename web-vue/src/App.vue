<template>
    <div id="app">
        <div id="nav">
            <router-link to="/">Home</router-link>
            |
            <router-link to="/products">Products</router-link>
            |
            <router-link to="/policies">Policies</router-link>
            |
            <router-link to="/about">About</router-link>
        </div>

        <hr/>

        <div id="main-container">
            <router-view/>
        </div>

        <footer>
            <img src="./assets/logo_horizontal.png" alt="Logo Footer"/>
            <p><a href="https://asc.altkom.pl">Altkom Software & Consulting LAB @ 2018</a></p>
        </footer>
    </div>
</template>

<style>
    #app {
        font-family: 'Avenir', Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        color: #2c3e50;
    }

    #nav {
        padding: 10px 10px 0 10px;
    }

    #nav a {
        font-weight: bold;
        color: #2c3e50;
    }

    #nav a.router-link-exact-active {
        color: #42b983;
    }

    #main-container {
        width: 80%;
        margin: 0 auto;
    }

    footer {
        text-align: center;
        border-top: 1px gray solid;
        margin-left: 10em;
        margin-right: 10em;
        margin-top: 30px;
    }

    footer > img {
        height: 60px;
    }

    footer > hr {
        width: 90%;
    }

    footer > p > a {
        color: black;
    }

</style>
