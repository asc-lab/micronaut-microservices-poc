<template>
    
        <div id="blogjumbotron" class="jumbotron container">
            <h1> {{postDetails.title}} </h1>
            <span v-for="c in postDetails.categories" :key="c" class="badge badge-pill badge-primary">{{c}}</span>
            <p class="lead">{{postDetails.introduction}}</p>
            <span v-html="postDetails.htmlContent"></span>
        </div>
   
</template>


<script>
import {HTTP} from "./http/ApiClient";

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
        created: function() {
            HTTP.get('crm/blogposts/' + this.postId).then(response => {
                this.postDetails = response.data;
            });
        }
}
</script>

<style>
    #blogjumbotron {
        text-align: left;
    }

</style>