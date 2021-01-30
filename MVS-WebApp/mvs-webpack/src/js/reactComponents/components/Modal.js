import React from 'react';

export function Modal(props) {

    const output = Object.entries(props.data).map(([key, value]) => {
        if (key === "albumImage") {
            return;
        }
        if (key === "duration") {
            const minutes = Math.floor(value / 60);
            const seconds = value - minutes * 60;
            value = (minutes < 10 ? `0${minutes}` : minutes) + ":" + (seconds < 10 ? `0${seconds}` : seconds);
        }
        return (
            <tr key={'tr' + props.data.id + key}>
                <td key={'td1' + props.data.id + key} className="details-key">{key}</td>
                <td key={'td2' + props.data.id + key}>{value}</td>
            </tr>
        );
    });

    return (
        <div id="modal-dialog" onClick={props.clear}>
            <div id="modal-container">
                <table id="song-info-table">
                    <caption>Song Details</caption>
                    <tbody>
                        {output}
                    </tbody>
                </table>
            </div>
        </div>
    );
}
