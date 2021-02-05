import React from 'react';
import { Navbar } from './Navbar';
import { NavLink, Link } from 'react-router-dom';

export class AdminNavbar extends Navbar {
    render() {
        return (
            <div className="navbar">
                <Link to='/admin.html'>
                    <img src="img/icon.png" />
                </Link>
                <button className='navigationbar-items collapsable'>
                    <NavLink to={{
                        pathname: "/allSongs-admin",
                        state: { isEditable: true },
                    }} activeClassName='active'>All Songs</NavLink>
                </button>
                <button className="navigationbar-items collapsable">
                    <NavLink to="/currentSong-admin" activeClassName='active'>Current Song</NavLink>
                </button>
                <button className="navigationbar-items collapsable">
                    <NavLink to="/mostlyVoted-admin">Mostly voted</NavLink>
                </button>
                <div className="navigationbar-username">{this.state.user}</div>
            </div>
        )
    }
}
