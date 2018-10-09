import axios from 'axios';
import auth from './Auth';

export const HTTP = axios.create({
    baseURL: (process.env.VUE_APP_BACKEND_URL ? process.env.VUE_APP_BACKEND_URL : "/api/"),
    headers: {
        'Authorization': auth.getAuthHeader()
    }
});

HTTP.interceptors.response.use(
    (response) => {
        return response;
    },
    (error) => {
        console.log(error.response.status);
        if (error.response.status === 401 || error.response.status === 403) {
            auth.clearToken();
            window.location.href = '/#/account';
        }
    }
);