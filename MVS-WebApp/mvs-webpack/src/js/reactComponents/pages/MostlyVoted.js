import React, { useState, useEffect } from 'react';
import { Song } from '../components/Song'
import { getMostlyVoted } from '../../api';

export function MostlyVoted() {

    const [songlist, setSonglist] = useState(null);

    useEffect(() => {
        const fetchData = async () => {
            getMostlyVoted().then(data => { data.error ? setSonglist(null) : setSonglist(data) });
        };
        fetchData();
    }, []);

    return (
        <div className='songlist'>
            {songlist && songlist.map((item, idx) => {
                return (
                    <Song key={item.id} data={item} isEditable={false}></Song>
                )
            })}
        </div>
    );
}
