import axios from 'axios';
import auth from './Auth'

export const HTTP = axios.create({
    baseURL: 'http://localhost:8081/api/',
    headers: {
        'Access-Control-Allow-Origin': 'http://localhost:8080',
    },
    transformRequest: [
        (data, headers) => {
            headers.Authorization = auth.getAuthHeader()
        }
    ]
});

HTTP.interceptors.response.use(
    (response) => { return response },
    (error) => {
        console.log(error.response.status)
        window.location.href = '/#/account'
    }
)