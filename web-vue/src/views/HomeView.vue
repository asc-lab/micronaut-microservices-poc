<template>
  <div class="home">
    <img src="../assets/logo.png" />
    <HelloWorld msg="Welcome to ASCLAB Micronaut PoC"/>
  </div>
</template>

<script>
import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'home',
  components: {
    HelloWorld
  }
}
</script>
