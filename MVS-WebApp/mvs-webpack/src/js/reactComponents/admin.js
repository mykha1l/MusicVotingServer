import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter, Route } from 'react-router-dom';
import { AdminNavbar } from './components/AdminNavbar';
import { CurrentSong } from './pages/CurrentSong';
import { WelcomeAdmin } from './pages/WelcomeAdmin';
import { AllSongs } from './pages/AllSongs';
import { MostlyVoted } from './pages/MostlyVoted';
import { Settings } from './pages/Settings';

function Admin() {
    return (
        <>
            <BrowserRouter>
                <AdminNavbar />
                <Route exact path='/admin.html' component={WelcomeAdmin} />
                <Route exact path='/allSongs-admin' component={AllSongs} />
                <Route exact path='/currentSong-admin' component={CurrentSong} />
                <Route exact path='/mostlyVoted-admin' component={MostlyVoted} />
                <Route exact path='/settings-admin' component={Settings} />
            </BrowserRouter>
        </>
    )
}

const root = document.querySelector('#root');
ReactDOM.render(<Admin></Admin>, root);
