'use strict';

import { Navbar } from './navbar'
import { SongList } from './songList';
import { getAllSongs, getPairs } from '../api';
import { SongUpload } from './SongUpload';
import {RegistrationForm} from './registrationForm';

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
        if (this.state.error) {
            return (
                <div>
                    <Navbar buttonClicked={this.buttonClicked.bind(this)}></Navbar>
                    <div className='error'>{this.state.error}</div>
                </div>
            )
        } else if (this.state.buttonClicked === 'upload') {
            return (
                <div>
                    <Navbar buttonClicked={this.buttonClicked.bind(this)}></Navbar>
                    <SongUpload error={this.catchError.bind(this)}></SongUpload>
                </div>
            )
        } else if (this.state.buttonClicked === 'register') {
            return (
                <div>
                    <Navbar buttonClicked={this.buttonClicked.bind(this)}></Navbar>
                    <RegistrationForm></RegistrationForm>
                </div>
            )
        } else {
            return (
                <div>
                    <Navbar buttonClicked={this.buttonClicked.bind(this)}></Navbar>
                    <SongList songs={this.state.allSongs} pairs={this.state.songPairs}></SongList>
                </div>
            )
        }
    }
}

const root = document.querySelector('#root');
ReactDOM.render(<User></User>, root);
