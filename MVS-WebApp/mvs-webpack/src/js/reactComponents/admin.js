'use strict';

import { AdminNavbar } from './admin-navbar'
import { CurrentSong } from './currentSong'
import { SongList } from './songList'
import { getAllSongs } from '../api'

class Admin extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            buttonClicked: '',
            allSongs: '',
            currentSong: null,
        };
    }

    buttonClicked(button) {
        this.setState({buttonClicked : button});
    } 

    componentDidUpdate(prevProps, prevState) {
        if (prevState.buttonClicked != this.state.buttonClicked) {
            if (this.state.buttonClicked === 'allSongs') {
                getAllSongs().then(data => { this.setState({ allSongs: data }) });
            } else if (this.state.buttonClicked === 'currentSong') {
                this.setState({allSongs: null});
            }
        }
    }

    render() {
        if (this.state.allSongs) {
            return (
                <div>
                    <AdminNavbar buttonClicked={this.buttonClicked.bind(this)}></AdminNavbar>
                    <SongList songs={this.state.allSongs}></SongList>
                </div>

            )
        } else {
            return (
                <div>
                    <AdminNavbar buttonClicked={this.buttonClicked.bind(this)}></AdminNavbar>
                    <CurrentSong></CurrentSong>
                </div>
            )
        }
    }
}

const root = document.querySelector('#root');
ReactDOM.render(<Admin></Admin>, root);
