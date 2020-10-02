'use strict';

import {AdminNavbar} from './admin-navbar'
import {CurrentSong} from './currentSong'

const navbarContainer = document.querySelector('.navbar-container');
ReactDOM.render(<AdminNavbar></AdminNavbar>, navbarContainer);

const songContainer = document.querySelector('.songlist');
ReactDOM.render(<CurrentSong></CurrentSong>, songContainer);
