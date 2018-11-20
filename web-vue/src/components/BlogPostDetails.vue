<template>

    <div id="blogjumbotron" class="jumbotron container">
        <h1> {{postDetails.title}} </h1>
        <span v-for="c in postDetails.categories" :key="c" class="badge badge-pill badge-primary">{{c}}</span>
        <p class="lead">{{postDetails.introduction}}</p>
        <span v-if="postDetails.htmlContent" v-html="postDetails.htmlContent"></span>
        <div v-if="postDetails.markdownContent" v-html="previewText"></div>
        <div v-if="postDetails.author" class="authors">
            <strong>Author: </strong>
            <span>{{postDetails.author.username}} ({{postDetails.author.email}})</span>
        </div>
    </div>

</template>


<script>
    import {HTTP} from "./http/ApiClient";
    let marked = require('marked');

    export default {
        name: 'BlogPostDetails',
        props: {
            postId: String
        },
        data() {
            return {
                postDetails: {}
            };
        },
        created: function () {
            HTTP.get('crm/blogposts/' + this.postId).then(response => {
                this.postDetails = response.data;
            });
        },
        computed: {
            previewText() {
                marked.setOptions({
                    renderer: new marked.Renderer(),
                    gfm: true,
                    tables: true,
                    breaks: true,
                    pedantic: false,
                    sanitize: true,
                    smartLists: true,
                    smartypants: false
                });
                return marked(this.postDetails.markdownContent);
            }
        }
    }
</script>

<style>
    #blogjumbotron {
        text-align: left;
    }

</style>