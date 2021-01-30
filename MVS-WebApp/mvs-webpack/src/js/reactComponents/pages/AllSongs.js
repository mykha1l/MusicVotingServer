import React, { useState, useEffect } from 'react';
import { getAllSongs } from '../../api';
import { Song } from '../components/Song';
import { Modal } from '../components/Modal';

export function AllSongs() {

    const [songList, setSongList] = useState(null);
    const [clickedSong, setClickedSong] = useState(-1);

    useEffect(() => {
        const fetchData = async () => {
            getAllSongs().then(data => { data.error ? setSongList(null) : setSongList(data) });
        };
        fetchData();
    }, []);

    return (
        <div className='songlist'>
            {songList && songList.map((item, idx) => {
                return (<Song data={item} isEditable={false} key={item.id} onClick={() => setClickedSong(idx)}></Song>);
            })}
            {(clickedSong > -1) && <Modal data={songList[clickedSong]} clear={() => {setClickedSong(-1)}}></Modal>}
        </div>
    );
}
