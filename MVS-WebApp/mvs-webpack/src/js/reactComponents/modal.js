export function Modal(props) {

    const output = Object.entries(props.data).map(([key, value]) => {
        if (key === "albumImage") {
            return;
        }
        return (
            <tr>
                <td>{key}</td>
                <td>{value}</td>
            </tr>
        );
    });

    return (
        <div id="modal-dialog" onClick={props.clear}>
            <div id="modal-container">
                <table id="song-info-table">
                    <body>
                        {output}
                    </body>
                </table>
            </div>
        </div>
    );
}
