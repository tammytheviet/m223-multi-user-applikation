import React, { useEffect, useState } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import { Container ,Paper,Button} from '@material-ui/core';
import AuthService from '../auth.service';

const useStyles = makeStyles((theme) => ({
  root: {
    '& > *': {
      margin: theme.spacing(1),
     
    },
  },
}));

export default function Manga() {
    const paperStyle={padding:'50px 20px', width:600,margin:"20px auto"}
    const[updatedTitel,setUpdatedTitel]=useState('')
    const[updatedGenre,setUpdatedGenre]=useState('')
    const[updatedZeichner,setUpdatedZeichner]=useState('')
    const[updatedAutor,setUpdatedAutor]=useState('')
    const[updatedStatus,setUpdatedStatus]=useState('')
    const[updatedVeröffentlichungsdatum,setUpdatedVeröffentlichungsdatum]=useState('')
    const[manga, setManga]=useState('')
    const classes = useStyles();
    const [ currentUser, setCurrentUser ] = useState();
    
    useEffect(()=>{
      fetch("http://localhost:8080/manga")
      .then(r => r.json())
      .then((res)=>{
        setManga(res);
      })
    
      const user = AuthService.getCurrentUser();
      if (user) {
        setCurrentUser(user)
        setShowAdmin(user.roles.includes("ROLE_ADMIN")
      )}
    },[])

    const update = async (id) => {
      const updatedManga = {updatedTitel,updatedGenre,updatedZeichner,updatedAutor,updatedStatus,updatedVeröffentlichungsdatum}
      console.log(updatedManga)
      fetch(`http://localhost:8080/manga/${id}`, {
        method: 'PUT',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
          body:JSON.stringify(updatedManga)
        }
      }).then(() => { 
        console.log("Existing manga updated")
        alert("Existing manga updated");
      });
    }
  
    return (

    <Container>
        <Paper elevation={3} style={paperStyle}>
            <h1 style={{color:"blue"}}><u>Update Manga</u></h1>

        <form className={classes.root} noValidate autoComplete="off">
        
          <TextField id="outlined-basic" label="Titel" variant="outlined" fullWidth 
          value={updatedTitel}
          onChange={(e)=>setUpdatedTitel(e.target.value)}
          />
          <TextField id="outlined-basic" label="Genre" variant="outlined" fullWidth
          value={updatedGenre}
          onChange={(e)=>setUpdatedGenre(e.target.value)}
          />
          <TextField id="outlined-basic" label="Zeichner" variant="outlined" fullWidth
          value={updatedZeichner}
          onChange={(e)=>setUpdatedZeichner(e.target.value)}
          />
          <TextField id="outlined-basic" label="Autor" variant="outlined" fullWidth
          value={updatedAutor}
          onChange={(e)=>setUpdatedAutor(e.target.value)}
          />
          <TextField id="outlined-basic" label="Status" variant="outlined" fullWidth
          value={updatedStatus}
          onChange={(e)=>setUpdatedStatus(e.target.value)}
          />
          <TextField id="outlined-basic" label="Veröffentlichungsdatum" variant="outlined" fullWidth
          value={updatedVeröffentlichungsdatum}
          onChange={(e)=>setUpdatedVeröffentlichungsdatum(e.target.value)}
          />
          <Button variant="contained" color="primary" onClick={() => update(localStorage.mangaid)}>
            Submit
          </Button>
        </form>
      </Paper>
    </Container>
  );
}