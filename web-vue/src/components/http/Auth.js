const API_URL = 'http://localhost:8090/'
const LOGIN_URL = API_URL + 'login'

export default {

    user: {
        authenticated: false
    },

    login(context, creds) {
        context.$http.post(LOGIN_URL, creds)
            .then(
                (response) => {
                    localStorage.setItem('jwt', response.body.accessToken);
                    this.user.authenticated = true
                }
            )
    },

    logout() {
        localStorage.removeItem('jwt')
        this.user.authenticated = false
    },

    getAuthHeader() {
        return 'Bearer ' + localStorage.getItem('jwt')
    }
}
