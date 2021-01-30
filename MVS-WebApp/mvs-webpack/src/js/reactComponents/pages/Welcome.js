import React from 'react';

export function Welcome(props) {
    const welcomeMsg = ` Hey there! Welcome to the Music Voting Server -
     an application that generates playlists according to collected votes from users and plays music.
     If you see this message, the server is running and you can start voting right away by clicking "Start Voting"
     button.
    `
    return (
        <>
            <img id="welcome-img" src="../img/welcome.png"></img>
            <div id="welcome-txt">{welcomeMsg}</div>
        </>);
}
