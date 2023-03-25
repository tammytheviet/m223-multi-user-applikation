import * as React from "react";
import { useState } from "react"
import { makeStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import { Paper, Button } from '@material-ui/core';
import { useNavigate } from "react-router-dom";

const useStyles = makeStyles((theme) => ({
    root: {
      '& > *': {
        margin: theme.spacing(1),
       
      },
    },
}));

export default function Register(){
    const paperStyle={padding:'50px 20px', width:600,margin:"20px auto"}
    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate()
    const classes = useStyles();

    const handleSubmit = (event) =>{
        event.preventDefault();
        fetch("http://localhost:8080/api/auth/signup", {
            method:"POST",
            redirect: "follow",
            headers:{
                "Accept": "application/json",
                "Content-Type":"application/json"
            },
            body:JSON.stringify({
                "username": username, 
                "email": email, 
                "password": password
            })
        }).then(res => {
            return console.log(res)
        })
        return console.log("Success")
    }

    return (
    <>
            <Paper elevation={3} style={paperStyle}>
                <h1 style={{color:"blue"}}><u>Register</u></h1>

                <form className={classes.root} noValidate autoComplete="off" onSubmit={() => {handleSubmit(); navigate("/login")}}>
                
                    <TextField id="outlined-basic" label="Username" variant="outlined" fullWidth 
                    value={username}
                    onChange={(e)=>setUsername(e.target.value)}
                    />
                    <TextField id="outlined-basic" label="Email" variant="outlined" fullWidth
                    value={email}
                    onChange={(e)=>setEmail(e.target.value)}
                    />
                    <TextField id="outlined-basic" label="Password" variant="outlined" fullWidth
                    value={password}
                    onChange={(e)=>setPassword(e.target.value)}
                    type="password"
                    />
                    <Button variant="contained" color="primary" type="submit">
                        Submit
                    </Button>
                </form>
            </Paper>
    </>
    );
}