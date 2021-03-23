import React, {useEffect} from 'react';

export function Chat() {
    const ws = new WebSocket("ws://" + location.hostname + ":8801/chat");

    useEffect(() => {
        ws.onmessage = (message) => {
            const msg = message.data;
            document.getElementById("chat").append(msg + "\n");
        }
        ws.onerror = function () {
            document.getElementById("chat").append("<Error occurred. Try reloading.>");
        }
        ws.onclose = function () {
            document.getElementById("chat").append("<Connection closed. Try reloading.>");
        }
    }, []);

    function sendToChat() {
        const username = document.getElementById("user-name");
        const message = document.getElementById("chat-message");
        if (username && message) {
            
            ws.send(username.value + ': ' + message.value);
            message.value = '';
        }
    }

    return (
        <>
            <div className='chatarea'>
                <textarea className='chat' id='chat'></textarea>
                <div>Name:<input id='user-name' />Message:<input id='chat-message' /><button onClick={sendToChat}>Send</button></div>
            </div>
        </>
    )
}
