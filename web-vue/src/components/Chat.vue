<template>
    <div>
        <b-form-input id="message"
                      type="text"
                      v-model="message"
                      required
                      @keyup.native.enter="send"
                      placeholder="Type your message">
        </b-form-input>

        <div class="messages-container">
            <pre>{{chat}}</pre>
        </div>
    </div>
</template>

<script>
    import auth from './http/Auth'

    export default {
        name: "Chat",
        data() {
            return {
                user: {},
                webSocket: {},
                chat: '',
                message: ''
            }
        },
        beforeCreate() {
            if(!auth.isAuthenticated())
                window.location.href = "/";
        },
        created() {
            this.user = auth.getAuthDetails();

            this.webSocket = new WebSocket("ws://localhost:8787/ws/chat/main/" + this.user.username);

            this.webSocket.onmessage = event => {
                console.log('Hello from websocket onmessage. Event: ' + event.data);
                this.appendToChat(event.data);
            };

            this.webSocket.onclose = () => {
                console.error("WebSocket connection closed");
            };
        },
        methods: {
            send () {
                this.chat += '[' + this.user.username + '] ' + this.message + '\n';
                let tempMsg = this.message;
                this.message = '';
                this.webSocket.send(tempMsg);
            },
            appendToChat(text) {
                this.chat += text + '\n';
            }
        }
    }
</script>

<style scoped>
    .messages-container {
        margin-top: 20px;
    }
</style>