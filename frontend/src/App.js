import "./App.css"
import ReactDOM from "react-dom";
import { Routes, Route } from "react-router-dom";
import Manga from "./components/Manga";
import MangaList from "./components/MangaList";
import MangaUpdate from "./components/MangaUpdate";
import Layout from "./components/Layout";
import Chapter from "./components/Chapter";
import GlobalNavigation from "./GlobalNavigation";

const App = () => {
  return (
    <div className="App">
    <GlobalNavigation/>
      <Routes>
        <Route path="/" element={<Layout />}>
        <Route index element={<MangaList />} />
        <Route path="login" element={<Login />} />
        <Route path="register" element={<Register />} />
        <Route path="manga" element={<Manga />} />
        <Route path="mangalist" element={<MangaList />} />
        <Route path="mangaupdate" element={<MangaUpdate />} />
        <Route path="chapter" element={<Chapter />} />
        </Route>
      </Routes>
    </div>
  );
}

const Layout = () => {
  return(
    <div className="App">
      <div className="content">
        <header className="App-header">
          <h1>Welcome to the Manga Library</h1>
          <hr/>
          <Outlet/>
        </header>
      </div>
    </div>);
  }

export default App;