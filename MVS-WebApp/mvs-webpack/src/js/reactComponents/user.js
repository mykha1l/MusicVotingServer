import React from 'react';
import ReactDOM from 'react-dom' ;
import { Navbar } from './components/Navbar';
import { SongUpload } from './pages/SongUpload';
import { RegistrationForm } from './pages/RegistrationForm';
import { LoginForm } from './pages/LoginForm';
import { Welcome } from './pages/Welcome';
import { BrowserRouter, Route } from 'react-router-dom';
import { AllSongs } from './pages/AllSongs';
import { Voting } from './pages/Voting';
import { Chat } from './pages/Chat';
import { SearchResults } from './pages/SearchResults';

function User() {
    return (
        <BrowserRouter>
            <Navbar />
            <Route exact path='/' component={Welcome} />
            <Route exact path='/index.html' component={Welcome} />
            <Route exact path='/upload' component={SongUpload} />
            <Route exact path='/allSongs' component={AllSongs} />
            <Route exact path='/vote' component={Voting} />
            <Route exact path='/chat' component={Chat} />
            <Route exact path='/login' component={LoginForm} />
            <Route exact path='/register' component={RegistrationForm} />
            <Route path='/search' component={SearchResults} />
        </BrowserRouter>
    );
}

const root = document.querySelector('#root');
ReactDOM.render(<User></User>, root);
