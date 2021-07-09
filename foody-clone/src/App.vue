<template>
  <Banner msg="FOOD-ĐÂY" />
  <NavBar/>
  <router-view></router-view>
</template>

<script>
//import { keyCloakService } from "./assets/js/services/keycloak-service";
import Banner from "./components/Banner.vue";
import NavBar from "./components/NavBar.vue"
export default {
  name: "App",
  components: {
    Banner, NavBar
  }
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

#billAvatar {
  border: 2px solid lightgray;
  border-radius: 50%;
}
</style>
