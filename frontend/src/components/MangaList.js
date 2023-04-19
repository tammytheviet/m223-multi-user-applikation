import React, { useEffect, useState } from 'react';
import { Container, Paper, Button} from '@material-ui/core';
import {Link } from "react-router-dom";
import Chapter from "./Chapter";
import AddIcon from '@mui/icons-material/Add';
import DeleteIcon from '@mui/icons-material/Delete';
import AuthService from '../auth.service';

export default function Manga() {
    const paperStyle={padding:'50px 20px', width:600,margin:"20px auto"}
    const[manga,setManga]=useState([])
    const[chapter,setChapter]=useState([])
    const [open,setOpen]=useState(false);
    const [ currentUser, setCurrentUser ] = useState();
    const [ showAdmin, setShowAdmin ] = useState(false);

  useEffect(()=>{

  fetch("http://localhost:8080/manga")
  .then(res=>res.json())
  .then((result)=>{
    setManga(result);
  })

  const user = AuthService.getCurrentUser();
  if (user) {
      setCurrentUser(user)
      setShowAdmin(user.roles.includes("ROLE_ADMIN"))
  }
},
[])

  const remove = async (id) => {
    fetch(`http://localhost:8080/chapter?id=${id}`, {
      method: 'DELETE',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(() => { 
      let updatedChapter = [...chapter].filter(i => i.id !== id);
      setChapter(updatedChapter);
    });
    fetch(`http://localhost:8080/manga?id=${id}`, {
      method: 'DELETE',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(() => { 
      let updatedManga = [...manga].filter(i => i.id !== id);
      setManga(updatedManga);
    });
  }

  return (

    <Container>
    <h1>Mangas</h1>

    <Paper elevation={3} style={paperStyle}>
      {manga.map(manga=>(
        <Paper elevation={6} style={{margin:"10px",padding:"15px", textAlign:"left"}} key={manga.id}>
         Titel: {manga.titel}<br/>
         Genre: {manga.genre}<br/>
         Zeichner: {manga.zeichner}<br/>
         Autor: {manga.autor}<br/>
         Status: {manga.status}<br/>
         Veröffentlichungsdatum: {manga.veröffentlichungsdatum}<br/>
         Chapters: {chapter.map((chapter)=>{return chapter.inhalt}).join(', ')}<br/>
         {showAdmin && (
          <Button variant="contained" color="primary">
            <Link to="/mangaupdate">Update</Link>
          </Button>
         )}
         {showAdmin && (
         <Button variant="contained" color="secondary" onClick={() => remove(manga.id)}>
            <DeleteIcon/>
         </Button>
         )}
         {showAdmin && (
         <Button variant="contained" color="default" onClick={() => setOpen(true)}>
            <AddIcon />{open && <Chapter id={manga.id}/>}
         </Button>
         )}
        </Paper>
      ))
}
    </Paper>
    </Container>
  );
}