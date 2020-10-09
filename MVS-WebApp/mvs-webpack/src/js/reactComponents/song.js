'use strict';

export class Song extends React.Component {

    render() {
        if (this.props.data) {
            return (
                <div className='song' onClick={() => this.props.onClick(this.props.data.filename)}>
                    <div className='artist'>{this.props.data.artist}</div>
                    <div className='image'>
                        <img width='200px' src={'data:image/png;base64,' + this.props.data.albumImage} />
                    </div>
                    <div className='song-name'>{this.props.data.title}</div>
                </div>
            )
        } else {
            return (
                <div></div>
            )
        }
    }
}
