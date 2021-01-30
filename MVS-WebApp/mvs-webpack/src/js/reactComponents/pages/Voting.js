import React, { useState, useEffect } from 'react';
import { getPairs, sendVotes } from '../../api';
import { Song } from '../components/Song';
import { VotingProgress } from '../components/VotingProgress';

export function Voting() {

    const [counter, setCounter] = useState(0);
    const [songPairs, setSongPairs] = useState(null);
    const [votes, setVotes] = useState([]);

    function handleClick(filename) {
        setCounter(counter + 1);
        setVotes(votes.concat(filename))
        if (counter >= (songPairs.length - 1)) {
            sendVotes(votes);
        }
    }

    useEffect(() => {
        const fetchData = async () => {
            getPairs().then(data => { data.error ? setSongPairs(null) : setSongPairs(data) });
        };
        fetchData();
    }, []);

    if (!songPairs || (counter >= songPairs.length)) {
        return (
            <div></div>
        );
    } else {
        return (
            <>
                <VotingProgress total={songPairs.length} counter={counter}></VotingProgress>
                <div className='songlist'>
                    <Song data={songPairs[counter][0]} onClick={handleClick.bind(this)}></Song>
                    <Song data={songPairs[counter][1]} onClick={handleClick.bind(this)}></Song>
                </div>
            </>
        );
    }
}
