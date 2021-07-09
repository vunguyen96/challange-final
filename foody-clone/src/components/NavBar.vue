<template>
  <div id="navbar">
    <span id="logout">
      <Button label="Logout" icon="pi pi-sign-out" @click="logout"/>
    </span>
  </div>
</template>

<script>
import * as Keycloak from "keycloak-js";

let initOptions = {
  url: process.env.VUE_APP_AUTH_SERVER,
  realm: process.env.VUE_APP_REALM,
  clientId: process.env.VUE_APP_CLIENT_ID,
  
};

export default {
  name: "NavBar",
  methods: {
    logout() {
      let keycloak = Keycloak(initOptions);
      let object = { redirectUri: process.env.VUE_APP_ROOT_URI };
      keycloak.init().then(keycloak.logout(object));
    },
  },
};
</script>
<style scoped>
#navbar {
  display: flex;
  justify-content: flex-end;
}
</style>
