const API_URL = 'http://localhost:8090/'
const LOGIN_URL = API_URL + 'login'

export default {

    login(context, creds) {
        console.info(context)
        console.info(creds)

        context.$http.post(LOGIN_URL, creds)
            .then(
                (response) => {
                    localStorage.removeItem('jwt');
                    localStorage.setItem('jwt', response.body.accessToken);
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
