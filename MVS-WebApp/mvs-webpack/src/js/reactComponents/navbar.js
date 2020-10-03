'use strict';

import { initAllSongs } from "../allsongs"
import { initVoting } from "../voting"

export class Navbar extends React.Component {
    render() {
        return (
            <div className="navbar">
            <img src="img/icon.png" />
            <button className="navigationbar-items" onClick={() => initAllSongs()}>All Songs</button>
            <button className="navigationbar-items" onClick={() => initVoting()}>Start voting</button>
          </div>
        )
    }
}
