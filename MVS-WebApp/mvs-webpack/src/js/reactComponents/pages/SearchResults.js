import React, { useState } from 'react';
import { Song } from '../components/Song';
import { Modal } from '../components/Modal';

export function SearchResults(props) {

    const songs = props.location.state.results;
    const [clickedSong, setClickedSong] = useState(-1);

    return (
        <div className='songlist'>
            {songs.map((item, idx) => {
                return (
                    <Song key={item.id} data={item} isEditable={false} onClick={() => setClickedSong(idx)}></Song>)
            })}
            {(clickedSong > -1) && <Modal data={songs[clickedSong]} clear={() => { setClickedSong(-1) }}></Modal>}
        </div>
    );
}
