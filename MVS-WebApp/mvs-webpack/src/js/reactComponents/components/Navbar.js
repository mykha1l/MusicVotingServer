import React from 'react';
import { getUser, logout } from '../../api';
import { Hamburger } from './Hamburger';
import { Search } from './Search';
import { NavLink, Link } from 'react-router-dom';

export class Navbar extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            user: '',
            tab:'',
        }
    }

    componentDidMount() {
        getUser().then((data) => { this.setState({ user: data.username }) });
    }

    handleClick(button) {
        this.props.buttonClicked(button);
        this.setState({tab: button});
    }

    updateTab() {
        this.setState({tab: ''});
    }

    render() {
        return (
            <div className="navbar">
                <Link to='/'>
                    <img src="img/icon.png" />
                </Link>
                <button className='navigationbar-items collapsable'>                    
                <NavLink to={{
                        pathname: "/allSongs",
                        state: { isEditable: false },
                    }} activeClassName='active'>All Songs</NavLink>
                </button>
                <button className='navigationbar-items collapsable'>
                    <NavLink to='/vote' activeClassName='active'>Start voting</NavLink>
                </button>
                <button className='navigationbar-items collapsable' >
                    <NavLink to='/upload' activeClassName='active'>Song Upload</NavLink>
                </button>
                <button className='navigationbar-items collapsable' >
                    <NavLink to='/chat' activeClassName='active'>Chat</NavLink>
                </button>
                <Search updateSongList={this.props.updateSongList} updateButtonClicked={this.props.buttonClicked} updateTab={this.updateTab.bind(this)}></Search>
                <div className="account-container">
                    <div className="navigationbar-username collapsable">{this.state.user}</div>
                    {
                        this.state.user !== 'anonymousUser' ? <button className="navigationbar-items collapsable" onClick={
                            () => logout().then(() => location.reload())
                        }>Logout</button>
                            : <>
                                <button className="navigationbar-items collapsable">
                                    <NavLink to='/login' activeClassName='active'>Login</NavLink>
                                </button>
                                <button className="navigationbar-items collapsable">
                                    <NavLink to='/register' activeClassName='active'>Register</NavLink>
                                </button>
                            </>
                    }
                </div>
                {window.innerWidth < 768 ? <Hamburger buttonClicked={this.props.buttonClicked}></Hamburger> : null}
            </div>
        )
    }
}
