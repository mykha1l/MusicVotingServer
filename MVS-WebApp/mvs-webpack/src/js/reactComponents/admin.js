import React from 'react';
import ReactDOM from 'react-dom' ;
import { AdminNavbar } from './components/AdminNavbar'
import { CurrentSong } from './pages/CurrentSong'
import { SongList } from './pages/SongList'
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

    refreshAllSongs() {
        getAllSongs().then(data => { data.error ? this.catchError(data.error) : this.setState({ allSongs: data, error: null }) });
    }

    catchError(error) {
        console.log("catched error");
        this.setState({error: error, buttonClicked: '', allSongs: ''});
    }

    componentDidUpdate(prevProps, prevState) {
        if (prevState.buttonClicked != this.state.buttonClicked) {
            if (this.state.buttonClicked === 'allSongs') {
                this.refreshAllSongs();
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
        const BUTTON_CLICKED = {
            allSongs: <SongList songs={this.state.allSongs} isEditable={true} onSongChange={this.refreshAllSongs.bind(this)}></SongList>,
            currentSong: <CurrentSong></CurrentSong>,
            mostlyVoted: <SongList songs={this.state.mostlyVoted}></SongList>,
            home: <div></div>
        }
        return (
            <div>
                <AdminNavbar buttonClicked={this.buttonClicked.bind(this)}></AdminNavbar>
                {this.state.error ? <div className='error'>{this.state.error}</div> : BUTTON_CLICKED[this.state.buttonClicked]}
            </div>
        )

    }
}

const root = document.querySelector('#root');
ReactDOM.render(<Admin></Admin>, root);
