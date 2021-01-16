export class Hamburger extends React.Component {

    handleClick(e) {
        e.preventDefault();
        let content = document.getElementsByClassName('dropdown-content')[0];
        let image = document.getElementById('hamburger');
        if (content.style.display === 'flex') {
            image.style.opacity = '100%';
            content.style.display = 'none';
        } else {
            image.style.opacity = '25%';
            content.style.display = 'flex';
        }
    }

    render() {
        return (
            <div className="dropdown" onClick={this.handleClick}>
                <img id="hamburger" src="img/menu.png" width="25px" />
                <div className="dropdown-content">
                    <button onClick={() => this.props.buttonClicked("allSongs")}>All Songs</button>
                    <button onClick={() => this.props.buttonClicked("voting")}>Start voting</button>
                    <button onClick={() => this.props.buttonClicked("upload")}>Song Upload</button>
                </div>
            </div>
        )
    }
}
