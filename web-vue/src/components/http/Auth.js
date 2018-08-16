const API_URL = 'http://localhost:8090/'
const LOGIN_URL = API_URL + 'login'

export default {

    login(context, creds) {
        context.$http.post(LOGIN_URL, creds)
            .then(
                (response) => {
                    localStorage.setItem('jwt', response.body.accessToken);
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
