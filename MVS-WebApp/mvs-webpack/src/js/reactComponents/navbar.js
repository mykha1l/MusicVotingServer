'use strict';

import { getUser, logout } from '../api';

export class Navbar extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            user: ''
        }
    }

    componentDidMount() {
        getUser().then((data) => { this.setState({ user: data }) });
    }

    render() {
        const { isLoggedIn } = this.state.user !=='anonymousUser';
        return (
            <div className="navbar">
                <img src="img/icon.png" />
                <button className="navigationbar-items" onClick={() => this.props.buttonClicked("allSongs")}>All Songs</button>
                <button className="navigationbar-items" onClick={() => this.props.buttonClicked("voting")}>Start voting</button>
                <button className="navigationbar-items" onClick={() => this.props.buttonClicked("upload")}>Song Upload</button>
                <div className="navigationbar-username">{this.state.user}</div>
                {
                    isLoggedIn ? <button className="navigationbar-items" onClick={() => logout()}>Logout</button>
                        : <div>
                            <button className="navigationbar-items" onClick={() => logout()}>Log in</button>
                            <button className="navigationbar-items" onClick={() => this.props.buttonClicked("register")}>Register</button>
                        </div>
                }
            </div>
        )
    }
}
