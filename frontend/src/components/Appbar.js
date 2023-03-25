import "../App.css"
import React from "react";
import {Link } from "react-router-dom";
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';

export default function Appbar() {  
  return (
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
  );
}
