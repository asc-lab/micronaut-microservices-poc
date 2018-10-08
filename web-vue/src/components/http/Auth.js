const API_URL = process.env.VUE_APP_AUTH_URL;
const LOGIN_URL = API_URL + 'login';

export default {

    login(context, credentials) {
        context.$http.post(LOGIN_URL, credentials)
            .then(
                (response) => {
                    localStorage.removeItem('jwt');
                    localStorage.setItem('jwt', response.body.access_token);
                },
                (error) => {
                    console.info(error)
                }
            )
    },

    logout() {
        localStorage.removeItem('jwt')
    },

    isAuthenticated() {
        return localStorage.getItem('jwt') != null
    },

    getAuthHeader() {
        return 'Bearer ' + localStorage.getItem('jwt')
    }
}
