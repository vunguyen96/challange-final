import * as Keycloak from "keycloak-js";

let initOptions = {
  url: process.env.VUE_APP_AUTH_SERVER,
  realm: process.env.VUE_APP_REALM,
  clientId: process.env.VUE_APP_CLIENT_ID,
  onLoad: "login-required",
};

let keycloak = Keycloak(initOptions);

function authenticate() {
  return keycloak
    .init({
      onLoad: initOptions.onLoad,
      checkLoginIframe: false,
      redirectUri: location.origin,
      enableLogging: true
    })
}

export const keyCloakService = {
  authenticate,
  keycloak
};
