import axios from 'axios';

export const HTTP = axios.create({
    baseURL: 'http://localhost:8081/',
    headers: {
        'Access-Control-Allow-Origin': 'http://localhost:8080',
    }
});