const API_URL = (process.env.VUE_APP_AUTH_URL ? process.env.VUE_APP_AUTH_URL : "/");
const LOGIN_URL = API_URL + 'login';

export default {

    login(context, credentials) {
        context.$http.post(LOGIN_URL, credentials)
            .then(
                (response) => {
                    this.clearToken();
                    localStorage.setItem('jwt', response.body.access_token);
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
        localStorage.removeItem('jwt');
    },

    isAuthenticated() {
        return localStorage.getItem('jwt') != null;
    },

    getAuthHeader() {
        return 'Bearer ' + localStorage.getItem('jwt');
    }
}
