'use strict';

import { AdminNavbar } from './admin-navbar'
import { CurrentSong } from './currentSong'
import { SongList } from './songList'
import { getAllSongs } from '../api'
import { getMostlyVoted } from '../api'

class Admin extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            buttonClicked: '',
            allSongs: '',
            currentSong: null,
            mostlyVoted: [],
            error: null,
        };
    }

    buttonClicked(button) {
        this.setState({buttonClicked : button});
    } 

    componentDidUpdate(prevProps, prevState) {
        if (prevState.buttonClicked != this.state.buttonClicked) {
            if (this.state.buttonClicked === 'allSongs') {
                getAllSongs().then(data => { this.setState({ allSongs: data, error: null }) });
            } else if (this.state.buttonClicked === 'currentSong') {
                this.setState({ allSongs: null, error: null, currentSong: true });
            } else if (this.state.buttonClicked === 'mostlyVoted') {
                getMostlyVoted().then(data => { this.setState({ mostlyVoted: data, allSongs: null, currentSong: null, error: null }); })
                    .catch((error) => {
                        console.error('Error:', error);
                        this.setState({error: error});
                    });
            }
        }
    }

    render() {
        if (this.state.error) {
            return (
                <div>
                    <AdminNavbar buttonClicked={this.buttonClicked.bind(this)}></AdminNavbar>
                    <div>{this.state.error.toString()}</div>
                </div>
            )
        } else if (this.state.allSongs) {
            return (
                <div>
                    <AdminNavbar buttonClicked={this.buttonClicked.bind(this)}></AdminNavbar>
                    <SongList songs={this.state.allSongs}></SongList>
                </div>

            )
        } else if (this.state.currentSong) {
            return (
                <div>
                    <AdminNavbar buttonClicked={this.buttonClicked.bind(this)}></AdminNavbar>
                    <CurrentSong></CurrentSong>
                </div>
            )
        } else {
            return (
                <div>
                    <AdminNavbar buttonClicked={this.buttonClicked.bind(this)}></AdminNavbar>
                    <SongList songs={this.state.mostlyVoted}></SongList>
                </div>
            )
        }
    }
}

const root = document.querySelector('#root');
ReactDOM.render(<Admin></Admin>, root);
