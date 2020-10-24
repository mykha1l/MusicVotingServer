'use strict';

export class Song extends React.Component {

    render() {
        if (this.props.data) {
            if (!this.props.data.filename) {
                return (
                    <div className='song'>{this.props.data}</div>
                )
            } else {
                const artist = this.props.data.artist ? this.props.data.artist : this.props.data.filename;
                return (
                    <div className='song' onClick={() => this.props.onClick(this.props.data.filename)}>
                        <div className='artist'>{artist}</div>
                        <div className='image'>
                            <img width='200px' src={this.props.data.albumImage ?
                                'data:image/png;base64,' + this.props.data.albumImage :
                                'img/no-image.png'} />
                        </div>
                        <div className='song-name'>{this.props.data.title ? this.props.data.title : '...'}</div>
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
