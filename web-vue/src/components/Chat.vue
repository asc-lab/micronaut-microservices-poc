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
    import ws from './http/WebSocket'

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
            this.webSocket = ws.create(this.user.username);

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
                this.webSocket.send(this.message);
                this.message = '';
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