import { searchSong } from '../../api'

export class Search extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            searchText: ''
        }
    }

    handleClick() {
        this.props.updateTab();
        searchSong(this.state.searchText).then((data) => {
            this.props.updateSongList(data);
            this.props.updateButtonClicked("search");
        });
    }
    
    handleChange() {
        const val = event.target.value;
        this.setState({ searchText: val });
    }

    render () {
        return (
            <>
                <input id="search-bar" type="text" onChange={this.handleChange.bind(this)}></input>
                <span>
                    <img id="search-image" src="../img/search.png" onClick={this.handleClick.bind(this)}></img>
                </span>
            </>
        )
    }
}
