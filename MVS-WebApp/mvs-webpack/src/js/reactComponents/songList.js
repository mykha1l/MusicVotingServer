'use strict';

import { Song } from './song'

export class SongList extends React.Component {

    constructor(props) {
        super(props);
    }

    render() {
        const songs = this.props.songs;
        if (songs) {
            return (
                <div className='songlist'>
                    {songs.map((item, idx) => {
                        return (
                            <Song data={item}></Song>)
                    })}
                </div>)
        } else {
            return (<div></div>);
        }
    }
}
