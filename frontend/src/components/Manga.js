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

export default function Manga() {
    const paperStyle={padding:'50px 20px', width:600,margin:"20px auto"}
    const[titel,setTitel]=useState('')
    const[genre,setGenre]=useState('')
    const[zeichner,setZeichner]=useState('')
    const[autor,setAutor]=useState('')
    const[status,setStatus]=useState('')
    const[veröffentlichungsdatum,setVeröffentlichungsdatum]=useState('')
    const classes = useStyles();

  const handleClick=(e)=>{
    console.log("test")
    e.preventDefault()
    const manga={titel,genre,zeichner,autor,status,veröffentlichungsdatum }
    console.log(manga)
    fetch("http://localhost:8080/manga",{
      method:"POST",
      headers:{"Content-Type":"application/json"},
      body:JSON.stringify(manga)

  }).then(()=>{
    console.log("New Manga added")
    alert("New Manga added");
  })
}
  return (

    <Container>
        <Paper elevation={3} style={paperStyle}>
            <h1 style={{color:"blue"}}><u>Add Manga</u></h1>

    <form className={classes.root} noValidate autoComplete="off">
    
      <TextField id="outlined-basic" label="Titel" variant="outlined" fullWidth 
      value={titel}
      onChange={(e)=>setTitel(e.target.value)}
      />
      <TextField id="outlined-basic" label="Genre" variant="outlined" fullWidth
      value={genre}
      onChange={(e)=>setGenre(e.target.value)}
      />
      <TextField id="outlined-basic" label="Zeichner" variant="outlined" fullWidth
      value={zeichner}
      onChange={(e)=>setZeichner(e.target.value)}
      />
      <TextField id="outlined-basic" label="Autor" variant="outlined" fullWidth
      value={autor}
      onChange={(e)=>setAutor(e.target.value)}
      />
      <TextField id="outlined-basic" label="Status" variant="outlined" fullWidth
      value={status}
      onChange={(e)=>setStatus(e.target.value)}
      />
      <TextField id="outlined-basic" label="Veröffentlichungsdatum" variant="outlined" fullWidth
      value={veröffentlichungsdatum}
      onChange={(e)=>setVeröffentlichungsdatum(e.target.value)}
      />
      <Button variant="contained" color="primary" onClick={handleClick}>
        Submit
      </Button>
    </form>
  </Paper>
</Container>
  );
}