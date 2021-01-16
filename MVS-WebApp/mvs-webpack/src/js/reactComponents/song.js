import {deleteSong} from '../api'

export class Song extends React.Component {

    handleSongDelete(id) {
        deleteSong(id);
        this.props.onDelete();
    }

    render() {
        const isEditable = this.props.isEditable;
        if (this.props.data) {
            if (!this.props.data.filename) {
                return (
                    <div className='song'>{this.props.data}</div>
                )
            } else {
                const artist = this.props.data.artist ? this.props.data.artist : this.props.data.filename;
                return (
                    <div className='song' onClick={() => this.props.onClick ? this.props.onClick(this.props.data.filename) : null}>
                        <div className='artist'>{artist}</div>
                        <div className='image'>
                            <img width='100%' src={this.props.data.albumImage ?
                                'data:image/png;base64,' + this.props.data.albumImage :
                                'img/no-image.png'} />
                        </div>
                        <div className='song-name'>{this.props.data.title ? this.props.data.title : '...'}</div>
                        {isEditable &&
                            <span id='delete-song-icon' onClick={() => this.handleSongDelete(this.props.data.id)}>
                                <img src='img/close.png'></img>
                            </span>}
                    </div>
                )
            }
        } else {
            return (
                <div></div>
            )
        }
    }
}
