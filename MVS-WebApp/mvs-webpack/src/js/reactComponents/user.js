'use strict';

import { Navbar } from './navbar'
import { SongList } from './songList';
import { getAllSongs, getPairs } from '../api';
import { SongUpload } from './SongUpload';
import { RegistrationForm } from './registrationForm';
import { LoginForm } from './loginForm';

class User extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            buttonClicked: '',
            allSongs: '',
            songPairs: '',
            error: '',
        };
    }

    buttonClicked(button) {
        this.setState({ buttonClicked: button, error: '' });
    }

    catchError(error) {
        this.setState({error: error, buttonClicked: '', allSongs: '', songPairs: ''});
    }

    componentDidUpdate(prevProps, prevState) {
        if (prevState.buttonClicked != this.state.buttonClicked) {
            if (this.state.buttonClicked === 'allSongs') {
                getAllSongs().then(data => { this.setState({ allSongs: data }) });
            } else if (this.state.buttonClicked === 'voting') {
                getPairs().then(data => { this.setState({ songPairs: data }) })
            }
        }
    }

    render() {
        const BUTTON_CLICKED = {
            upload: <SongUpload error={this.catchError.bind(this)}></SongUpload>,
            register: <RegistrationForm></RegistrationForm>,
            login: <LoginForm></LoginForm>,
            allSongs: <SongList songs={this.state.allSongs} pairs={this.state.songPairs}></SongList>,
            voting: <SongList songs={this.state.allSongs} pairs={this.state.songPairs}></SongList>,
        };
        return (
            <div>
                <Navbar buttonClicked={this.buttonClicked.bind(this)}></Navbar>
                {this.state.error ? <div className='error'>{this.state.error}</div> : BUTTON_CLICKED[this.state.buttonClicked]}
            </div>
        )
    }
}

const root = document.querySelector('#root');
ReactDOM.render(<User></User>, root);
