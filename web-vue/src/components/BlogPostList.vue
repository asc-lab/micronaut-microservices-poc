<template>
    <div>
        
        <div class="row" v-for="post in blogPosts" :key="post.id">
            <BlogPostDetails :postId="post.id"></BlogPostDetails>
        </div>
    
    </div>
</template>

<script>
    import {HTTP} from "./http/ApiClient";
    import BlogPostDetails from "./BlogPostDetails"

    export default {
        name: 'BlogPostList',
        components: { BlogPostDetails },
        data() {
            return {
                blogPosts: []
            };
        },
        created: function () {
            HTTP.get('crm/blogposts').then(response => {
                this.blogPosts = response.data.items;
            });
        }
    }
</script>

<style scoped lang="scss">

</style>