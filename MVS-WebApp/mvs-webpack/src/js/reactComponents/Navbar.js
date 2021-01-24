import { getUser, logout } from '../api';
import { Hamburger } from './Hamburger';
import { Search } from './Search';

export class Navbar extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            user: '',
            tab:'',
        }
    }

    componentDidMount() {
        getUser().then((data) => { this.setState({ user: data.username }) });
    }

    handleClick(button) {
        this.props.buttonClicked(button);
        this.setState({tab: button});
    }

    updateTab() {
        this.setState({tab: ''});
    }

    render() {
        return (
            <div className="navbar">
                <img src="img/icon.png" onClick={() => this.handleClick("home")} />
                <button className={`navigationbar-items ${this.state.tab === "allSongs" ? "active" : ""}`} onClick={() => this.handleClick("allSongs")}>All Songs</button>
                <button className={`navigationbar-items ${this.state.tab === "voting" ? "active" : ""}`} onClick={() => this.handleClick("voting")}>Start voting</button>
                <button className={`navigationbar-items ${this.state.tab === "upload" ? "active" : ""}`} onClick={() => this.handleClick("upload")}>Song Upload</button>
                <Search updateSongList={this.props.updateSongList} updateButtonClicked={this.props.buttonClicked} updateTab={this.updateTab.bind(this)}></Search>
                <div className="navigationbar-username">{this.state.user}</div>
                {
                    this.state.user !== 'anonymousUser' ? <button className="navigationbar-items" onClick={
                        () => logout().then(() => location.reload())
                    }>Logout</button>
                        : <div>
                            <button className="navigationbar-items" onClick={() => this.props.buttonClicked("login")}>Log in</button>
                            <button className="navigationbar-items" onClick={() => this.props.buttonClicked("register")}>Register</button>
                        </div>
                }
                {window.innerWidth < 768 ? <Hamburger buttonClicked={this.props.buttonClicked}></Hamburger> : null}
            </div>
        )
    }
}
