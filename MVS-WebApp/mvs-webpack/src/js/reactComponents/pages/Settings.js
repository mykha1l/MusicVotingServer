import React, { useState, useEffect } from 'react';
import {
    getVotersNumber, getPlaylistSize, setVotersNumber as setVotersNumberAPI, setPlaylistSize as setPlaylistSizeAPI
} from '../../api';

export function Settings() {

    const [votersNumber, setVotersNumber] = useState(0);
    const [playlistSize, setPlaylistSize] = useState(0);

    useEffect(() => {
        const fetchDataVoters = async () => {
            getVotersNumber().then(data => { data.error ? setVotersNumber(0) : setVotersNumber(data) });
        };
        fetchDataVoters();
        const fetchDataPlaylistSize = async () => {
            getPlaylistSize().then(data => { data.error ? setPlaylistSize(0) : setPlaylistSize(data) });
        };
        fetchDataPlaylistSize();
    }, []);

    function handleSubmit() {
        event.preventDefault();
        setVotersNumberAPI(votersNumber);
        setPlaylistSizeAPI(playlistSize);
    }

    function handleChange() {
        const name = event.target.name;
        const val = event.target.value;
        if (name === 'votersNumber') {
            setVotersNumber(val);
        } else if (name === 'playlistSize') {
            setPlaylistSize(val);
        }
    }

    return (
        <form onSubmit={handleSubmit}>
            <label htmlFor="votersNumber">Voters number:</label>
            <input type="text" value={votersNumber} id="votersNumber" name="votersNumber" onChange={handleChange}></input>
            <label htmlFor="playlistSize">Playlist size:</label>
            <input type="text" value={playlistSize} id="playlistSize" name="playlistSize" onChange={handleChange}></input>
            <input type="submit" value="Submit"></input>
        </form>
    );
}
