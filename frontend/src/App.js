import "./App.css"
import { createRoot } from 'react-dom/client';
import { Routes, Route, BrowserRouter } from "react-router-dom";
import Manga from "./components/Manga";
import MangaList from "./components/MangaList";
import MangaUpdate from "./components/MangaUpdate";
import Layout from "./components/Layout";
import Chapter from "./components/Chapter";
import Login from "./components/Login";
import Register from "./components/Register";

export default function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Layout />}>
          <Route path="login" element={<Login />} />
          <Route path="register" element={<Register />} />
          <Route path="manga" element={<Manga />} />
          <Route path="mangalist" element={<MangaList />} />
          <Route path="mangaupdate" element={<MangaUpdate />} />
          <Route path="chapter" element={<Chapter />} />
          </Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

const container = document.getElementById("root");
const root = createRoot(container);
root.render(<App />);