import { Song } from './song'
import { sendVotes } from '../api'
import { Modal } from './modal'

export class SongList extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            counter: 0,
            votes: [],
            songInfo: null,
        }
    }

    clickSong(filename) {
        this.setState((prevState) => ({
            counter: this.state.counter + 1,
            votes: [...prevState.votes, filename],
        }));
        if (this.state.counter >= (this.props.pairs.length - 1)) {
            sendVotes(this.state.votes);
        }
    }

    showSongDetails(songInfo) {
        this.setState({ songInfo: songInfo });
    }

    clearSongDetails() {
        this.setState({songInfo: null});
    }

    render() {
        const songs = this.props.songs;
        const pairs = this.props.pairs;
        const isEditable = this.props.isEditable;
        if (songs) {
            return (
                <div className='songlist'>
                    {songs.map((item, idx) => {
                        return (
                            <Song onClick={() => this.showSongDetails(item)} data={item} isEditable={isEditable} onDelete={this.props.onSongRemove}></Song>)
                    })}
                    {this.state.songInfo && <Modal data={this.state.songInfo} clear={this.clearSongDetails.bind(this)}></Modal>}
                </div>)
        } else if (pairs) {
            if (this.state.counter >= (pairs.length)) {
                return (
                    <div></div>
                );
            } else {
                return (
                    <div className='songlist'>
                        <Song data={pairs[this.state.counter][0]} onClick={this.clickSong.bind(this)}></Song>
                        <Song data={pairs[this.state.counter][1]} onClick={this.clickSong.bind(this)}></Song>
                    </div>);
            }
        } else {
            return (<div></div>);
        }
    }
}
