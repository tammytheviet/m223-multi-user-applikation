import * as React from "react";
import { useState } from "react"
import { makeStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import { Paper,Button,Link } from '@material-ui/core';
import { useNavigate } from "react-router-dom";
import AuthService from "../auth.service";

const useStyles = makeStyles((theme) => ({
    root: {
      '& > *': {
        margin: theme.spacing(1),
       
      },
    },
}));

export default function Login() {
    const paperStyle={padding:'50px 20px', width:600,margin:"20px auto"};
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [setError] = useState(null);
    const navigate = useNavigate();
    const classes = useStyles();

    const handleSubmit = (e) => {
        e.preventDefault();
        AuthService.login(username, password).then(
            () => {
                navigate('/AccountDetails');
                window.location.reload();
            },
            (error) => {
                const resMessage =
                    (error.response &&
                        error.response.data &&
                        error.response.data.message) ||
                    error.message ||
                    error.toString();
                setError(resMessage);
            });
    };

    return (
    <>
            <Paper elevation={3} style={paperStyle}>
                <h1 style={{color:"blue"}}><u>Login</u></h1>

                
                <form className={classes.root} noValidate autoComplete="off" onSubmit={handleSubmit}>
                    <TextField id="outlined-basic" label="Username" variant="outlined" fullWidth 
                    value={username}
                    onChange={(e)=>setUsername(e.target.value)}
                    />
                    <TextField id="outlined-basic" label="Password" variant="outlined" fullWidth
                    value={password}
                    onChange={(e)=>setPassword(e.target.value)}
                    type="password"
                    />
                    <Button variant="contained" color="primary" type="submit">
                        Login
                    </Button><br/>
                    <Link href="./register">Don't have an account?</Link>
                </form>
            </Paper>
    </>
    );
}