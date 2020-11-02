'use strict';

export class SongUpload extends React.Component {

    upload(input) {
        fetch('http://www.example.net', {
            method: 'POST',
            headers: {
                "Content-Type": "audio/mpeg"
            },
            body: input.target.files[0]
        }).then(
            response => response.json()
        ).then(
            success => console.log(success)
        ).catch(
            error => console.log(error)
        );
    }

    render() {
        return (
            <div>You can upload an mp3 file to the server:
                <div><input type="file" id="input" onChange={this.upload}></input></div>
            </div>
        )
    }

}
