import React from 'react';

export function WelcomeAdmin() {
    const welcomeMsg = ` Welcome to the Admin Page of Music Voting Server -
     here you can edit/delete songs, browse voting statistics, see currently played song and change
     server settings.
    `
    return (
        <>
            <div id="welcome-txt">{welcomeMsg}</div>
        </>
    );
}
