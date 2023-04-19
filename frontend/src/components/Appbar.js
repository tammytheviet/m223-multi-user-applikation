import "../App.css"
import React from "react";
import {Link } from "react-router-dom";
import AuthService from '../auth.service';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Button from '@mui/material/Button';
import AppBars from '@mui/material/AppBar';


class AppBar extends React.Component
{
   constructor(props) 
   {
      super(props);
      this.logOut = this.logOut.bind(this);
      this.state = {
         showAdmin: false,
         currentUser: undefined,
      };
   }

   componentDidMount() 
   {
      const user = AuthService.getCurrentUser();
      if (user) 
      {
         this.setState(
         {
            currentUser: user,
            showAdmin: user.roles.includes("ROLE_ADMIN"),
         });
      }
   }


   logOut() 
   {
      AuthService.logout();
      this.setState(
      {
         showAdmin: false,
         currentUser: undefined,
      });
   }

   render() {
      const { currentUser, showAdmin } = this.state;

      return(
        <Box sx={{ flexGrow: 1 }}>
          <AppBars position="static">
          <Toolbar>
              <Button color="inherit">
                <Link to="/mangalist">Manga List</Link>
              </Button>
            {showAdmin && (
              <Button color="inherit">
                <Link to={"/admin"}> Admin Board </Link>
              </Button>
            )}
            {showAdmin && (
              <Button color="inherit">
                <Link to="/manga">Add Manga</Link>
              </Button>
            )}
            {currentUser ? (
            <div className="navbar-nav ml-auto">
            <Button color="inherit">
              <Link to={"/accountdetails"}> {currentUser.username} </Link>
            </Button>
            <Button color="inherit">
              <a href="/login" onClick={this.logOut}> LogOut </a>
            </Button>
            </div>
            ) : (
            <div className="navbar-nav ml-auto">
            <Button color="inherit">
              <Link to={"/login"}> Login </Link>
            </Button>
            <Button color="inherit">
              <Link to={"/register"}> Register </Link>
            </Button>
            </div>
            )}
          </Toolbar>
          </AppBars>
        </Box>
      )
   }
}

/*
<Box sx={{ flexGrow: 1 }}>
      <AppBar position="static">
        <Toolbar>
          <Button color="inherit">
            <Link to="/mangalist">Manga List</Link>
          </Button>
          <Button color="inherit">
            <Link to="/manga">Add Manga</Link>
          </Button>
          <Button color="inherit">
            <Link to="/login">Login</Link>
          </Button>
          <Button color="inherit">
            <Link to="/register">Register</Link>
          </Button>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            Manga Library
          </Typography>
        </Toolbar>
      </AppBar>
    </Box>
    */
 
export default AppBar;
