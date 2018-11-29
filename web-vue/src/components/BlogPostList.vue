<template>
    <div>

       <nav class="navbar navbar-light bg-light">
            <form class="form-inline my-2 my-lg-0">
                <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" v-model="searchPhrase">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit" @click.prevent="search">Search</button>
            </form>
        </nav>

        <hr/>
        
        <div class="row" v-for="post in blogPosts" :key="post.id">
            <BlogPostDetails :postId="post.id"></BlogPostDetails>
        </div>
        
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
                <li class="page-item" :class="{disabled: currentPage===0}"><a class="page-link" href="#" @click.prevent="prevPage">Previous</a></li>
                <li class="page-item" v-if="currentPage>0"><a class="page-link" href="#" @click.prevent="gotoPage(currentPage-1)">{{currentPage}}</a></li>
                <li class="page-item"><a class="page-link" href="#" @click.prevent="gotoPage(currentPage)">{{currentPage+1}}</a></li>
                <li class="page-item" v-if="hasMore"><a class="page-link" href="#" @click.prevent="gotoPage(currentPage+1)">{{currentPage+2}}</a></li>
                <li class="page-item" :class="{disabled: !hasMore}"><a class="page-link" href="#" @click.prevent="nextPage">Next</a></li>
            </ul>
        </nav>
        
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
                blogPosts: [],
                currentPage: 0,
                pageSize: 2,
                hasMore: false,
                searchPhrase: ''
            };
        },
        created: function () {
            this.loadBlogPosts();
        },
        methods: {
            nextPage: function() {
                if (this.hasMore) {
                    this.currentPage += 1;
                    this.loadBlogPosts();
                }
            },
            prevPage: function() {
                if (this.currentPage>0) {
                    this.currentPage -= 1;
                    this.loadBlogPosts();
                }
            },
            gotoPage : function(pageNumber) {
                this.currentPage = pageNumber;
                this.loadBlogPosts();
            },
            search: function() {
                this.currentPage = 0;
                this.loadBlogPosts();
            },
            loadBlogPosts() {
                HTTP.get('cms/blogposts?pageNumber=' + this.currentPage + '&pageSize=' + this.pageSize + '&searchPhrase=' + this.searchPhrase).then(response => {
                    this.blogPosts = response.data.items;
                    this.hasMore = response.data.more;
                });
            }
        }
    }
</script>

<style scoped lang="scss">

</style>