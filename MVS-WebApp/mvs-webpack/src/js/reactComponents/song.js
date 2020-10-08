'use strict';

export class Song extends React.Component {

    render() {
        return (
            <div className='song'>
                <div className='artist'>{this.props.data.artist}</div>
                <div className='image'>
                    <img width='200px' src={'data:image/png;base64,' + this.props.data.albumImage} />
                </div>
                <div className='song-name'>{this.props.data.title}</div>
            </div>
        )
    }
}
