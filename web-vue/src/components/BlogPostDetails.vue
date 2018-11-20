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
                this.postDetails.htmlContent = this.resolveLinks(response);
            });
        },
        methods: {
            resolveLinks: function(response) {
                const imagesHanlderUrl = process.env.VUE_APP_BACKEND_URL + 'crm/images/';
                var someElement = document.createElement('div');
                someElement.innerHTML = response.data.htmlContent;
                var links = someElement.querySelectorAll('img[data-hippo-link]');
                for (var index = 0; index < links.length; index++) {
                    //links where name = data-hippo-link
                    var linkName = links[index].getAttribute('data-hippo-link');
                    for (var j=0;j<response.data.links.length; j++) {
                        if (response.data.links[j].name === linkName) {
                            links[index].src =  imagesHanlderUrl + linkName;
                        }
                    }
                }
                return someElement.innerHTML;
            }
        }
}
</script>

<style>
    #blogjumbotron {
        text-align: left;
    }

</style>