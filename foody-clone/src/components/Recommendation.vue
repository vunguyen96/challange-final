<template>
  <h2>Recommentdations</h2>
  <div class="p-grid p-jc-center p-mt-5">
    <a
      v-for="merchant in merchants"
      :key="merchant.id"
      @click="$router.push('/merchant/' + merchant.id)"
      class="p-col-2"
    >
      <img width="80" :src="merchant.image" alt="Picture" />
      <br />
      <span class="p-text-center">
        {{ merchant.name }}
      </span>
    </a>
  </div>
</template>

<script>
import { keyCloakService } from '../assets/js/services/keycloak-service';
export default {
  name: "Recommentdation",
  data() {
    return {
      merchants: [],
    };
  },
  created() {
    if (this.$store.getters["authentication/getAccessToken"]) {
      this.fetchMerchants();
    } else {
      keyCloakService.authenticate().then((auth) => {
      if (!auth) {
        window.location.reload();
      } else {
        this.$store.dispatch('authentication/updateAccessToken', keyCloakService.keycloak.token);
        this.$store.dispatch('authentication/updateUserName', keyCloakService.keycloak.tokenParsed.preferred_username);
        this.fetchMerchants();
       }
    });
      
    }
    
  },
  methods: {
    fetchMerchants() {
      fetch(new URL(process.env.VUE_APP_MERCHANT_API, process.env.VUE_APP_BACKEND_URI), {
        headers: {
          "Content-Type": "application/json",
          Authorization:
            "Bearer " + this.$store.getters["authentication/getAccessToken"],
        },}).then(
        response => response.json().then(
          data => this.merchants = data
        ));
    }
  },
};
</script>

<style></style>
