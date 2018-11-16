const API_URL = (process.env.VUE_APP_AUTH_URL ? process.env.VUE_APP_AUTH_URL : "/");
const LOGIN_URL = API_URL + 'login';
const TOKEN_KEY = "jwt";
const DETAILS_KEY = "auth-details";

export default {

    login(context, credentials) {
        this.clearToken();
        context.$http.post(LOGIN_URL, credentials)
            .then(
                (response) => {
                    localStorage.setItem(TOKEN_KEY, response.body.access_token);
                    localStorage.setItem(DETAILS_KEY, JSON.stringify(response.body));
                },
                (error) => {
                    console.info(error);
                }
            )
    },

    logout() {
        this.clearToken();
    },

    clearToken() {
        localStorage.removeItem(TOKEN_KEY);
    },

    isAuthenticated() {
        return localStorage.getItem(TOKEN_KEY) != null;
    },

    getAuthHeader() {
        return 'Bearer ' + localStorage.getItem(TOKEN_KEY);
    },

    getAuthDetails() {
        if (!this.isAuthenticated())
            return null;

        return JSON.parse(localStorage.getItem(DETAILS_KEY));
    }
}
