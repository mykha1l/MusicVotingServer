'use strict';

import { initVoting } from "../voting"

export class Navbar extends React.Component {

    render() {
        return (
            <div className="navbar">
            <img src="img/icon.png" />
            <button className="navigationbar-items" onClick={() => this.props.buttonClicked("allSongs")}>All Songs</button>
            <button className="navigationbar-items" onClick={() => initVoting()}>Start voting</button>
          </div>
        )
    }
}
