import axios from 'axios';
import auth from './Auth'

export const HTTP = axios.create({
    baseURL: 'http://localhost:8081/api/',
    transformRequest: [
        (data, headers) => {
            headers.Authorization = auth.getAuthHeader()
        }
    ]
});

HTTP.interceptors.response.use(
    (response) => {
        return response
    },
    (error) => {
        console.log(error.response.status);
        if (error.response.status === 401 || error.response.status === 403) {
            window.location.href = '/#/account'
        }
    }
);