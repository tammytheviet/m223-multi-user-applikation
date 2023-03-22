import React, { useState } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import { Container ,Paper,Button} from '@material-ui/core';

const useStyles = makeStyles((theme) => ({
  root: {
    '& > *': {
      margin: theme.spacing(1),
     
    },
  },
}));

export const Chapter = ({id}) =>{
    const paperStyle={padding:'50px 20px', width:600,margin:"20px auto"}
    const[inhalt,setInhalt]=useState('')
    const classes = useStyles();
    

  const handleClick = (id) => { 
    console.log("test")
    console.log(id)
    const chapter={inhalt}
    console.log(chapter)
    fetch(`http://localhost:8080/chapter/${id}`,{
      method:"POST",
      headers:{"Content-Type":"application/json"},
      body:JSON.stringify(chapter)

  }).then(()=>{
    console.log("New Chapter added")
    alert("New Chapter added");
  })
}
  return (

    <Container>
        <Paper elevation={3} style={paperStyle}>
            <h1 style={{color:"blue"}}><u>Add Chapter</u></h1>

    <form className={classes.root} noValidate autoComplete="off">
    
      <TextField id="outlined-basic" label="Inhalt" variant="outlined" fullWidth 
      value={inhalt}
      onChange={(e)=>setInhalt(e.target.value)}
      />
      <Button variant="contained" color="secondary">
        Cancel
      </Button>
      <Button variant="contained" color="primary" onClick={() => handleClick(id)}>
        Submit
      </Button>
    </form>
  </Paper>
</Container>
  );
}
export default Chapter