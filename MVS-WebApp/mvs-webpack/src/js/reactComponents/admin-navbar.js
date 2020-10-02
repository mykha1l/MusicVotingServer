'use strict';

import { initAllSongs } from "../allsongs"
import { displayCurrentSong } from "../admin"

export class AdminNavbar extends React.Component {
    render() {
        return (
            <div className="navbar">
                <img src="img/icon.png"></img>
                <button className="navigationbar-items" onClick={() => initAllSongs()}>All Songs</button>
                <button className="navigationbar-items" onClick={() => displayCurrentSong()}>Current Song</button>
                <button className="navigationbar-items">Mostly votted</button>
                <button className="navigationbar-items">Setting</button>
            </div>
        )
    }
}
