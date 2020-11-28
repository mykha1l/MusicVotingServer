'use strict';

export class SongUpload extends React.Component {

    upload(input) {
        const formData = new FormData();
        formData.append('file', input.target.files[0]);
        fetch('/api/v1/upload', {
            method: 'POST',
            body: formData
        }).then(
            response => {
                const props = this.props;
                if (!response.error) {
                    response.text().then(function (text) {
                        console.error(text);
                        props.error(text);
                      });
                }
            }
        ).then(
            success => console.log(success)
        ).catch(
            error => {
                console.error("Song upload error: " + error);
                this.props.error(error.toString());
            }
        );
    }

    render() {
        return (
            <div>You can upload an mp3 file to the server:
                <div><input type="file" id="input" onChange={this.upload.bind(this)}></input></div>
            </div>
        )
    }

}
