import axios from 'axios';
import auth from './Auth'

export const HTTP = axios.create({
    baseURL: 'http://localhost:8081/api/',
    transformRequest: [
        (data, headers) => {
            console.info("data");
            console.info(data);
            console.info("headers");
            console.info(headers);
            headers.Authorization = auth.getAuthHeader()
        }
    ]
});

HTTP.interceptors.response.use(
    (response) => {
        return response
    },
    (error) => {
        console.info("interecpotr error");
        console.log(error.response.status);
        console.log(error)
        // window.location.href = '/#/account'
    }
)