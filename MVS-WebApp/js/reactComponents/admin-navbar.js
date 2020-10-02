'use strict';

class AdminNavbar extends React.Component {
    render() {
        return (
            <div className="navbar">
                <img src="img/icon.png"></img>
                <button className="navigationbar-items" >All Songs</button>
                <button className="navigationbar-items">Current Song</button>
                <button className="navigationbar-items">Mostly votted</button>
                <button className="navigationbar-items">Setting</button>
            </div>
        )
    }
}

const domContainer = document.querySelector('.navbar-container');
ReactDOM.render(<AdminNavbar></AdminNavbar>, domContainer);
