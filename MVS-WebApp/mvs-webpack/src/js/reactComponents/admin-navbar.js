'use strict';

export class AdminNavbar extends React.Component {
    render() {
        return (
            <div className="navbar">
                <img src="img/icon.png"></img>
                <button className="navigationbar-items" onClick={() => this.props.buttonClicked("allSongs")}>All Songs</button>
                <button className="navigationbar-items" onClick={() => this.props.buttonClicked("currentSong")}>Current Song</button>
                <button className="navigationbar-items" onClick={() => this.props.buttonClicked("mostlyVoted")}>Mostly votted</button>
                <button className="navigationbar-items">Setting</button>
            </div>
        )
    }
}
