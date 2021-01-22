import { Navbar } from './navbar'
import { SongList } from './songList';
import { getAllSongs, getPairs } from '../api';
import { SongUpload } from './SongUpload';
import { RegistrationForm } from './registrationForm';
import { LoginForm } from './loginForm';
import { Welcome } from './welcome';

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

    updateSongList(songList) {
        this.setState({ allSongs: songList, error: '' });
    }

    catchError(error) {
        this.setState({error: error, buttonClicked: '', allSongs: '', songPairs: ''});
    }

    componentDidUpdate(prevProps, prevState) {
        if (prevState.buttonClicked != this.state.buttonClicked) {
            if (this.state.buttonClicked === 'allSongs') {
                getAllSongs().then(data => { data.error ? this.catchError(data.error) : this.setState({ allSongs: data }) });
            } else if (this.state.buttonClicked === 'voting') {
                getPairs().then(data => { data.error ? this.catchError(data.error) : this.setState({ songPairs: data }) })
            }
        }
    }

    render() {
        const BUTTON_CLICKED = {
            upload: <SongUpload error={this.catchError.bind(this)}></SongUpload>,
            register: <RegistrationForm></RegistrationForm>,
            login: <LoginForm></LoginForm>,
            allSongs: <SongList songs={this.state.allSongs}></SongList>,
            search: <SongList songs={this.state.allSongs}></SongList>,
            voting: <SongList pairs={this.state.songPairs}></SongList>,
            home: <Welcome></Welcome>
        };
        return (
            <div>
                <Navbar buttonClicked={this.buttonClicked.bind(this)} updateSongList={this.updateSongList.bind(this)} ></Navbar>
                {this.state.error ? <div className='error'>{this.state.error}</div> : BUTTON_CLICKED[this.state.buttonClicked]}
                {!this.state.buttonClicked && <Welcome></Welcome>}
            </div>
        )
    }
}

const root = document.querySelector('#root');
ReactDOM.render(<User></User>, root);
