'use strict';

import { Navbar } from './navbar'
import { SongList } from './songList';
import { getAllSongs } from '../allsongs';

class User extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            buttonClicked: '',
            allSongs: ''
        };
    }

    buttonClicked(button) {
        this.setState({buttonClicked : button});
    } 

    componentDidUpdate(prevProps, prevState) {
        if (prevState.buttonClicked != this.state.buttonClicked) {
            if (this.state.buttonClicked === 'allSongs') {
                getAllSongs().then(data => { this.setState({ allSongs: data }) });
            }
        }
    }

    render() {
        return (
            <div>
                <Navbar buttonClicked={this.buttonClicked.bind(this)}></Navbar>
                <SongList songs={this.state.allSongs}></SongList>
            </div>
        )
    }
}

const root = document.querySelector('#root');
ReactDOM.render(<User></User>, root);
