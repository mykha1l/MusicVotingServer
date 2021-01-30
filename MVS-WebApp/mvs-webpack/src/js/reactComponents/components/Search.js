import React from 'react';
import { searchSong } from '../../api'
import { Redirect } from 'react-router-dom';

export class Search extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            searchText: '',
            results: null,
        }
    }

    handleClick() {
        this.props.updateTab();
        searchSong(this.state.searchText).then((data) => {
            this.props.updateSongList(data);
            this.props.updateButtonClicked("search");
            this.setState({results: data});
        });
    }
    
    handleChange() {
        const val = event.target.value;
        this.setState({ searchText: val });
    }

    handleKeyPress() {
        if (event.keyCode === 13) {
            this.handleClick();
        }
    }

    render () {
        return (
            <>
                {this.state.results && <Redirect to={{
                    pathname: "/search",
                    state: { results: this.state.results }
                }} />}
            <div className="collapsable">
                <input id="search-bar" type="text" onChange={this.handleChange.bind(this)} onKeyPress={this.handleKeyPress.bind(this)}></input>
                <span>
                    <img id="search-image" src="../img/search.png" onClick={this.handleClick.bind(this)}></img>
                </span>
            </div>
            </>
        )
    }
}
